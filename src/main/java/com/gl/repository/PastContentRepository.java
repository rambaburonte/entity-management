package com.gl.repository;

import com.gl.entity.PastContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PastContentRepository extends JpaRepository<PastContent, Integer> {
}