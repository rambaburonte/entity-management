package com.gl.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gl.dto.PricingResponse;
import com.gl.dto.RegistrationRequest;
import com.gl.dto.RegistrationResponse;
import com.gl.entity.Registration;
import com.gl.repository.RegistrationRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final EmailService emailService;

    /**
     * Calculate pricing based on registration type, conference deadlines, and current date
     * Note: Current database schema doesn't have deadline fields, using fixed pricing
     */
    public PricingResponse calculatePricing(String conferenceId, String registrationType) {
        // Since the database doesn't have early_bird_date, standard_date fields,
        // we'll use fixed pricing structure
        String category = "Standard"; // Default category
        LocalDate categoryEndDate = LocalDate.now().plusMonths(3);

        // Build pricing map based on category and registration type
        Map<String, BigDecimal> prices = new HashMap<>();
        
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

        return PricingResponse.builder()
                .registrationCategory(category)
                .categoryEndDate(categoryEndDate)
                .prices(prices)
                .conferenceId(conferenceId)
                .conferenceName(conferenceId) // Use ID as name for now
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
