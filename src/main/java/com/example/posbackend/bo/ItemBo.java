package com.example.posbackend.bo;

import com.example.posbackend.dto.ItemDTO;

import java.sql.Connection;

public interface ItemBo {
    boolean saveItem(ItemDTO itemDTO, Connection connection);
    boolean updateItem(ItemDTO itemDTO, Connection connection);
    boolean deleteItem(String itemId, Connection connection);
}
