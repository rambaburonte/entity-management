package com.gl.repository;

import com.gl.entity.SponsorsMobileapp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SponsorsMobileappRepository extends JpaRepository<SponsorsMobileapp, Integer> {
}