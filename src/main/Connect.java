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
	}
	
	public Connection getConnection() {
		try {
			if(this.con == null || this.con.isClosed()) {
				con = this.connect(fullUrl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return this.con;
	}

	private Connection connect(String fullURL) {

		try {
			Connection connection = DriverManager.getConnection(fullURL);
			System.out.println("Connection established.");
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
				System.out.println("Connection closed.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
