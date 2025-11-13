package com.gl.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.entity.Sponsors;
import com.gl.service.SponsorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/sponsors")
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

    @GetMapping("/user/{user}")
    public ResponseEntity<List<Sponsors>> getSponsorsByUser(@PathVariable String user) {
        List<Sponsors> sponsors = sponsorService.getSponsorsByUser(user);
        return ResponseEntity.ok(sponsors);
    }
}
