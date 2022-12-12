package com.order.service.service;

import com.order.service.model.OrderInfo;
import com.order.service.model.OrderRequest;
import com.order.service.model.PaymentInfo;
import com.order.service.repository.OrderRepository;
import com.order.service.util.EntityGenerator;
import com.order.service.util.ServiceConstant;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final RestTemplate restTemplate;

    @Autowired
    public OrderServiceImpl(OrderRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    @Transactional
    @Override
    public Optional<OrderRequest> saveCustomerOrder(OrderRequest orderRequest) {
        EntityGenerator generator = new EntityGenerator(orderRequest);

        if (generator.getOrderInfo().getOrderId() != null && !generator.getOrderInfo().getOrderId().isBlank()) {
            ResponseEntity<PaymentInfo> response = savePaymentDataFromService(generator, generator.getOrderInfo());
            if (Objects.nonNull(response.getBody())) {
                OrderInfo order = generator.getOrderInfo();
                order.setPaymentInfo(response.getBody());
                repository.save(generator.getOrderInfo());
                return Optional.of(new OrderRequest(true,generator.getOrderInfo().getOrderId()));
            }
        }
        return Optional.of(new OrderRequest(false,null));
    }

    @Override
    public Optional<OrderRequest> getOrderInfo(String orderId) {

        OrderRequest response = new OrderRequest();
        Optional<OrderInfo> order = repository.findById(orderId);
        if (order.isPresent()) {
            ResponseEntity<PaymentInfo> payments = getPaymentDataFromService(order.get().getOrderId());
            response.setName(order.get().getName());
            response.setOrderDate(order.get().getOrderDate());
            response.setOrderId(order.get().getOrderId());
            response.setQuantity(order.get().getQuantity());
            response.setAmount(Objects.requireNonNull(payments.getBody()).getAmount());
            response.setTranDate(Objects.requireNonNull(payments.getBody()).getTranDate());
            response.setStatus(Objects.requireNonNull(payments.getBody()).isStatus());
            response.setTransactionId(Objects.requireNonNull(payments.getBody()).getTransactionId());
        }
        return Optional.of(response);
    }

    private ResponseEntity<PaymentInfo> savePaymentDataFromService(EntityGenerator generator, OrderInfo savedOrder) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        PaymentInfo pay = generator.getPaymentInfo();
        pay.setOrderId(savedOrder.getOrderId());
        pay.setStatus(true);
        HttpEntity<PaymentInfo> entity = new HttpEntity<>(pay, headers);
        return restTemplate.exchange(ServiceConstant.SAVE_PAYMENT_SERVICE_CALL, HttpMethod.POST, entity, PaymentInfo.class);
    }

    private ResponseEntity<PaymentInfo> getPaymentDataFromService(String orderId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PaymentInfo> entity = new HttpEntity<>(null, headers);
        return restTemplate.exchange(ServiceConstant.GET_PAYMENT_SERVICE_CALL + "/" + orderId, HttpMethod.GET, entity, PaymentInfo.class);
    }
}
