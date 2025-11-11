package com.gl.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gl.entity.Conference;
import com.gl.repository.ConferenceRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConferenceService {

    private final ConferenceRepository conferenceRepository;

    /**
     * Get all conferences
     */
    public List<Conference> getAllConferences() {
        return (List<Conference>) conferenceRepository.findAll();
    }

    /**
     * Get conference by ID
     */
    public Optional<Conference> getConferenceById(String id) {
        return conferenceRepository.findById(id);
    }

    /**
     * Get active conferences (conference date is in the future)
     */
    public List<Conference> getActiveConferences() {
        LocalDate today = LocalDate.now();
        return conferenceRepository.findActiveConferences(today);
    }

    /**
     * Get upcoming conferences (within next 6 months)
     */
    public List<Conference> getUpcomingConferences() {
        LocalDate today = LocalDate.now();
        LocalDate sixMonthsLater = today.plusMonths(6);
        return conferenceRepository.findUpcomingConferences(today, sixMonthsLater);
    }

    /**
     * Get conference by name (user field)
     */
    public Optional<Conference> getConferenceByName(String confName) {
        return conferenceRepository.findByConfName(confName);
    }

    /**
     * Check if conference is in early bird period
     */
    public boolean isEarlyBirdPeriod(String conferenceId) {
        Optional<Conference> conference = conferenceRepository.findById(conferenceId);
        if (conference.isPresent() && conference.get().getEarlyBirdDate() != null) {
            return LocalDate.now().isBefore(conference.get().getEarlyBirdDate());
        }
        return false;
    }

    /**
     * Check if conference is in standard period
     */
    public boolean isStandardPeriod(String conferenceId) {
        Optional<Conference> conference = conferenceRepository.findById(conferenceId);
        if (conference.isPresent() && conference.get().getStandardDate() != null) {
            LocalDate today = LocalDate.now();
            LocalDate earlyBird = conference.get().getEarlyBirdDate();
            LocalDate standard = conference.get().getStandardDate();
            
            if (earlyBird != null) {
                return today.isAfter(earlyBird) && today.isBefore(standard);
            } else {
                return today.isBefore(standard);
            }
        }
        return false;
    }

    /**
     * Get conference details with pricing category
     */
    public ConferenceDetailsDTO getConferenceDetails(String conferenceId) {
        Conference conference = conferenceRepository.findById(conferenceId)
                .orElseThrow(() -> new RuntimeException("Conference not found: " + conferenceId));

        LocalDate today = LocalDate.now();
        String pricingCategory;
        LocalDate deadlineDate;

        if (conference.getEarlyBirdDate() != null && today.isBefore(conference.getEarlyBirdDate())) {
            pricingCategory = "EarlyBird";
            deadlineDate = conference.getEarlyBirdDate();
        } else if (conference.getStandardDate() != null && today.isBefore(conference.getStandardDate())) {
            pricingCategory = "Standard";
            deadlineDate = conference.getStandardDate();
        } else {
            pricingCategory = "Final";
            deadlineDate = conference.getConferenceDate();
        }

        return ConferenceDetailsDTO.builder()
                .conferenceId(conference.getUser())
                .conferenceName(conference.getConfName())
                .conferenceDate(conference.getConferenceDate())
                .location(conference.getLocation())
                .country(conference.getCountry())
                .earlyBirdDeadline(conference.getEarlyBirdDate())
                .standardDeadline(conference.getStandardDate())
                .abstractDeadline(conference.getAbstractDate())
                .currentPricingCategory(pricingCategory)
                .nextDeadline(deadlineDate)
                .isActive(conference.getConferenceDate() != null && 
                         conference.getConferenceDate().isAfter(today))
                .build();
    }

    /**
     * DTO for conference details with pricing info
     */
    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class ConferenceDetailsDTO {
        private String conferenceId;
        private String conferenceName;
        private LocalDate conferenceDate;
        private String location;
        private String country;
        private LocalDate earlyBirdDeadline;
        private LocalDate standardDeadline;
        private LocalDate abstractDeadline;
        private String currentPricingCategory;
        private LocalDate nextDeadline;
        private Boolean isActive;
    }
}
