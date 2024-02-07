package main;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Select {
    private Connect connect;

    public Select(Connect connect) {
        this.connect = connect;
    }
    private ArrayList<HashMap<String, Object>> acquireResults(ResultSet results) throws SQLException {
        ResultSetMetaData rsMetaData = results.getMetaData();
        int count = rsMetaData.getColumnCount();
        ArrayList<String> columns = new ArrayList<>();
        ArrayList<HashMap<String, Object>> finalRet = new ArrayList<>();
        for(int i = 1; i<=count; i++) {
            columns.add(rsMetaData.getColumnName(i));
        }
        while (results.next()) {
            HashMap<String, Object> m = new HashMap<>();
            for (String s : columns) {
                m.put(s, results.getObject(s));
            }
            finalRet.add(m);
        }
        return finalRet;
    }
    public ArrayList<HashMap<String, Object>> selectEvent(String eventName, String gameName, String Location, String OnlineLiveAddress, String ID) throws SQLException {
        Connection connection = connect.getConnection();
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
        ArrayList<HashMap<String, Object>> finalRet = acquireResults(results);
        connect.close();
        return finalRet;
    }
    public ArrayList<HashMap<String, Object>> selectGear(String manf, String startingPrice, String link, String type, String modelNumber) throws SQLException {
        Connection connection = connect.getConnection();
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
        ArrayList<HashMap<String, Object>> finalRet = acquireResults(results);
        connect.close();
        return finalRet;
    }
    public ArrayList<HashMap<String, Object>> selectHas(String EventID, String MatchID) throws SQLException {
        Connection connection = connect.getConnection();
        CallableStatement stmt = connection.prepareCall("{call SelectHas(?, ?)}");
        if (EventID.isEmpty()) EventID = null;
        if (MatchID.isEmpty()) MatchID = null;
        stmt.setString(1, EventID);
        stmt.setString(2, MatchID);
        ResultSet results = stmt.executeQuery();
        ArrayList<HashMap<String, Object>> finalRet = acquireResults(results);
        connect.close();
        return finalRet;
    }
    public ArrayList<HashMap<String, Object>> selectHeld(String MatchOrganizationID, String EventID) throws SQLException {
        Connection connection = connect.getConnection();
        CallableStatement stmt = connection.prepareCall("{call SelectHeld(?, ?)}");
        if (MatchOrganizationID.isEmpty()) MatchOrganizationID = null;
        if (EventID.isEmpty()) EventID = null;
        stmt.setString(1, MatchOrganizationID);
        stmt.setString(2, EventID);
        ResultSet results = stmt.executeQuery();
        ArrayList<HashMap<String, Object>> finalRet = acquireResults(results);
        connect.close();
        return finalRet;
    }
    public ArrayList<HashMap<String, Object>> selectMatch(String ID, String DateAndTime, String score, String watchingHours) throws SQLException {
        Connection connection = connect.getConnection();
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
        ArrayList<HashMap<String, Object>> finalRet = acquireResults(results);
        connect.close();
        return finalRet;
    }
    public ArrayList<HashMap<String, Object>> selectMatchOrganization(String ID, String Contact, String Sponsor, String OrganizationName) throws SQLException {
        Connection connection = connect.getConnection();
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
        ArrayList<HashMap<String, Object>> finalRet = acquireResults(results);
        connect.close();
        return finalRet;
    }
    public ArrayList<HashMap<String, Object>> selectParticipateIn(String PlayerID, String MatchID, String Stats) throws SQLException {
        Connection connection = connect.getConnection();
        CallableStatement stmt = connection.prepareCall("{call SelectParticipateIn(?, ?, ?)}");
        if (PlayerID.isEmpty()) PlayerID = null;
        if (MatchID.isEmpty()) MatchID = null;
        if (Stats.isEmpty()) Stats = null;
        stmt.setString(1, PlayerID);
        stmt.setString(2, MatchID);
        stmt.setString(3, Stats);
        ResultSet results = stmt.executeQuery();
        ArrayList<HashMap<String, Object>> finalRet = acquireResults(results);
        connect.close();
        return finalRet;
    }
    public ArrayList<HashMap<String, Object>> selectPlacedIn(String TeamID, String EventID, String Rank) throws SQLException {
        Connection connection = connect.getConnection();
        CallableStatement stmt = connection.prepareCall("{call SelectPlacedIn(?, ?, ?)}");
        if (TeamID.isEmpty()) TeamID = null;
        if (EventID.isEmpty()) EventID = null;
        if (Rank.isEmpty()) Rank = null;
        stmt.setString(1, TeamID);
        stmt.setString(2, EventID);
        stmt.setString(3, Rank);
        ResultSet results = stmt.executeQuery();
        ArrayList<HashMap<String, Object>> finalRet = acquireResults(results);
        connect.close();
        return finalRet;
    }
    public ArrayList<HashMap<String, Object>> selectPlayedOn(String TeamID, String MatchID) throws SQLException {
        Connection connection = connect.getConnection();
        CallableStatement stmt = connection.prepareCall("{call SelectPlayedOn(?, ?)}");
        if (TeamID.isEmpty()) TeamID = null;
        if (MatchID.isEmpty()) MatchID = null;
        stmt.setString(1, TeamID);
        stmt.setString(2, MatchID);
        ResultSet results = stmt.executeQuery();
        ArrayList<HashMap<String, Object>> finalRet = acquireResults(results);
        connect.close();
        return finalRet;
    }
    public ArrayList<HashMap<String, Object>> selectPlayer(String ID, String nation, String PlayerName, String username, String DOB, String experience, String role) throws SQLException {
        Connection connection = connect.getConnection();
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
        ArrayList<HashMap<String, Object>> finalRet = acquireResults(results);
        connect.close();
        return finalRet;
    }
    public ArrayList<HashMap<String, Object>> selectPlaysFor(String PlayerID, String TeamID) throws SQLException {
        Connection connection = connect.getConnection();
        CallableStatement stmt = connection.prepareCall("{call SelectPlaysFor(?, ?)}");
        if (PlayerID.isEmpty()) PlayerID = null;
        if (TeamID.isEmpty()) TeamID = null;
        stmt.setString(1, PlayerID);
        stmt.setString(2, TeamID);
        ResultSet results = stmt.executeQuery();
        ArrayList<HashMap<String, Object>> finalRet = acquireResults(results);
        connect.close();
        return finalRet;
    }
    public ArrayList<HashMap<String, Object>> selectTeam(String ID, String TeamName, String TeamSponser, String dateFound) throws SQLException {
        Connection connection = connect.getConnection();
        CallableStatement stmt = connection.prepareCall("{call SelectTeam(?, ?, ?, ?)}");
        if (ID.isEmpty()) ID = null;
        if (TeamName.isEmpty()) TeamName = null;
        if (TeamSponser.isEmpty()) TeamSponser = null;
        if (dateFound.isEmpty()) dateFound = null;
        stmt.setString(1, ID);
        stmt.setString(2, TeamName);
        stmt.setString(3, TeamSponser);
        stmt.setString(4, dateFound);
        ResultSet results = stmt.executeQuery();
        ArrayList<HashMap<String, Object>> finalRet = acquireResults(results);
        connect.close();
        return finalRet;
    }
    public ArrayList<HashMap<String, Object>> selectUses(String PlayerID, String Gear, String Since) throws SQLException {
        Connection connection = connect.getConnection();
        CallableStatement stmt = connection.prepareCall("{call SelectUses(?, ?, ?)}");
        if (PlayerID.isEmpty()) PlayerID = null;
        if (Gear.isEmpty()) Gear = null;
        if (Since.isEmpty()) Since = null;
        stmt.setString(1, PlayerID);
        stmt.setString(2, Gear);
        stmt.setString(3, Since);
        ResultSet results = stmt.executeQuery();
        ArrayList<HashMap<String, Object>> finalRet = acquireResults(results);
        connect.close();
        return finalRet;
    }
}
