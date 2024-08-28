package com.example.posbackend.dao;

import com.example.posbackend.entity.Order;

import java.util.List;

public interface OrderDao {

    boolean saveOrder();

    List<Order> getAll();
}
