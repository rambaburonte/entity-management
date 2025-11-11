package com.gl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.entity.Brochure;

@Repository
public interface BrochureRepository extends JpaRepository<Brochure, Integer> {
    List<Brochure> findByUser(Integer user);
    List<Brochure> findByEmail(String email);
    Long countByUser(Integer user);
}
