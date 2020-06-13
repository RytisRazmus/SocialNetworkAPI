package lt.viko.eif.fivehorsemen.SocialNetworkAPI.cucumber.api;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.*;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.repository.APIRepositoryImpl;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.client.RestTemplate;
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
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RunWith(MockitoJUnitRunner.class)
public class APIControllerStepDefs {

    @InjectMocks
    private lt.viko.eif.fivehorsemen.SocialNetworkAPI.api.APIController apiController;

    @Mock
    private APIRepositoryImpl repository;

    private String weatherApiKey = "9692b48fc9c249b4a960cd898a147223";

    private String loveApiKey = "045de38290mshb58ec6d51d4e6a9p1d0760jsn01c573420a6a";

    private String languageKey = "493ef97d248008918b1ed299b11626b1";

    private User user;
    private Map<String, String> map = new HashMap<>();
    private String userId;
    private ArrayList<Friend> friends = new ArrayList<>();
    private Post post;
    private Friend friend;
    private ArrayList<FriendPost> posts;
    private String city;
    private RestTemplate restTemplate;
    private String result;
    private HttpHeaders headers = new HttpHeaders();
    private int statusCode;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        HttpServletRequest httpServletRequestMock = new MockHttpServletRequest();
        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(httpServletRequestMock);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);
    }

    @Given("User enters his information")
    public void userEntersHisInformation() {

        user = new User("99", "albatrosas@kaunas.lt", "Joseph", "Stalin",
                "+340567261", "1953-03-05", "1878-12-18", "pazhalsta");
    }

    @When("User presses register")
    public void userPressesRegister() {

        when(repository.addUser(user)).thenReturn(true);
    }

    @Then("New User created")
    public void newUserCreated() {
        String result = apiController.register(user);
        assertEquals("User added.", result);
    }

    @Given("User enters email and password")
    public void userEntersEmailAndPassword() {
        user = new User("1", "laurynas.zlatkus@gmail.com", "Laurynas", "Zlatkus",
                "911", "2020-01-15 18:25:16", "2020-05-15", "123456");

        when(repository.getUser("laurynas.zlatkus@gmail.com","123456")).thenReturn(user);
    }

    @When("User tries to login")
    public void userTriesToLogin() {
        map.put("email", "laurynas.zlatkus@gmail.com");
        map.put("password", "123456");
    }

    @Then("User logs in")
    public void userLogsIn() {
        User result = apiController.login(map);
        assertEquals(result, user);
    }

    @Given("User has an id")
    public void userHasAnId() {
       this.userId = "3";
    }

    @When("User looks for friend invites")
    public void userLooksForFriendInvites() {
        FriendInvite friendInv1 = new FriendInvite("9", "Not Bruce", "Not Wayne", null,
                "46");
        ArrayList<FriendInvite> invites = new ArrayList<>();
        invites.add(friendInv1);
        when(repository.getFriendInvites(userId)).thenReturn(invites);

    }

    @Then("User sees friend invites")
    public void userSeesFriendInvites() {
        ArrayList<FriendInvite> friendInvites = apiController.getFriendInvites(userId);
        assertThat(friendInvites.size()).isEqualTo(1);
    }

    @Given("User is logged in")
    public void userIsLoggedIn() {
        this.userId = "3";
    }

    @When("User enters friend id")
    public void userEntersFriendId() {
        when(repository.insertFriendInvite("1", userId)).thenReturn(true);

    }

    @Then("Friend invite is sent")
    public void friendInviteIsSent() {
        Map<String, String> map = new HashMap<>();
        map.put("toUser", "1");
        map.put("fromUser", userId);
        String result = apiController.sendFriendInvite(map);
        assertEquals(result,"Invite sent.");
    }

    @Given("User id")
    public void userId() {
        this.userId = "3";
    }

    @When("User has friends")
    public void userHasFriends() {
        Friend friend = new Friend("1", "Evaldas", "Tamutis",
                "https://www.cutoutme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg");
        Friend friend1 = new Friend("2", "Andrius", "Rimiškis",
                "https://www.cutoutme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg");

        friends.add(friend);
        friends.add(friend1);
        when(repository.getFriends(userId)).thenReturn(friends);
    }

    @Then("Show friends")
    public void showFriends() {
        ArrayList<Friend> result = apiController.getFriends(userId);
        assertThat(result.size()).isEqualTo(2);
        assertEquals(result, friends);
    }

    @Given("User selects add post")
    public void userSelectsAddPost() {
        this.userId = "3";
    }

    @When("User enters necessary post data")
    public void userEntersNecessaryPostData() {
        post = new Post("1","Pavargau" ,"https://www.cuto" +
                "utme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg");
        when(repository.addPost(post)).thenReturn(true);
    }

    @Then("User adds a post")
    public void userAddsAPost() {
        String result = apiController.addPost(post);
        assertEquals(result,"Post added.");
    }

    @Given("User selects search")
    public void userSelectsSearch() {
        friend = new Friend("1", "Evaldas", "Tamutis",
                "https://www.cutoutme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg");
    }

    @When("User enters friends name")
    public void userEntersFriendsName() {

        Link link = linkTo(Friend.class).slash("/api/friends").withSelfRel();
        friend.setLink(link);
        friends = new ArrayList<>();
        friends.add(friend);
        when(repository.searchUser("Evaldas")).thenReturn(friends);
    }

    @Then("User sees a list of friends")
    public void userSeesAListOfFriends() {
        ArrayList<Friend> result = apiController.searchForFriend("Evaldas");
        assertEquals(result, friends);
    }

    @Given("User has a friend invite")
    public void userHasAFriendInvite() {
        friend = new Friend("4", "Evaldas", "Tamutis",
                "https://www.cutoutme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg");
    }

    @When("User wants to delete it")
    public void userWantsToDeleteIt() {
        when(repository.deleteFriendInv(friend.getId())).thenReturn(true);
    }

    @Then("Friend invite deleted")
    public void friendInviteDeleted() {
        Boolean result = apiController.deleteFriendInv(friend.getId());
        assertThat(result).isTrue();
    }

    @Given("User gets a friend invite")
    public void userGetsAFriendInvite() {
        friend = new Friend("4", "Evaldas", "Tamutis",
                "https://www.cutoutme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg");
        userId = "3";
    }

    @When("User accepts the friend invite")
    public void userAcceptsTheFriendInvite() {
        when(repository.acceptFriendInvite(userId, friend.getId())).thenReturn(true);
    }

    @Then("Friend is added")
    public void friendIsAdded() {
        boolean result = apiController.acceptFriend(userId, friend.getId());
        assertThat(result).isTrue();
    }

    @Given("User has friend who posted")
    public void usersHasFriendsWhoPosted() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = format.parse("2020-05-14");
        FriendPost friendPost = new FriendPost(myDate, "Laurynas", "Zlatkus", "1",
                "Maciau gera pana.", "https://upload.wikimedia.org/wikipedia/commons/0/0c/Cow" +
                "_female_black_white.jpg", "https://i.pinimg.com/474x/ff/5d/06/ff5d0624c089399d5736f8ce" +
                "0cc5ebeb.jpg");
        FriendPost friendPost1 = new FriendPost(myDate, "Evaldas", "Tamutis", "2",
                "Maciau gera pana.", "https://upload.wikimedia.org/wikipedia/commons/0/0c/Cow" +
                "_female_black_white.jpg", "https://i.pinimg.com/474x/ff/5d/06/ff5d0624c089399d5736f8ce" +
                "0cc5ebeb.jpg");
        posts = new ArrayList<>();
        posts.add(friendPost);
        posts.add(friendPost1);
    }

    @When("User opens posts feed")
    public void userOpensPostsFeed() {
        when(repository.getFriendPosts("1")).thenReturn(posts);
    }

    @Then("User sees posts made by friends")
    public void userSeesPostsMadeByFriends() {
        ArrayList<FriendPost> result = apiController.posts("1");
        assertEquals(result, posts);
    }


    @Given("User has specified his living location")
    public void userHasSpecifiedHisLivingLocation() throws ParseException {
        city = "Vilnius";
    }

    @When("User wants to see the weather")
    public void userWantsToSeeTheWeather() {
        restTemplate = new RestTemplate();
    }

    @Then("User gets weather information")
    public void userGetsWeatherInformation() {
        String uri = "https://api.weatherbit.io/v2.0/current?city=" + city + "&key=" + weatherApiKey;
        String result = restTemplate.getForObject(uri, String.class);
        assertThat(result).isNotNull();
    }

    @When("Client enters some text")
    public void clientEntersSomeText() {
        map = new HashMap<>();
        map.put("text", "Aš žinau ką veikei aną vasarą.");

    }

    @Then("The language is returned based on the text")
    public void languageIsReturned() {
        result = apiController.detectlanguage(map);
        assertThat(result).isNotNull();
    }

    @Given("User and his friend have an id")
    public void userAndFriendHaveId()  {
        user = new User("99", "albatrosas@kaunas.lt", "Joseph", "Stalin",
                "+340567261", "1953-03-05", "1878-12-18", "pazhalsta");
        friend = new Friend("9", "Evaldas", "Tamutis",
                "https://www.cutoutme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg");
    }

    @When("User presses calculate")
    public void userPressesCalculate() {
        String uri = "https://love-calculator.p.rapidapi.com/getPercentage?fname=" + user.getName() +
                "&sname=" + friend.getName();
        RestTemplate restTemplate = new RestTemplate();

        headers = new HttpHeaders();
        headers.set("x-rapidapi-host", "love-calculator.p.rapidapi.com");
        headers.set("x-rapidapi-key", loveApiKey);

        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                uri, HttpMethod.GET, entity, String.class);
        statusCode = response.getStatusCodeValue();
    }

    @Then("Love percent is returned")
    public void loveIsReturned() {

        assertEquals(200, statusCode);
    }

    @Given("Client specifies an email")
    public void clientSpecifiesAnEmail()  {
        user = new User("99", "albatrosas@kaunas.lt", "Joseph", "Stalin",
                "+340567261", "1953-03-05", "1878-12-18", "pazhalsta");
    }

    @When("Client sends a request")
    public void clientSendsARequest() {
        String uri = "https://api.mailboxvalidator.com/v1/validation/single?key=" + "W99YN42B0IJQXL3B5LYR" +
                "&format=json&email=" + user.getEmail();
        RestTemplate restTemplate = new RestTemplate();
        result = restTemplate.getForObject(uri, String.class);

    }

    @Then("Verification result is returned")
    public void resultIsReturned() {
        assertThat(result).isNotNull();
    }

}
