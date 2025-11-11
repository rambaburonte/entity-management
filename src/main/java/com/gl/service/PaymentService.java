package com.gl.service;

import com.gl.dto.PaymentRequest;
import com.gl.dto.PaymentResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    private final RegistrationService registrationService;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeApiKey;
        log.info("Stripe API initialized");
    }

    /**
     * Create a payment intent for Stripe
     */
    public PaymentResponse createPaymentIntent(PaymentRequest request) {
        try {
            // Convert amount to cents (Stripe requires smallest currency unit)
            long amountInCents = request.getAmount().multiply(new BigDecimal("100")).longValue();

            // Build metadata
            Map<String, String> metadata = new HashMap<>();
            if (request.getRegistrationId() != null) {
                metadata.put("registrationId", request.getRegistrationId().toString());
            }
            if (request.getConferenceId() != null) {
                metadata.put("conferenceId", request.getConferenceId());
            }
            if (request.getDescription() != null) {
                metadata.put("description", request.getDescription());
            }
            if (request.getMetadata() != null) {
                metadata.putAll(request.getMetadata());
            }

            // Create payment intent
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(amountInCents)
                    .setCurrency(request.getCurrency())
                    .setDescription(request.getDescription())
                    .setReceiptEmail(request.getEmail())
                    .putAllMetadata(metadata)
                    .setAutomaticPaymentMethods(
                            PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                    .setEnabled(true)
                                    .build()
                    )
                    .build();

            PaymentIntent paymentIntent = PaymentIntent.create(params);

            log.info("Payment intent created: {} for registration: {}", 
                    paymentIntent.getId(), request.getRegistrationId());

            return PaymentResponse.builder()
                    .status("success")
                    .message("Payment intent created successfully")
                    .paymentIntentId(paymentIntent.getId())
                    .clientSecret(paymentIntent.getClientSecret())
                    .amount(request.getAmount())
                    .currency(request.getCurrency())
                    .registrationId(request.getRegistrationId())
                    .paymentStatus(paymentIntent.getStatus())
                    .build();

        } catch (StripeException e) {
            log.error("Error creating payment intent", e);
            return PaymentResponse.builder()
                    .status("error")
                    .message("Failed to create payment intent: " + e.getMessage())
                    .build();
        }
    }

    /**
     * Confirm payment and update registration status
     */
    public PaymentResponse confirmPayment(String paymentIntentId) {
        try {
            PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);
            
            // Extract registration ID from metadata
            String registrationIdStr = paymentIntent.getMetadata().get("registrationId");
            Integer registrationId = registrationIdStr != null ? Integer.parseInt(registrationIdStr) : null;

            // Update registration payment status
            if (registrationId != null && "succeeded".equals(paymentIntent.getStatus())) {
                registrationService.updatePaymentStatus(registrationId, "succeeded", paymentIntentId);
            }

            return PaymentResponse.builder()
                    .status("success")
                    .message("Payment confirmed")
                    .paymentIntentId(paymentIntentId)
                    .paymentStatus(paymentIntent.getStatus())
                    .registrationId(registrationId)
                    .build();

        } catch (StripeException e) {
            log.error("Error confirming payment", e);
            return PaymentResponse.builder()
                    .status("error")
                    .message("Failed to confirm payment: " + e.getMessage())
                    .build();
        }
    }

    /**
     * Handle Stripe webhook events
     */
    public void handleWebhook(String payload, String sigHeader) {
        try {
            // Verify webhook signature and process event
            // This would require webhook secret configuration
            log.info("Received webhook event");
            
            // Process different event types
            // payment_intent.succeeded, payment_intent.payment_failed, etc.
            
        } catch (Exception e) {
            log.error("Error processing webhook", e);
        }
    }

    /**
     * Retrieve payment intent details
     */
    public PaymentIntent getPaymentIntent(String paymentIntentId) throws StripeException {
        return PaymentIntent.retrieve(paymentIntentId);
    }
}
