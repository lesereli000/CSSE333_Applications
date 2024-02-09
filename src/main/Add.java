package main;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class Add {
	private Connect connect;
	
	public Add(Connect connect) {
		this.connect = connect;
	}

	
	public void addEvent(String onlineLiveAddr, String Location, String gameName, String EventName) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call AddEvent(?,?,?,?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, onlineLiveAddr);
			stmt.setString(2, Location);
			stmt.setString(2, gameName);
			stmt.setString(2, EventName);
			stmt.execute();
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesfull added");
			} else {
				System.out.println("The EventName already exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for addEvent");
		}
		
		connect.close();
	}
	
	public void addPlayer(String nation, String playerName, String username, 
			String DOB, String experience, String role) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call AddPlayer(?,?,?,?,?,?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, nation);
			stmt.setString(3, playerName);
			stmt.setString(4, username);
			stmt.setString(5, DOB);
			stmt.setString(6, experience);
			stmt.setString(7, role);
			
			stmt.execute();
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesful add player");
			} else {
				System.out.println("The username already exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for addPlayer");
		}
		
		connect.close();
	}
	
	public void addTeam(String teamName, String Sponser, String dateFound) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call AddTeam(?,?,?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2,teamName);
			stmt.setString(3,Sponser);
			stmt.setString(4,dateFound);
			stmt.execute();
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesfull add team");
			} else {
				System.out.println("The TeamName already exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for addTeam");
		}
		
		connect.close();
	}
	
	public void addGear(String modelNumber, String manf, String startingPrice, String link, String type) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call AddGear(?,?,?,?,?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, modelNumber);
			stmt.setString(3, manf);
			stmt.setString(4, startingPrice);
			stmt.setString(5, link);
			stmt.setString(6, type);
			stmt.execute();
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesfull add");
			} else {
				System.out.println("The Gear modelNumber already exists");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for addGear");
		}
		
		connect.close();
	}
	
	public void addMatch(String DateAndTime, String score, String watchingHours) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call AddMatch(?,?,?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, DateAndTime);
			stmt.setString(3, score);
			stmt.setString(4, watchingHours);
			stmt.execute();
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesfull add");
			} else {
				System.out.println("The Match already exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for addMatch");
		}
		
		connect.close();
	}
	
	public void addHeld(String event, String org) {

		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call AddHeld(?, ?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, event);
			stmt.setString(3, org);
			
			stmt.execute();
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesful add");
			} else {
				System.out.println("The event or organization does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for addHeld");
		}
		
		connect.close();
	}
	
	public void addHas(String event, String match) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call AddHas(?, ?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, event);
			stmt.setString(3, match);
			
			stmt.execute();
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesful add");
			} else {
				System.out.println("The event or match does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for addHas");
		}
		
		connect.close();
	}
	
	public void addOrg(String Contact, String Sponser, String org) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call AddMatchOrganization(?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, Contact);
			stmt.setString(3, Sponser);
			stmt.setString(2, org);
			
			stmt.execute();
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesful add");
			} else {
				System.out.println("The Organization name already exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for addOrg");
		}
		
		connect.close();
	}
	
	public void addParticipateIn(String player, String match, String Stats) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call AddParticipateIn(?,?,?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, player);
			stmt.setString(3, match);
			stmt.setString(4, Stats);
			
			stmt.execute();
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesful add");
			} else {
				System.out.println("The player or match does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for addParticipateIn");
		}
		
		connect.close();
	}
	
	public void addPlacedIn(String team, String event, String rank) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call AddPlacedIn(?,?,?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, team);
			stmt.setString(3, event);
			stmt.setString(4, rank);
			
			stmt.execute();
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesful add");
			} else {
				System.out.println("The team or event does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for addPlacedIn");
		}
		
		connect.close();
	}
	
	public void addPlayedOn(String team, String match) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call DeletePlayedOn(?, ?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, team);
			stmt.setString(3, match);
			
			stmt.execute();
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesful add");
			} else {
				System.out.println("The team or match does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for addPlayedOn");
		}
		
		connect.close();
	}
	
	public void addPlaysFor(String player, String team) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call AddPlaysFor(?, ?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, player);
			stmt.setString(3, team);
			
			stmt.execute();
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesful add");
			} else {
				System.out.println("The player or team does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for addPlaysFor");
		}
		
		connect.close();
	}
	
	public void addUses(String player, String Gear, String Since) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call AddUses(?,?,?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, player);
			stmt.setString(3,  Gear);
			stmt.setString(4,  Since);
			stmt.execute();
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesful add");
			} else {
				System.out.println("The player or gear does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for addUses");
		}
		
		connect.close();
	}
}
