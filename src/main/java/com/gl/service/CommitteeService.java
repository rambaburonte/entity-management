package com.gl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gl.entity.Committee;
import com.gl.repository.CommitteeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommitteeService {

    private final CommitteeRepository committeeRepository;

    public List<Committee> getAllCommittee() {
        return committeeRepository.findAll();
    }

    public Committee getCommitteeById(Integer id) {
        return committeeRepository.findById(id).orElse(null);
    }

    public List<Committee> getCommitteeByConference(Integer conferenceId) {
        return committeeRepository.findByUser(conferenceId);
    }

    public List<Committee> getCommitteeByCategory(String category, Integer conferenceId) {
        return committeeRepository.findByCategoryAndUser(category, conferenceId);
    }
}
