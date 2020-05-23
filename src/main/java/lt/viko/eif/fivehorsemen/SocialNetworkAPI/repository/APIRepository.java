package lt.viko.eif.fivehorsemen.SocialNetworkAPI.repository;

import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.*;

import java.util.ArrayList;

public interface APIRepository {

    User getUser(String email, String password);

    boolean deleteFriendInv(String id);

    Friend searchUser(String fullname);

    boolean acceptFriendInvite(String toUser, String fromUser);

    ArrayList<FriendPost> getFriendPosts(String userId);

    ArrayList<FriendInvite> getFriendInvites(String userId);

    boolean addUser(User user);

    boolean insertFriendInvite(String toUser, String fromUser);

    ArrayList<Friend> getFriends(String userId);

    boolean addPost(Post post);

    String getCity(String userId);

    User identifyUser(String userId);

}
