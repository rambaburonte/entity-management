package com.gl.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gl.entity.Conference;

@Repository
public interface ConferenceRepository extends CrudRepository<Conference, String> {
    
    /**
     * Find conference by name
     */
    Optional<Conference> findByConfName(String confName);
    
    /**
     * Find all active conferences (conference date is in the future)
     */
    @Query("SELECT c FROM Conference c WHERE c.conferenceDate > :today ORDER BY c.conferenceDate ASC")
    List<Conference> findActiveConferences(@Param("today") LocalDate today);
    
    /**
     * Find upcoming conferences within a date range
     */
    @Query("SELECT c FROM Conference c WHERE c.conferenceDate BETWEEN :startDate AND :endDate ORDER BY c.conferenceDate ASC")
    List<Conference> findUpcomingConferences(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    
    /**
     * Find conferences by country
     */
    List<Conference> findByCountry(String country);
    
    /**
     * Find conferences by location
     */
    List<Conference> findByLocation(String location);
}
