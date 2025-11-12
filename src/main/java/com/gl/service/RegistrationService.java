package com.gl.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gl.dto.PricingResponse;
import com.gl.dto.RegistrationRequest;
import com.gl.dto.RegistrationResponse;
import com.gl.entity.ImportantDetails;
import com.gl.entity.Registration;
import com.gl.repository.ImportantDetailsRepository;
import com.gl.repository.RegistrationRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final ImportantDetailsRepository importantDetailsRepository;
    private final EmailService emailService;

    /**
     * Calculate pricing based on registration type, conference deadlines, and current date
     * Implements PHP pricing logic: EarlyBird -> Standard -> OnSpot (Final)
     */
    public PricingResponse calculatePricing(String conferenceId, String registrationType) {
        try {
            // Try to parse conferenceId as Integer to get ImportantDetails
            Integer confId = null;
            try {
                confId = Integer.parseInt(conferenceId);
            } catch (NumberFormatException e) {
                log.warn("Conference ID is not numeric: {}", conferenceId);
            }

            // Get ImportantDetails if confId is valid
            ImportantDetails details = null;
            if (confId != null) {
                details = importantDetailsRepository.findById(confId).orElse(null);
            }

            LocalDate today = LocalDate.now();
            String category;
            LocalDate categoryEndDate;
            Map<String, BigDecimal> prices = new HashMap<>();

            if (details != null && details.getEarlyBird() != null && details.getMidTerm() != null) {
                // Parse dates from ImportantDetails
                LocalDate earlyBirdDate = parseDate(details.getEarlyBird());
                LocalDate midTermDate = parseDate(details.getMidTerm());
                LocalDate onSpotDate = parseDate(details.getOnSpot());

                // Determine pricing category based on current date
                if (earlyBirdDate != null && !today.isAfter(earlyBirdDate)) {
                    // EarlyBird pricing
                    category = "EarlyBird";
                    categoryEndDate = earlyBirdDate;
                    prices.put("Speaker", new BigDecimal("779.00"));
                    prices.put("Delegate", new BigDecimal("899.00"));
                    prices.put("Poster", new BigDecimal("449.00"));
                    prices.put("Student", new BigDecimal("329.00"));
                } else if (midTermDate != null && !today.isAfter(midTermDate)) {
                    // Standard pricing
                    category = "Standard";
                    categoryEndDate = midTermDate;
                    prices.put("Speaker", new BigDecimal("879.00"));
                    prices.put("Delegate", new BigDecimal("999.00"));
                    prices.put("Poster", new BigDecimal("549.00"));
                    prices.put("Student", new BigDecimal("429.00"));
                } else {
                    // OnSpot/Final pricing
                    category = "Final";
                    categoryEndDate = onSpotDate != null ? onSpotDate : today.plusMonths(1);
                    prices.put("Speaker", new BigDecimal("979.00"));
                    prices.put("Delegate", new BigDecimal("1099.00"));
                    prices.put("Poster", new BigDecimal("649.00"));
                    prices.put("Student", new BigDecimal("529.00"));
                }
            } else {
                // Fallback to default Standard pricing if no details found
                log.warn("No ImportantDetails found for conference ID: {}, using default pricing", conferenceId);
                category = "Standard";
                categoryEndDate = today.plusMonths(3);
                prices.put("Speaker", new BigDecimal("879.00"));
                prices.put("Delegate", new BigDecimal("999.00"));
                prices.put("Poster", new BigDecimal("549.00"));
                prices.put("Student", new BigDecimal("429.00"));
            }

            // Add fixed sponsor pricing
            prices.put("Platinum", new BigDecimal("10000.00"));
            prices.put("Gold", new BigDecimal("7500.00"));
            prices.put("Silver", new BigDecimal("5000.00"));
            prices.put("Exhibitor", new BigDecimal("3000.00"));
            prices.put("Promotional", new BigDecimal("1000.00"));

            String conferenceName = details != null ? details.getConferenceTitle() : conferenceId;

            return PricingResponse.builder()
                    .registrationCategory(category)
                    .categoryEndDate(categoryEndDate)
                    .prices(prices)
                    .conferenceId(conferenceId)
                    .conferenceName(conferenceName)
                    .build();

        } catch (Exception e) {
            log.error("Error calculating pricing for conference: {}", conferenceId, e);
            // Return default pricing on error
            Map<String, BigDecimal> defaultPrices = new HashMap<>();
            defaultPrices.put("Speaker", new BigDecimal("879.00"));
            defaultPrices.put("Delegate", new BigDecimal("999.00"));
            defaultPrices.put("Poster", new BigDecimal("549.00"));
            defaultPrices.put("Student", new BigDecimal("429.00"));
            
            return PricingResponse.builder()
                    .registrationCategory("Standard")
                    .categoryEndDate(LocalDate.now().plusMonths(3))
                    .prices(defaultPrices)
                    .conferenceId(conferenceId)
                    .conferenceName(conferenceId)
                    .build();
        }
    }

    /**
     * Parse date string from ImportantDetails
     * Format examples: "June 30, 2025", "October 28, 2025"
     */
    private LocalDate parseDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }

        try {
            // Remove extra spaces and parse
            String cleanDate = dateStr.replaceAll(",", "").trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d yyyy");
            return LocalDate.parse(cleanDate, formatter);
        } catch (DateTimeParseException e) {
            log.error("Failed to parse date: {}", dateStr, e);
            return null;
        }
    }

    /**
     * Submit registration and return calculated amount for payment
     */
    @Transactional
    public RegistrationResponse submitRegistration(RegistrationRequest request) {
        try {
            // Calculate pricing
            PricingResponse pricing = calculatePricing(request.getConferenceId(), request.getRegistrationType());
            
            // Determine amount based on registration type
            BigDecimal amount;
            if ("Sponsor".equals(request.getRegistrationType()) && request.getSponsorPlan() != null) {
                amount = pricing.getPrices().get(request.getSponsorPlan());
            } else {
                amount = pricing.getPrices().get(request.getRegistrationType());
            }

            if (amount == null) {
                return RegistrationResponse.builder()
                        .status("error")
                        .message("Invalid registration type or sponsor plan")
                        .build();
            }

            // Create registration record
            Registration registration = new Registration();
            registration.setCategory(request.getRegistrationType());
            registration.setName(request.getName());
            registration.setEmail(request.getEmail());
            registration.setPhone(request.getPhone());
            registration.setOrg(request.getOrganization());
            registration.setCountry(request.getCountry());
            registration.setAddress(request.getAddress());
            registration.setConf(request.getConferenceId());
            registration.setTitle(request.getTitle());
            registration.setPrice(amount.doubleValue());
            registration.setStatus(0); // pending
            registration.setDate(LocalDate.now());
            
            if ("Sponsor".equals(request.getRegistrationType())) {
                // For sponsors, we might want to store sponsor plan in description or another field
                registration.setDescription("Sponsor Plan: " + request.getSponsorPlan());
            }

            Registration savedRegistration = registrationRepository.save(registration);

            log.info("Registration created successfully: ID={}, Email={}, Type={}", 
                    savedRegistration.getId(), savedRegistration.getEmail(), savedRegistration.getCategory());

            return RegistrationResponse.builder()
                    .status("success")
                    .message("Registration submitted successfully. Please proceed with payment.")
                    .registrationId(savedRegistration.getId())
                    .amount(amount)
                    .registrationCategory(pricing.getRegistrationCategory())
                    .build();

        } catch (Exception e) {
            log.error("Error submitting registration: ", e);
            return RegistrationResponse.builder()
                    .status("error")
                    .message("Failed to submit registration: " + e.getMessage())
                    .build();
        }
    }

    /**
     * Get registration by ID
     */
    public Optional<Registration> getRegistrationById(Integer id) {
        return registrationRepository.findById(id);
    }

    /**
     * Get all registrations for a conference
     */
    public Iterable<Registration> getRegistrationsByConference(String conferenceId) {
        return registrationRepository.findByConf(conferenceId);
    }

    /**
     * Get registrations by email
     */
    public Iterable<Registration> getRegistrationsByEmail(String email) {
        return registrationRepository.findByEmail(email);
    }

    /**
     * Update payment status after successful payment
     */
    @Transactional
    public void updatePaymentStatus(Integer registrationId, String paymentStatus, String paymentIntentId) {
        Registration registration = registrationRepository.findById(registrationId)
                .orElseThrow(() -> new RuntimeException("Registration not found: " + registrationId));
        
        registration.setStatus("succeeded".equals(paymentStatus) ? 1 : 0);
        registration.setTId(paymentIntentId);
        registration.setPaymentType("stripe");
        registrationRepository.save(registration);

        // Send confirmation email if payment succeeded
        if ("succeeded".equals(paymentStatus)) {
            try {
                emailService.sendRegistrationConfirmationEmail(registration);
                log.info("Registration confirmation email sent to: {}", registration.getEmail());
            } catch (Exception e) {
                log.error("Failed to send registration confirmation email", e);
            }
        }
    }
}
