package com.payment.service.service;

import com.payment.service.model.PaymentInfo;

import java.util.Optional;

public interface PaymentService {

    Optional<PaymentInfo> savePaymentRecord(PaymentInfo paymentInfo);

    Optional<PaymentInfo> getPaymentInfo(String orderId);
}
