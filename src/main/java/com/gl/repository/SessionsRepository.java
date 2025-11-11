package com.gl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.entity.Sessions;

@Repository
public interface SessionsRepository extends JpaRepository<Sessions, Integer> {
    List<Sessions> findByConferenceId(Integer conferenceId);
    List<Sessions> findByConferenceIdAndDay(Integer conferenceId, Integer day);
    List<Sessions> findByConferenceIdAndDate(Integer conferenceId, String date);
    List<Sessions> findByConferenceIdAndTrack(Integer conferenceId, String track);
}