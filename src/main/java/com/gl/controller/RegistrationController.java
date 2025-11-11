package com.gl.controller;

import com.gl.dto.PricingResponse;
import com.gl.dto.RegistrationRequest;
import com.gl.dto.RegistrationResponse;
import com.gl.entity.Registration;
import com.gl.service.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registration")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Registration", description = "Registration management APIs for React frontend")
public class RegistrationController {

    private final RegistrationService registrationService;

    /**
     * Calculate pricing for registration based on conference and type
     * GET /api/registration/pricing?conferenceId=xxx&type=Speaker
     */
    @Operation(summary = "Calculate pricing", description = "Get pricing information based on conference deadlines and registration type")
    @GetMapping("/pricing")
    public ResponseEntity<PricingResponse> calculatePricing(
            @RequestParam String conferenceId,
            @RequestParam String type) {
        try {
            PricingResponse pricing = registrationService.calculatePricing(conferenceId, type);
            return ResponseEntity.ok(pricing);
        } catch (Exception e) {
            log.error("Error calculating pricing", e);
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Submit registration
     * POST /api/registration
     */
    @Operation(summary = "Submit registration", description = "Submit a new registration and get payment details")
    @PostMapping
    public ResponseEntity<RegistrationResponse> submitRegistration(
            @Valid @RequestBody RegistrationRequest request) {
        log.info("Received registration request: {} for conference: {}", 
                request.getEmail(), request.getConferenceId());
        
        RegistrationResponse response = registrationService.submitRegistration(request);
        
        if ("success".equals(response.getStatus())) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Get registration by ID
     * GET /api/registration/{id}
     */
    @Operation(summary = "Get registration", description = "Get registration details by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Registration> getRegistration(@PathVariable Integer id) {
        return registrationService.getRegistrationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get registrations by conference ID
     * GET /api/registration/conference/{conferenceId}
     */
    @Operation(summary = "Get registrations by conference", description = "Get all registrations for a specific conference")
    @GetMapping("/conference/{conferenceId}")
    public ResponseEntity<Iterable<Registration>> getRegistrationsByConference(
            @PathVariable String conferenceId) {
        return ResponseEntity.ok(registrationService.getRegistrationsByConference(conferenceId));
    }

    /**
     * Get registrations by email
     * GET /api/registration/email/{email}
     */
    @Operation(summary = "Get registrations by email", description = "Get all registrations for a specific email address")
    @GetMapping("/email/{email}")
    public ResponseEntity<Iterable<Registration>> getRegistrationsByEmail(
            @PathVariable String email) {
        return ResponseEntity.ok(registrationService.getRegistrationsByEmail(email));
    }
}
