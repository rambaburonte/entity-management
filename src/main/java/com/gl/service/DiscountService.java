package com.gl.service;

import com.gl.dto.DiscountValidationRequest;
import com.gl.dto.DiscountValidationResponse;
import com.gl.entity.DiscountCode;
import com.gl.repository.DiscountCodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscountService {

    private final DiscountCodeRepository discountCodeRepository;

    /**
     * Validate and apply discount code
     */
    public DiscountValidationResponse validateDiscountCode(DiscountValidationRequest request) {
        try {
            Optional<DiscountCode> discountOpt = discountCodeRepository.findValidDiscountCode(
                    request.getDiscountCode(),
                    request.getConferenceId(),
                    request.getRegistrationType(),
                    LocalDate.now()
            );

            if (discountOpt.isEmpty()) {
                return DiscountValidationResponse.builder()
                        .status("error")
                        .message("Invalid or expired discount code")
                        .isValid(false)
                        .originalAmount(request.getOriginalAmount())
                        .finalAmount(request.getOriginalAmount())
                        .build();
            }

            DiscountCode discount = discountOpt.get();

            // Calculate discount amount
            BigDecimal discountAmount;
            if ("percentage".equalsIgnoreCase(discount.getDiscountType())) {
                discountAmount = request.getOriginalAmount()
                        .multiply(discount.getDiscountValue())
                        .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
            } else {
                discountAmount = discount.getDiscountValue();
            }

            // Calculate final amount
            BigDecimal finalAmount = request.getOriginalAmount().subtract(discountAmount);
            if (finalAmount.compareTo(BigDecimal.ZERO) < 0) {
                finalAmount = BigDecimal.ZERO;
            }

            log.info("Discount code '{}' applied: Original=${}, Discount=${}, Final=${}",
                    discount.getCode(), request.getOriginalAmount(), discountAmount, finalAmount);

            return DiscountValidationResponse.builder()
                    .status("success")
                    .message("Discount code applied successfully")
                    .isValid(true)
                    .discountCode(discount.getCode())
                    .discountType(discount.getDiscountType())
                    .discountValue(discount.getDiscountValue())
                    .originalAmount(request.getOriginalAmount())
                    .discountAmount(discountAmount)
                    .finalAmount(finalAmount)
                    .build();

        } catch (Exception e) {
            log.error("Error validating discount code", e);
            return DiscountValidationResponse.builder()
                    .status("error")
                    .message("Failed to validate discount code: " + e.getMessage())
                    .isValid(false)
                    .originalAmount(request.getOriginalAmount())
                    .finalAmount(request.getOriginalAmount())
                    .build();
        }
    }

    /**
     * Increment discount code usage count
     */
    @Transactional
    public void incrementDiscountUsage(String code) {
        Optional<DiscountCode> discountOpt = discountCodeRepository.findByCode(code);
        if (discountOpt.isPresent()) {
            DiscountCode discount = discountOpt.get();
            discount.setCurrentUses(discount.getCurrentUses() + 1);
            discountCodeRepository.save(discount);
            log.info("Discount code '{}' usage incremented to: {}", code, discount.getCurrentUses());
        }
    }

    /**
     * Get discount by code
     */
    public Optional<DiscountCode> getDiscountByCode(String code) {
        return discountCodeRepository.findByCode(code);
    }
}
