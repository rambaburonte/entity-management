package com.gl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.entity.Sessions;
import com.gl.repository.SessionsRepository;

@Service
public class SessionsService {

    @Autowired
    private SessionsRepository sessionsRepository;

    public List<Sessions> getAllSessions() {
        return sessionsRepository.findAll();
    }

    public Sessions getSessionById(Integer id) {
        return sessionsRepository.findById(id).orElse(null);
    }

    public List<Sessions> getSessionsByConference(Integer conferenceId) {
        return sessionsRepository.findByConferenceId(conferenceId);
    }

    public List<Sessions> getSessionsByConferenceAndDay(Integer conferenceId, Integer day) {
        return sessionsRepository.findByConferenceIdAndDay(conferenceId, day);
    }

    public List<Sessions> getSessionsByConferenceAndDate(Integer conferenceId, String date) {
        return sessionsRepository.findByConferenceIdAndDate(conferenceId, date);
    }

    public List<Sessions> getSessionsByConferenceAndTrack(Integer conferenceId, String track) {
        return sessionsRepository.findByConferenceIdAndTrack(conferenceId, track);
    }
}