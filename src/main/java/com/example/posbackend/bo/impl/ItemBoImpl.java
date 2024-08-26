package com.example.posbackend.bo.impl;

import com.example.posbackend.bo.ItemBo;
import com.example.posbackend.dao.ItemDao;
import com.example.posbackend.dao.impl.ItemDaoImpl;
import com.example.posbackend.dto.ItemDTO;
import com.example.posbackend.entity.Item;

import java.sql.Connection;

public class ItemBoImpl implements ItemBo {

    public ItemDao itemDao = new ItemDaoImpl();


    @Override
    public boolean saveItem(ItemDTO itemDTO, Connection connection) {
        Item item = new Item(
                itemDTO.getItemId(),
                itemDTO.getItemName(),
                itemDTO.getPrice(),
                itemDTO.getQuantity()
        );

        System.out.println(item);

        return itemDao.saveItem(item,connection);
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO, Connection connection) {
        Item item = new Item(
                itemDTO.getItemId(),
                itemDTO.getItemName(),
                itemDTO.getPrice(),
                itemDTO.getQuantity()
        );
        System.out.println(item);

        return itemDao.updateItem(item,connection);
    }

    @Override
    public boolean deleteItem(String itemId, Connection connection) {
        try {
            return itemDao.deleteItem(itemId, connection);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete item with ID: " + itemId, e);
        }
    }
}
