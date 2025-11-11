package com.gl.controller;

import com.gl.dto.BrochureDownloadRequest;
import com.gl.entity.Brochure;
import com.gl.repository.BrochureRepository;
import com.gl.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/brochure")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Brochure Management", description = "APIs for brochure download requests")
@CrossOrigin(origins = "*")
public class BrochureController {

    private final BrochureRepository brochureRepository;
    private final EmailService emailService;

    @Operation(summary = "Request brochure download")
    @PostMapping("/download")
    public ResponseEntity<Map<String, String>> requestBrochure(
            @Valid @RequestBody BrochureDownloadRequest request) {
        
        log.info("Brochure request from: {}", request.getEmail());
        
        try {
            // Save brochure request to database
            Brochure brochure = new Brochure();
            brochure.setProf(request.getProf());
            brochure.setName(request.getName());
            brochure.setEmail(request.getEmail());
            brochure.setAlternateEmail(request.getAlternateEmail());
            brochure.setPhone(request.getPhone());
            brochure.setOrganization(request.getOrganization());
            brochure.setCountry(request.getCountry());
            brochure.setMessage(request.getMessage());
            brochure.setUser(request.getUser());
            
            brochureRepository.save(brochure);
            
            // Send confirmation email
            emailService.sendBrochureDownloadEmail(
                    request.getEmail(),
                    request.getName(),
                    "Conference" // You can get this from database based on user ID
            );
            
            Map<String, String> response = new HashMap<>();
            response.put("message", "Thank you for your interest. We will send you the brochure shortly.");
            response.put("status", "success");
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (Exception e) {
            log.error("Error processing brochure request", e);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error processing your request: " + e.getMessage());
            response.put("status", "error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "Get all brochure requests")
    @GetMapping
    public ResponseEntity<List<Brochure>> getAllBrochureRequests() {
        List<Brochure> brochures = brochureRepository.findAll();
        return ResponseEntity.ok(brochures);
    }

    @Operation(summary = "Get brochure requests by conference/user")
    @GetMapping("/conference/{userId}")
    public ResponseEntity<List<Brochure>> getBrochuresByUser(@PathVariable Integer userId) {
        List<Brochure> brochures = brochureRepository.findByUser(userId);
        return ResponseEntity.ok(brochures);
    }
}
