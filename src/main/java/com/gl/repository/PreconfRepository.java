package com.gl.repository;

import com.gl.entity.Preconf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreconfRepository extends JpaRepository<Preconf, Integer> {
}