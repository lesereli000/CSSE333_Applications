package main;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class Select {
    private Connect connect;

    public Select(Connect connect) {
        this.connect = connect;
    }
    
    private Object[][] acquireResults(ResultSet rs) {
    	try {
    		// get rows and columns
	    	ResultSetMetaData rsMetaData = rs.getMetaData();
	    	int columns = rsMetaData.getColumnCount();
	    	List<Object[]> results = new ArrayList<Object[]>();
	    	
	    	// loop through and add to output
	        while (rs.next()) {
	        	Object[] row = new Object[columns];
	        	for(int i = 1; i <= columns; i++) {
	        		row[i - 1] = rs.getObject(i);
	        	}
	        	results.add(row);
	        }
	        return listToArray(results);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
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
    
    public Object[][] selectEvent(String eventName, String gameName, String Location, String OnlineLiveAddress, String ID) {
        Connection connection = connect.getConnection();
        Object[][] finalRet = null;
        try {
	        CallableStatement stmt = connection.prepareCall("{call SelectEvent(?, ?, ?, ?, ?)}");
	        if (eventName.isEmpty()) eventName = null;
	        if (gameName.isEmpty()) gameName = null;
	        if (Location.isEmpty()) Location = null;
	        if (OnlineLiveAddress.isEmpty()) OnlineLiveAddress = null;
	        if (ID.isEmpty()) ID = null;
	        stmt.setString(1, eventName);
	        stmt.setString(2, gameName);
	        stmt.setString(3, Location);
	        stmt.setString(4, OnlineLiveAddress);
	        stmt.setString(5, ID);
	
	        ResultSet results = stmt.executeQuery();
	        finalRet = acquireResults(results);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        connect.close();
        return finalRet;
    }
    
    public Object[][] selectGear(String manf, String startingPrice, String link, String type, String modelNumber) {
        Connection connection = connect.getConnection();
        Object[][] finalRet = null;
        try {
	        CallableStatement stmt = connection.prepareCall("{call SelectGear(?, ?, ?, ?, ?)}");
	        if (manf.isEmpty()) manf = null;
	        if (startingPrice.isEmpty()) startingPrice = null;
	        if (link.isEmpty()) link = null;
	        if (type.isEmpty()) type = null;
	        if (modelNumber.isEmpty()) modelNumber = null;
	        stmt.setString(1, manf);
	        stmt.setString(2, startingPrice);
	        stmt.setString(3, link);
	        stmt.setString(4, type);
	        stmt.setString(5, modelNumber);
	        ResultSet results = stmt.executeQuery();
	        finalRet = acquireResults(results);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
        connect.close();
        return finalRet;
    }
    
    public Object[][] selectHas(String EventID, String MatchID) {
        Connection connection = connect.getConnection();
        Object[][] finalRet = null;
        try {
	        CallableStatement stmt = connection.prepareCall("{call SelectHas(?, ?)}");
	        if (EventID.isEmpty()) EventID = null;
	        if (MatchID.isEmpty()) MatchID = null;
	        stmt.setString(1, EventID);
	        stmt.setString(2, MatchID);
	        ResultSet results = stmt.executeQuery();
	        finalRet = acquireResults(results);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
        connect.close();
        return finalRet;
    }
    
    public Object[][] selectHeld(String MatchOrganizationID, String EventID) {
        Connection connection = connect.getConnection();
        Object[][] finalRet = null;
        try {
	        CallableStatement stmt = connection.prepareCall("{call SelectHeld(?, ?)}");
	        if (MatchOrganizationID.isEmpty()) MatchOrganizationID = null;
	        if (EventID.isEmpty()) EventID = null;
	        stmt.setString(1, MatchOrganizationID);
	        stmt.setString(2, EventID);
	        ResultSet results = stmt.executeQuery();
	        finalRet = acquireResults(results);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
        connect.close();
        return finalRet;
    }
    
    public Object[][] selectMatch(String ID, String DateAndTime, String score, String watchingHours) {
        Connection connection = connect.getConnection();
        Object[][] finalRet = null;
        try {
	        CallableStatement stmt = connection.prepareCall("{call SelectMatch(?, ?, ?, ?)}");
	        if (ID.isEmpty()) ID = null;
	        if (DateAndTime.isEmpty()) DateAndTime = null;
	        if (score.isEmpty()) score = null;
	        if (watchingHours.isEmpty()) watchingHours = null;
	        stmt.setString(1, ID);
	        stmt.setString(2, DateAndTime);
	        stmt.setString(3, score);
	        stmt.setString(4, watchingHours);
	        ResultSet results = stmt.executeQuery();
	        finalRet = acquireResults(results);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
        connect.close();
        return finalRet;
    }
    
    public Object[][] selectMatchOrganization(String ID, String Contact, String Sponsor, String OrganizationName) {
        Connection connection = connect.getConnection();
        Object[][] finalRet = null;
        try {
	        CallableStatement stmt = connection.prepareCall("{call SelectMatchOrganization(?, ?, ?, ?)}");
	        if (ID.isEmpty()) ID = null;
	        if (Contact.isEmpty()) Contact = null;
	        if (Sponsor.isEmpty()) Sponsor = null;
	        if (OrganizationName.isEmpty()) OrganizationName = null;
	        stmt.setString(1, ID);
	        stmt.setString(2, Contact);
	        stmt.setString(3, Sponsor);
	        stmt.setString(4, OrganizationName);
	        ResultSet results = stmt.executeQuery();
	        finalRet = acquireResults(results);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
        connect.close();
        return finalRet;
    }
    
    public Object[][] selectParticipateIn(String PlayerID, String MatchID, String Stats) {
        Connection connection = connect.getConnection();
        Object[][] finalRet = null;
        try {
	        CallableStatement stmt = connection.prepareCall("{call SelectParticipateIn(?, ?, ?)}");
	        if (PlayerID.isEmpty()) PlayerID = null;
	        if (MatchID.isEmpty()) MatchID = null;
	        if (Stats.isEmpty()) Stats = null;
	        stmt.setString(1, PlayerID);
	        stmt.setString(2, MatchID);
	        stmt.setString(3, Stats);
	        ResultSet results = stmt.executeQuery();
	        finalRet = acquireResults(results);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
        connect.close();
        return finalRet;
    }
    
    public Object[][] selectPlacedIn(String TeamID, String EventID, String Rank) {
        Connection connection = connect.getConnection();
        Object[][] finalRet = null;
        try {
	        CallableStatement stmt = connection.prepareCall("{call SelectPlacedIn(?, ?, ?)}");
	        if (TeamID.isEmpty()) TeamID = null;
	        if (EventID.isEmpty()) EventID = null;
	        if (Rank.isEmpty()) Rank = null;
	        stmt.setString(1, TeamID);
	        stmt.setString(2, EventID);
	        stmt.setString(3, Rank);
	        ResultSet results = stmt.executeQuery();
	        finalRet = acquireResults(results);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
        connect.close();
        return finalRet;
    }
    
    public Object[][] selectPlayedOn(String TeamID, String MatchID) {
        Connection connection = connect.getConnection();
        Object[][] finalRet = null;
        try {
	        CallableStatement stmt = connection.prepareCall("{call SelectPlayedOn(?, ?)}");
	        if (TeamID.isEmpty()) TeamID = null;
	        if (MatchID.isEmpty()) MatchID = null;
	        stmt.setString(1, TeamID);
	        stmt.setString(2, MatchID);
	        ResultSet results = stmt.executeQuery();
	        finalRet = acquireResults(results);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
        connect.close();
        return finalRet;
    }
    
    public Object[][] selectPlayer(String ID, String nation, String PlayerName, String username, String DOB, String experience, String role) {
        Connection connection = connect.getConnection();
        Object[][] finalRet = null;
        try {
	        CallableStatement stmt = connection.prepareCall("{call SelectPlayer(?, ?, ?, ?, ?, ?, ?)}");
	        if (ID.isEmpty()) ID = null;
	        if (nation.isEmpty()) nation = null;
	        if (PlayerName.isEmpty()) PlayerName = null;
	        if (username.isEmpty()) username = null;
	        if (DOB.isEmpty()) DOB = null;
	        if (experience.isEmpty()) experience = null;
	        if (role.isEmpty()) role = null;
	        stmt.setString(1, ID);
	        stmt.setString(2, nation);
	        stmt.setString(3, PlayerName);
	        stmt.setString(4, username);
	        stmt.setString(5, DOB);
	        stmt.setString(6, experience);
	        stmt.setString(7, role);
	        ResultSet results = stmt.executeQuery();
	        finalRet = acquireResults(results);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        connect.close();
        return finalRet;
    }
    
    public Object[][] selectPlaysFor(String PlayerID, String TeamID) {
        Connection connection = connect.getConnection();
        Object[][] finalRet = null;
        try {
	        CallableStatement stmt = connection.prepareCall("{call SelectPlaysFor(?, ?)}");
	        if (PlayerID.isEmpty()) PlayerID = null;
	        if (TeamID.isEmpty()) TeamID = null;
	        stmt.setString(1, PlayerID);
	        stmt.setString(2, TeamID);
	        ResultSet results = stmt.executeQuery();
	        finalRet = acquireResults(results);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        connect.close();
        return finalRet;
    }
    
    public Object[][] selectTeam(String ID, String TeamName, String TeamSponser, String dateFound) {
        Connection connection = connect.getConnection();
        Object[][] finalRet = null;
        try {
	        CallableStatement stmt = connection.prepareCall("{call SelectTeam(?, ?, ?, ?)}");
	        if (ID.isEmpty()) ID = "-1";
	        if (TeamName.isEmpty()) TeamName = null;
	        if (TeamSponser.isEmpty()) TeamSponser = null;
	        if (dateFound.isEmpty()) dateFound = null;
	        stmt.setInt(1, Integer.parseInt(ID));
	        stmt.setString(2, TeamName);
	        stmt.setString(3, TeamSponser);
	        stmt.setString(4, dateFound);
	        ResultSet results = stmt.executeQuery();
	        finalRet = acquireResults(results);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        connect.close();
        return finalRet;
    }
    
    public Object[][] selectUses(String PlayerID, String Gear, String Since) {
        Connection connection = connect.getConnection();
        Object[][] finalRet = null;
        try {
	        CallableStatement stmt = connection.prepareCall("{call SelectUses(?, ?, ?)}");
	        if (PlayerID.isEmpty()) PlayerID = null;
	        if (Gear.isEmpty()) Gear = null;
	        if (Since.isEmpty()) Since = null;
	        stmt.setString(1, PlayerID);
	        stmt.setString(2, Gear);
	        stmt.setString(3, Since);
	        ResultSet results = stmt.executeQuery();
	        finalRet = acquireResults(results);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        connect.close();
        return finalRet;
    }
}
