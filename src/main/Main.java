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
		Populator p = new Populator(con);
		p.populateAll();
		
		// close the connection
		connect.close();
//		TestUI ui = new TestUI();
	}
}
