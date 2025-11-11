package com.gl.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.entity.Conference;
import com.gl.service.ConferenceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/conferences")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Conference", description = "Conference information APIs for React frontend")
public class ConferenceController {

    private final ConferenceService conferenceService;

    /**
     * Get all conferences
     * GET /api/conferences
     */
    @Operation(summary = "Get all conferences", description = "Retrieve all conferences")
    @GetMapping
    public ResponseEntity<List<Conference>> getAllConferences() {
        return ResponseEntity.ok(conferenceService.getAllConferences());
    }

    /**
     * Get active conferences
     * GET /api/conferences/active
     */
    @Operation(summary = "Get active conferences", description = "Get conferences with future dates")
    @GetMapping("/active")
    public ResponseEntity<List<Conference>> getActiveConferences() {
        return ResponseEntity.ok(conferenceService.getActiveConferences());
    }

    /**
     * Get upcoming conferences (next 6 months)
     * GET /api/conferences/upcoming
     */
    @Operation(summary = "Get upcoming conferences", description = "Get conferences within next 6 months")
    @GetMapping("/upcoming")
    public ResponseEntity<List<Conference>> getUpcomingConferences() {
        return ResponseEntity.ok(conferenceService.getUpcomingConferences());
    }

    /**
     * Get conference by ID
     * GET /api/conferences/{id}
     */
    @Operation(summary = "Get conference by ID", description = "Get conference details by identifier")
    @GetMapping("/{id}")
    public ResponseEntity<Conference> getConferenceById(@PathVariable String id) {
        return conferenceService.getConferenceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get conference details with pricing category
     * GET /api/conferences/{id}/details
     */
    @Operation(summary = "Get conference details", description = "Get conference with current pricing category and deadlines")
    @GetMapping("/{id}/details")
    public ResponseEntity<ConferenceService.ConferenceDetailsDTO> getConferenceDetails(@PathVariable String id) {
        try {
            return ResponseEntity.ok(conferenceService.getConferenceDetails(id));
        } catch (RuntimeException e) {
            log.error("Conference not found: {}", id, e);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Check if conference is in early bird period
     * GET /api/conferences/{id}/is-early-bird
     */
    @Operation(summary = "Check early bird period", description = "Check if conference is in early bird registration period")
    @GetMapping("/{id}/is-early-bird")
    public ResponseEntity<Boolean> isEarlyBirdPeriod(@PathVariable String id) {
        return ResponseEntity.ok(conferenceService.isEarlyBirdPeriod(id));
    }

    /**
     * Check if conference is in standard period
     * GET /api/conferences/{id}/is-standard
     */
    @Operation(summary = "Check standard period", description = "Check if conference is in standard registration period")
    @GetMapping("/{id}/is-standard")
    public ResponseEntity<Boolean> isStandardPeriod(@PathVariable String id) {
        return ResponseEntity.ok(conferenceService.isStandardPeriod(id));
    }
}
