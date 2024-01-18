package main;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class Connect {
	private Connection connection = null;

	private String url = "jdbc:sqlserver://${dbServer};databaseName=${dbName};"
			+ "user=${user};password={${pass}};encrypt=false";
	
	private String fullUrl = url.replace("${dbServer}", "golem.csse.rose-hulman.edu")
			.replace("${dbName}", "E-SportsDataTracking")
			.replace("${user}", "CSSE330esportDataTracking")
			.replace("${pass}", "esportTracking666!");

	public void connect() {
		
		try {
			connection = DriverManager.getConnection(fullUrl);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return this.connection;
	}
	
	public void closeconnection() {
		try {
	        if (this.connection != null && !this.connection.isClosed()) {
	            this.connection.close();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
}
