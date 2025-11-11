package com.gl.repository;

import com.gl.entity.Sponsors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SponsorsRepository extends JpaRepository<Sponsors, Integer> {
    List<Sponsors> findByConfId(Integer confId);
    List<Sponsors> findBySponsorTypeAndConfId(String sponsorType, Integer confId);
}