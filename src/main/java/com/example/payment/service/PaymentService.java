package com.example.payment.service;

import com.example.payment.dto.*;
import com.example.payment.entity.*;
import com.example.payment.exception.PaymentNotFoundException;
import com.example.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service @Transactional
public class PaymentService {
 private final PaymentRepository repository;
 public PaymentService(PaymentRepository repository){this.repository=repository;}

 public Payment create(CreatePaymentRequest r){
  Payment p=new Payment(); p.setOrderId(r.orderId()); p.setAmount(r.amount());
  p.setPaymentMethod(r.paymentMethod()); p.setStatus(r.status()==null?PaymentStatus.INITIATED:r.status());
  p.setTransactionReference(r.transactionReference()); return repository.save(p);
 }
 @Transactional(readOnly=true) public List<Payment> getAll(){return repository.findAll();}
 @Transactional(readOnly=true) public Payment getById(Long id){return find(id);}
 @Transactional(readOnly=true) public List<Payment> getByOrderId(Long orderId){return repository.findByOrderId(orderId);}

 public Payment update(Long id, UpdatePaymentRequest r){
  Payment p=find(id); p.setOrderId(r.orderId()); p.setAmount(r.amount());
  p.setPaymentMethod(r.paymentMethod()); p.setStatus(r.status()); p.setTransactionReference(r.transactionReference());
  return repository.save(p);
 }
 public Payment patch(Long id, PatchPaymentRequest r){
  Payment p=find(id);
  if(r.orderId()!=null)p.setOrderId(r.orderId());
  if(r.amount()!=null)p.setAmount(r.amount());
  if(r.paymentMethod()!=null)p.setPaymentMethod(r.paymentMethod());
  if(r.status()!=null)p.setStatus(r.status());
  if(r.transactionReference()!=null)p.setTransactionReference(r.transactionReference());
  return repository.save(p);
 }
 public void delete(Long id){repository.delete(find(id));}
 private Payment find(Long id){return repository.findById(id).orElseThrow(()->new PaymentNotFoundException(id));}
}
