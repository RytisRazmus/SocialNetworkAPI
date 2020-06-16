package lt.viko.eif.fivehorsemen.SocialNetworkAPI.cucumber.repository;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.*;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.database.MySqlConnection;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

@RunWith(MockitoJUnitRunner.class)
public class APIRepositoryImplStepdefs {

    @InjectMocks
    private lt.viko.eif.fivehorsemen.SocialNetworkAPI.repository.APIRepositoryImpl repository;

    @Mock
    private MySqlConnection mySqlConnection;

    private User user;
    private Map<String, String> map = new HashMap<>();
    private ArrayList<Friend> friends = new ArrayList<>();
    private Post post;
    private Friend friend;
    private ArrayList<FriendPost> posts;
    private String city;
    String fromUser;
    String toUser;
    ArrayList<FriendInvite> friendInvites;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        HttpServletRequest httpServletRequestMock = new MockHttpServletRequest();
        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(httpServletRequestMock);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);
    }

    @Given("Users list")
    public void usersList() {
        user = new User("99", "albatrosas@kaunas.lt", "Joseph", "Stalin",
                "+340567261", "1953-03-05", "1878-12-18", "pazhalsta");
    }

    @When("User enters user email and password")
    public void userEnterUserEmailAndPassword() {

        when(mySqlConnection.getUser("albatrosas@kaunas.lt","pazhalsta")).thenReturn(user);
    }

    @Then("User gets desired user")
    public void userGetsDesiredUser() {
        User result = repository.getUser("albatrosas@kaunas.lt","pazhalsta");
        assertEquals(user, result);
    }

    @Given("Existing friends")
    public void existingFriends() {
        friend = new Friend("1", "Evaldas", "Tamutis",
                "https://www.cutoutme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg");
        ArrayList<Friend> friends = new ArrayList<>();
        friends.add(friend);
    }
    @When("user starts a friend search")
    public void userEntersFriendsName() {

        when(mySqlConnection.searchUser("Evaldas")).thenReturn(friends);
    }

    @Then("User gets desired friend")
    public void userGetsDesiredFriend() {
        ArrayList<Friend> result = repository.searchUser("Evaldas");
        assertEquals(result,friends);
    }
    @When("User wants to delete 1 friend invite")
    public void userWantsToDelete1FriendInvite() {

        when(mySqlConnection.deleteFriendInv("1")).thenReturn(true);
    }

    @Then("Friend invite is deleted")
    public void FriendInviteIsDeleted() {
        Boolean result = repository.deleteFriendInv("1");
        assertThat(result).isTrue();
    }
    @Given("Two existing users")
    public void twoExistingFriends() {
        fromUser = "Evaldas";
        toUser = "Valanciunas";
    }
    @When("User accepts other user invite")
    public void userAcceptsOtherUserInvite() {

        when(mySqlConnection.acceptFriendInvite(toUser,fromUser)).thenReturn(true);
    }

    @Then("They become friends")
    public void theyBecomeFriends() {
        boolean result = repository.acceptFriendInvite(toUser,fromUser);
        assertThat(result).isTrue();
    }
    @Given("Existing posts")
    public void existingPosts() throws ParseException {
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
    }
    @When("User wants to get user posts")
    public void userWantsToGetUserPosts() {
        when(mySqlConnection.getFriendPosts("1")).thenReturn(posts);
    }

    @Then("User gets posts")
    public void UsergetsPosts() {
        ArrayList<FriendPost> result = repository.getFriendPosts("1");
        assertEquals(result,posts);
    }
    @Given("Existing invites")
    public void existingInvites() {
        FriendInvite friendInv = new FriendInvite("1", "Evaldas", "Tamutis", "https://" +
                "www.cutoutme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg", "1");
        FriendInvite friendInv1 = new FriendInvite("2", "Elon", "Musk", "https://" +
                "www.cutoutme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg", "2");
        friendInvites = new ArrayList<>();
        friendInvites.add(friendInv);
        friendInvites.add(friendInv1);

    }
    @When("User wants to get invites")
    public void userWantsToGetInvites() {

        when(mySqlConnection.getFriendInvites("1")).thenReturn(friendInvites);
    }

    @Then("User gets invites")
    public void userGetsInvites() {
        ArrayList<FriendInvite> result = repository.getFriendInvites("1");
        assertThat(result.size()).isEqualTo(2);
        assertEquals(result, friendInvites);
    }
    @Given("New user")
    public void newUser() {
        user = new User("1", "laurynas.zlatkus@gmail.com", "Laurynas", "Zlatkus",
                "911", "2020-01-15 18:25:16", "2020-05-15", "123456");

    }
    @When("Wants to add a new user")
    public void wantsToAddANewUser() {
        when(mySqlConnection.addUser(user)).thenReturn(true);
    }

    @Then("New user added")
    public void newUserAdded() {
        Boolean result = repository.addUser(user);
        assertEquals(result,true);
    }
    @When("Wants to insert friend invite")
    public void wantsToInsertFriendInvite() {
        when(mySqlConnection.insertFriendInvite("laurynas","Evaldas")).thenReturn(true);
    }

    @Then("Friend invite is inserted")
    public void friendInviteIsInserted() {
        boolean result = repository.insertFriendInvite("laurynas","Evaldas");
        assertEquals(result,true);
    }
    @Given("Friends list")
    public void friendsList() {
        Friend friend = new Friend("1", "Evaldas", "Tamutis",
                "https://www.cutoutme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg");
        Friend friend1 = new Friend("2", "Andrius", "Rimiškis",
                "https://www.cutoutme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg");
        friends = new ArrayList<>();
        friends.add(friend);
        friends.add(friend1);

    }
    @When("User wants to get all friends")
    public void userWantsToGetAllFriends() {
        when(mySqlConnection.getFriends("1")).thenReturn(friends);
    }

    @Then("User gets all friends")
    public void userGetsAllFriends() {
        ArrayList<Friend> result = repository.getFriends("1");
        assertThat(result.size()).isEqualTo(2);
        assertEquals(result, friends);
    }

    @Given("New post")
    public void NewPost() {
        post =new Post("1","Pavargau" ,"https://www.cuto" +
                "utme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg");

    }
    @When("User want to add a new post")
    public void userWantsToAddANewPost() {
        when(mySqlConnection.addPost(post)).thenReturn(true);
    }

    @Then("new post posted")
    public void newPostPosted() {
        boolean result = repository.addPost(post);
        assertEquals(result,true);
    }

    @Given("Existing city")
    public void existingCity() {
        String city = "Vilnius";

    }
    @When("User want to get the city")
    public void userWantsToGetTheCity() {
        when(mySqlConnection.getCity("1")).thenReturn(city);
    }

    @Then("The city is received")
    public void theCityisRecieved() {
        String result = repository.getCity("1");
        assertEquals(result,city);
    }
    @When("User wants to identify user")
    public void userWantsToIdentifyuser() {
        when(mySqlConnection.identifyUser("1")).thenReturn(user);
    }

    @Then("User is identified")
    public void userisIdentified() {
        User result = repository.identifyUser("1");
        assertEquals(result,user);
    }
}
