package com.gl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    private String status;
    private String message;
    private String paymentIntentId;
    private String clientSecret; // For Stripe frontend
    private BigDecimal amount;
    private String currency;
    private Integer registrationId;
    private String paymentStatus; // requires_payment_method, requires_confirmation, requires_action, processing, succeeded, canceled
}
