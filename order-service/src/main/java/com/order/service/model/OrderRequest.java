package com.order.service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderRequest {

    private String orderId;
    private String name;
    private Integer quantity;
    private Date orderDate;
    private Double amount;
    private Date tranDate;
    private String transactionId;
    private Boolean status;

    public OrderRequest(boolean status, String orderId) {
        this.orderId = orderId;
        this.status = status;
    }
}
