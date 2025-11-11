package com.gl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gl.entity.Sponsors;
import com.gl.repository.SponsorsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SponsorService {

    private final SponsorsRepository sponsorsRepository;

    public List<Sponsors> getAllSponsors() {
        return sponsorsRepository.findAll();
    }

    public Sponsors getSponsorById(Integer id) {
        return sponsorsRepository.findById(id).orElse(null);
    }

    public List<Sponsors> getSponsorsByConference(Integer conferenceId) {
        return sponsorsRepository.findByConfId(conferenceId);
    }

    public List<Sponsors> getSponsorsByType(String type, Integer conferenceId) {
        return sponsorsRepository.findBySponsorTypeAndConfId(type, conferenceId);
    }
}
