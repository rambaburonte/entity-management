package com.gl.repository;

import com.gl.entity.PendingWorks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PendingWorksRepository extends JpaRepository<PendingWorks, Integer> {
}