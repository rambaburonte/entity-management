package com.gl.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BrochureDownloadRequest {
    
    @NotBlank(message = "Title is mandatory")
    private String prof;
    
    @NotBlank(message = "Name is mandatory")
    @Size(max = 500)
    private String name;
    
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Enter Valid Email")
    @Size(max = 500)
    private String email;
    
    @Size(max = 500)
    private String alternateEmail;
    
    @NotBlank(message = "Phone is mandatory")
    @Size(max = 500)
    private String phone;
    
    @NotBlank(message = "Organization is mandatory")
    @Size(max = 500)
    private String organization;
    
    @NotBlank(message = "Country is mandatory")
    @Size(max = 500)
    private String country;
    
    @NotBlank(message = "Message is mandatory")
    private String message;
    
    private Integer user; // Conference ID
}
