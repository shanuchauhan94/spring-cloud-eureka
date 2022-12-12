package com.order.service.repository;

import com.order.service.model.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<OrderInfo, String> {
}
