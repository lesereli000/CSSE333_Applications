package main;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class Main {

	public static void main(String[] args) {
		String url = "jdbc:sqlserver://${dbServer};databaseName=${dbName};"
				+ "user=${user};password={${pass}};encrypt=false";
		
		String fullUrl = url.replace("${dbServer}", "golem.csse.rose-hulman.edu")
							.replace("${dbName}", "FoodDeliverysongz1")
							.replace("${user}", "FoodDeliveryUsersongz1")
							.replace("${pass}", "Password123");
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(fullUrl);
			
			}catch (SQLException e) {
				e.printStackTrace();
						
			}
			finally {
				
				try {
					if(connection != null && connection.isClosed()) connection.close();
				
				}catch (SQLException e) {
					
				}
				
			}

	}

}
