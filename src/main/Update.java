package main;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class Update {
    private Connect connect;

    public Update(Connect connect) {
        this.connect = connect;
    }


    public void updateEvent(String id, String onlineLiveAddr, String Location, String gameName, String EventName) {
        Connection con = connect.getConnection();
        try {
            CallableStatement stmt = con.prepareCall("{? = call UpdateEvent(?,?,?,?,?)}");
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.setString(2, id);
            stmt.setString(3, onlineLiveAddr);
            stmt.setString(4, Location);
            stmt.setString(5, gameName);
            stmt.setString(6, EventName);
            stmt.execute();

            int retCode = stmt.getInt(1);
            if (retCode == 0){
                System.out.println("Successfully updated");
            } else {
                System.out.println("Illegal Event Update");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("UpdateEvent call failed");
        }

        connect.close();
    }
    public void updateGear(String modelNumber, String manf, String type, String startingPrice, String link) {
        Connection con = connect.getConnection();
        try {
            CallableStatement stmt = con.prepareCall("{? = call UpdateGear(?,?,?,?,?)}");
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.setString(2, modelNumber);
            stmt.setString(3, manf);
            stmt.setString(4, type);
            stmt.setString(5, startingPrice);
            stmt.setString(6, link);
            stmt.execute();

            int retCode = stmt.getInt(1);
            if (retCode == 0){
                System.out.println("Successfully updated");
            } else {
                System.out.println("Illegal Gear Update");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("UpdateGear call failed");
        }

        connect.close();
    }
    public void updateMatch(String ID, String dateAndTime, String score, String watchingHours) {
        Connection con = connect.getConnection();
        try {
            CallableStatement stmt = con.prepareCall("{? = call UpdateMatch(?,?,?,?)}");
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.setString(2, ID);
            stmt.setString(3, dateAndTime);
            stmt.setString(4, score);
            stmt.setString(5, watchingHours);
            stmt.execute();

            int retCode = stmt.getInt(1);
            if (retCode == 0){
                System.out.println("Successfully updated");
            } else {
                System.out.println("Illegal Match Update");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("UpdateMatch call failed");
        }

        connect.close();
    }
    public void updateMatchOrganization(String ID, String contact, String sponsor, String organizationName) {
        Connection con = connect.getConnection();
        try {
            CallableStatement stmt = con.prepareCall("{? = call UpdateMatchOrganization(?,?,?,?)}");
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.setString(2, ID);
            stmt.setString(3, contact);
            stmt.setString(4, sponsor);
            stmt.setString(5, organizationName);
            stmt.execute();

            int retCode = stmt.getInt(1);
            if (retCode == 0){
                System.out.println("Successfully updated");
            } else {
                System.out.println("Illegal MatchOrganization Update");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("UpdateMatchOrganization call failed");
        }

        connect.close();
    }
    public void updateParticipateIn(String playerID, String matchID, String stats) {
        Connection con = connect.getConnection();
        try {
            CallableStatement stmt = con.prepareCall("{? = call UpdateParticipateIn(?,?,?)}");
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.setString(2, playerID);
            stmt.setString(3, matchID);
            stmt.setString(4, stats);
            stmt.execute();

            int retCode = stmt.getInt(1);
            if (retCode == 0){
                System.out.println("Successfully updated");
            } else {
                System.out.println("Illegal ParticipateIn Update");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("UpdateParticipateIn call failed");
        }

        connect.close();
    }
    public void updatePlacedIn(String teamID, String eventID, String rank) {
        Connection con = connect.getConnection();
        try {
            CallableStatement stmt = con.prepareCall("{? = call UpdatePlacedIn(?,?,?)}");
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.setString(2, teamID);
            stmt.setString(3, eventID);
            stmt.setString(4, rank);
            stmt.execute();

            int retCode = stmt.getInt(1);
            if (retCode == 0){
                System.out.println("Successfully updated");
            } else {
                System.out.println("Illegal PlacedIn Update");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("UpdatePlacedIn call failed");
        }

        connect.close();
    }
    public void updatePlayer(String ID, String nation, String playerName, String username, String dob, String experience, String role) {
        Connection con = connect.getConnection();
        try {
            CallableStatement stmt = con.prepareCall("{? = call UpdatePlayer(?,?,?,?,?,?,?)}");
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.setString(2, ID);
            stmt.setString(3, nation);
            stmt.setString(4, playerName);
            stmt.setString(5, username);
            stmt.setString(6, dob);
            stmt.setString(7, experience);
            stmt.setString(8, role);
            stmt.execute();

            int retCode = stmt.getInt(1);
            if (retCode == 0){
                System.out.println("Successfully updated");
            } else {
                System.out.println("Illegal Player Update");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("UpdatePlayer call failed");
        }

        connect.close();
    }
    public void updateTeam(String ID, String teamName, String teamSponsor, String dateFound) {
        Connection con = connect.getConnection();
        try {
            CallableStatement stmt = con.prepareCall("{? = call UpdateTeam(?,?,?,?)}");
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.setString(2, ID);
            stmt.setString(3, teamName);
            stmt.setString(4, teamSponsor);
            stmt.setString(5, dateFound);
            stmt.execute();

            int retCode = stmt.getInt(1);
            if (retCode == 0){
                System.out.println("Successfully updated");
            } else {
                System.out.println("Illegal Team Update");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("UpdateTeam call failed");
        }

        connect.close();
    }
    public void updateUses(String playerID, String gear, String since) {
        Connection con = connect.getConnection();
        try {
            CallableStatement stmt = con.prepareCall("{? = call UpdateUses(?,?,?)}");
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.setString(2, playerID);
            stmt.setString(3, gear);
            stmt.setString(4, since);
            stmt.execute();

            int retCode = stmt.getInt(1);
            if (retCode == 0){
                System.out.println("Successfully updated");
            } else {
                System.out.println("Illegal Uses Update");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("UpdateUses call failed");
        }

        connect.close();
    }
}
