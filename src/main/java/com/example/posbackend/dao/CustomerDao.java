package com.example.posbackend.dao;

import com.example.posbackend.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;

public interface CustomerDao {

    CustomerDTO getCustomer(String customerId , Connection connection) throws SQLException;
    String saveCustomer(CustomerDTO customerDTO , Connection connection);
    boolean deleteCustomer(String customerId , Connection connection);
    boolean updateCustomer(String customerId , CustomerDTO customer , Connection connection);

}
