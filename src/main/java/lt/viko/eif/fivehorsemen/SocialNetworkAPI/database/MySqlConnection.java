package lt.viko.eif.fivehorsemen.SocialNetworkAPI.database;

import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.Friend;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.User;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public User getUser(String email, String password){
        String statement = "SELECT id, email, name, surname, password, phoneNumber, lastSeen," +
                                    "dateOfBirth FROM User WHERE email = ? AND password = ?";
        User user = null;

        try {
            Connection conn = this.connect();

            PreparedStatement pstmt = conn.prepareStatement(statement);

            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                user = new User(rs.getString("id"), rs.getString("email"), rs.getString("name"),
                        rs.getString("surname"), rs.getString("password"), rs.getString("phoneNumber"),
                        rs.getString("lastSeen"), rs.getString("dateOfBirth"));
            }

            rs.close();
            pstmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void deleteFriendInv(String id) {
        String statement = "DELETE FROM FriendInvite WHERE id = ?";

        try {
            Connection conn = this.connect();

            PreparedStatement pstmt = conn.prepareStatement(statement);

            pstmt.setString(1, id);

            pstmt.executeUpdate();

            conn.close();
            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Friend searchUser(String fullname) {
        String statement = "SELECT user.id, user.name, user.surname, img.imageUrl as imageUrl " +
        "FROM User user " +
        "LEFT JOIN UserImage usrImg ON (usrImg.userId = user.id) " +
        "LEFT JOIN Image img ON (img.id = usrImg.image) " +
        "AND usrImg.isPrimaryImage = 1 "+
        "WHERE user.fullName LIKE ?";

        Friend friend = null;

        try {
            Connection conn = this.connect();

            PreparedStatement pstmt = conn.prepareStatement(statement);

            pstmt.setString(1, "%"+fullname+"%");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                friend = new Friend (rs.getString("user.id"), rs.getString("user.name"),
                                        rs.getString("user.surname"), rs.getString("img.imageUrl"));
            }

            rs.close();
            pstmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friend;
    }

    public void acceptFriendInvite(String toUser, String fromUser) {
        String statement = "INSERT INTO Friend(userId, friendId) VALUES(?, ?)";
        String secStatement = "INSERT INTO Friend(friendId, userId) VALUES(?, ?)";
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(statement);

            pstmt.setString(1, toUser);
            pstmt.setString(2, fromUser);

            pstmt.executeUpdate();

            pstmt = conn.prepareStatement(secStatement);

            pstmt.setString(1, toUser);
            pstmt.setString(2, fromUser);

            pstmt.executeUpdate();
            pstmt.close();

            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
