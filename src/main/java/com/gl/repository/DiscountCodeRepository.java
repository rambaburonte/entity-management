package com.gl.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gl.entity.DiscountCode;

@Repository
public interface DiscountCodeRepository extends CrudRepository<DiscountCode, Integer> {
    
    /**
     * Find discount code by code value
     */
    Optional<DiscountCode> findByCode(String code);
    
    /**
     * Find active discount codes for a conference
     */
    @Query("SELECT d FROM DiscountCode d WHERE d.conferenceId = :conferenceId " +
           "AND d.isActive = true " +
           "AND (d.validFrom IS NULL OR d.validFrom <= :today) " +
           "AND (d.validUntil IS NULL OR d.validUntil >= :today)")
    List<DiscountCode> findActiveDiscountsByConference(
            @Param("conferenceId") String conferenceId,
            @Param("today") LocalDate today);
    
    /**
     * Find valid discount code for specific registration type
     */
    @Query("SELECT d FROM DiscountCode d WHERE d.code = :code " +
           "AND d.conferenceId = :conferenceId " +
           "AND d.isActive = true " +
           "AND (d.validFrom IS NULL OR d.validFrom <= :today) " +
           "AND (d.validUntil IS NULL OR d.validUntil >= :today) " +
           "AND (d.registrationType IS NULL OR d.registrationType = :registrationType) " +
           "AND (d.maxUses IS NULL OR d.currentUses < d.maxUses)")
    Optional<DiscountCode> findValidDiscountCode(
            @Param("code") String code,
            @Param("conferenceId") String conferenceId,
            @Param("registrationType") String registrationType,
            @Param("today") LocalDate today);
}
