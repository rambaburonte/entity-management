package com.gl.repository;

import com.gl.entity.TimeManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeManagementRepository extends JpaRepository<TimeManagement, Integer> {
}