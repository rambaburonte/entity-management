package com.gl.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AbstractSubmissionRequest {
    
    @NotBlank(message = "Title is mandatory")
    @Pattern(regexp = "^[a-zA-Z\\.\\-_]{1,20}$", message = "Title with only a-z A-Z.")
    private String title;
    
    @NotBlank(message = "Name is mandatory")
    @Pattern(regexp = "^[a-zA-Z\\.\\-_ ]{1,30}$", message = "Name with only a-z A-Z.")
    private String name;
    
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Enter Valid Email")
    @Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$", message = "Enter Valid Email with only a-z 0-9")
    private String email;
    
    @NotBlank(message = "Phone Number is mandatory")
    @Pattern(regexp = "^[0-9]{9,15}$", message = "Phone Number must be 9-15 digits")
    private String phno;
    
    @NotBlank(message = "Organization is mandatory")
    @Pattern(regexp = "^[a-zA-Z0-9_.@#&+\\-\\.\\ ]{1,90}$", message = "Organization with only a-z A-Z 0-9.")
    private String organization;
    
    @NotBlank(message = "Country is mandatory")
    @Pattern(regexp = "^[a-zA-Z_.()&+'\\-:\\.\\ ]{1,50}$", message = "Enter Your Country")
    private String country;
    
    @NotBlank(message = "Category is mandatory")
    private String category;
    
    @NotBlank(message = "Session is mandatory")
    private String session;
    
    @NotBlank(message = "Captcha is mandatory")
    private String captchaCode;
    
    private String user; // Conference/Event name
    
    private String presentationTitle;
    
    private String address;
    
    private String sentFrom;
    
    private String entity; // Domain identifier
}
