package com.gl.repository;

import com.gl.entity.PastSpeakers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PastSpeakersRepository extends JpaRepository<PastSpeakers, Integer> {
}