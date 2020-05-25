package lt.viko.eif.fivehorsemen.SocialNetworkAPI.api;
<<<<<<< HEAD
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.*;
=======

import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.Friend;
>>>>>>> 0935b39209e18ce316d776ee0ba9988ef6e68742
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.repository.APIRepositoryImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.Link;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
<<<<<<< HEAD
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
=======

>>>>>>> 0935b39209e18ce316d776ee0ba9988ef6e68742
import java.util.ArrayList;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
<<<<<<< HEAD
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

=======
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
>>>>>>> 0935b39209e18ce316d776ee0ba9988ef6e68742

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class APIControllerTest {

    @InjectMocks
    APIController apiController;

    @Mock
    APIRepositoryImpl repository;

    @Test
    @Before
    private void setUp() {
        HttpServletRequest httpServletRequestMock = new MockHttpServletRequest();
        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(httpServletRequestMock);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);

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
        when(repository.getFriendInvites("1")).thenReturn(friendInvites);
        ArrayList<FriendInvite> result = apiController.getFriendInvites("1");
        assertThat(result.size()).isEqualTo(2);
        assertEquals(result, friendInvites);
    }

    @Test
    void register() throws Exception {

    }

    @Test
    void sendFriendInvite() {
    }

    @Test
    void getFriends() throws Exception {
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
    void addPost() {

    }

    @Test
    void login() {
        User user = new User("1", "laurynas.zlatkus@gmail.com", "Laurynas", "Zlatkus",
                "911", "2020-01-15 18:25:16", "2020-05-15", "123456");
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
    }

    @Test
    void acceptFriend() {
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


    }

    @Test
    void detectlanguage() {
    }

    @Test
    void getLove() {
    }

    @Test
    void verifyMail() {
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

