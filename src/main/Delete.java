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
	
}
