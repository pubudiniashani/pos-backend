package com.example.posbackend.bo.impl;

import com.example.posbackend.bo.CustomerBo;
import com.example.posbackend.dao.CustomerDao;
import com.example.posbackend.dao.impl.CustomerDaoImpl;
import com.example.posbackend.dto.CustomerDTO;
import com.example.posbackend.entity.Customer;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {

    public CustomerDao customerDao = new CustomerDaoImpl();

    @Override
    public List<CustomerDTO> getAllCustomers(Connection connection) {

        List<Customer> customerList = customerDao.getAllCustomers(connection);

        List<CustomerDTO> customerDTOS = new ArrayList<>();

        for (Customer customer:customerList) {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerId(customer.getCustomerId());
            customerDTO.setCustomerName(customer.getCustomerName());
            customerDTO.setAddress(customer.getAddress());
            customerDTO.setContactNumber(customer.getContactNumber());

            customerDTOS.add(customerDTO);
        }

        return customerDTOS;
    }


    @Override
    public boolean  saveCustomer(CustomerDTO customerDTO, Connection connection) {

        Customer customer = new Customer(
        customerDTO.getCustomerId(),
        customerDTO.getCustomerName(),
        customerDTO.getAddress(),
        customerDTO.getContactNumber()
        );

        System.out.println(customer);

        return customerDao.saveCustomer(customer,connection);

    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO, Connection connection) {
        Customer customer = new Customer(
          customerDTO.getCustomerId(),
          customerDTO.getCustomerName(),
          customerDTO.getAddress(),
          customerDTO.getContactNumber()
        );

        System.out.println("boimpl"+ customer);

        return customerDao.updateCustomer(customer,connection);
    }


    @Override
    public boolean deleteCustomer(String customerId, Connection connection) {
        try {
            return customerDao.deleteCustomer(customerId, connection);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete customer with ID: " + customerId, e);
        }
    }

}




