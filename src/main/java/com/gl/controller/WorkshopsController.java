package com.gl.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.entity.Workshops;
import com.gl.service.WorkshopsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/workshops")
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

    @GetMapping("/user/{user}")
    public ResponseEntity<List<Workshops>> getWorkshopsByUser(@PathVariable Integer user) {
        List<Workshops> workshops = workshopsService.getWorkshopsByUser(user);
        return ResponseEntity.ok(workshops);
    }
}