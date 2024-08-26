package com.example.posbackend.dao;

import com.example.posbackend.entity.Item;

import java.sql.Connection;

public interface ItemDao {

    boolean saveItem(Item item, Connection connection);
    boolean updateItem(Item item, Connection connection);
    boolean deleteItem(String itemId, Connection connection);
}
