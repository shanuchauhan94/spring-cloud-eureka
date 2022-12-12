package com.order.service.service;

import com.order.service.model.OrderRequest;

import java.util.Optional;

public interface OrderService {

    Optional<OrderRequest> saveCustomerOrder(OrderRequest orderRequest);

    Optional<OrderRequest> getOrderInfo(String orderId);
}
