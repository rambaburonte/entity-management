package com.gl.repository;

import com.gl.entity.Yrf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YrfRepository extends JpaRepository<Yrf, Integer> {
}