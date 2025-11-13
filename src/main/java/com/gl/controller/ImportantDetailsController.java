package com.gl.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.entity.ImportantDetails;
import com.gl.service.ImportantDetailsService;

/**
 * REST Controller for managing conference configuration and important details.
 * Provides dynamic configuration for the React frontend.
 */
@RestController
@RequestMapping("/api/conference-config")
@CrossOrigin(origins = "*")
public class ImportantDetailsController {

    @Autowired
    private ImportantDetailsService importantDetailsService;

    /**
     * Get all conference configurations
     * GET /api/conference-config
     */
    @GetMapping
    public ResponseEntity<List<ImportantDetails>> getAllConfigs() {
        List<ImportantDetails> configs = importantDetailsService.getAllConfigs();
        return ResponseEntity.ok(configs);
    }

    /**
     * Get conference configuration by short name
     * GET /api/conference-config/{shortName}
     * Example: /api/conference-config/ICCE2026
     */
    @GetMapping("/{shortName}")
    public ResponseEntity<ImportantDetails> getConfigByShortName(@PathVariable String shortName) {
        ImportantDetails config = importantDetailsService.getConfigByShortName(shortName);
        if (config != null) {
            return ResponseEntity.ok(config);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Get complete site configuration (formatted for React frontend)
     * GET /api/conference-config/{shortName}/site-config
     * Returns structured JSON with all site configuration including:
     * - Conference details (name, title, theme, venue, dates)
     * - Deadlines (early bird, mid-term, late, on-spot)
     * - Social media links (facebook, linkedin, twitter, instagram)
     * - Contact information (emails, phone)
     * - Logo and branding
     */
    @GetMapping("/{shortName}/site-config")
    public ResponseEntity<Map<String, Object>> getSiteConfig(@PathVariable String shortName) {
        Map<String, Object> siteConfig = importantDetailsService.getSiteConfig(shortName);
        if (siteConfig != null && !siteConfig.isEmpty()) {
            return ResponseEntity.ok(siteConfig);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Get pricing deadlines for a conference
     * GET /api/conference-config/{shortName}/deadlines
     */
    @GetMapping("/{shortName}/deadlines")
    public ResponseEntity<Map<String, Object>> getDeadlines(@PathVariable String shortName) {
        Map<String, Object> deadlines = importantDetailsService.getDeadlines(shortName);
        if (deadlines != null && !deadlines.isEmpty()) {
            return ResponseEntity.ok(deadlines);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Get social media links for a conference
     * GET /api/conference-config/{shortName}/social-links
     */
    @GetMapping("/{shortName}/social-links")
    public ResponseEntity<Map<String, String>> getSocialLinks(@PathVariable String shortName) {
        Map<String, String> socialLinks = importantDetailsService.getSocialLinks(shortName);
        if (socialLinks != null && !socialLinks.isEmpty()) {
            return ResponseEntity.ok(socialLinks);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Get conference contact information
     * GET /api/conference-config/{shortName}/contact
     */
    @GetMapping("/{shortName}/contact")
    public ResponseEntity<Map<String, String>> getContactInfo(@PathVariable String shortName) {
        Map<String, String> contactInfo = importantDetailsService.getContactInfo(shortName);
        if (contactInfo != null && !contactInfo.isEmpty()) {
            return ResponseEntity.ok(contactInfo);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Check if registration is open
     * GET /api/conference-config/{shortName}/registration-status
     */
    @GetMapping("/{shortName}/registration-status")
    public ResponseEntity<Map<String, Object>> getRegistrationStatus(@PathVariable String shortName) {
        Map<String, Object> status = importantDetailsService.getRegistrationStatus(shortName);
        return ResponseEntity.ok(status);
    }

    /**
     * Check if abstract submission is open
     * GET /api/conference-config/{shortName}/abstract-status
     */
    @GetMapping("/{shortName}/abstract-status")
    public ResponseEntity<Map<String, Object>> getAbstractSubmissionStatus(@PathVariable String shortName) {
        Map<String, Object> status = importantDetailsService.getAbstractSubmissionStatus(shortName);
        return ResponseEntity.ok(status);
    }

    /**
     * Get current pricing tier (early bird, standard, late)
     * GET /api/conference-config/{shortName}/pricing-tier
     */
    @GetMapping("/{shortName}/pricing-tier")
    public ResponseEntity<Map<String, String>> getCurrentPricingTier(@PathVariable String shortName) {
        Map<String, String> tier = importantDetailsService.getCurrentPricingTier(shortName);
        return ResponseEntity.ok(tier);
    }

    /**
     * Get conference logo URL
     * GET /api/conference-config/{shortName}/logo
     */
    @GetMapping("/{shortName}/logo")
    public ResponseEntity<Map<String, String>> getLogoUrl(@PathVariable String shortName) {
        Map<String, String> logo = importantDetailsService.getLogoUrl(shortName);
        if (logo != null && !logo.isEmpty()) {
            return ResponseEntity.ok(logo);
        }
        return ResponseEntity.notFound().build();
    }
}
