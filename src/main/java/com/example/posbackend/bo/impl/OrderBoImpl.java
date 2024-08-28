package com.example.posbackend.bo.impl;

import com.example.posbackend.bo.OrderBo;
import com.example.posbackend.dto.OrderDTO;

import java.sql.Connection;

public class OrderBoImpl implements OrderBo {
    @Override
    public boolean saveOrder(OrderDTO orderDTO, Connection connection) {
        return false;
    }

    @Override
    public boolean deleteOrder(String orderId, Connection connection) {
        return false;
    }


}
