package br.com.ftech.webcom.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexaoMysql {
	
	public Connection getConnection() {
        try {
            return DriverManager.getConnection(
          "jdbc:mysql://localhost/webcom", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
