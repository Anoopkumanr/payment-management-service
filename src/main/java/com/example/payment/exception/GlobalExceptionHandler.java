package com.example.payment.exception;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
 @ExceptionHandler(PaymentNotFoundException.class) @ResponseStatus(HttpStatus.NOT_FOUND)
 public Map<String,Object> notFound(PaymentNotFoundException e){
  Map<String,Object> b=new LinkedHashMap<>(); b.put("timestamp",LocalDateTime.now()); b.put("status",404);
  b.put("error","Not Found"); b.put("message",e.getMessage()); return b;
 }
 @ExceptionHandler(MethodArgumentNotValidException.class) @ResponseStatus(HttpStatus.BAD_REQUEST)
 public Map<String,Object> validation(MethodArgumentNotValidException e){
  Map<String,String> errors=new LinkedHashMap<>();
  e.getBindingResult().getFieldErrors().forEach(x->errors.put(x.getField(),x.getDefaultMessage()));
  Map<String,Object> b=new LinkedHashMap<>(); b.put("timestamp",LocalDateTime.now()); b.put("status",400);
  b.put("error","Validation Failed"); b.put("errors",errors); return b;
 }
}
