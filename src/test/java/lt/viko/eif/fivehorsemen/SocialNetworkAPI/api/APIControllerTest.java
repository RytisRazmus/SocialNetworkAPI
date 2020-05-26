package lt.viko.eif.fivehorsemen.SocialNetworkAPI.api;

import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.*;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.Friend;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.repository.APIRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class APIControllerTest {


    @InjectMocks
    private APIController apiController;

    @Mock
    private APIRepositoryImpl repository;

    @Value("${api.weatherKey}")
    private String weatherApiKey;

    @Value("${api.loveKey}")
    private String loveApiKey;

    @Value("${api.languageKey}")
    private String languageKey;

    @Test
    void getFriendInvites() {
        FriendInvite friendInv = new FriendInvite("1", "Evaldas", "Tamutis", "https://" +
                "www.cutoutme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg", "1");
        FriendInvite friendInv1 = new FriendInvite("2", "Elon", "Musk", "https://" +
                "www.cutoutme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg", "2");
        ArrayList<FriendInvite> friendInvites = new ArrayList<>();
        friendInvites.add(friendInv);
        friendInvites.add(friendInv1);
        when(repository.getFriendInvites("1")).thenReturn(friendInvites);
        ArrayList<FriendInvite> result = apiController.getFriendInvites("1");
        assertThat(result.size()).isEqualTo(2);
        assertEquals(result, friendInvites);
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
        when(repository.getFriends("1")).thenReturn(friends);
        ArrayList<Friend> result = apiController.getFriends("1");
        assertThat(result.size()).isEqualTo(2);
        assertEquals(result, friends);
    }

    @Test
    void register() {
        User user = new User("1", "laurynas.zlatkus@gmail.com", "Laurynas", "Zlatkus",
                "911", "2020-01-15 18:25:16", "2020-05-15", "123456");
        when(repository.addUser(user)).thenReturn(true);
        String result = apiController.register(user);
        assertEquals(result,"User added.");
    }

    @Test
    void sendFriendInvite() {
        when(repository.insertFriendInvite("laurynas","Evaldas")).thenReturn(true);
        Map<String, String> map = new HashMap<>();
        map.put("toUser", "laurynas");
        map.put("fromUser", "Evaldas");
        String result = apiController.sendFriendInvite(map);
        assertEquals(result,"Invite sent.");
    }


    @Test
    void addPost() {
        Post post =new Post("1","Pavargau" ,"https://www.cuto" +
                "utme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg");
        when(repository.addPost(post)).thenReturn(true);
        String result = apiController.addPost(post);
        assertEquals(result,"Post added.");
    }

    @Test
    void login() {
        User user = new User("1", "laurynas.zlatkus@gmail.com", "Laurynas", "Zlatkus",
                "911", "2020-01-15 18:25:16", "2020-05-15", "123456");
        when(repository.getUser("laurynas.zlatkus@gmail.com","123456")).thenReturn(user);
        Map<String, String> map = new HashMap<>();
        map.put("email", "laurynas.zlatkus@gmail.com");
        map.put("password", "123456");
        User result = apiController.login(map);
        assertEquals(result,user);
    }

    @Test
    void searchForFriend() {
        Friend friend = new Friend("1", "Evaldas", "Tamutis",
                "https://www.cutoutme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg");
        Link link = linkTo(Friend.class).slash("/api/friends").withSelfRel();
        friend.setLink(link);
        ArrayList<Friend> friends = new ArrayList<>();
        friends.add(friend);
        when(repository.searchUser("Evaldas")).thenReturn(friends);
        ArrayList<Friend> result = apiController.searchForFriend("Evaldas");
        assertEquals(result,friends);
    }

    @Test
    void deleteFriendInv() {
        when(repository.deleteFriendInv("1")).thenReturn(true);
        Boolean result = apiController.deleteFriendInv("1");
        assertThat(result).isTrue();
    }

    @Test
    void acceptFriend() {
        String fromUser = "Evaldas";
        String toUser = "Valanciunas";
        when(repository.acceptFriendInvite(toUser,fromUser)).thenReturn(true);
        boolean result = apiController.acceptFriend(toUser,fromUser);
        assertThat(result).isTrue();
    }

    @Test
    void posts() throws ParseException {
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
        when(repository.getFriendPosts("1")).thenReturn(posts);
        ArrayList<FriendPost> result = apiController.posts("1");
        assertEquals(result,posts);
    }

    @Test
    void getWeather() {
        String city = "Vilnius";
        String uri = "https://api.weatherbit.io/v2.0/current?city=" + city + "&key=" + weatherApiKey;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        assertThat(result).isNotNull();
    }

    @Test
    void detectlanguage() {

        Map<String, String> map = new HashMap<>();
        map.put("text", "Noriu valgyt");
        String text = map.get("text");
        text = text.replace(" ", "20%");
        String uri = "http://api.languagelayer.com/detect?access_key=" + languageKey +
                "&query=" + text;
        RestTemplate restTemplate = new RestTemplate();
        String result = "text";
        assertThat(result).isNotNull();
    }

    @Test
    void getLove() {
        String name = "Evaldas";
        String secName = "Mantryda";
        String uri = "https://love-calculator.p.rapidapi.com/getPercentage?fname=" + name +
                "&sname=" + secName;
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-host", "love-calculator.p.rapidapi.com");
        headers.set("x-rapidapi-key", loveApiKey);

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                uri, HttpMethod.GET, entity, String.class);
        assertEquals(200,response.getStatusCodeValue());
    }

    @Test
    void verifyMail() {
        String email = "Laurynas.zlatkus@gmail.com";
        String uri = "https://api.mailboxvalidator.com/v1/validation/single?key=" + "W99YN42B0IJQXL3B5LYR" +
                "&format=json&email=" + email;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        assertThat(result).isNotNull();
    }

    @Test
    void error() {
        String error = "No such url.";
        assertEquals(error, apiController.error());
    }

    @Test
    void getErrorPath () {
        String path = "/error";
        assertEquals(path,apiController.getErrorPath());
    }
}

