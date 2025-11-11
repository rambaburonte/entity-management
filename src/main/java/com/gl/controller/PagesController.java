package com.gl.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.entity.Pages;
import com.gl.service.PagesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pages")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PagesController {

    private final PagesService pagesService;

    @GetMapping("/conference/{conferenceId}")
    public ResponseEntity<Pages> getPagesByConference(@PathVariable Integer conferenceId) {
        Pages pages = pagesService.getPagesByConference(conferenceId);
        if (pages == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pages> getPagesById(@PathVariable Integer id) {
        Pages pages = pagesService.getPagesById(id);
        if (pages == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pages);
    }
}