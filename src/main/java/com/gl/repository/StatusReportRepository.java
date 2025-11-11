package com.gl.repository;

import com.gl.entity.StatusReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusReportRepository extends JpaRepository<StatusReport, Integer> {
}