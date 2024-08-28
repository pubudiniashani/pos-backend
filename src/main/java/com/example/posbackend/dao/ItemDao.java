package com.example.posbackend.dao;

import com.example.posbackend.entity.Item;

import java.sql.Connection;
import java.util.List;

public interface ItemDao {

    List<Item> getAllItems(Connection connection);
    boolean saveItem(Item item, Connection connection);
    boolean updateItem(Item item, Connection connection);
    boolean deleteItem(String itemId, Connection connection);
}
