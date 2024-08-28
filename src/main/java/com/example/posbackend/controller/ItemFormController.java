package com.example.posbackend.controller;

import com.example.posbackend.bo.ItemBo;
import com.example.posbackend.bo.impl.ItemBoImpl;
import com.example.posbackend.dto.CustomerDTO;
import com.example.posbackend.dto.ItemDTO;
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
import java.util.List;

@WebServlet(urlPatterns = "/item")
public class ItemFormController extends HttpServlet {

    Connection connection;

    private ItemBo itemBo = new ItemBoImpl();


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
        try (var writer = resp.getWriter()) {

            Jsonb jsonb = JsonbBuilder.create();

            List<ItemDTO> itemDTOS = itemBo.getAllItems(connection);

            String jsonResponse = jsonb.toJson(itemDTOS);
            writer.write(jsonResponse);
            resp.setStatus(HttpServletResponse.SC_OK);


        } catch (JsonException e) {
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

            ItemDTO itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);

            itemBo.saveItem(itemDTO, connection);
            System.out.println(itemDTO);


        } catch (JsonException e) {
            throw new RuntimeException(e);

        }



    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        try (var writer = resp.getWriter()) {

            Jsonb jsonb = JsonbBuilder.create();

            ItemDTO  itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);

            boolean isUpdated = itemBo.updateItem(itemDTO, connection);
            System.out.println(isUpdated);

            if (isUpdated) {
                writer.write("Customer Updated Successfully");
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                writer.write("Failed to Update Customer");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

        } catch (JsonbException e) {
            e.printStackTrace(); // Log the exception
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JSON data");
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemId = req.getParameter("itemId");
        System.out.println("Received itemId: " + itemId);

        if (itemId == null || itemId.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Customer ID is required.");
            return;
        }

        try (var writer = resp.getWriter()) {
            boolean isDeleted = itemBo.deleteItem(itemId, connection);
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
