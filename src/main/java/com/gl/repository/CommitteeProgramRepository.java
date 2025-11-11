package com.gl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.entity.CommitteeProgram;

@Repository
public interface CommitteeProgramRepository extends JpaRepository<CommitteeProgram, Integer> {
}