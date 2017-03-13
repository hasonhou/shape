package com.pro.shape.main.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.pro.shape.main.model.LoginUsers;
import com.pro.shape.main.util.ConnectionPool;

/**
 * Database operation
 * @author Dylan
 * @date 2017-3-10
 */
public class CommonDao {
	static{
		try {
			//≥ı ºªØLOGINUSER±Ì
			createTable();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void createTable() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            DatabaseMetaData meta = conn.getMetaData();

            ResultSet rsTables = meta.getTables(null, null, "LOGINUSER",
                    new String[] { "TABLE" });
            if (!rsTables.next()) {
                stmt = conn.createStatement();
                stmt.execute("CREATE TABLE LOGINUSER(ID VARCHAR(1024),USERNAME VARCHAR(100),REALNAME VARCHAR(100),PWD VARCHAR(100),PRIMARY KEY(ID))");
            }
            rsTables.close();
        } finally {
            releaseConnection(conn, stmt, null);
        }
    }

    public static void addInfo(LoginUsers user) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();

            stmt = conn
                    .prepareStatement("INSERT INTO LOGINUSER VALUES(?,?,?,?)");
            stmt.setString(1, UUID.randomUUID().toString() );
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getRealname());
            stmt.setString(4, user.getPwd());
            stmt.execute();

        } finally {
            releaseConnection(conn, stmt, null);
        }
    }

    public static boolean isInfoExits(String userName)
            throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn
                    .prepareStatement("SELECT ID FROM LOGINUSER WHERE USERNAME=? ");
            stmt.setString(1, userName);
            rs = stmt.executeQuery();
            return rs.next();
        } finally {
            releaseConnection(conn, stmt, rs);
        }
    }
    
    public static List<LoginUsers> getAllUser()
            throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<LoginUsers> list = new ArrayList<LoginUsers>();
        ResultSet rs = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn
                    .prepareStatement("SELECT * FROM LOGINUSER ");
            rs = stmt.executeQuery();
            while(rs.next()){
            	LoginUsers user = new LoginUsers();
            	user.setId(rs.getString("ID"));
            	user.setUsername(rs.getString("USERNAME"));
            	user.setRealname(rs.getString("REALNAME"));
            	list.add(user);
            }
            return list;
        } finally {
            releaseConnection(conn, stmt, rs);
        }
    }

    private static void releaseConnection(Connection conn, Statement stmt,
            ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
}
