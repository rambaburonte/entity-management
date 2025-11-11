package com.gl.repository;

import com.gl.entity.Positives;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositivesRepository extends JpaRepository<Positives, Integer> {
}