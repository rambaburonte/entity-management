package com.gl.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gl.entity.Conference;

@Repository
public interface ConferenceRepository extends CrudRepository<Conference, Integer> {
    
    /**
     * Find conference by name
     */
    Optional<Conference> findByName(String name);
    
    /**
     * Find conference by venue
     */
    List<Conference> findByVenue(String venue);
    
    /**
     * Find conferences by name containing
     */
    List<Conference> findByNameContainingIgnoreCase(String name);
}
