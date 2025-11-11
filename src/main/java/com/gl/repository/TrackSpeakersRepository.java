package com.gl.repository;

import com.gl.entity.TrackSpeakers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackSpeakersRepository extends JpaRepository<TrackSpeakers, Integer> {
}