package com.example.payment.dto;
import com.example.payment.entity.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record UpdatePaymentRequest(
 @NotNull @Min(1) Long orderId,
 @NotNull @DecimalMin("0.01") BigDecimal amount,
 @NotNull PaymentMethod paymentMethod,
 @NotNull PaymentStatus status,
 @NotBlank String transactionReference) {}
