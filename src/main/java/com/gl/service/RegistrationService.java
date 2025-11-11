package com.gl.service;

import com.gl.dto.PricingResponse;
import com.gl.dto.RegistrationRequest;
import com.gl.dto.RegistrationResponse;
import com.gl.entity.Conference;
import com.gl.entity.Registration;
import com.gl.repository.ConferenceRepository;
import com.gl.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final ConferenceRepository conferenceRepository;
    private final EmailService emailService;
    private final DiscountService discountService;

    /**
     * Calculate pricing based on registration type, conference deadlines, and current date
     */
    public PricingResponse calculatePricing(String conferenceId, String registrationType) {
        Conference conference = conferenceRepository.findById(conferenceId)
                .orElseThrow(() -> new RuntimeException("Conference not found: " + conferenceId));

        LocalDate today = LocalDate.now();
        
        // Determine pricing category based on deadlines
        String category;
        LocalDate categoryEndDate;
        
        if (conference.getEarlyBirdDate() != null && today.isBefore(conference.getEarlyBirdDate())) {
            category = "EarlyBird";
            categoryEndDate = conference.getEarlyBirdDate().minusDays(1);
        } else if (conference.getStandardDate() != null && today.isBefore(conference.getStandardDate())) {
            category = "Standard";
            categoryEndDate = conference.getStandardDate().minusDays(1);
        } else {
            category = "Final";
            categoryEndDate = conference.getConferenceDate(); // Until conference starts
        }

        // Build pricing map based on category and registration type
        Map<String, BigDecimal> prices = new HashMap<>();
        
        switch (category) {
            case "EarlyBird":
                prices.put("Speaker", new BigDecimal("779.00"));
                prices.put("Delegate", new BigDecimal("899.00"));
                prices.put("Poster", new BigDecimal("449.00"));
                prices.put("Student", new BigDecimal("329.00"));
                // Sponsor pricing is fixed
                prices.put("Platinum", new BigDecimal("10000.00"));
                prices.put("Gold", new BigDecimal("7500.00"));
                prices.put("Silver", new BigDecimal("5000.00"));
                prices.put("Exhibitor", new BigDecimal("3000.00"));
                prices.put("Promotional", new BigDecimal("1000.00"));
                break;
                
            case "Standard":
                prices.put("Speaker", new BigDecimal("879.00"));
                prices.put("Delegate", new BigDecimal("999.00"));
                prices.put("Poster", new BigDecimal("549.00"));
                prices.put("Student", new BigDecimal("429.00"));
                // Sponsor pricing is fixed
                prices.put("Platinum", new BigDecimal("10000.00"));
                prices.put("Gold", new BigDecimal("7500.00"));
                prices.put("Silver", new BigDecimal("5000.00"));
                prices.put("Exhibitor", new BigDecimal("3000.00"));
                prices.put("Promotional", new BigDecimal("1000.00"));
                break;
                
            case "Final":
                prices.put("Speaker", new BigDecimal("979.00"));
                prices.put("Delegate", new BigDecimal("1099.00"));
                prices.put("Poster", new BigDecimal("649.00"));
                prices.put("Student", new BigDecimal("529.00"));
                // Sponsor pricing is fixed
                prices.put("Platinum", new BigDecimal("10000.00"));
                prices.put("Gold", new BigDecimal("7500.00"));
                prices.put("Silver", new BigDecimal("5000.00"));
                prices.put("Exhibitor", new BigDecimal("3000.00"));
                prices.put("Promotional", new BigDecimal("1000.00"));
                break;
        }

        return PricingResponse.builder()
                .registrationCategory(category)
                .categoryEndDate(categoryEndDate)
                .prices(prices)
                .conferenceId(conferenceId)
                .conferenceName(conference.getConfName())
                .build();
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
