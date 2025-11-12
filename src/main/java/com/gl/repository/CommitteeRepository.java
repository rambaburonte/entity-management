package com.gl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.entity.Committee;
import java.util.List;

@Repository
public interface CommitteeRepository extends JpaRepository<Committee, Integer> {
    List<Committee> findByUser(Integer user);
}