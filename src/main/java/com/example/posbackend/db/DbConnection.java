package com.example.posbackend.db;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection dbConnection;
    private Connection connection;

    /*private DbConnection() throws NamingException, SQLException {
        var ctx = new InitialContext();
        DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/pos");
        connection = pool.getConnection();
    }

    public static DbConnection getInstance() throws SQLException, NamingException {
        if (dbConnection == null) {
            dbConnection = new DbConnection();
        }
        return dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }*/


}
