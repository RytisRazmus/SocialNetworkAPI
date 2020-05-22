package lt.viko.eif.fivehorsemen.SocialNetworkAPI.repository;

import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.*;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.database.MySqlConnection;

import java.util.ArrayList;

public class APIRepositoryImpl implements APIRepository {

    private MySqlConnection mySqlConnection = new MySqlConnection();

    @Override
    public User getUser(String email, String password) {
        return null;
    }

    @Override
    public void deleteFriendInv(String id) {

    }

    @Override
    public Friend searchUser(String fullname) {
        return null;
    }

    @Override
    public void acceptFriendInvite(String toUser, String fromUser) {

    }

    @Override
    public ArrayList<FriendPost> getFriendPosts(String userId) {
        return null;
    }

    @Override
    public ArrayList<FriendInvite> getFriendInvites(String userId) {
        return null;
    }

    @Override
    public boolean addUser(User user) {
        return false;
    }

    @Override
    public boolean insertFriendInvite(String toUser, String fromUser) {
        return false;
    }

    @Override
    public ArrayList<Friend> getFriends(String userId) {
        return null;
    }

    @Override
    public boolean addPost(Post post) {
        return false;
    }
}
