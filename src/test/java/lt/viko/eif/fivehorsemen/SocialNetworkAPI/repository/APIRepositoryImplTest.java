package lt.viko.eif.fivehorsemen.SocialNetworkAPI.repository;

import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.*;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.database.MySqlConnection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * The APIRepositoryImpl class tests
 *
 * @author Laurynas Zlatkus
 * @author Rytis Razmus
 * @author Jonas Zemaitis
 * @author Evaldas Tamutis
 * @author Evaldas Zalnierius
 */

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class APIRepositoryImplTest {

    @InjectMocks
    private APIRepositoryImpl repository;

    @Mock
    private MySqlConnection mySqlConnection;

    /**
     * Test of getUser from APIRepositoryImpl class
     */

    @Test
    void getUser() {
        User user = new User("1", "laurynas.zlatkus@gmail.com", "Laurynas", "Zlatkus",
                "911", "2020-01-15 18:25:16", "2020-05-15", "123456");
        when(mySqlConnection.getUser("laurynas.zlatkus@gmail.com","123465")).thenReturn(user);
        User result = repository.getUser("laurynas.zlatkus@gmail.com","123465");
        assertEquals(user,result);
    }

    /**
     * Test of searchUser from APIRepositoryImpl class
     */

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

    /**
     * Test of deleteFriendInv from APIRepositoryImpl class
     */

    @Test
    void deleteFriendInv() {
        when(mySqlConnection.deleteFriendInv("1")).thenReturn(true);
        Boolean result = repository.deleteFriendInv("1");
        assertThat(result).isTrue();
    }

    /**
     * Test of acceptFriendInvite from APIRepositoryImpl class
     */

    @Test
    void acceptFriendInvite() {
        String fromUser = "Evaldas";
        String toUser = "Valanciunas";
        when(mySqlConnection.acceptFriendInvite(toUser,fromUser)).thenReturn(true);
        boolean result = repository.acceptFriendInvite(toUser,fromUser);
        assertThat(result).isTrue();
    }

    /**
     * Test of getFriendPosts from APIRepositoryImpl class
     */

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

    /**
     * Test of getFriendInvites from APIRepositoryImpl class
     */

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

    /**
     * Test of addUser from APIRepositoryImpl class
     */

    @Test
    void addUser() {
        User user = new User("1", "laurynas.zlatkus@gmail.com", "Laurynas", "Zlatkus",
                "911", "2020-01-15 18:25:16", "2020-05-15", "123456");
        when(mySqlConnection.addUser(user)).thenReturn(true);
        Boolean result = repository.addUser(user);
        assertEquals(result,true);
    }

    /**
     * Test of insertFriendInvites from APIRepositoryImpl class
     */

    @Test
    void insertFriendInvite() {
        when(mySqlConnection.insertFriendInvite("laurynas","Evaldas")).thenReturn(true);
        boolean result = repository.insertFriendInvite("laurynas","Evaldas");
        assertEquals(result,true);
    }

    /**
     * Test of getFriends from APIRepositoryImpl class
     */

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

    /**
     * Test of addPost from APIRepositoryImpl class
     */

    @Test
    void addPost() {
        Post post =new Post("1","Pavargau" ,"https://www.cuto" +
                "utme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg");
        when(mySqlConnection.addPost(post)).thenReturn(true);
        boolean result = repository.addPost(post);
        assertEquals(result,true);
    }

    /**
     * Test of getCity from APIRepositoryImpl class
     */

    @Test
    void getCity() {
        String city = "Vilnius";
        when(mySqlConnection.getCity("1")).thenReturn(city);
        String result = repository.getCity("1");
        assertEquals(result,city);
    }

    /**
     * Test of identifyUser from APIRepositoryImpl class
     */

    @Test
    void identifyUser() {
        User user = new User("1", "laurynas.zlatkus@gmail.com", "Laurynas", "Zlatkus",
                "911", "2020-01-15 18:25:16", "2020-05-15", "123456");
        when(mySqlConnection.identifyUser("1")).thenReturn(user);
        User result = repository.identifyUser("1");
        assertEquals(result,user);
    }
}