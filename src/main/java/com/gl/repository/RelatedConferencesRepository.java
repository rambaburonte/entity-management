package com.gl.repository;

import com.gl.entity.RelatedConferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelatedConferencesRepository extends JpaRepository<RelatedConferences, Integer> {
}