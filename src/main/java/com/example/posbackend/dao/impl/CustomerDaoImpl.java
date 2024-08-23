package com.example.posbackend.dao.impl;

import com.example.posbackend.dao.CustomerDao;
import com.example.posbackend.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class CustomerDaoImpl implements CustomerDao {

    static  String GET_CUSTOMER ="SELECT * FROM customer WHERE customerId = ?";

    public String SAVE_CUSTOMER = "INSERT INTO customer (customerId, customerName, address, contactNumber) VALUES (?, ?, ?, ?)";

    static String UPDATE_CUSTOMER = " UPDATE customer SET customerName=?,address=?,contactNumber=? WHERE customerId=?";

    static String DELETE_CUSTOMER = "DELETE FROM customer WHERE customerId=?";


    @Override
    public CustomerDTO getCustomer(String customerId, Connection connection) throws SQLException {

        var customerDTO = new CustomerDTO();

        try {
            var ps = connection.prepareStatement(GET_CUSTOMER);
            ps.setString(1, customerId);
            var resultSet = ps.executeQuery();
            while (resultSet.next()) {
                customerDTO.setCustomerId(resultSet.getString("customerId"));
                customerDTO.setCustomerName(resultSet.getString("customerName"));
                customerDTO.setAddress(resultSet.getString("address"));
                customerDTO.setContactNumber(resultSet.getString("contactNumber"));

            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return customerDTO;


    }

    @Override
    public String saveCustomer(CustomerDTO customerDTO, Connection connection) {
        return null;
    }

    @Override
    public boolean deleteCustomer(String customerId, Connection connection) {
        return false;
    }

    @Override
    public boolean updateCustomer(String customerId, CustomerDTO customer, Connection connection) {
        return false;
    }
}
