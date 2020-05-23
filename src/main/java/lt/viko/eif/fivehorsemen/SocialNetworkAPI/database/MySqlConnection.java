package lt.viko.eif.fivehorsemen.SocialNetworkAPI.database;

import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.*;

import java.sql.*;
import java.util.ArrayList;

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

    public boolean deleteFriendInv(String id) {
        String statement = "DELETE FROM FriendInvite WHERE id = ?";
        boolean success = false;

        try {
            Connection conn = this.connect();

            PreparedStatement pstmt = conn.prepareStatement(statement);

            pstmt.setString(1, id);

            pstmt.executeUpdate();
            success = true;
            conn.close();
            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
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

    public boolean acceptFriendInvite(String toUser, String fromUser) {
        String statement = "INSERT INTO Friend(userId, friendId) VALUES(?, ?)";
        String secStatement = "INSERT INTO Friend(friendId, userId) VALUES(?, ?)";

        boolean success = false;
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

            success = true;

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return success;
    }

    public ArrayList<FriendPost> getFriendPosts(String userId) {
        String statement = "SELECT post.date, f2.name, f2.surname, f2.id, im.imageURL as 'profileImage', content.description," +
                            "image.imageUrl FROM Post post INNER JOIN Content content ON (post.contentId = content.id) " +
                            "LEFT JOIN Image image ON (image.contentId = content.id) " +
                            "INNER JOIN User user ON (user.id = ?) " +
                            "INNER JOIN Friend friend ON friend.userId = user.id " +
                            "INNER JOIN (SELECT * FROM User) f2 ON friend.friendid = f2.id " +
                            "INNER JOIN UserImage uImage ON friendId = uImage.userId " +
                            "INNER JOIN Image im on uImage.image = im.id && uImage.isPrimaryImage = 1 " +
                            "WHERE friend.friendId = post.userId " +
                            "ORDER BY post.date DESC";

        ArrayList<FriendPost> friendsPosts = new ArrayList<>();

        try {
            Connection conn = this.connect();

            PreparedStatement pstmt = conn.prepareStatement(statement);

            pstmt.setString(1, userId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                friendsPosts.add(new FriendPost(rs.getDate("post.date"), rs.getString("f2.name"),
                                                rs.getString("f2.surname"),rs.getString("f2.id"),
                                                rs.getString("content.description"),rs.getString("image.imageUrl"),
                                                rs.getString("profileImage")));
            }

            rs.close();
            pstmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friendsPosts;

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

    public String getCity(String userId) {
        String city = "";
        try {
            String sql =
                    "SELECT city FROM User WHERE User.id = ?;";

            PreparedStatement ps = connect().prepareStatement(sql);

            ps.setString(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                city = rs.getString("city");
            }
            System.out.println(city);
            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return city;
    }

    public User identifyUser(String userId) {
        String statement = "SELECT * FROM User WHERE User.id = ?";
        User user = null;

        try {
            Connection conn = this.connect();

            PreparedStatement pstmt = conn.prepareStatement(statement);

            pstmt.setString(1, userId);

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

}
