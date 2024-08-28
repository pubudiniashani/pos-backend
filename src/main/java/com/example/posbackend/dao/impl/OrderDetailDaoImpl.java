package com.example.posbackend.dao.impl;

import com.example.posbackend.dao.OrderDetailDao;

public class OrderDetailDaoImpl implements OrderDetailDao {
    @Override
    public boolean delete(String orderId, String itemId) {
        return false;
    }

    @Override
    public boolean exist(String orderId, String itemId) {
        return false;
    }
}
