package com.payment.service.service;

import com.payment.service.model.PaymentInfo;
import com.payment.service.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<PaymentInfo> savePaymentRecord(PaymentInfo paymentInfo) {
        PaymentInfo payment = repository.save(paymentInfo);
        return Optional.of(payment);
    }

    @Override
    public Optional<PaymentInfo> getPaymentInfo(String orderId) {
        return repository.findByOrderId(orderId);
    }
}
