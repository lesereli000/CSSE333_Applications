package main;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Set;

public class Populator {

	private Connect connect;
	private DataProcessor dp;
	
	public Populator(Connect connect) {
		dp = new DataProcessor("src/data/SampleData.csv");
		this.connect = connect;
	}
	
	public void populateAll() {
		populatePlayers();
		populateEvents();
		populateTeams();
		populateGear();
		populateMatch();
		populateOrg();
		populateHas();
		populateHeld();
		populateParticipateIn();
		populatePlayedOn();
		populatePlaysFor();
		populatePlacedIn();
		populateUses();
	}

	private void populatePlayers() {
		Connection con = connect.getConnection();
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
		connect.close();
	}
	
	private void populateEvents() {
		Connection con = connect.getConnection();
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
		connect.close();
	}
	
	private void populateTeams() {
		Connection con = connect.getConnection();
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
		connect.close();
	}
	
	private void populateGear() {
		Connection con = connect.getConnection();
		Set<StringArrayWrapper> dataSet = dp.getGearTableInfo();
		
		for(StringArrayWrapper row : dataSet) {
			try {
				CallableStatement stmt = con.prepareCall("{? = call addGear(?, ?, ?, ?, ?)}");
				stmt.registerOutParameter(1, Types.INTEGER);
				stmt.setString(2, row.getData(0));
				stmt.setString(3, row.getData(1));
				stmt.setString(4, row.getData(2));
				stmt.setString(5, row.getData(3));
				stmt.setString(6, row.getData(4));
				
				stmt.execute();
				
				int retCode = stmt.getInt(1);
				if (retCode == 0){
					System.out.println("Succesfull add");
				} else if (retCode == 1) {
					System.out.println("Gear already exists");
				} else {
					System.out.println("Unidentified return code");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Call Failed for " + row.toString());
			}
		}
		connect.close();
	}
	
	private void populateHas() {
		Connection con = connect.getConnection();
		Set<StringArrayWrapper> dataSet = dp.getHasTableInfo();
		
		for(StringArrayWrapper row : dataSet) {
			try {
				CallableStatement stmt = con.prepareCall("{? = call addHas(?, ?)}");
				stmt.registerOutParameter(1, Types.INTEGER);
				stmt.setString(2, row.getData(0));
				stmt.setString(3, row.getData(1));
				
				stmt.execute();
				
				int retCode = stmt.getInt(1);
				if (retCode == 0){
					System.out.println("Succesfull add");
				} else if (retCode == 1) {
					System.out.println("Has already exists");
				} else {
					System.out.println("Unidentified return code");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Call Failed for " + row.toString());
			}
		}
		connect.close();
	}
	
	private void populateHeld() {
		Connection con = connect.getConnection();
		Set<StringArrayWrapper> dataSet = dp.getHeldTableInfo();
		
		for(StringArrayWrapper row : dataSet) {
			try {
				CallableStatement stmt = con.prepareCall("{? = call addHeld(?, ?)}");
				stmt.registerOutParameter(1, Types.INTEGER);
				stmt.setString(2, row.getData(0));
				stmt.setString(3, row.getData(1));
				
				stmt.execute();
				
				int retCode = stmt.getInt(1);
				if (retCode == 0){
					System.out.println("Succesfull add");
				} else if (retCode == 1) {
					System.out.println("Held already exists");
				} else {
					System.out.println("Unidentified return code");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Call Failed for " + row.toString());
			}
		}
		connect.close();
	}
	
	private void populateMatch() {
		Connection con = connect.getConnection();
		Set<StringArrayWrapper> dataSet = dp.getMatchTableInfo();
		
		for(StringArrayWrapper row : dataSet) {
			try {
				CallableStatement stmt = con.prepareCall("{? = call addMatch(?, ?, ?)}");
				stmt.registerOutParameter(1, Types.INTEGER);
				stmt.setString(2, row.getData(0));
				stmt.setString(3, row.getData(1));
				stmt.setInt(4, Integer.parseInt(row.getData(2)));
				
				stmt.execute();
				
				int retCode = stmt.getInt(1);
				if (retCode == 0){
					System.out.println("Succesfull add");
				} else if (retCode == 1) {
					System.out.println("Match already exists");
				} else {
					System.out.println("Unidentified return code");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Call Failed for " + row.toString());
			}
		}
		connect.close();
	}
	
	private void populateOrg() {
		Connection con = connect.getConnection();
		Set<StringArrayWrapper> dataSet = dp.getOrgTableInfo();
		
		for(StringArrayWrapper row : dataSet) {
			try {
				CallableStatement stmt = con.prepareCall("{? = call addMatchOrganization(?, ?, ?)}");
				stmt.registerOutParameter(1, Types.INTEGER);
				stmt.setString(2, row.getData(0));
				stmt.setString(3, row.getData(1));
				stmt.setString(4, row.getData(2));
				
				stmt.execute();
				
				int retCode = stmt.getInt(1);
				if (retCode == 0){
					System.out.println("Succesfull add");
				} else if (retCode == 1) {
					System.out.println("MatchOrganization already exists");
				} else {
					System.out.println("Unidentified return code");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Call Failed for " + row.toString());
			}
		}
		connect.close();
	}
	
	private void populateParticipateIn() {
		Connection con = connect.getConnection();
		Set<StringArrayWrapper> dataSet = dp.getParticipateInTableInfo();
		
		for(StringArrayWrapper row : dataSet) {
			try {
				CallableStatement stmt = con.prepareCall("{? = call addParticipateIn(?, ?, ?)}");
				stmt.registerOutParameter(1, Types.INTEGER);
				stmt.setString(2, row.getData(0));
				stmt.setString(3, row.getData(1));
				stmt.setString(4, row.getData(2));
				
				stmt.execute();
				
				int retCode = stmt.getInt(1);
				if (retCode == 0){
					System.out.println("Succesfull add");
				} else if (retCode == 1) {
					System.out.println("ParticipateIn already exists");
				} else {
					System.out.println("Unidentified return code");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Call Failed for " + row.toString());
			}
		}
		connect.close();
	}
	
	private void populatePlacedIn() {
		Connection con = connect.getConnection();
		Set<StringArrayWrapper> dataSet = dp.getPlacedInTableInfo();
		
		for(StringArrayWrapper row : dataSet) {
			try {
				CallableStatement stmt = con.prepareCall("{? = call addPlacedIn(?, ?, ?)}");
				stmt.registerOutParameter(1, Types.INTEGER);
				stmt.setString(2, row.getData(1));
				stmt.setString(3, row.getData(0));
				stmt.setString(4, row.getData(2));
				
				stmt.execute();
				
				int retCode = stmt.getInt(1);
				if (retCode == 0){
					System.out.println("Succesfull add");
				} else if (retCode == 1) {
					System.out.println("PlacedIn already exists");
				} else {
					System.out.println("Unidentified return code");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Call Failed for " + row.toString());
			}
		}
		connect.close();
	}
	
	private void populatePlayedOn() {
		Connection con = connect.getConnection();
		Set<StringArrayWrapper> dataSet = dp.getPlayedOnTableInfo();
		
		for(StringArrayWrapper row : dataSet) {
			try {
				CallableStatement stmt = con.prepareCall("{? = call addPlayedOn(?, ?)}");
				stmt.registerOutParameter(1, Types.INTEGER);
				stmt.setString(2, row.getData(1));
				stmt.setString(3, row.getData(0));
				
				stmt.execute();
				
				int retCode = stmt.getInt(1);
				if (retCode == 0){
					System.out.println("Succesfull add");
				} else if (retCode == 1) {
					System.out.println("PlayedOn already exists");
				} else {
					System.out.println("Unidentified return code");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Call Failed for " + row.toString());
			}
		}
		connect.close();
	}
	
	private void populatePlaysFor() {
		Connection con = connect.getConnection();
		Set<StringArrayWrapper> dataSet = dp.getPlaysForTableInfo();
		
		for(StringArrayWrapper row : dataSet) {
			try {
				CallableStatement stmt = con.prepareCall("{? = call addPlaysFor(?, ?)}");
				stmt.registerOutParameter(1, Types.INTEGER);
				stmt.setString(2, row.getData(1));
				stmt.setString(3, row.getData(0));
				
				stmt.execute();
				
				int retCode = stmt.getInt(1);
				if (retCode == 0){
					System.out.println("Succesfull add");
				} else if (retCode == 1) {
					System.out.println("PlayersFor already exists");
				} else {
					System.out.println("Unidentified return code");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Call Failed for " + row.toString());
			}
		}
		connect.close();
	}
	
	private void populateUses() {
		Connection con = connect.getConnection();
		Set<StringArrayWrapper> dataSet = dp.getUsesTableInfo();
		
		for(StringArrayWrapper row : dataSet) {
			try {
				CallableStatement stmt = con.prepareCall("{? = call addUses(?, ?, ?)}");
				stmt.registerOutParameter(1, Types.INTEGER);
				stmt.setString(2, row.getData(1));
				stmt.setString(3, row.getData(0));
				stmt.setString(4, row.getData(2));
				
				stmt.execute();
				
				int retCode = stmt.getInt(1);
				if (retCode == 0){
					System.out.println("Succesfull add");
				} else if (retCode == 1) {
					System.out.println("Uses already exists");
				} else {
					System.out.println("Unidentified return code");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Call Failed for " + row.toString());
			}
		}
		connect.close();
	}
}
