package com.gl.dto;

import java.math.BigDecimal;
import java.util.Map;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentRequest {
    
    @NotNull(message = "Amount is mandatory")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private BigDecimal amount;
    
    @NotBlank(message = "Currency is mandatory")
    private String currency; // "usd"
    
    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;
    
    private String description; // Registration type
    
    private Integer registrationId; // Link to registration record
    
    private String conferenceId;
    
    private Map<String, String> metadata; // Additional data
}
