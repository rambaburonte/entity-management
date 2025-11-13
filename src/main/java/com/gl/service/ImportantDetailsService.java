package com.gl.service;

import com.gl.entity.ImportantDetails;
import com.gl.repository.ImportantDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * Service for managing conference configuration from important_details table.
 * Provides methods to fetch and format conference data for the React frontend.
 */
@Service
public class ImportantDetailsService {

    @Autowired
    private ImportantDetailsRepository importantDetailsRepository;

    private static final DateTimeFormatter[] DATE_FORMATTERS = {
        DateTimeFormatter.ofPattern("yyyy-MM-dd"),
        DateTimeFormatter.ofPattern("dd-MM-yyyy"),
        DateTimeFormatter.ofPattern("MM/dd/yyyy"),
        DateTimeFormatter.ofPattern("yyyy/MM/dd")
    };

    /**
     * Get all conference configurations
     */
    public List<ImportantDetails> getAllConfigs() {
        return importantDetailsRepository.findAll();
    }

    /**
     * Get conference configuration by short name
     */
    public ImportantDetails getConfigByShortName(String shortName) {
        return importantDetailsRepository.findByShortName(shortName.toUpperCase())
                .orElse(null);
    }

    /**
     * Get complete site configuration formatted for React frontend
     */
    public Map<String, Object> getSiteConfig(String shortName) {
        ImportantDetails config = getConfigByShortName(shortName);
        if (config == null) {
            return Collections.emptyMap();
        }

        Map<String, Object> siteConfig = new LinkedHashMap<>();
        
        // Basic conference info
        siteConfig.put("shortName", config.getShortName());
        siteConfig.put("name", config.getShortName());
        siteConfig.put("fullName", config.getConferenceTitle());
        siteConfig.put("title", config.getConferenceTitle());
        siteConfig.put("theme", config.getTheme());
        siteConfig.put("tagline", config.getTheme());
        siteConfig.put("confUrl", config.getConfUrl());
        
        // Dates
        Map<String, String> dates = new LinkedHashMap<>();
        dates.put("conferenceDates", config.getConferenceDates());
        dates.put("abstractSubmissionDeadline", config.getAbstractSubmissionDeadline());
        dates.put("abstractSubmissionOpens", config.getAbstractSubmissionOpens());
        dates.put("registrationOpens", config.getRegistrationOpens());
        dates.put("earlyBirdDeadline", config.getEarlyBird());
        dates.put("midTermDeadline", config.getMidTerm());
        dates.put("lateRegistrationDeadline", config.getLateRegistration());
        dates.put("onSpotDate", config.getOnSpot());
        siteConfig.put("dates", dates);
        
        // Venue
        Map<String, String> venue = new LinkedHashMap<>();
        venue.put("name", config.getConferenceVenue());
        venue.put("address", config.getConferenceVenue());
        venue.put("location", config.getConferenceVenue());
        siteConfig.put("venue", venue);
        
        // Contact
        Map<String, String> contact = new LinkedHashMap<>();
        contact.put("email", config.getEmailId1());
        contact.put("email1", config.getEmailId1());
        contact.put("email2", config.getEmailId2());
        contact.put("email3", config.getEmailId3());
        siteConfig.put("contact", contact);
        
        // Social media links
        Map<String, String> social = new LinkedHashMap<>();
        social.put("facebook", config.getFacebookLink());
        social.put("linkedin", config.getLinkedinLink());
        social.put("twitter", config.getTwitterLink());
        social.put("instagram", config.getInstagramLink());
        siteConfig.put("social", social);
        
        // Branding
        Map<String, String> branding = new LinkedHashMap<>();
        branding.put("logo", config.getEntityLogo());
        branding.put("pcName", config.getPcName());
        siteConfig.put("branding", branding);
        
        // Pricing tier info
        siteConfig.put("pricingTier", getCurrentPricingTier(shortName));
        
        // Status flags
        siteConfig.put("registrationOpen", isRegistrationOpen(config));
        siteConfig.put("abstractSubmissionOpen", isAbstractSubmissionOpen(config));
        
        return siteConfig;
    }

    /**
     * Get pricing deadlines
     */
    public Map<String, Object> getDeadlines(String shortName) {
        ImportantDetails config = getConfigByShortName(shortName);
        if (config == null) {
            return Collections.emptyMap();
        }

        Map<String, Object> deadlines = new LinkedHashMap<>();
        deadlines.put("abstractSubmissionDeadline", config.getAbstractSubmissionDeadline());
        deadlines.put("abstractSubmissionOpens", config.getAbstractSubmissionOpens());
        deadlines.put("registrationOpens", config.getRegistrationOpens());
        deadlines.put("earlyBird", config.getEarlyBird());
        deadlines.put("midTerm", config.getMidTerm());
        deadlines.put("lateRegistration", config.getLateRegistration());
        deadlines.put("onSpot", config.getOnSpot());
        
        return deadlines;
    }

    /**
     * Get social media links
     */
    public Map<String, String> getSocialLinks(String shortName) {
        ImportantDetails config = getConfigByShortName(shortName);
        if (config == null) {
            return Collections.emptyMap();
        }

        Map<String, String> socialLinks = new LinkedHashMap<>();
        socialLinks.put("facebook", config.getFacebookLink());
        socialLinks.put("linkedin", config.getLinkedinLink());
        socialLinks.put("twitter", config.getTwitterLink());
        socialLinks.put("instagram", config.getInstagramLink());
        socialLinks.put("twitterTweets", config.getTwitterTweets());
        
        return socialLinks;
    }

    /**
     * Get contact information
     */
    public Map<String, String> getContactInfo(String shortName) {
        ImportantDetails config = getConfigByShortName(shortName);
        if (config == null) {
            return Collections.emptyMap();
        }

        Map<String, String> contactInfo = new LinkedHashMap<>();
        contactInfo.put("email1", config.getEmailId1());
        contactInfo.put("email2", config.getEmailId2());
        contactInfo.put("email3", config.getEmailId3());
        contactInfo.put("pcName", config.getPcName());
        
        return contactInfo;
    }

    /**
     * Check if registration is open
     */
    public Map<String, Object> getRegistrationStatus(String shortName) {
        ImportantDetails config = getConfigByShortName(shortName);
        Map<String, Object> status = new LinkedHashMap<>();
        
        if (config == null) {
            status.put("isOpen", false);
            status.put("message", "Conference not found");
            return status;
        }

        boolean isOpen = isRegistrationOpen(config);
        status.put("isOpen", isOpen);
        status.put("registrationOpens", config.getRegistrationOpens());
        status.put("onSpotDate", config.getOnSpot());
        status.put("message", isOpen ? "Registration is currently open" : "Registration is closed");
        
        return status;
    }

    /**
     * Check if abstract submission is open
     */
    public Map<String, Object> getAbstractSubmissionStatus(String shortName) {
        ImportantDetails config = getConfigByShortName(shortName);
        Map<String, Object> status = new LinkedHashMap<>();
        
        if (config == null) {
            status.put("isOpen", false);
            status.put("message", "Conference not found");
            return status;
        }

        boolean isOpen = isAbstractSubmissionOpen(config);
        status.put("isOpen", isOpen);
        status.put("opensDate", config.getAbstractSubmissionOpens());
        status.put("deadline", config.getAbstractSubmissionDeadline());
        status.put("message", isOpen ? "Abstract submission is currently open" : "Abstract submission is closed");
        
        return status;
    }

    /**
     * Get current pricing tier
     */
    public Map<String, String> getCurrentPricingTier(String shortName) {
        ImportantDetails config = getConfigByShortName(shortName);
        Map<String, String> tier = new LinkedHashMap<>();
        
        if (config == null) {
            tier.put("tier", "standard");
            tier.put("label", "Standard");
            tier.put("color", "yellow");
            return tier;
        }

        LocalDate today = LocalDate.now();
        LocalDate earlyBirdDate = parseDate(config.getEarlyBird());
        LocalDate midTermDate = parseDate(config.getMidTerm());
        LocalDate lateDate = parseDate(config.getLateRegistration());

        String currentTier = "standard";
        String label = "Standard";
        String color = "yellow";

        if (earlyBirdDate != null && today.isBefore(earlyBirdDate) || today.isEqual(earlyBirdDate)) {
            currentTier = "earlyBird";
            label = "Early Bird";
            color = "green";
        } else if (midTermDate != null && (today.isBefore(midTermDate) || today.isEqual(midTermDate))) {
            currentTier = "midTerm";
            label = "Mid Term";
            color = "yellow";
        } else if (lateDate != null && (today.isBefore(lateDate) || today.isEqual(lateDate))) {
            currentTier = "late";
            label = "Late Registration";
            color = "orange";
        } else {
            currentTier = "onSpot";
            label = "On Spot";
            color = "red";
        }

        tier.put("tier", currentTier);
        tier.put("label", label);
        tier.put("color", color);
        tier.put("earlyBirdDeadline", config.getEarlyBird());
        tier.put("midTermDeadline", config.getMidTerm());
        tier.put("lateDeadline", config.getLateRegistration());
        tier.put("onSpotDate", config.getOnSpot());
        
        return tier;
    }

    /**
     * Get logo URL
     */
    public Map<String, String> getLogoUrl(String shortName) {
        ImportantDetails config = getConfigByShortName(shortName);
        Map<String, String> logo = new LinkedHashMap<>();
        
        if (config != null && config.getEntityLogo() != null) {
            logo.put("logo", config.getEntityLogo());
            logo.put("url", config.getEntityLogo());
        }
        
        return logo;
    }

    // Helper methods

    private boolean isRegistrationOpen(ImportantDetails config) {
        if (config.getRegistrationOpens() == null) {
            return true; // Default to open if no date specified
        }

        LocalDate today = LocalDate.now();
        LocalDate opensDate = parseDate(config.getRegistrationOpens());
        LocalDate onSpotDate = parseDate(config.getOnSpot());

        if (opensDate != null && today.isBefore(opensDate)) {
            return false; // Not yet opened
        }

        if (onSpotDate != null && today.isAfter(onSpotDate)) {
            return false; // Already closed
        }

        return true;
    }

    private boolean isAbstractSubmissionOpen(ImportantDetails config) {
        if (config.getAbstractSubmissionDeadline() == null) {
            return true; // Default to open if no date specified
        }

        LocalDate today = LocalDate.now();
        LocalDate opensDate = parseDate(config.getAbstractSubmissionOpens());
        LocalDate deadline = parseDate(config.getAbstractSubmissionDeadline());

        if (opensDate != null && today.isBefore(opensDate)) {
            return false; // Not yet opened
        }

        if (deadline != null && today.isAfter(deadline)) {
            return false; // Already closed
        }

        return true;
    }

    private LocalDate parseDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }

        for (DateTimeFormatter formatter : DATE_FORMATTERS) {
            try {
                return LocalDate.parse(dateStr, formatter);
            } catch (DateTimeParseException e) {
                // Try next formatter
            }
        }

        return null; // Could not parse
    }
}
