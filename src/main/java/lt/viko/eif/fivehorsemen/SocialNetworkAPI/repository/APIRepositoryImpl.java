package lt.viko.eif.fivehorsemen.SocialNetworkAPI.repository;

import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.*;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.database.MySqlConnection;

import java.util.ArrayList;

public class APIRepositoryImpl implements APIRepository {

    private MySqlConnection mySqlConnection = new MySqlConnection();

    @Override
    public User getUser(String email, String password) {
        return mySqlConnection.getUser(email, password);
    }

    @Override
    public void deleteFriendInv(String id) {
        mySqlConnection.deleteFriendInv(id);
    }

    @Override
    public Friend searchUser(String fullname) {
        return mySqlConnection.searchUser(fullname);
    }

    @Override
    public void acceptFriendInvite(String toUser, String fromUser) {
        mySqlConnection.acceptFriendInvite(toUser, fromUser);
    }

    @Override
    public ArrayList<FriendPost> getFriendPosts(String userId) {
        return mySqlConnection.getFriendPosts(userId);
    }

    @Override
    public ArrayList<FriendInvite> getFriendInvites(String userId) {
        return mySqlConnection.getFriendInvites(userId);
    }

    @Override
    public boolean addUser(User user) {
        return mySqlConnection.addUser(user);
    }

    @Override
    public boolean insertFriendInvite(String toUser, String fromUser) {
        return mySqlConnection.insertFriendInvite(toUser, fromUser);
    }

    @Override
    public ArrayList<Friend> getFriends(String userId) {
        return mySqlConnection.getFriends(userId);
    }

    @Override
    public boolean addPost(Post post) {
        return mySqlConnection.addPost(post);
    }

    @Override
    public String getCity(String userId) {
        return mySqlConnection.getCity(userId);
    }
}
