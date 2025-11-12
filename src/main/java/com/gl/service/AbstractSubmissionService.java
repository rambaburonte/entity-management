package com.gl.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
    
    @Value("${file.upload-dir}")
    private String uploadDir;

    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("gif", "png", "jpg", "pdf", "doc", "docx");

    @Transactional
    public AbstractSubmissionResponse submitAbstract(
            AbstractSubmissionRequest request,
            MultipartFile file,
            HttpServletRequest httpRequest) {
        
        try {
            // Get IP address
            String ipAddress = getClientIpAddress(httpRequest);
            
            // Create entity
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
            
            // Save to database to get ID
            AbstractSubmission savedSubmission = abstractSubmissionRepository.save(submission);
            
            // Handle file upload
            String attachmentUrl = null;
            if (file != null && !file.isEmpty()) {
                String uploadResult = handleFileUpload(file, savedSubmission.getId());
                if (uploadResult.startsWith("Error")) {
                    return new AbstractSubmissionResponse(
                            savedSubmission.getId(),
                            uploadResult,
                            "error",
                            null,
                            null
                    );
                }
                
                // Update submission with attachment filename
                savedSubmission.setAttachment(uploadResult);
                abstractSubmissionRepository.save(savedSubmission);
                attachmentUrl = "/uploads/" + uploadResult;
            } else {
                return new AbstractSubmissionResponse(
                        savedSubmission.getId(),
                        "Please upload your abstract file.",
                        "error",
                        null,
                        null
                );
            }
            
            // Send confirmation email
            emailService.sendAbstractConfirmationEmail(savedSubmission, attachmentUrl);
            
            return new AbstractSubmissionResponse(
                    savedSubmission.getId(),
                    "Thank you for your interest in submitting Abstract. We will get back to you soon...",
                    "success",
                    request.getEmail(),
                    attachmentUrl
            );
            
        } catch (Exception e) {
            log.error("Error submitting abstract", e);
            return new AbstractSubmissionResponse(
                    null,
                    "Error submitting abstract: " + e.getMessage(),
                    "error",
                    null,
                    null
            );
        }
    }

    private String handleFileUpload(MultipartFile file, Integer submissionId) {
        try {
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null) {
                return "Error: Invalid file";
            }
            
            // Get file extension
            String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
            
            // Validate extension
            if (!ALLOWED_EXTENSIONS.contains(extension)) {
                return "Error: Your abstract file should be a PDF/DOC/DOCX type of file.";
            }
            
            // Create new filename
            String newFilename = submissionId + "-Abstract." + extension;
            
            // Create upload directory if it doesn't exist
            File uploadDirectory = new File(uploadDir);
            if (!uploadDirectory.exists()) {
                uploadDirectory.mkdirs();
            }
            
            // Save file
            Path filePath = Paths.get(uploadDir, newFilename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            
            log.info("File uploaded successfully: {}", newFilename);
            return newFilename;
            
        } catch (IOException e) {
            log.error("Error uploading file", e);
            return "Error: Failed to upload your abstract file.";
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
        return abstractSubmissionRepository.findAll();
    }

    public AbstractSubmission getSubmissionById(Integer id) {
        return abstractSubmissionRepository.findById(id).orElse(null);
    }

    public List<AbstractSubmission> getSubmissionsByUser(String user) {
        return abstractSubmissionRepository.findByUser(user);
    }

    public List<AbstractSubmission> getSubmissionsByEmail(String email) {
        return abstractSubmissionRepository.findByEmail(email);
    }
}
