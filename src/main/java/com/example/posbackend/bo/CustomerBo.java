package com.example.posbackend.bo;

import com.example.posbackend.dto.CustomerDTO;

import java.sql.Connection;

public interface CustomerBo {

    boolean deleteCustomer(String customerId, Connection connection);

    boolean saveCustomer(CustomerDTO customerDTO, Connection connection);

    boolean updateCustomer(CustomerDTO customerDTO, Connection connection);

}
