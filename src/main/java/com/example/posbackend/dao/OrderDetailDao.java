package com.example.posbackend.dao;

public interface OrderDetailDao {
    boolean delete(String orderId, String itemId);
    boolean exist(String orderId, String itemId);
}
