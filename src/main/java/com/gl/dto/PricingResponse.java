package com.gl.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PricingResponse {
    private String registrationCategory; // "EarlyBird", "Standard", "Final"
    private LocalDate categoryEndDate;
    private Map<String, BigDecimal> prices; // {"speaker": 779, "delegate": 899, ...}
    private String conferenceId;
    private String conferenceName;
}
