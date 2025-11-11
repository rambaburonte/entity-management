package com.gl.repository;

import com.gl.entity.VenueAccommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueAccommodationRepository extends JpaRepository<VenueAccommodation, Integer> {
}