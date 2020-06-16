package lt.viko.eif.fivehorsemen.SocialNetworkAPI.cucumber.data;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.Friend;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.FriendPost;
import org.springframework.hateoas.Link;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Friend posts object test
 *
 * @author Laurynas Zlatkus
 * @author Rytis Razmus
 * @author Jonas Zemaitis
 * @author Evaldas Tamutis
 * @author Evaldas Zalnierius
 */

public class FriendPostStepdefs {

    private FriendPost friendPost;
    private String result;

    @Given("Existing friend's post")
    public void existingFriendsPost() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = format.parse("2020-05-14");

        friendPost = new FriendPost(myDate,"Laurynas","Zlatkus","1",
                "Maciau gera pana.","https://upload.wikimedia.org/wikipedia/commons/0/0c/Cow" +
                "_female_black_white.jpg","https://i.pinimg.com/474x/ff/5d/06/ff5d0624c089399d5736f8ce" +
                "0cc5ebeb.jpg");

        HttpServletRequest httpServletRequestMock = new MockHttpServletRequest();
        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(httpServletRequestMock);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);
    }

    @When("Asks for post's date")
    public void askForPostsDate() {
        SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
        result = dmyFormat.format(friendPost.getDate());
    }

    @Then("Received date")
    public void receivedDate() {
        assertEquals(result, "2020-05-14");
    }

    @When("Asks for profile image")
    public void askForProfileImage() {
        result = friendPost.getProfileImage();
    }

    @Then("Received profile image")
    public void receivedProfileImage() {
        assertEquals(result, "https://i.pinimg.com/474x/ff/5d/06/ff5d0624c089399d5736f8ce" +
                "0cc5ebeb.jpg");
    }

    @When("Asks for friend's name")
    public void askForFriendsName() {
        result = friendPost.getName();
    }

    @Then("Received friend's name")
    public void receivedFriendsName() {
        assertEquals(result, "Laurynas");
    }

    @When("Asks for friend's surname")
    public void askForFriendsSurmame() {
        result = friendPost.getSurname();
    }

    @Then("Received friend's surname")
    public void receivedFriendsSurname() {
        assertEquals(result, "Zlatkus");
    }

    @When("Asks for friend's posts links")
    public void asksForFriendsPostsLinks() {
        Link link = linkTo(FriendPost.class).slash("/api/posts").withSelfRel();
        friendPost.setLink(link);
    }

    @Then("Received friend's posts links")
    public void receviedFriendsPostsLinks() {
        Link link = linkTo(FriendPost.class).slash("/api/posts").withSelfRel();
        assertEquals(link, friendPost.getLink());
    }
    @When("Provides friend's posts links")
    public void proviedsFriendsPostsLinks() {
        Link link = linkTo(FriendPost.class).slash("/api/posts").withSelfRel();
        friendPost.setLink(link);
    }

    @Then("Links to friend's posts sent")
    public void linksToFriendsPostsSent() {
        Link link = linkTo(FriendPost.class).slash("/api/posts").withSelfRel();
        assertEquals(link, friendPost.getLink());
    }
}

