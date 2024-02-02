package main;

import java.io.File;
import java.util.Scanner;

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
		
		//call the population scripts
//		Populator p = new Populator(connect);
//		p.populateAll();
		
		@SuppressWarnings("unused")
		UI ui = new UI(connect, false);
		
	}
}
