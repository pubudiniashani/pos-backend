package com.example.posbackend.controller;

import com.example.posbackend.bo.CustomerBo;
import com.example.posbackend.bo.impl.CustomerBoImpl;
import com.example.posbackend.dto.CustomerDTO;
import jakarta.json.JsonException;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet(urlPatterns = "/customer")
public class CustomerFormController extends HttpServlet {

    Connection connection;
    private CustomerBo customerBo = new CustomerBoImpl();

    @Override
    public void init() throws ServletException {


        try {
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/pos");
            this.connection = pool.getConnection();

            System.out.println("database connection established");

        } catch (NamingException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getContentType().toLowerCase().startsWith("application/json") || req.getContentType() == null) {
            //send error
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        }

        try (var writer = resp.getWriter()) {

            Jsonb jsonb = JsonbBuilder.create();

            CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);

            customerBo.saveCustomer(customerDTO, connection);
            System.out.println(customerDTO);


        } catch (JsonException e) {
            throw new RuntimeException(e);

        }


    }


   /* @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String id = req.getParameter("customerId");
        System.out.println(id);
        resp.setContentType("application/json");

        try (var writer = resp.getWriter()) {

            Jsonb jsonb = JsonbBuilder.create();
            CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);


            boolean isUpdated = customerBo.updateCustomer(customerDTO,connection);
            System.out.println(isUpdated);
            if (isUpdated) {
                writer.write("Customer Updated Successfully");
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                writer.write("Failed to Update Customer");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        } catch (JsonbException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JSON data");
        }
    }*/

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        try (var writer = resp.getWriter()) {

            Jsonb jsonb = JsonbBuilder.create();
            CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);

            boolean isUpdated = customerBo.updateCustomer(customerDTO, connection);

            if (isUpdated) {
                writer.write("Customer Updated Successfully");
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                writer.write("Failed to Update Customer");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

        } catch (JsonbException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JSON data");
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerId = req.getParameter("customerId");
        System.out.println("Received customerId: " + customerId);

        if (customerId == null || customerId.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Customer ID is required.");
            return;
        }

        try (var writer = resp.getWriter()) {
            boolean isDeleted = customerBo.deleteCustomer(customerId, connection);
            System.out.println(isDeleted);
            //System.out.println("Customer deleted: " + isDeleted);
            if (isDeleted) {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Customer not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }

    }
}



