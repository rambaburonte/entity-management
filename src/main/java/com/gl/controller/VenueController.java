package com.gl.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.entity.VenueAccommodation;
import com.gl.entity.VenueHelpdesk;
import com.gl.entity.VenueHospitality;
import com.gl.service.VenueService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/venue")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class VenueController {

    private final VenueService venueService;

    @GetMapping("/accommodation")
    public ResponseEntity<VenueAccommodation> getVenueAccommodation() {
        VenueAccommodation accommodation = venueService.getVenueAccommodation();
        if (accommodation == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(accommodation);
    }

    @GetMapping("/helpdesk")
    public ResponseEntity<VenueHelpdesk> getVenueHelpdesk() {
        VenueHelpdesk helpdesk = venueService.getVenueHelpdesk();
        if (helpdesk == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(helpdesk);
    }

    @GetMapping("/hospitality")
    public ResponseEntity<VenueHospitality> getVenueHospitality() {
        VenueHospitality hospitality = venueService.getVenueHospitality();
        if (hospitality == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(hospitality);
    }
}