package com.order.service.util;

import com.order.service.model.OrderInfo;
import com.order.service.model.OrderRequest;
import com.order.service.model.PaymentInfo;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EntityGenerator {

    private OrderInfo orderInfo;
    private PaymentInfo paymentInfo;

    public EntityGenerator(OrderRequest orderRequest) {
        OrderInfo order = new OrderInfo();
        PaymentInfo payment = new PaymentInfo();
        order.setOrderId(UUID.randomUUID().toString());
        order.setName(orderRequest.getName());
        order.setPrice(orderRequest.getAmount());
        order.setQuantity(orderRequest.getQuantity());
        order.setOrderDate(new Date());
        this.orderInfo = order;

        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setAmount(orderRequest.getAmount());
        payment.setTranDate(new Date());
        this.paymentInfo = payment;
    }
}
