package com.example.posbackend.dao;

import com.example.posbackend.dto.CustomerDTO;
import com.example.posbackend.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CustomerDao {

    List<Customer> getAllCustomers(Connection connection);
    boolean saveCustomer(Customer customer , Connection connection);
    boolean deleteCustomer(String customerId , Connection connection);
    boolean updateCustomer(Customer customer , Connection connection);

}
