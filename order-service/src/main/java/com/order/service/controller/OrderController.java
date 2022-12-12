package com.order.service.controller;

import com.order.service.model.OrderRequest;
import com.order.service.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService service;

    @Autowired
    public OrderController(OrderService orderService) {
        this.service = orderService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<OrderRequest> saveOrders(@RequestBody OrderRequest orderRequest) {
        System.err.println("save order service {} "+ orderRequest.getName());
        return service.saveCustomerOrder(orderRequest).map(m -> new ResponseEntity<>(m, HttpStatus.CREATED))
                .orElseThrow(() -> new RuntimeException("Invalid Request."));
    }

    @GetMapping("/info/{orderId}")
    public ResponseEntity<OrderRequest> getOrder(@PathVariable String orderId) {
        System.err.println("get order information service with order-id {} "+ orderId);
        return service.getOrderInfo(orderId).map(m -> new ResponseEntity<>(m, HttpStatus.OK))
                .orElseThrow(() -> new RuntimeException("Record Not Found for given Order-Id " + orderId));
    }
}
