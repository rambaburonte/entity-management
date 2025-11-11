package com.gl.repository;

import com.gl.entity.VenueHelpdesk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueHelpdeskRepository extends JpaRepository<VenueHelpdesk, Integer> {
}