package com.gl.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountValidationRequest {
    
    @NotBlank(message = "Discount code is mandatory")
    private String discountCode;
    
    @NotBlank(message = "Conference ID is mandatory")
    private String conferenceId;
    
    @NotNull(message = "Original amount is mandatory")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private BigDecimal originalAmount;
    
    @NotBlank(message = "Registration type is mandatory")
    private String registrationType;
}
