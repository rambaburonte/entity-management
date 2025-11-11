package com.gl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.entity.Conferences;

@Repository
public interface ConferencesRepository extends JpaRepository<Conferences, Integer> {
}