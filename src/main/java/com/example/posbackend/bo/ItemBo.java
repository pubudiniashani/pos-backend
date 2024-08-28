package com.example.posbackend.bo;

import com.example.posbackend.dto.CustomerDTO;
import com.example.posbackend.dto.ItemDTO;

import java.sql.Connection;
import java.util.List;

public interface ItemBo {

    List<ItemDTO> getAllItems(Connection connection);
    boolean saveItem(ItemDTO itemDTO, Connection connection);
    boolean updateItem(ItemDTO itemDTO, Connection connection);
    boolean deleteItem(String itemId, Connection connection);
}
