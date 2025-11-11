package com.gl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.entity.Tracks;

@Repository
public interface TracksRepository extends JpaRepository<Tracks, Integer> {
    List<Tracks> findByUser(Integer user);
    List<Tracks> findByUserAndTrackDate(Integer user, java.time.LocalDate trackDate);
}