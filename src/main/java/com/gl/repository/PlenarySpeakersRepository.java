package com.gl.repository;

import com.gl.entity.PlenarySpeakers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlenarySpeakersRepository extends JpaRepository<PlenarySpeakers, Integer> {
}