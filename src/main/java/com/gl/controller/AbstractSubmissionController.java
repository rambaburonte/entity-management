package com.gl.controller;

import com.gl.dto.AbstractSubmissionRequest;
import com.gl.dto.AbstractSubmissionResponse;
import com.gl.entity.AbstractSubmission;
import com.gl.service.AbstractSubmissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/abstracts")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Abstract Submission", description = "APIs for abstract submission and management")
@CrossOrigin(origins = "*")
public class AbstractSubmissionController {

    private final AbstractSubmissionService abstractSubmissionService;

    @Operation(summary = "Submit a new abstract", description = "Submit an abstract with file upload")
    @PostMapping(value = "/submit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AbstractSubmissionResponse> submitAbstract(
            @Valid @ModelAttribute AbstractSubmissionRequest request,
            @RequestParam(value = "uploadfile", required = false) MultipartFile file,
            HttpServletRequest httpRequest) {
        
        log.info("Received abstract submission from: {}", request.getEmail());
        
        AbstractSubmissionResponse response = abstractSubmissionService.submitAbstract(request, file, httpRequest);
        
        if ("success".equals(response.getStatus())) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @Operation(summary = "Get all abstract submissions")
    @GetMapping
    public ResponseEntity<List<AbstractSubmission>> getAllSubmissions() {
        List<AbstractSubmission> submissions = abstractSubmissionService.getAllSubmissions();
        return ResponseEntity.ok(submissions);
    }

    @Operation(summary = "Get abstract submission by ID")
    @GetMapping("/{id}")
    public ResponseEntity<AbstractSubmission> getSubmissionById(@PathVariable Integer id) {
        AbstractSubmission submission = abstractSubmissionService.getSubmissionById(id);
        if (submission != null) {
            return ResponseEntity.ok(submission);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get abstract submissions by conference/user")
    @GetMapping("/conference/{user}")
    public ResponseEntity<List<AbstractSubmission>> getSubmissionsByUser(@PathVariable String user) {
        List<AbstractSubmission> submissions = abstractSubmissionService.getSubmissionsByUser(user);
        return ResponseEntity.ok(submissions);
    }

    @Operation(summary = "Get abstract submissions by email")
    @GetMapping("/email/{email}")
    public ResponseEntity<List<AbstractSubmission>> getSubmissionsByEmail(@PathVariable String email) {
        List<AbstractSubmission> submissions = abstractSubmissionService.getSubmissionsByEmail(email);
        return ResponseEntity.ok(submissions);
    }
}
