package com.gl.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrationRequest {
    
    @NotBlank(message = "Registration type is mandatory")
    private String registrationType; // "Speaker", "Delegate", "Poster", "Student", "Sponsor"
    
    @NotBlank(message = "Name is mandatory")
    @Size(max = 500)
    private String name;
    
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Enter Valid Email")
    @Size(max = 500)
    private String email;
    
    @NotBlank(message = "Phone is mandatory")
    @Size(max = 50)
    private String phone;
    
    @Size(max = 500)
    private String organization;
    
    @Size(max = 500)
    private String country;
    
    @Size(max = 500)
    private String address;
    
    private String conferenceId; // User/Conference identifier
    
    // For sponsor registrations
    private String sponsorPlan; // "Platinum", "Gold", "Silver", "Exhibitor", "Promotional Sponsor", "Ad-Sponsor"
    
    // Additional fields
    private String title; // Dr., Mr., Mrs., Ms.
    private String designation;
    private String specialRequirements;
    private String discountCode; // Optional discount code
}
