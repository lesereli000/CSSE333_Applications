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
	
	public void deleteEvent(int event) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call DeleteEvent(?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setInt(2, event);
			
			stmt.execute();
			
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
	
	public void deletePlayer(int player) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call DeletePlayer(?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setInt(2, player);
			
			stmt.execute();
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesful delete");
			} else {
				System.out.println("The Player does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for deletePlayer");
		}
		
		connect.close();
	}
	
	public void deleteTeam(int teamID) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call DeleteTeam(?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setInt(2, teamID);
			
			stmt.execute();
			
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
	
	public void deleteGear(String gear) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call DeleteGear(?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, gear);
			
			stmt.execute();
			
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
	
	public void deleteMatch(int match) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call DeleteMatch(?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setInt(2, match);
			
			stmt.execute();
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesfull delete");
			} else {
				System.out.println("The Event does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for deleteMatch");
		}
		
		connect.close();
	}
	
	public void deleteHeld(int event, int org) {

		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call DeleteHeld(?, ?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setInt(2, event);
			stmt.setInt(3, org);
			
			stmt.execute();
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesful delete");
			} else {
				System.out.println("The Held does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for deleteHeld");
		}
		
		connect.close();
	}
	
	public void deleteHas(int eventID, int matchID) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call DeleteHas(?, ?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setInt(2, eventID);
			stmt.setInt(3, matchID);
			
			stmt.execute();
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesful delete");
			} else {
				System.out.println("The Has does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for deleteHas");
		}
		
		connect.close();
	}

	public void deleteOrg(int org) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call DeleteMatchOrganization(?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setInt(2, org);
			
			stmt.execute();
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesful delete");
			} else {
				System.out.println("The Org does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for deleteOrg");
		}
		
		connect.close();
	}
	
	public void deleteParticipateIn(int player, int match) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call DeleteParticipateIn(?, ?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setInt(2, player);
			stmt.setInt(3, match);
			
			stmt.execute();
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesful delete");
			} else {
				System.out.println("The record does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for deleteParticipateIn");
		}
		
		connect.close();
	}
	
	public void deletePlacedIn(int team, int event) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call DeletePlacedIn(?, ?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setInt(2, team);
			stmt.setInt(3, event);
			
			stmt.execute();
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesful delete");
			} else {
				System.out.println("The record does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for deletePlacedIn");
		}
		
		connect.close();
	}
	
	public void deletePlayedOn(int team, int match) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call DeletePlayedOn(?, ?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setInt(2, team);
			stmt.setInt(3, match);
			
			stmt.execute();
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesful delete");
			} else {
				System.out.println("The record does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for deletePlayedOn");
		}
		
		connect.close();
	}
	
	public void deletePlaysFor(int player, int team) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call DeletePlaysFor(?, ?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setInt(2, player);
			stmt.setInt(3, team);
			
			stmt.execute();
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesful delete");
			} else {
				System.out.println("The record does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for deletePlaysFor");
		}
		
		connect.close();
	}
	
	public void deleteUses(int player, String Gear) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call DeleteUses(?, ?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setInt(2, player);
			stmt.setString(3,  Gear);
			
			stmt.execute();
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesful delete");
			} else {
				System.out.println("The record does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for deleteUses");
		}
		
		connect.close();
	}
}
