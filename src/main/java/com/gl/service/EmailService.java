package com.gl.service;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.gl.entity.AbstractSubmission;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    @Value("${brevo.api.key}")
    private String brevoApiKey;

    @Value("${brevo.api.url}")
    private String brevoApiUrl;

    @Value("${brevo.sender.email}")
    private String senderEmail;

    @Value("${brevo.sender.name}")
    private String senderName;

    @Value("${file.upload-dir}")
    private String uploadDir;

    private final WebClient.Builder webClientBuilder;

    public void sendAbstractConfirmationEmail(AbstractSubmission submission, String attachmentUrl) {
        try {
            // Read HTML template
            String htmlContent = loadAbstractConfirmationTemplate(submission, attachmentUrl);

            // Prepare attachment
            String attachmentContent = null;
            String attachmentName = null;
            if (submission.getAttachment() != null) {
                File attachmentFile = new File(uploadDir, submission.getAttachment());
                if (attachmentFile.exists()) {
                    byte[] fileContent = Files.readAllBytes(attachmentFile.toPath());
                    attachmentContent = Base64.getEncoder().encodeToString(fileContent);
                    attachmentName = submission.getAttachment();
                }
            }

            // Build email request
            Map<String, Object> emailRequest = new HashMap<>();
            emailRequest.put("sender", Map.of("email", senderEmail, "name", senderName));
            emailRequest.put("to", List.of(Map.of("email", submission.getEmail(), "name", submission.getFname())));
            emailRequest.put("subject", "Abstract Submission Confirmation for " + submission.getUser());
            emailRequest.put("htmlContent", htmlContent);

            if (attachmentContent != null) {
                emailRequest.put("attachment", List.of(Map.of(
                        "name", attachmentName,
                        "content", attachmentContent
                )));
            }

            // Send email via Brevo API
            WebClient webClient = webClientBuilder.build();
            String response = webClient.post()
                    .uri(brevoApiUrl)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .header("Accept", MediaType.APPLICATION_JSON_VALUE)
                    .header("api-key", brevoApiKey)
                    .bodyValue(emailRequest)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            log.info("Email sent successfully to: {}", submission.getEmail());
            log.debug("Brevo API response: {}", response);

        } catch (Exception e) {
            log.error("Error sending email to: {}", submission.getEmail(), e);
        }
    }

    private String loadAbstractConfirmationTemplate(AbstractSubmission submission, String attachmentUrl) {
        // HTML template similar to abstract-confirmation.html
        String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy"));
        
        return String.format("""
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <title>Abstract Submission Confirmation</title>
            </head>
            <body style="font-family: Arial, sans-serif; line-height: 1.6; color: #333;">
                <div style="max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ddd;">
                    <h2 style="color: #a11c4b;">Abstract Submission Confirmation</h2>
                    <p>Dear <strong>%s %s</strong>,</p>
                    <p>Thank you for submitting your abstract to <strong>%s</strong>.</p>
                    
                    <h3>Submission Details:</h3>
                    <table style="width: 100%%; border-collapse: collapse;">
                        <tr>
                            <td style="padding: 8px; border-bottom: 1px solid #ddd;"><strong>Name:</strong></td>
                            <td style="padding: 8px; border-bottom: 1px solid #ddd;">%s %s</td>
                        </tr>
                        <tr>
                            <td style="padding: 8px; border-bottom: 1px solid #ddd;"><strong>Country:</strong></td>
                            <td style="padding: 8px; border-bottom: 1px solid #ddd;">%s</td>
                        </tr>
                        <tr>
                            <td style="padding: 8px; border-bottom: 1px solid #ddd;"><strong>Date:</strong></td>
                            <td style="padding: 8px; border-bottom: 1px solid #ddd;">%s</td>
                        </tr>
                        <tr>
                            <td style="padding: 8px; border-bottom: 1px solid #ddd;"><strong>Email:</strong></td>
                            <td style="padding: 8px; border-bottom: 1px solid #ddd;">%s</td>
                        </tr>
                        <tr>
                            <td style="padding: 8px; border-bottom: 1px solid #ddd;"><strong>Mobile Number:</strong></td>
                            <td style="padding: 8px; border-bottom: 1px solid #ddd;">%s</td>
                        </tr>
                        <tr>
                            <td style="padding: 8px; border-bottom: 1px solid #ddd;"><strong>Organization:</strong></td>
                            <td style="padding: 8px; border-bottom: 1px solid #ddd;">%s</td>
                        </tr>
                        <tr>
                            <td style="padding: 8px; border-bottom: 1px solid #ddd;"><strong>Category:</strong></td>
                            <td style="padding: 8px; border-bottom: 1px solid #ddd;">%s</td>
                        </tr>
                        <tr>
                            <td style="padding: 8px; border-bottom: 1px solid #ddd;"><strong>Session:</strong></td>
                            <td style="padding: 8px; border-bottom: 1px solid #ddd;">%s</td>
                        </tr>
                    </table>
                    
                    <p style="margin-top: 20px;">Your abstract has been received and is under review. We will contact you soon.</p>
                    
                    <p style="margin-top: 30px;">Best regards,<br><strong>%s Team</strong></p>
                </div>
            </body>
            </html>
            """,
                submission.getTitle(), submission.getFname(),
                submission.getUser(),
                submission.getTitle(), submission.getFname(),
                submission.getCountry(),
                dateStr,
                submission.getEmail(),
                submission.getPhno(),
                submission.getOrg(),
                submission.getCategory(),
                submission.getTrackName(),
                submission.getUser()
        );
    }

    public void sendBrochureDownloadEmail(String toEmail, String toName, String confName) {
        try {
            String htmlContent = String.format("""
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <title>Brochure Download</title>
                </head>
                <body style="font-family: Arial, sans-serif; line-height: 1.6; color: #333;">
                    <div style="max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ddd;">
                        <h2 style="color: #a11c4b;">Thank you for your interest!</h2>
                        <p>Dear <strong>%s</strong>,</p>
                        <p>Thank you for requesting the brochure for <strong>%s</strong>.</p>
                        <p>We will contact you shortly with more information.</p>
                        <p style="margin-top: 30px;">Best regards,<br><strong>%s Team</strong></p>
                    </div>
                </body>
                </html>
                """, toName, confName, confName);

            Map<String, Object> emailRequest = new HashMap<>();
            emailRequest.put("sender", Map.of("email", senderEmail, "name", senderName));
            emailRequest.put("to", List.of(Map.of("email", toEmail, "name", toName)));
            emailRequest.put("subject", "Brochure Request - " + confName);
            emailRequest.put("htmlContent", htmlContent);

            WebClient webClient = webClientBuilder.build();
            webClient.post()
                    .uri(brevoApiUrl)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .header("Accept", MediaType.APPLICATION_JSON_VALUE)
                    .header("api-key", brevoApiKey)
                    .bodyValue(emailRequest)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            log.info("Brochure download email sent to: {}", toEmail);

        } catch (Exception e) {
            log.error("Error sending brochure email to: {}", toEmail, e);
        }
    }

    public void sendRegistrationConfirmationEmail(com.gl.entity.Registration registration) {
        try {
            String htmlContent = String.format("""
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <title>Registration Confirmation</title>
                </head>
                <body style="font-family: Arial, sans-serif; line-height: 1.6; color: #333;">
                    <div style="max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ddd;">
                        <h2 style="color: #a11c4b;">Registration Confirmation</h2>
                        <p>Dear <strong>%s</strong>,</p>
                        <p>Thank you for registering for the conference.</p>
                        
                        <h3>Registration Details:</h3>
                        <table style="width: 100%%; border-collapse: collapse;">
                            <tr>
                                <td style="padding: 8px; border-bottom: 1px solid #ddd;"><strong>Registration ID:</strong></td>
                                <td style="padding: 8px; border-bottom: 1px solid #ddd;">%s</td>
                            </tr>
                            <tr>
                                <td style="padding: 8px; border-bottom: 1px solid #ddd;"><strong>Registration Type:</strong></td>
                                <td style="padding: 8px; border-bottom: 1px solid #ddd;">%s</td>
                            </tr>
                            <tr>
                                <td style="padding: 8px; border-bottom: 1px solid #ddd;"><strong>Email:</strong></td>
                                <td style="padding: 8px; border-bottom: 1px solid #ddd;">%s</td>
                            </tr>
                            <tr>
                                <td style="padding: 8px; border-bottom: 1px solid #ddd;"><strong>Amount Paid:</strong></td>
                                <td style="padding: 8px; border-bottom: 1px solid #ddd;">$%s USD</td>
                            </tr>
                            <tr>
                                <td style="padding: 8px; border-bottom: 1px solid #ddd;"><strong>Payment Status:</strong></td>
                                <td style="padding: 8px; border-bottom: 1px solid #ddd;">%s</td>
                            </tr>
                            <tr>
                                <td style="padding: 8px; border-bottom: 1px solid #ddd;"><strong>Organization:</strong></td>
                                <td style="padding: 8px; border-bottom: 1px solid #ddd;">%s</td>
                            </tr>
                        </table>
                        
                        <p style="margin-top: 20px;">We look forward to seeing you at the conference!</p>
                        
                        <p style="margin-top: 30px;">Best regards,<br><strong>Conference Team</strong></p>
                    </div>
                </body>
                </html>
                """,
                registration.getName(),
                registration.getId(),
                registration.getCategory(),
                registration.getEmail(),
                registration.getPrice(),
                registration.getStatus() == 1 ? "Paid" : "Pending",
                registration.getOrg()
            );

            Map<String, Object> emailRequest = new HashMap<>();
            emailRequest.put("sender", Map.of("email", senderEmail, "name", senderName));
            emailRequest.put("to", List.of(Map.of("email", registration.getEmail(), "name", registration.getName())));
            emailRequest.put("subject", "Registration Confirmation - " + registration.getCategory());
            emailRequest.put("htmlContent", htmlContent);

            WebClient webClient = webClientBuilder.build();
            webClient.post()
                    .uri(brevoApiUrl)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .header("Accept", MediaType.APPLICATION_JSON_VALUE)
                    .header("api-key", brevoApiKey)
                    .bodyValue(emailRequest)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            log.info("Registration confirmation email sent to: {}", registration.getEmail());

        } catch (Exception e) {
            log.error("Error sending registration email to: {}", registration.getEmail(), e);
        }
    }
}
