package com.example.posbackend.controller;

import com.example.posbackend.bo.OrderBo;
import com.example.posbackend.bo.impl.OrderBoImpl;
import com.example.posbackend.dto.CustomerDTO;
import com.example.posbackend.dto.OrderDTO;
import jakarta.json.JsonException;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
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

@WebServlet(urlPatterns = "/order")
public class OrderController extends HttpServlet {

    Connection connection;

    private OrderBo orderBo = new OrderBoImpl();

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getContentType().toLowerCase().startsWith("application/json") || req.getContentType() == null) {
            //send error
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        }

        try (var writer = resp.getWriter()) {

            Jsonb jsonb = JsonbBuilder.create();

            OrderDTO orderDTO = jsonb.fromJson(req.getReader(), OrderDTO.class);

            orderBo.saveOrder(orderDTO, connection);
            System.out.println(orderDTO);


        } catch (JsonException e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        System.out.println("Received customerId: " + orderId);

        if (orderId == null || orderId.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Customer ID is required.");
            return;
        }

        try (var writer = resp.getWriter()) {
            boolean isDeleted = orderBo.deleteOrder(orderId, connection);
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

