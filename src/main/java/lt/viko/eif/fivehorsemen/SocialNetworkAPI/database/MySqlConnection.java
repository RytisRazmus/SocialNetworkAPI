package lt.viko.eif.fivehorsemen.SocialNetworkAPI.database;

import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.User;

import java.sql.*;

public class MySqlConnection {
    static String USER = "lopai";
    static String PASSWORD = "tumasonis";
    static String DB_URL = "jdbc:mysql://78.61.168.194:3306/cityTransport";

    private Connection connect () {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public User fetchUser(){
        Statement stmt = null;
        User user = null;

        try {
            stmt = this.connect().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, email, name, surname, password, phoneNumber, lastSeen," +
                                                "dateOfBirth FROM User WHERE id = 3");

            while (rs.next()) {
                user = new User(rs.getString("id"), rs.getString("email"), rs.getString("name"),
                        rs.getString("surname"), rs.getString("password"), rs.getString("phoneNumber"),
                        rs.getString("lastSeen"), rs.getString("dateOfBirth"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
