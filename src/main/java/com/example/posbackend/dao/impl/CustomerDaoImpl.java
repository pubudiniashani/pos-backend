package com.example.posbackend.dao.impl;

import com.example.posbackend.dao.CustomerDao;
import com.example.posbackend.dto.CustomerDTO;
import com.example.posbackend.entity.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    //static  String GET_CUSTOMER ="SELECT * FROM customer WHERE customerId = ?";

    static String GET_ALL = "SELECT * FROM customer";

    public String SAVE_CUSTOMER = "INSERT INTO customer (customerId, customerName, address, contactNumber) VALUES (?, ?, ?, ?)";

    static String UPDATE_CUSTOMER = " UPDATE customer SET customerName=?,address=?,contactNumber=? WHERE customerId=?";

    static String DELETE_CUSTOMER = "DELETE FROM customer WHERE customerId=?";


    @Override
    public List<Customer> getAllCustomers(Connection connection) {

        List<Customer> customers = new ArrayList<>();

        try {
            var pstm = connection.prepareStatement(GET_ALL);
            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()){
                Customer customer = new Customer();
                customer.setCustomerId(resultSet.getString("customerId"));
                customer.setCustomerName(resultSet.getString("customerName"));
                customer.setAddress(resultSet.getString("address"));
                customer.setContactNumber(resultSet.getString("contactNumber"));

                customers.add(customer);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return customers;

    }

    /*@Override
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


    }*/

    @Override
    public boolean saveCustomer(Customer customer, Connection connection) {
        try {
            var pstm = connection.prepareStatement(SAVE_CUSTOMER);

            pstm.setString(1,customer.getCustomerId());
            pstm.setString(2,customer.getCustomerName());
            pstm.setString(3,customer.getAddress());
            pstm.setString(4,customer.getContactNumber());

          return  pstm.executeUpdate() > 0;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteCustomer(String customerId, Connection connection) {

        try {
            var pstm = connection.prepareStatement(DELETE_CUSTOMER);

            pstm.setString(1,customerId);

            return pstm.executeUpdate() > 0 ;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public boolean updateCustomer(Customer customer, Connection connection) {
        try {
            var pstm = connection.prepareStatement(UPDATE_CUSTOMER);

            pstm.setString(1,customer.getCustomerName());
            pstm.setString(2,customer.getAddress());
            pstm.setString(3,customer.getContactNumber());
            pstm.setString(4,customer.getCustomerId());

            System.out.println("dao " + customer);

            return pstm.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
