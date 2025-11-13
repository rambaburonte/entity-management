package com.gl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.entity.ImportantDetails;

/**
 * Repository interface for ImportantDetails entity.
 * Provides methods to access conference configuration data.
 */
@Repository
public interface ImportantDetailsRepository extends JpaRepository<ImportantDetails, Integer> {
    
    /**
     * Find conference configuration by short name
     * @param shortName Conference short name (e.g., "ICCE2026")
     * @return Optional containing ImportantDetails if found
     */
    Optional<ImportantDetails> findByShortName(String shortName);
    
    /**
     * Check if a conference exists by short name
     * @param shortName Conference short name
     * @return true if conference exists, false otherwise
     */
    boolean existsByShortName(String shortName);
}