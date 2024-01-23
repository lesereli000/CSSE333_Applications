package main;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		// get connection info from text file
		Scanner serverInfo = null;
		String[] conInfo = new String[4];
		try {
			serverInfo = new Scanner(new File("src/data/serverInfo.txt"));
			
			String line = serverInfo.nextLine();
			conInfo[2] = line.substring(line.indexOf("=") + 1);
			
			line = serverInfo.nextLine();
			conInfo[3] = line.substring(line.indexOf("=") + 1);
			
			line = serverInfo.nextLine();
			conInfo[1] = line.substring(line.indexOf("=") + 1);
			
			line = serverInfo.nextLine();
			conInfo[0] = line.substring(line.indexOf("=") + 1);
			
			serverInfo.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//connect to db
		Connect connect = new Connect(conInfo[0], conInfo[1], conInfo[2], conInfo[3]);
		Connection con = connect.getConnection();
		
		//call the population scripts
		populate(con);
		
		connect.close();
//		TestUI ui = new TestUI();
	}

	private static void populate(Connection con) {
		DataProcessor dp = new DataProcessor("src/data/SampleData.csv");
		
		Set<StringArrayWrapper> dataSet = dp.getPlayerTableInfo();
		
		for(StringArrayWrapper row : dataSet) {
			try {
				CallableStatement stmt = con.prepareCall("{? = call addPlayer(?, ?, ?, ?, ?, ?)}");
				stmt.registerOutParameter(1, Types.INTEGER);
				stmt.setString(2, row.getData(0));
				stmt.setString(3, row.getData(1));
				stmt.setString(4, row.getData(2));
				stmt.setString(5, row.getData(3));
				stmt.setString(6, row.getData(4));
				stmt.setString(7, row.getData(5));
				stmt.execute();
				
				int retCode = stmt.getInt(1);
				if (retCode == 0){
					System.out.println("Succesfull add");
				} else if (retCode == 1) {
					System.out.println("Username already exists");
				} else {
					System.out.println("Unidentified return code");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Call Failed for " + row.toString());
			}
		}
	}
	
	

}
