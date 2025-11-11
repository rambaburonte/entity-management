package com.gl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.entity.Tracks;
import com.gl.repository.TracksRepository;

@Service
public class TracksService {

    @Autowired
    private TracksRepository tracksRepository;

    public List<Tracks> getAllTracks() {
        return tracksRepository.findAll();
    }

    public Tracks getTrackById(Integer id) {
        return tracksRepository.findById(id).orElse(null);
    }

    public List<Tracks> getTracksByConference(Integer conferenceId) {
        return tracksRepository.findByUser(conferenceId);
    }

    public List<Tracks> getTracksByConferenceAndDate(Integer conferenceId, java.time.LocalDate date) {
        return tracksRepository.findByUserAndTrackDate(conferenceId, date);
    }
}