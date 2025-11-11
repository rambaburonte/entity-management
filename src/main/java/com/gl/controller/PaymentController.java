package com.gl.controller;

import com.gl.dto.PaymentRequest;
import com.gl.dto.PaymentResponse;
import com.gl.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Payment", description = "Stripe payment processing APIs for React frontend")
public class PaymentController {

    private final PaymentService paymentService;

    /**
     * Create payment intent for Stripe
     * POST /api/payment/create-intent
     */
    @Operation(summary = "Create payment intent", description = "Create a Stripe payment intent for frontend payment processing")
    @PostMapping("/create-intent")
    public ResponseEntity<PaymentResponse> createPaymentIntent(
            @Valid @RequestBody PaymentRequest request) {
        log.info("Creating payment intent for amount: {} {}", request.getAmount(), request.getCurrency());
        
        PaymentResponse response = paymentService.createPaymentIntent(request);
        
        if ("success".equals(response.getStatus())) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Confirm payment
     * POST /api/payment/confirm/{paymentIntentId}
     */
    @Operation(summary = "Confirm payment", description = "Confirm a payment and update registration status")
    @PostMapping("/confirm/{paymentIntentId}")
    public ResponseEntity<PaymentResponse> confirmPayment(
            @PathVariable String paymentIntentId) {
        log.info("Confirming payment: {}", paymentIntentId);
        
        PaymentResponse response = paymentService.confirmPayment(paymentIntentId);
        
        if ("success".equals(response.getStatus())) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Stripe webhook endpoint
     * POST /api/payment/webhook
     */
    @Operation(summary = "Stripe webhook", description = "Handle Stripe webhook events")
    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhook(
            @RequestBody String payload,
            @RequestHeader("Stripe-Signature") String sigHeader) {
        log.info("Received Stripe webhook");
        
        try {
            paymentService.handleWebhook(payload, sigHeader);
            return ResponseEntity.ok("Webhook processed");
        } catch (Exception e) {
            log.error("Error processing webhook", e);
            return ResponseEntity.badRequest().body("Webhook processing failed");
        }
    }
}
