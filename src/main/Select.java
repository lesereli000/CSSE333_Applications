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
    // TODO: Eric Bender (Me) needs to finish this up over the weekend
    // Might need Has, Held, ParticipateIn, PlacedIn, PlayedOn, PlaysFor, Uses
    // For sure need Match, Match Organization, Player, Team

}
