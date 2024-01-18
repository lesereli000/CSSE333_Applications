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

	private String url = "jdbc:sqlserver://${dbServer};databaseName=${dbName};" + "user=${user};password={${pass}};";

	private String fullUrl = url.replace("${dbServer}", "golem.csse.rose-hulman.edu")
			.replace("${dbName}", "E-SportsDataTracking").replace("${user}", "CSSE330esportDataTracking")
			.replace("${pass}", "esportTracking666!") + "encrypt=true;trustServerCertificate=true";

	public Connection getConnection() {
		return this.connection;
	}

	public void connect() {

		try {
			connection = DriverManager.getConnection(fullUrl);
			System.out.println("Connection established");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			if (this.connection != null && !this.connection.isClosed()) {
				this.connection.close();
				System.out.println("connection closed.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
