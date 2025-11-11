package com.gl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.entity.VenueAccommodation;
import com.gl.entity.VenueHelpdesk;
import com.gl.entity.VenueHospitality;
import com.gl.repository.VenueAccommodationRepository;
import com.gl.repository.VenueHelpdeskRepository;
import com.gl.repository.VenueHospitalityRepository;

@Service
public class VenueService {

    @Autowired
    private VenueAccommodationRepository venueAccommodationRepository;

    @Autowired
    private VenueHelpdeskRepository venueHelpdeskRepository;

    @Autowired
    private VenueHospitalityRepository venueHospitalityRepository;

    public VenueAccommodation getVenueAccommodation() {
        return venueAccommodationRepository.findAll().stream().findFirst().orElse(null);
    }

    public VenueHelpdesk getVenueHelpdesk() {
        return venueHelpdeskRepository.findAll().stream().findFirst().orElse(null);
    }

    public VenueHospitality getVenueHospitality() {
        return venueHospitalityRepository.findAll().stream().findFirst().orElse(null);
    }
}