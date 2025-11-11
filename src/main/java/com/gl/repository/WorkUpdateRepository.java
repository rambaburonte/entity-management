package com.gl.repository;

import com.gl.entity.WorkUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkUpdateRepository extends JpaRepository<WorkUpdate, Integer> {
}