package com.gl.repository;

import com.gl.entity.RenewedSpeakers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RenewedSpeakersRepository extends JpaRepository<RenewedSpeakers, Integer> {
}