package com.gl.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.entity.Members;
import com.gl.service.SpeakerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/speakers")
@RequiredArgsConstructor
public class SpeakerController {

    private final SpeakerService speakerService;

    @GetMapping
    public ResponseEntity<List<Members>> getAllSpeakers() {
        List<Members> speakers = speakerService.getAllSpeakers();
        return ResponseEntity.ok(speakers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Members> getSpeakerById(@PathVariable Integer id) {
        Members speaker = speakerService.getSpeakerById(id);
        if (speaker == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(speaker);
    }

    @GetMapping("/conference/{conferenceId}")
    public ResponseEntity<List<Members>> getSpeakersByConference(@PathVariable Integer conferenceId) {
        List<Members> speakers = speakerService.getSpeakersByConference(conferenceId);
        return ResponseEntity.ok(speakers);
    }

    @GetMapping("/category/{category}/conference/{conferenceId}")
    public ResponseEntity<List<Members>> getSpeakersByCategory(
            @PathVariable String category,
            @PathVariable Integer conferenceId) {
        List<Members> speakers = speakerService.getSpeakersByCategory(category, conferenceId);
        return ResponseEntity.ok(speakers);
    }
}
