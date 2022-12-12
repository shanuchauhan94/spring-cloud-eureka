package com.order.service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
public class OrderInfo implements Serializable {

    @Id
    private String orderId;
    private String name;
    private Double price;
    private Date orderDate;
    private int quantity;

    @JdbcTypeCode(SqlTypes.JSON)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transId", referencedColumnName = "transactionId")
    private PaymentInfo paymentInfo;

}
