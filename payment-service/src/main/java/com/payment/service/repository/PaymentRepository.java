package com.payment.service.repository;

import com.payment.service.model.PaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface PaymentRepository extends JpaRepository<PaymentInfo, String> {

    Optional<PaymentInfo> findByOrderId(String orderId);
}
