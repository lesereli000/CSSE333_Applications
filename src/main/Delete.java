package main;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;


public class Delete {
	private Connect connect;
	
	public Delete(Connect connect) {
		this.connect = connect;
	}
	
	private void deleteEvent(String ID) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call DeleteEvent(?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, ID);
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesfull delete");
			} else {
				System.out.println("The Event does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for deleteEvent");
		}
		
		connect.close();
	}
	
	private void deleteGear(String modelNumber) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call DeleteGear(?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, modelNumber);
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesfull delete");
			} else {
				System.out.println("The Gear does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for deleteGear");
		}
		
		connect.close();
	}
	
	private void deleteHas(String EventID, String MatchID) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call DeleteHas(?,?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, EventID);
			stmt.setString(3, MatchID);
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesfull delete");
			} else {
				System.out.println("The item in [Has] does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for deleteHas");
		}
	}
	
	private void deleteHeld(String MatchOrganizationID, String EventID) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call DeleteHeld(?,?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, MatchOrganizationID);
			stmt.setString(3, EventID);
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesfull delete");
			} else {
				System.out.println("The item in [Held] does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for deleteHeld");
		}
		
		connect.close();
	}
	

	private void deleteLogin(String username) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call DeleteLogin(?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, username);
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesfull delete");
			} else {
				System.out.println("The user does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for deleteLogin");
		}
		
		connect.close();
	}
	

	private void deleteMatch(String ID) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call DeleteMatch(?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, ID);
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesfull delete");
			} else {
				System.out.println("The Match does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for deleteMatch");
		}
		
		connect.close();
	}
	

	private void deleteMatchOrganization(String ID) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call DeleteMatchOrganization(?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, ID);
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesfull delete");
			} else {
				System.out.println("The MatchOrganization does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for deleteMatchOrganization");
		}
		
		connect.close();
	}
	

	private void deleteParticipateIn(String PlayerID, String MatchID) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call DeleteParticipateIn(?,?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, PlayerID);
			stmt.setString(3, MatchID);
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesfull delete");
			} else {
				System.out.println("The item in the [DeletePartipateIn] does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for deleteParticipateIn");
		}
		
		connect.close();
	}
	

	private void deletePlacedIn(String TeamID, String EventID) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call DeletePlacedIn(?,?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, TeamID);
			stmt.setString(3, EventID);
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesfull delete");
			} else {
				System.out.println("The item in [PlacedIn] does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for deletePlacedIn");
		}
		
		connect.close();
	}
	

	private void deletePlayedOn(String TeamID, String MatchID) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call DeletePlayedOn(?,?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, TeamID);
			stmt.setString(3, MatchID);
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesfull delete");
			} else {
				System.out.println("The item in [PlayedOn] does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for deletePlayedOn");
		}
		
		connect.close();
	}
	

	private void deletePlayer(String ID) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call DeletePlayer(?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, ID);
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesfull delete");
			} else {
				System.out.println("The Player does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for deletePlayer");
		}
		
		connect.close();
	}
	

	private void deletePlaysFor(String PlayerID, String TeamID) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call DeletePlaysFor(?,?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, PlayerID);
			stmt.setString(3, TeamID);
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesfull delete");
			} else {
				System.out.println("The item in [PlaysFor] does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for deleteEvent");
		}
		
		connect.close();
	}
	
	private void deleteUses(String PlayerID, String Gear) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call DeleteUses(?,?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, PlayerID);
			stmt.setString(3, Gear);
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesfull delete");
			} else {
				System.out.println("The item in [Uses] does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for deleteUses");
		}
		
		connect.close();
	}

	private void deleteTeam(String ID) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call DeleteTeam(?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, ID);
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesfull delete");
			} else {
				System.out.println("The Team does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for deleteTeam");
		}
		
		connect.close();
	}
}
