package com.gl.controller;

import com.gl.dto.DiscountValidationRequest;
import com.gl.dto.DiscountValidationResponse;
import com.gl.service.DiscountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/discount")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Discount", description = "Discount code validation APIs for React frontend")
public class DiscountController {

    private final DiscountService discountService;

    /**
     * Validate discount code
     * POST /api/discount/validate
     */
    @Operation(summary = "Validate discount code", description = "Validate and calculate discount for registration")
    @PostMapping("/validate")
    public ResponseEntity<DiscountValidationResponse> validateDiscount(
            @Valid @RequestBody DiscountValidationRequest request) {
        log.info("Validating discount code: {} for conference: {}", 
                request.getDiscountCode(), request.getConferenceId());
        
        DiscountValidationResponse response = discountService.validateDiscountCode(request);
        
        if (response.getIsValid()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
}
