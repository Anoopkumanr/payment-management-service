package com.example.payment.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="payments")
public class Payment {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private Long orderId;
 @Column(nullable=false, precision=12, scale=2) private BigDecimal amount;
 @Enumerated(EnumType.STRING) @Column(nullable=false, length=30) private PaymentMethod paymentMethod;
 @Enumerated(EnumType.STRING) @Column(nullable=false, length=20) private PaymentStatus status;
 @Column(nullable=false, unique=true, length=100) private String transactionReference;
 @Column(nullable=false, updatable=false) private LocalDateTime createdAt;
 @Column(nullable=false) private LocalDateTime updatedAt;

 @PrePersist void onCreate() {
  LocalDateTime now=LocalDateTime.now(); createdAt=now; updatedAt=now;
  if(status==null) status=PaymentStatus.INITIATED;
 }
 @PreUpdate void onUpdate(){ updatedAt=LocalDateTime.now(); }

 public Payment(){}
 public Long getId(){return id;} public void setId(Long v){id=v;}
 public Long getOrderId(){return orderId;} public void setOrderId(Long v){orderId=v;}
 public BigDecimal getAmount(){return amount;} public void setAmount(BigDecimal v){amount=v;}
 public PaymentMethod getPaymentMethod(){return paymentMethod;} public void setPaymentMethod(PaymentMethod v){paymentMethod=v;}
 public PaymentStatus getStatus(){return status;} public void setStatus(PaymentStatus v){status=v;}
 public String getTransactionReference(){return transactionReference;} public void setTransactionReference(String v){transactionReference=v;}
 public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getUpdatedAt(){return updatedAt;}
}
