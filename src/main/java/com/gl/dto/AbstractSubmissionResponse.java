package com.gl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbstractSubmissionResponse {
    private Integer id;
    private String message;
    private String status; // success, error
    private String confirmationEmail;
    private String attachmentUrl;
}
