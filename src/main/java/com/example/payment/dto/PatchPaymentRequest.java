package com.example.payment.dto;
import com.example.payment.entity.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record PatchPaymentRequest(
 @Min(1) Long orderId,
 @DecimalMin("0.01") BigDecimal amount,
 PaymentMethod paymentMethod,
 PaymentStatus status,
 String transactionReference) {}
