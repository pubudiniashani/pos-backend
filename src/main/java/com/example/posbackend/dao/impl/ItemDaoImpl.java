package com.example.posbackend.dao.impl;

import com.example.posbackend.dao.ItemDao;
import com.example.posbackend.entity.Customer;
import com.example.posbackend.entity.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {

    public String SAVE_ITEM = "INSERT INTO item (itemId, itemName, price, quantity) VALUES (?, ?, ?, ?)";

    static String UPDATE_ITEM = " UPDATE item SET itemName=?,price=?,quantity=? WHERE itemId=?";

    static String DELETE_ITEM = "DELETE FROM item WHERE itemId=?";

    static String GET_ALL = "SELECT * FROM item";


    public List<Item> getAllItems(Connection connection) {

        List<Item> items = new ArrayList<>();

        try {
            var pstm = connection.prepareStatement(GET_ALL);
            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()){
                Item item = new Item();
                item.setItemId(resultSet.getString("itemId"));
                item.setItemName(resultSet.getString("itemName"));
                item.setPrice(Double.parseDouble(resultSet.getString("price")));
                item.setQuantity(Integer.parseInt(resultSet.getString("quantity")));

                items.add(item);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return items;

    }
    @Override
    public boolean saveItem(Item item, Connection connection) {

        try {
            var pstm = connection.prepareStatement(SAVE_ITEM);
            pstm.setString(1,item.getItemId());
            pstm.setString(2, item.getItemName());
            pstm.setString(3, String.valueOf(item.getPrice()));
            pstm.setString(4, String.valueOf(item.getQuantity()));

            return  pstm.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateItem(Item item, Connection connection) {

        try {
            var pstm = connection.prepareStatement(UPDATE_ITEM);
            pstm.setString(1, item.getItemName());
            pstm.setString(2, String.valueOf(item.getPrice()));
            pstm.setString(3, String.valueOf(item.getQuantity()));
            pstm.setString(4,item.getItemId());

            System.out.println("dao " + item);

            return  pstm.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteItem(String itemId, Connection connection) {

        try {
            var pstm = connection.prepareStatement(DELETE_ITEM);

            pstm.setString(1,itemId);

            return pstm.executeUpdate() > 0 ;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
