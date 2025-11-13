package com.gl.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.entity.Committee;
import com.gl.service.CommitteeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/committee")
@RequiredArgsConstructor
public class CommitteeController {

    private final CommitteeService committeeService;

    @GetMapping
    public ResponseEntity<List<Committee>> getAllCommittee() {
        log.info("GET /api/committee - Fetching all committee members");
        List<Committee> committee = committeeService.getAllCommittee();
        log.info("GET /api/committee - Retrieved {} committee members", committee.size());
        return ResponseEntity.ok(committee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Committee> getCommitteeById(@PathVariable Integer id) {
        log.info("GET /api/committee/{} - Fetching committee member by ID", id);
        Committee member = committeeService.getCommitteeById(id);
        if (member == null) {
            log.warn("GET /api/committee/{} - Committee member not found", id);
            return ResponseEntity.notFound().build();
        }
        log.info("GET /api/committee/{} - Retrieved committee member: {}", id, member.getName());
        return ResponseEntity.ok(member);
    }

    @GetMapping("/conference/{conferenceId}")
    public ResponseEntity<List<Committee>> getCommitteeByConference(@PathVariable Integer conferenceId) {
        log.info("GET /api/committee/conference/{} - Fetching committee members for conference", conferenceId);
        List<Committee> committee = committeeService.getCommitteeByConference(conferenceId);
        log.info("GET /api/committee/conference/{} - Retrieved {} committee members", conferenceId, committee.size());
        return ResponseEntity.ok(committee);
    }
}
