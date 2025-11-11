package com.gl.repository;

import com.gl.entity.VenueHospitality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueHospitalityRepository extends JpaRepository<VenueHospitality, Integer> {
}