package com.order.service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class PaymentInfo implements Serializable {

    @Id
    private String transactionId;
    private Double amount;
    private Date tranDate;
    private String orderId;
    private boolean status;
}
