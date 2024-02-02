package main;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Set;

public class Delete {
	private Connect connect;
	private DataProcessor dp;
	
	public Delete(Connect connect) {
		this.connect = connect;
	}
	
	public void deleteEvent(String ID) {
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
	
	public void deletePlayer(String ID) {
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
	
	public void deleteTeam(String ID) {
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
	
	public void deleteGear(String ID) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call DeleteGear(?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, ID);
			
			int retCode = stmt.getInt(1);
			if (retCode == 0){
				System.out.println("Succesfull delete");
			} else {
				System.out.println("The Gear does not exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Call Failed for deleteEvent");
		}
		
		connect.close();
	}
	
	public void deleteMatch(String ID) {
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
	
	public void deleteHeld(String ID) {

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
	
	public void deleteHas(String ID) {
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

	public void deleteOrg(String ID) {
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
	
	public void deleteParticipateIn(String ID) {
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
	
	public void deletePlacedIn(String ID) {
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
	
	public void deletePlayedOn(String ID) {
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
	
	public void deletePlaysFor(String ID) {
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
	
	public void deleteUses(String Player, String Gear) {
		Connection con = connect.getConnection();
		try {
			CallableStatement stmt = con.prepareCall("{? = call DeleteEvent(?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, Player);
			
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
}
