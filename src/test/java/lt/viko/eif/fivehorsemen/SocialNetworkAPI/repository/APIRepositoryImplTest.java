package lt.viko.eif.fivehorsemen.SocialNetworkAPI.repository;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.Friend;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.FriendInvite;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.FriendPost;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.User;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.database.MySqlConnection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.Link;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class APIRepositoryImplTest {

    @InjectMocks
    private APIRepositoryImpl repository;

    @Mock
    private MySqlConnection mySqlConnection;

    @Test
    void getUser() {
        User user = new User("1", "laurynas.zlatkus@gmail.com", "Laurynas", "Zlatkus",
                "911", "2020-01-15 18:25:16", "2020-05-15", "123456");
        when(mySqlConnection.getUser("laurynas.zlatkus@gmail.com","123465")).thenReturn(user);
        User result = repository.getUser("laurynas.zlatkus@gmail.com","123465");
        assertEquals(user,result);

    }

    @Test
    void deleteFriendInv() {
        when(mySqlConnection.deleteFriendInv("1")).thenReturn(true);
        Boolean result = repository.deleteFriendInv("1");
        assertThat(result).isTrue();
    }

    @Test
    void searchUser() {
        Friend friend = new Friend("1", "Evaldas", "Tamutis",
                "https://www.cutoutme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg");
        ArrayList<Friend> friends = new ArrayList<>();
        friends.add(friend);
        when(mySqlConnection.searchUser("Evaldas")).thenReturn(friends);
        ArrayList<Friend> result = repository.searchUser("Evaldas");
        assertEquals(result,friends);
    }

    @Test
    void acceptFriendInvite() {
        String fromUser = "Evaldas";
        String toUser = "Valanciunas";
        when(mySqlConnection.acceptFriendInvite(toUser,fromUser)).thenReturn(true);
        boolean result = repository.acceptFriendInvite(toUser,fromUser);
        assertThat(result).isTrue();
    }

    @Test
    void getFriendPosts() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = format.parse("2020-05-14");
        String dmy = format.format(myDate);
        FriendPost friendPost = new FriendPost(myDate, "Laurynas", "Zlatkus", "1",
                "Maciau gera pana.", "https://upload.wikimedia.org/wikipedia/commons/0/0c/Cow" +
                "_female_black_white.jpg", "https://i.pinimg.com/474x/ff/5d/06/ff5d0624c089399d5736f8ce" +
                "0cc5ebeb.jpg");
        FriendPost friendPost1 = new FriendPost(myDate, "Evaldas", "Tamutis", "2",
                "Maciau gera pana.", "https://upload.wikimedia.org/wikipedia/commons/0/0c/Cow" +
                "_female_black_white.jpg", "https://i.pinimg.com/474x/ff/5d/06/ff5d0624c089399d5736f8ce" +
                "0cc5ebeb.jpg");
        ArrayList<FriendPost> posts = new ArrayList<>();
        posts.add(friendPost);
        posts.add(friendPost1);
        when(mySqlConnection.getFriendPosts("1")).thenReturn(posts);
        ArrayList<FriendPost> result = repository.getFriendPosts("1");
        assertEquals(result,posts);
    }

    @Test
    void getFriendInvites() {
        FriendInvite friendInv = new FriendInvite("1", "Evaldas", "Tamutis", "https://" +
                "www.cutoutme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg", "1");
        FriendInvite friendInv1 = new FriendInvite("2", "Elon", "Musk", "https://" +
                "www.cutoutme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg", "2");
        ArrayList<FriendInvite> friendInvites = new ArrayList<>();
        friendInvites.add(friendInv);
        friendInvites.add(friendInv1);
        when(mySqlConnection.getFriendInvites("1")).thenReturn(friendInvites);
        ArrayList<FriendInvite> result = repository.getFriendInvites("1");
        assertThat(result.size()).isEqualTo(2);
        assertEquals(result, friendInvites);
    }

    @Test
    void addUser() {
        User user = new User("1", "laurynas.zlatkus@gmail.com", "Laurynas", "Zlatkus",
                "911", "2020-01-15 18:25:16", "2020-05-15", "123456");
        when(mySqlConnection.addUser(user)).thenReturn(true);
        Boolean result = repository.addUser(user);
        assertEquals(result,true);
    }

    @Test
    void insertFriendInvite() {
        when(mySqlConnection.insertFriendInvite("laurynas","Evaldas")).thenReturn(true);
        boolean result = repository.insertFriendInvite("laurynas","Evaldas");
        assertEquals(result,true);
    }

    @Test
    void getFriends() {
        Friend friend = new Friend("1", "Evaldas", "Tamutis",
                "https://www.cutoutme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg");
        Friend friend1 = new Friend("2", "Andrius", "Rimiškis",
                "https://www.cutoutme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg");
        ArrayList<Friend> friends = new ArrayList<>();
        friends.add(friend);
        friends.add(friend1);
        when(mySqlConnection.getFriends("1")).thenReturn(friends);
        ArrayList<Friend> result = repository.getFriends("1");
        assertThat(result.size()).isEqualTo(2);
        assertEquals(result, friends);
    }

    @Test
    void addPost() {
    }

    @Test
    void getCity() {
    }

    @Test
    void identifyUser() {
    }
}