package main;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class Admin {

	private Connect connect;
	
	public Admin(Connect connect) {
		this.connect = connect;
	}
	
	public Object[][] getPermsTableData(){
		Connection connection = connect.getConnection();
        Object[][] finalRet = null;
        try {
	        CallableStatement stmt = connection.prepareCall("{? = call getUserPerms(?)}");
	        stmt.registerOutParameter(1, Types.INTEGER);
	        stmt.setString(2, null);
	
	        ResultSet results = stmt.executeQuery();
	        
	        List<Object[]> rows = new ArrayList<Object[]>();

	        while(results.next()) {
	        	String perms = results.getString(2);
	        	rows.add(new Object[] {false, results.getString(1), perms.contains("r"), perms.contains("w"), perms.contains("d"), perms.contains("a")});
	        }
	        
	        finalRet = listToArray(rows);
	        
        } catch (Exception e) {
        	e.printStackTrace();
        }
        connect.close();
        return finalRet;
	}
	
	private Object[][] listToArray (List<Object[]> list) {
    	int rows = list.size();
    	
    	if(rows < 1) {
    		return null;
    	}
    	
    	Object[][] output = new Object[rows][list.get(0).length];
    	for(int i = 0; i < rows; i++) {
    		output[i] = list.get(i);
    	}
    	return output;
    }
	
	public void updatePermsTable(String username, String newPerms) {
		Connection connection = connect.getConnection();
        try {
	        CallableStatement stmt = connection.prepareCall("{? = call UpdateUserPerms(?, ?)}");
	        stmt.registerOutParameter(1, Types.INTEGER);
	        stmt.setString(2, username);
	        stmt.setString(3, newPerms);
	        
	        stmt.execute();
	        
        } catch (Exception e) {
        	e.printStackTrace();
        }
        connect.close();
	}
	
	public void removeUser(String username) {
		Connection connection = connect.getConnection();
        try {
	        CallableStatement stmt = connection.prepareCall("{? = call DeleteUser(?)}");
	        stmt.registerOutParameter(1, Types.INTEGER);
	        stmt.setString(2, username);
	        
	        stmt.execute();
	        
        } catch (Exception e) {
        	e.printStackTrace();
        }
        connect.close();
	}
	
}
