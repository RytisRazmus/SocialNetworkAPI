package lt.viko.eif.fivehorsemen.SocialNetworkAPI.repository;

import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.*;

import java.util.ArrayList;

/**
 * Interface class that has the following methods
 *
 * @author Laurynas Zlatkus
 * @author Rytis Razmus
 * @author Jonas Zemaitis
 * @author Evaldas Tamutis
 * @author Evaldas Zalnierius
 */

public interface APIRepository {

    /**
     * Get user object
     * @param email user email
     * @param password user password
     */
    User getUser(String email, String password);

    /**
     * Delete friend invitation
     * @param id friend id
     */

    boolean deleteFriendInv(String id);

    /**
     * Search for user
     * @param fullname user id
     */

    ArrayList<Friend> searchUser(String fullname);

    /**
     * Accept friend invite
     * @param toUser sender of friend request user id
     * @param fromUser accepted friend request user id
     */

    boolean acceptFriendInvite(String toUser, String fromUser);

    /**
     * Get friend posts
     * @param userId user id
     */

    ArrayList<FriendPost> getFriendPosts(String userId);

    /**
     * Get friend invites
     * @param userId user id
     */

    ArrayList<FriendInvite> getFriendInvites(String userId);

    /**
     * Create new user account
     * @param user user object
     */

    boolean addUser(User user);

    /**
     * Send a friend invite to friend
     * @param toUser receiver user id
     * @param fromUser sender user id
     */

    boolean insertFriendInvite(String toUser, String fromUser);

    /**
     * Get friend list
     * @param userId user id
     */

    ArrayList<Friend> getFriends(String userId);

    /**
     * Add new post
     * @param post post object
     */

    boolean addPost(Post post);

    /**
     * Get user city
     * @param userId user id
     */

    String getCity(String userId);

    /**
     * Identify user
     * @param userId user id
     */

    User identifyUser(String userId);

}
