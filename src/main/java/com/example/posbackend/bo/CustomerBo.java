package com.example.posbackend.bo;

import com.example.posbackend.dto.CustomerDTO;

import java.sql.Connection;
import java.util.List;

public interface CustomerBo {

    List<CustomerDTO> getAllCustomers(Connection connection);

    boolean deleteCustomer(String customerId, Connection connection);

    boolean saveCustomer(CustomerDTO customerDTO, Connection connection);

    boolean updateCustomer(CustomerDTO customerDTO, Connection connection);

}
