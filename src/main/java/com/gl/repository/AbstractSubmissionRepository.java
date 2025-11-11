package com.gl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.entity.AbstractSubmission;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AbstractSubmissionRepository extends JpaRepository<AbstractSubmission, Integer> {
    
    List<AbstractSubmission> findByUser(String user);
    
    List<AbstractSubmission> findByEmail(String email);
    
    List<AbstractSubmission> findByCategory(String category);
    
    List<AbstractSubmission> findByTrackName(String trackName);
    
    List<AbstractSubmission> findByDate(LocalDate date);
    
    List<AbstractSubmission> findByUserAndCategory(String user, String category);
    
    List<AbstractSubmission> findByEntity(String entity);
    
    Long countByUser(String user);
    
    Long countByDate(LocalDate date);
}