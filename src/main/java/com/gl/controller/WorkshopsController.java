package com.gl.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.entity.Workshops;
import com.gl.service.WorkshopsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/workshops")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class WorkshopsController {

    private final WorkshopsService workshopsService;

    @GetMapping
    public ResponseEntity<List<Workshops>> getAllWorkshops() {
        List<Workshops> workshops = workshopsService.getAllWorkshops();
        return ResponseEntity.ok(workshops);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Workshops> getWorkshopById(@PathVariable Integer id) {
        Workshops workshop = workshopsService.getWorkshopById(id);
        if (workshop == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(workshop);
    }

    @GetMapping("/conference/{conferenceId}")
    public ResponseEntity<List<Workshops>> getWorkshopsByConference(@PathVariable Integer conferenceId) {
        List<Workshops> workshops = workshopsService.getWorkshopsByConference(conferenceId);
        return ResponseEntity.ok(workshops);
    }

    @GetMapping("/conference/{conferenceId}/date")
    public ResponseEntity<List<Workshops>> getWorkshopsByConferenceAndDate(
            @PathVariable Integer conferenceId,
            @RequestParam String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            List<Workshops> workshops = workshopsService.getWorkshopsByConferenceAndDate(conferenceId, localDate);
            return ResponseEntity.ok(workshops);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}