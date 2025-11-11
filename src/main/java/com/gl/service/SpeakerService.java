package com.gl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.entity.Members;
import com.gl.repository.MembersRepository;

@Service
public class SpeakerService {
    @Autowired
    private MembersRepository speakersRepository;

    public List<Members> getAllSpeakers() {
        return speakersRepository.findAll();
    }

    public Members getSpeakerById(Integer id) {
        return speakersRepository.findById(id).orElse(null);
    }

    public List<Members> getSpeakersByConference(Integer conferenceId) {
        return speakersRepository.findByUser(conferenceId);
    }

    public List<Members> getSpeakersByCategory(String category, Integer conferenceId) {
        return speakersRepository.findByCategoryAndUser(category, conferenceId);
    }
}
