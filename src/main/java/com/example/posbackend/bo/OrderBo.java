package com.example.posbackend.bo;

import com.example.posbackend.dto.OrderDTO;

import java.sql.Connection;

public interface OrderBo {
    boolean saveOrder(OrderDTO orderDTO, Connection connection);

    boolean deleteOrder(String orderId, Connection connection);
}
