package com.example.posbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    private String orderId;
    private Date date;
    private double total;
    private String customerId;
}
