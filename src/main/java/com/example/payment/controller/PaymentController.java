package com.example.payment.controller;

import com.example.payment.dto.*;
import com.example.payment.entity.Payment;
import com.example.payment.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
 private final PaymentService service;
 public PaymentController(PaymentService service){this.service=service;}

 @PostMapping public ResponseEntity<Payment> create(@Valid @RequestBody CreatePaymentRequest r){
  return ResponseEntity.status(HttpStatus.CREATED).body(service.create(r));
 }
 @GetMapping public List<Payment> getAll(){return service.getAll();}
 @GetMapping("/{id}") public Payment getById(@PathVariable Long id){return service.getById(id);}
 @GetMapping("/order/{orderId}") public List<Payment> getByOrderId(@PathVariable Long orderId){return service.getByOrderId(orderId);}
 @PutMapping("/{id}") public Payment update(@PathVariable Long id,@Valid @RequestBody UpdatePaymentRequest r){return service.update(id,r);}
 @PatchMapping("/{id}") public Payment patch(@PathVariable Long id,@Valid @RequestBody PatchPaymentRequest r){return service.patch(id,r);}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){service.delete(id);return ResponseEntity.noContent().build();}
}
