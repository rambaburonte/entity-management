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

    public List<Sponsors> getSponsorsByUser(String user) {
        return sponsorsRepository.findByUser(user);
    }
}
