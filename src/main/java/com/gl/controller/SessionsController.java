package com.gl.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.entity.Sessions;
import com.gl.service.SessionsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class SessionsController {

    private final SessionsService sessionsService;

    @GetMapping
    public ResponseEntity<List<Sessions>> getAllSessions() {
        List<Sessions> sessions = sessionsService.getAllSessions();
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sessions> getSessionById(@PathVariable Integer id) {
        Sessions session = sessionsService.getSessionById(id);
        if (session == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(session);
    }

    @GetMapping("/conference/{conferenceId}")
    public ResponseEntity<List<Sessions>> getSessionsByConference(@PathVariable Integer conferenceId) {
        List<Sessions> sessions = sessionsService.getSessionsByConference(conferenceId);
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/conference/{conferenceId}/day/{day}")
    public ResponseEntity<List<Sessions>> getSessionsByConferenceAndDay(
            @PathVariable Integer conferenceId,
            @PathVariable Integer day) {
        List<Sessions> sessions = sessionsService.getSessionsByConferenceAndDay(conferenceId, day);
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/conference/{conferenceId}/date")
    public ResponseEntity<List<Sessions>> getSessionsByConferenceAndDate(
            @PathVariable Integer conferenceId,
            @RequestParam String date) {
        List<Sessions> sessions = sessionsService.getSessionsByConferenceAndDate(conferenceId, date);
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/conference/{conferenceId}/track/{track}")
    public ResponseEntity<List<Sessions>> getSessionsByConferenceAndTrack(
            @PathVariable Integer conferenceId,
            @PathVariable String track) {
        List<Sessions> sessions = sessionsService.getSessionsByConferenceAndTrack(conferenceId, track);
        return ResponseEntity.ok(sessions);
    }
}