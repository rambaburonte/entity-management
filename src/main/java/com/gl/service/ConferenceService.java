package com.gl.service;

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
    public Optional<Conference> getConferenceById(Integer id) {
        return conferenceRepository.findById(id);
    }

    /**
     * Get active conferences
     */
    public List<Conference> getActiveConferences() {
        return getAllConferences();
    }

    /**
     * Get upcoming conferences
     */
    public List<Conference> getUpcomingConferences() {
        return getAllConferences();
    }

    /**
     * Get conference by name
     */
    public Optional<Conference> getConferenceByName(String name) {
        return conferenceRepository.findByName(name);
    }

    /**
     * Check if conference is in early bird period
     * Note: The database schema doesn't have early_bird_date field
     */
    public boolean isEarlyBirdPeriod(String conferenceId) {
        // This functionality is not supported by current database schema
        return false;
    }

    /**
     * Check if conference is in standard period
     * Note: The database schema doesn't have standard_date field
     */
    public boolean isStandardPeriod(String conferenceId) {
        // This functionality is not supported by current database schema
        return false;
    }

    /**
     * Get conference details
     */
    public ConferenceDetailsDTO getConferenceDetails(String conferenceName) {
        Conference conference = conferenceRepository.findByName(conferenceName)
                .orElseThrow(() -> new RuntimeException("Conference not found: " + conferenceName));

        return ConferenceDetailsDTO.builder()
                .conferenceId(conference.getId())
                .conferenceName(conference.getName())
                .conferenceDate(conference.getConferenceDate())
                .venue(conference.getVenue())
                .theme(conference.getTheme())
                .website(conference.getWebsite())
                .email(conference.getEmail())
                .shortDesc(conference.getShortDesc())
                .ceremonyTime(conference.getCeremonyTime())
                .ceremonyPlace(conference.getCeremonyPlace())
                .build();
    }

    /**
     * DTO for conference details
     */
    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class ConferenceDetailsDTO {
        private Integer conferenceId;
        private String conferenceName;
        private String conferenceDate;
        private String venue;
        private String theme;
        private String website;
        private String email;
        private String shortDesc;
        private String ceremonyTime;
        private String ceremonyPlace;
    }
}
