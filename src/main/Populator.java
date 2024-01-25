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
	private void populateGear() {
		Set<StringArrayWrapper> dataSet = dp.getGearTableInfo();
		
		for(StringArrayWrapper row : dataSet) {
			try {
				CallableStatement stmt = con.prepareCall("{? = call addGear(?, ?, ?, ?)}");
				stmt.registerOutParameter(1, Types.INTEGER);
				stmt.setString(2, row.getData(0));
				stmt.setString(3, row.getData(1));
				stmt.setString(4, row.getData(2));
				stmt.setString(4, row.getData(3));
				
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
	}
	private void populateGearType() {
		Set<StringArrayWrapper> dataSet = dp.getGearTypeTableInfo();
		
		for(StringArrayWrapper row : dataSet) {
			try {
				CallableStatement stmt = con.prepareCall("{? = call addGearType(?, ?)}");
				stmt.registerOutParameter(1, Types.INTEGER);
				stmt.setString(2, row.getData(0));
				stmt.setString(3, row.getData(1));
				
				stmt.execute();
				
				int retCode = stmt.getInt(1);
				if (retCode == 0){
					System.out.println("Succesfull add");
				} else if (retCode == 1) {
					System.out.println("GearType already exists");
				} else {
					System.out.println("Unidentified return code");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Call Failed for " + row.toString());
			}
		}
		
	}
	private void populateHas() {
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
		
	}
	private void populateHeld() {
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
	}
	private void populateMatch() {
		Set<StringArrayWrapper> dataSet = dp.getMatchTableInfo();
		
		for(StringArrayWrapper row : dataSet) {
			try {
				CallableStatement stmt = con.prepareCall("{? = call addMatch(?, ?, ?)}");
				stmt.registerOutParameter(1, Types.INTEGER);
				stmt.setString(2, row.getData(0));
				stmt.setString(3, row.getData(1));
				stmt.setString(3, row.getData(2));
				
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
		
	}
	private void populateOrg() {
		Set<StringArrayWrapper> dataSet = dp.getOrgTableInfo();
		
		for(StringArrayWrapper row : dataSet) {
			try {
				CallableStatement stmt = con.prepareCall("{? = call addMatchOrganization(?, ?, ?)}");
				stmt.registerOutParameter(1, Types.INTEGER);
				stmt.setString(2, row.getData(0));
				stmt.setString(3, row.getData(1));
				stmt.setString(3, row.getData(2));
				
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
	}
	private void populateParticipateIn() {
		Set<StringArrayWrapper> dataSet = dp.getParticipateInTableInfo();
		
		for(StringArrayWrapper row : dataSet) {
			try {
				CallableStatement stmt = con.prepareCall("{? = call addParticipateIn(?, ?, ?)}");
				stmt.registerOutParameter(1, Types.INTEGER);
				stmt.setString(2, row.getData(0));
				stmt.setString(3, row.getData(1));
				stmt.setString(3, row.getData(2));
				
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
		
	}
	private void populatePlacedIn() {
		Set<StringArrayWrapper> dataSet = dp.getPlacedInTableInfo();
		
		for(StringArrayWrapper row : dataSet) {
			try {
				CallableStatement stmt = con.prepareCall("{? = call addPlacedIn(?, ?, ?)}");
				stmt.registerOutParameter(1, Types.INTEGER);
				stmt.setString(2, row.getData(0));
				stmt.setString(3, row.getData(1));
				stmt.setString(3, row.getData(2));
				
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
	}
	private void populatePlayedOn() {
		Set<StringArrayWrapper> dataSet = dp.getPlayedOnTableInfo();
		
		for(StringArrayWrapper row : dataSet) {
			try {
				CallableStatement stmt = con.prepareCall("{? = call addPlayedOn(?, ?)}");
				stmt.registerOutParameter(1, Types.INTEGER);
				stmt.setString(2, row.getData(0));
				stmt.setString(3, row.getData(1));
				
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
	}
	private void populatePlayerRole() {
Set<StringArrayWrapper> dataSet = dp.getPlayerRoleTableInfo();
		
		for(StringArrayWrapper row : dataSet) {
			try {
				CallableStatement stmt = con.prepareCall("{? = call addPlayerRole(?, ?)}");
				stmt.registerOutParameter(1, Types.INTEGER);
				stmt.setString(2, row.getData(0));
				stmt.setString(3, row.getData(1));
				
				
				stmt.execute();
				
				int retCode = stmt.getInt(1);
				if (retCode == 0){
					System.out.println("Succesfull add");
				} else if (retCode == 1) {
					System.out.println("PlayerRole already exists");
				} else {
					System.out.println("Unidentified return code");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Call Failed for " + row.toString());
			}
		}
	}
	private void populatePlaysFor() {
		Set<StringArrayWrapper> dataSet = dp.getPlaysForTableInfo();
		
		for(StringArrayWrapper row : dataSet) {
			try {
				CallableStatement stmt = con.prepareCall("{? = call addPlayersFor(?, ?)}");
				stmt.registerOutParameter(1, Types.INTEGER);
				stmt.setString(2, row.getData(0));
				stmt.setString(3, row.getData(1));
				
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
	}
	private void populateRole() {
		Set<StringArrayWrapper> dataSet = dp.getRoleTableInfo();
		
		for(StringArrayWrapper row : dataSet) {
			try {
				CallableStatement stmt = con.prepareCall("{? = call addRole(?)}");
				stmt.registerOutParameter(1, Types.INTEGER);
				stmt.setString(2, row.getData(0));

				
				stmt.execute();
				
				int retCode = stmt.getInt(1);
				if (retCode == 0){
					System.out.println("Succesfull add");
				} else if (retCode == 1) {
					System.out.println("Role already exists");
				} else {
					System.out.println("Unidentified return code");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Call Failed for " + row.toString());
			}
		}
	}
	private void populateType() {
		Set<StringArrayWrapper> dataSet = dp.getTypeTableInfo();
		
		for(StringArrayWrapper row : dataSet) {
			try {
				CallableStatement stmt = con.prepareCall("{? = call addType(?)}");
				stmt.registerOutParameter(1, Types.INTEGER);
				stmt.setString(2, row.getData(0));

				stmt.execute();
				
				int retCode = stmt.getInt(1);
				if (retCode == 0){
					System.out.println("Succesfull add");
				} else if (retCode == 1) {
					System.out.println("Type already exists");
				} else {
					System.out.println("Unidentified return code");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Call Failed for " + row.toString());
			}
		}
	}
	private void populateUses() {
Set<StringArrayWrapper> dataSet = dp.getUsesTableInfo();
		
		for(StringArrayWrapper row : dataSet) {
			try {
				CallableStatement stmt = con.prepareCall("{? = call addUses(?, ?, ?)}");
				stmt.registerOutParameter(1, Types.INTEGER);
				stmt.setString(2, row.getData(0));
				stmt.setString(3, row.getData(1));
				stmt.setString(3, row.getData(2));
				
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
	}
	
	
}
