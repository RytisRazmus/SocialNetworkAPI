package lt.viko.eif.fivehorsemen.SocialNetworkAPI.database;

import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.Friend;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.FriendInvite;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.Post;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.User;
import org.springframework.stereotype.Controller;

import java.sql.*;
import java.util.ArrayList;

public class MySqlConnection {
    static String USER = "lopai";
    static String PASSWORD = "tumasonis";
    static String DB_URL = "jdbc:mysql://78.61.168.194:3306/cityTransport";
    
    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public User fetchUser() {
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

    public ArrayList<FriendInvite> getFriendInvites(String userId) {
        ArrayList<FriendInvite> friendInvites = new ArrayList<>();
        try {
            String sql =
                    "SELECT name, surname, email, u.id, imageUrl, FriendInvite.id as 'inviteId' FROM FriendInvite\n" +
                            "    LEFT JOIN Friend ON Friend.friendId = FriendInvite.toUser && Friend.userId = FriendInvite.fromUser\n" +
                            "    Inner join User u on u.id = FriendInvite.fromUser\n" +
                            "    LEFT JOIN UserImage ui on ui.userId = u.id and ui.isPrimaryImage = 1\n" +
                            "    LEFT JOIN Image img on img.id = ui.image\n" +
                            "    WHERE FriendInvite.toUser = ? AND Friend.friendId IS NULL";

            PreparedStatement ps = connect().prepareStatement(sql);

            ps.setString(1, String.valueOf(userId));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                FriendInvite fi = new FriendInvite(rs.getString("id"), rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("imageUrl"), rs.getString("inviteId"));
                friendInvites.add(fi);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return friendInvites;
    }

    public boolean addUser(User user) {
        boolean success = false;
        try {
            String sql =
                    "INSERT INTO User(email, name, surname, password, phoneNumber, dateOfBirth)" +
                            "VALUES(?, ?, ?, ?, ?, ?);";

            PreparedStatement ps = connect().prepareStatement(sql);

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getName());
            ps.setString(3, user.getSurname());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getPhoneNumber());
            ps.setString(6, user.getDateOfBirth());
            ps.execute();
            success = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return success;
    }

    public boolean insertFriendInvite(String toUser, String fromUser) {
        boolean success = false;
        try {
            String sql =
                    "INSERT INTO FriendInvite(toUser, fromUser)" +
                            "VALUES(?, ?);";

            PreparedStatement ps = connect().prepareStatement(sql);

            ps.setString(1, toUser);
            ps.setString(2, fromUser);
            ps.execute();
            success = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return success;
    }

    public ArrayList<Friend> getFriends(String userId) {
        ArrayList<Friend> friends = new ArrayList<>();
        try {
            String sql =
                    "SELECT User.id, User.name, User.surname, imageUrl FROM User\n" +
                            "INNER JOIN Friend fr ON fr.userId = User.id AND fr.friendId = ?\n" +
                            "INNER JOIN UserImage ui on ui.userId = fr.userId\n" +
                            "INNER JOIN Image img on img.id = ui.image\n" +
                            "and ui.isPrimaryImage = 1;";

            PreparedStatement ps = connect().prepareStatement(sql);

            ps.setString(1, String.valueOf(userId));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Friend fi = new Friend(rs.getString("id"), rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("imageUrl"));
                friends.add(fi);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friends;
    }

    public boolean addPost(Post post) {
        boolean success = false;

        try {
            String sql1 = "INSERT INTO Content(description) VALUES('" + post.getDescription() + "');";
            PreparedStatement pstmt = connect().prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
            pstmt.executeUpdate();
            ResultSet keys = pstmt.getGeneratedKeys();
            keys.next();
            int key = keys.getInt(1);

            String sql2 = "INSERT INTO Post(userId, contentId) VALUES('" + post.getUserId() + "', '" + key + "');";
            pstmt = connect().prepareStatement(sql2);
            pstmt.executeUpdate();

            String sql3 = "INSERT INTO Image(imageURL, contentId) VALUES('" + post.getImageUrl() + "', '" + key +"');";
            pstmt = connect().prepareStatement(sql3);
            pstmt.executeUpdate();


            success = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return success;
    }
}
