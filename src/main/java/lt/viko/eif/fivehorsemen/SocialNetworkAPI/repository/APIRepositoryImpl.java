package lt.viko.eif.fivehorsemen.SocialNetworkAPI.repository;

import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.*;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.database.MySqlConnection;

import java.util.ArrayList;

/**
 * The APIRepositoryImpl class is for communicating with database
 * implements APIRepository interface
 *
 * @author Laurynas Zlatkus
 * @author Rytis Razmus
 * @author Jonas Zemaitis
 * @author Evaldas Tamutis
 * @author Evaldas Zalnierius
 */

public class APIRepositoryImpl implements APIRepository {

    private MySqlConnection mySqlConnection = new MySqlConnection();

    /**
     * Sends request to database for get user data
     * @param email user email
     * @param password user password
     * @return user object
     */
    @Override
    public User getUser(String email, String password) {
        return mySqlConnection.getUser(email, password);
    }

    /**
     * Sends request to database for delete friend invitation
     * @param id friend id
     * @return true if request deleted successfully
     */

    @Override
    public boolean deleteFriendInv(String id) {
        return mySqlConnection.deleteFriendInv(id);
    }

    /**
     * Sends request to database for user search
     * @param fullname user full name
     * @return list of user with same name
     */

    @Override
    public ArrayList<Friend> searchUser(String fullname) {
        return mySqlConnection.searchUser(fullname);
    }

    /**
     * Sends request to database for accept friend invite
     * @param toUser sender of friend request user id
     * @param fromUser accepted friend request user id
     * @return true if request was successful
     */

    @Override
    public boolean acceptFriendInvite(String toUser, String fromUser) {
        return mySqlConnection.acceptFriendInvite(toUser, fromUser);
    }

    /**
     * Sends request to database for get friend posts
     * @param userId user id
     * @return list of friend posts
     */

    @Override
    public ArrayList<FriendPost> getFriendPosts(String userId) {
        return mySqlConnection.getFriendPosts(userId);
    }

    /**
     * Sends request to database for get friend invites
     * @param userId user id
     * @return list of friend invites
     */

    @Override
    public ArrayList<FriendInvite> getFriendInvites(String userId) {
        return mySqlConnection.getFriendInvites(userId);
    }

    /**
     * Sends request to database for creating new user
     * @param user user object
     * @return true if request was successful
     */

    @Override
    public boolean addUser(User user) {
        return mySqlConnection.addUser(user);
    }

    /**
     * Sends request to database for sending friend request
     * @param toUser receiver user id
     * @param fromUser sender user id
     * @return true if request was successful
     */

    @Override
    public boolean insertFriendInvite(String toUser, String fromUser) {
        return mySqlConnection.insertFriendInvite(toUser, fromUser);
    }

    /**
     * Sends request to database for get friend list
     * @param userId user id
     * @return list of friends
     */

    @Override
    public ArrayList<Friend> getFriends(String userId) {
        return mySqlConnection.getFriends(userId);
    }

    /**
     * Sends request to database for add new post
     * @param post post object
     * @return true if request was successful
     */

    @Override
    public boolean addPost(Post post) {
        return mySqlConnection.addPost(post);
    }

    /**
     * Sends request to database for get user city
     * @param userId user id
     * @return a String with city name
     */

    @Override
    public String getCity(String userId) {
        return mySqlConnection.getCity(userId);
    }

    /**
     * Sends request to database for identify user
     * @param userId user id
     * @return if request was successful return user object
     */

    @Override
    public User identifyUser(String userId) {
        return mySqlConnection.identifyUser(userId);
    }
}
