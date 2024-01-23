package main;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Set;

public class Populator {

	private Connection con;
	private DataProcessor dp;
	
	public Populator(Connection con) {
		dp = new DataProcessor("src/data/SampleData.csv");
		this.con = con;
	}
	
	public void populateAll() {
//		populatePlayers();
//		populateEvents();
		populateTeams();
	}

	private void populatePlayers() {
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
	
	private void populateEvents() {
		Set<StringArrayWrapper> dataSet = dp.getEventTableInfo();
		
		for(StringArrayWrapper row : dataSet) {
			try {
				CallableStatement stmt = con.prepareCall("{? = call addEvent(?, ?, ?, ?)}");
				stmt.registerOutParameter(1, Types.INTEGER);
				stmt.setString(2, row.getData(0));
				stmt.setString(3, row.getData(1));
				stmt.setString(4, row.getData(2));
				stmt.setString(5, row.getData(3));
				stmt.execute();
				
				int retCode = stmt.getInt(1);
				if (retCode == 0){
					System.out.println("Succesfull add");
				} else if (retCode == 1) {
					System.out.println("Event Name already exists");
				} else {
					System.out.println("Unidentified return code");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Call Failed for " + row.toString());
			}
		}
	}
	
	private void populateTeams() {
		Set<StringArrayWrapper> dataSet = dp.getTeamTableInfo();
		
		for(StringArrayWrapper row : dataSet) {
			try {
				CallableStatement stmt = con.prepareCall("{? = call addTeam(?, ?, ?)}");
				stmt.registerOutParameter(1, Types.INTEGER);
				stmt.setString(2, row.getData(0));
				stmt.setString(3, row.getData(1));
				stmt.setString(4, row.getData(2));
				stmt.execute();
				
				int retCode = stmt.getInt(1);
				if (retCode == 0){
					System.out.println("Succesfull add");
				} else if (retCode == 1) {
					System.out.println("Team Name already exists");
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
