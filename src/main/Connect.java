package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	private Connection con = null;

	private String url = "jdbc:sqlserver://${dbServer};databaseName=${dbName};" + "user=${user};password={${pass}};encrypt=false";
	private String fullUrl;

	public Connect(String serverName, String dbName, String userName, String userPass) {
		fullUrl = url.replace("${dbServer}", serverName).replace("${dbName}", dbName).replace("${user}", userName).replace("${pass}", userPass);
		this.con = connect(fullUrl);
	}
	
	public Connection getConnection() {
		if(this.con != null) {
			return this.con;
		}
		return this.connect(fullUrl);
	}

	private Connection connect(String fullURL) {

		try {
			Connection connection = DriverManager.getConnection(fullURL);
			System.out.println("Connection established");
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void close() {
		try {
			if (this.con != null && !this.con.isClosed()) {
				this.con.close();
				System.out.println("connection closed.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
