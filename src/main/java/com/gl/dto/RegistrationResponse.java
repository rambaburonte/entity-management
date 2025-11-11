package com.gl.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponse {
    private String status; // "success", "error"
    private String message;
    private Integer registrationId;
    private BigDecimal amount;
    private String registrationCategory; // "EarlyBird", "Standard", "Final"
    private String paymentIntentId; // For Stripe payment
    private String clientSecret; // For Stripe payment
}
