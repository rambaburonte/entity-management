package com.gl.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.entity.Sponsors;
import com.gl.service.SponsorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sponsors")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class SponsorController {

    private final SponsorService sponsorService;

    @GetMapping
    public ResponseEntity<List<Sponsors>> getAllSponsors() {
        List<Sponsors> sponsors = sponsorService.getAllSponsors();
        return ResponseEntity.ok(sponsors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sponsors> getSponsorById(@PathVariable Integer id) {
        Sponsors sponsor = sponsorService.getSponsorById(id);
        if (sponsor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sponsor);
    }

    @GetMapping("/conference/{conferenceId}")
    public ResponseEntity<List<Sponsors>> getSponsorsByConference(@PathVariable Integer conferenceId) {
        List<Sponsors> sponsors = sponsorService.getSponsorsByConference(conferenceId);
        return ResponseEntity.ok(sponsors);
    }

    @GetMapping("/type/{type}/conference/{conferenceId}")
    public ResponseEntity<List<Sponsors>> getSponsorsByType(
            @PathVariable String type,
            @PathVariable Integer conferenceId) {
        List<Sponsors> sponsors = sponsorService.getSponsorsByType(type, conferenceId);
        return ResponseEntity.ok(sponsors);
    }
}
