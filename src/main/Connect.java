package main;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class Connect {

	private String dbServer;
	private String dbName;
	private String user;
	private String pass;



	public void connect() {
		// Construct the connection URL

		String url = "jdbc:sqlserver://${dbServer};databaseName=${dbName};"
				+ "user=${user};password={${pass}};encrypt=false";
		String fullUrl = url.replace("${dbServer}", "golem.csse.rose-hulman.edu")
				.replace("${dbName}", "E-SportsDataTracking")
				.replace("${user}", "CSSE330esportDataTracking")
				.replace("${pass}", "esportTracking666!");

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null && !connection.isClosed())
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
