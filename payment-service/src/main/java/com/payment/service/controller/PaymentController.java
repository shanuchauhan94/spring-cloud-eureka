package com.payment.service.controller;

import com.payment.service.model.PaymentInfo;
import com.payment.service.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService service;

    @Autowired
    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<PaymentInfo> savePaymentData(@RequestBody PaymentInfo paymentInfo) {
        return service.savePaymentRecord(paymentInfo).map(m -> new ResponseEntity<>(m, HttpStatus.CREATED))
                .orElseThrow(() -> new RuntimeException("Payment not recorded, Try Again."));
    }

    @GetMapping("/info/{orderId}")
    public ResponseEntity<PaymentInfo> getPaymentData(@PathVariable String orderId) {
        return service.getPaymentInfo(orderId).map(m -> new ResponseEntity<>(m, HttpStatus.CREATED))
                .orElseThrow(() -> new RuntimeException("Payment Record not found, Try Again."));
    }

}
