package com.gl.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.entity.Committee;
import com.gl.service.CommitteeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/committee")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CommitteeController {

    private final CommitteeService committeeService;

    @GetMapping
    public ResponseEntity<List<Committee>> getAllCommittee() {
        List<Committee> committee = committeeService.getAllCommittee();
        return ResponseEntity.ok(committee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Committee> getCommitteeById(@PathVariable Integer id) {
        Committee member = committeeService.getCommitteeById(id);
        if (member == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(member);
    }

    @GetMapping("/conference/{conferenceId}")
    public ResponseEntity<List<Committee>> getCommitteeByConference(@PathVariable Integer conferenceId) {
        List<Committee> committee = committeeService.getCommitteeByConference(conferenceId);
        return ResponseEntity.ok(committee);
    }
}
