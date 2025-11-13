package com.gl.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gl.dto.AbstractSubmissionRequest;
import com.gl.dto.AbstractSubmissionResponse;
import com.gl.entity.AbstractSubmission;
import com.gl.repository.AbstractSubmissionRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AbstractSubmissionService {

    private final AbstractSubmissionRepository abstractSubmissionRepository;
    private final EmailService emailService;

    @Transactional
    public AbstractSubmissionResponse submitAbstract(
            AbstractSubmissionRequest request,
            Object file,
            HttpServletRequest httpRequest) {
        
        try {
            log.info("Processing abstract submission for email: {}", request.getEmail());
            
            String ipAddress = getClientIpAddress(httpRequest);
            
            AbstractSubmission submission = new AbstractSubmission();
            submission.setUser(request.getUser());
            submission.setTitle(request.getTitle());
            submission.setFname(request.getName());
            submission.setCountry(request.getCountry());
            submission.setOrg(request.getOrganization());
            submission.setEmail(request.getEmail());
            submission.setPhno(request.getPhno());
            submission.setCategory(request.getCategory());
            submission.setSentFrom(request.getSentFrom());
            submission.setTrackName(request.getSession());
            submission.setAddress(request.getAddress());
            submission.setDate(LocalDate.now());
            submission.setIpaddress(ipAddress);
            submission.setPresentationTitle(request.getPresentationTitle());
            submission.setEntity(request.getEntity());
            
            AbstractSubmission savedSubmission = abstractSubmissionRepository.save(submission);
            log.info("Abstract submission saved with ID: {}", savedSubmission.getId());
            
            try {
                emailService.sendAbstractConfirmationEmail(savedSubmission, null);
                log.info("Confirmation email sent to: {}", request.getEmail());
            } catch (Exception emailEx) {
                log.error("Failed to send confirmation email", emailEx);
            }
            
            return new AbstractSubmissionResponse(
                    savedSubmission.getId(),
                    "Thank you for your interest in submitting Abstract. We will get back to you soon...",
                    "success",
                    request.getEmail(),
                    null
            );
            
        } catch (Exception e) {
            log.error("Error submitting abstract for email: {}", request.getEmail(), e);
            return new AbstractSubmissionResponse(
                    null,
                    "Error submitting abstract: " + e.getMessage(),
                    "error",
                    null,
                    null
            );
        }
    }

    private String getClientIpAddress(HttpServletRequest request) {
        String xForwardedForHeader = request.getHeader("X-Forwarded-For");
        if (xForwardedForHeader == null) {
            return request.getRemoteAddr();
        } else {
            return xForwardedForHeader.split(",")[0];
        }
    }

    public List<AbstractSubmission> getAllSubmissions() {
        log.info("Fetching all abstract submissions");
        return abstractSubmissionRepository.findAll();
    }

    public AbstractSubmission getSubmissionById(Integer id) {
        log.info("Fetching abstract submission by ID: {}", id);
        return abstractSubmissionRepository.findById(id).orElse(null);
    }

    public List<AbstractSubmission> getSubmissionsByUser(String user) {
        log.info("Fetching abstract submissions by user: {}", user);
        return abstractSubmissionRepository.findByUser(user);
    }

    public List<AbstractSubmission> getSubmissionsByEmail(String email) {
        log.info("Fetching abstract submissions by email: {}", email);
        return abstractSubmissionRepository.findByEmail(email);
    }
}
