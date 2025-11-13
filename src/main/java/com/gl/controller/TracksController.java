package com.gl.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.entity.Tracks;
import com.gl.service.TracksService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/tracks")
@RequiredArgsConstructor
public class TracksController {

    private final TracksService tracksService;

    @GetMapping
    public ResponseEntity<List<Tracks>> getAllTracks() {
        List<Tracks> tracks = tracksService.getAllTracks();
        return ResponseEntity.ok(tracks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tracks> getTrackById(@PathVariable Integer id) {
        Tracks track = tracksService.getTrackById(id);
        if (track == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(track);
    }

    @GetMapping("/conference/{conferenceId}")
    public ResponseEntity<List<Tracks>> getTracksByConference(@PathVariable Integer conferenceId) {
        List<Tracks> tracks = tracksService.getTracksByConference(conferenceId);
        return ResponseEntity.ok(tracks);
    }

    @GetMapping("/conference/{conferenceId}/date")
    public ResponseEntity<List<Tracks>> getTracksByConferenceAndDate(
            @PathVariable Integer conferenceId,
            @RequestParam String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            List<Tracks> tracks = tracksService.getTracksByConferenceAndDate(conferenceId, localDate);
            return ResponseEntity.ok(tracks);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}