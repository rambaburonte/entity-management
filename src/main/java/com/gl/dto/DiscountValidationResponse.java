package com.gl.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountValidationResponse {
    private String status; // "success", "error"
    private String message;
    private Boolean isValid;
    private String discountCode;
    private String discountType; // "percentage", "fixed"
    private BigDecimal discountValue;
    private BigDecimal originalAmount;
    private BigDecimal discountAmount;
    private BigDecimal finalAmount;
}
