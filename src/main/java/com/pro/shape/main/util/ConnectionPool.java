package com.pro.shape.main.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.h2.jdbcx.JdbcConnectionPool;



/**
 * H2 Operation
 * @author Dylan
 * @date 2017-3-10
 */
public class ConnectionPool {
	private static ConnectionPool cp = null;
    private JdbcConnectionPool jdbcCP = null;

    private ConnectionPool() {
        String dbPath ="./config/test";
        jdbcCP = JdbcConnectionPool.create("jdbc:h2:" + dbPath, "sa", "");
        jdbcCP.setMaxConnections(50);
    }

    public static ConnectionPool getInstance() {
        if (cp == null) {
            cp = new ConnectionPool();
        }
        return cp;
    }

    public Connection getConnection() throws SQLException {
        return jdbcCP.getConnection();
    }

}
