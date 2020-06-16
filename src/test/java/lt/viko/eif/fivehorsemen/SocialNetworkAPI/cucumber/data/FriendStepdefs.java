package lt.viko.eif.fivehorsemen.SocialNetworkAPI.cucumber.data;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.Friend;
import org.springframework.hateoas.Link;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Friend profile object test
 *
 * @author Laurynas Zlatkus
 * @author Rytis Razmus
 * @author Jonas Zemaitis
 * @author Evaldas Tamutis
 * @author Evaldas Zalnierius
 */

public class FriendStepdefs {

    private Friend friend;
    private String result;

    @Given("Existing friend")
    public void existingFriend() {
        friend = new Friend("1","Evaldas","Tamutis",
                "https://www.cutoutme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg");
        HttpServletRequest httpServletRequestMock = new MockHttpServletRequest();
        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(httpServletRequestMock);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);
    }

    @When("Asks for friend's id")
    public void asksForFriendsId() {
        result = friend.getId();
    }

    @Then("Received friend's id")
    public void receivedFriendsId() {
        assertEquals(result, "1");
    }

    @When("Asks for friend name")
    public void asksForFriendName() {
        result = friend.getName();
    }

    @Then("Received friend name")
    public void receivedFriendName() {
        assertEquals(result, "Evaldas");
    }

    @When("Asks for friend surname")
    public void asksForFriendSurname() {
        result = friend.getSurname();
    }

    @Then("Received friend surname")
    public void receivedFriendSurame() {
        assertEquals(result, "Tamutis");
    }

    @When("Asks for friend's image")
    public void asksForFriendsImage() {
        result = friend.getImageUrl();
    }

    @Then("Received friend's image")
    public void receivedFriendsImage() {
        assertEquals(result, "https://www.cutoutme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg");
    }

    @When("Asks for friends links")
    public void asksForFriendsLinks() {
        Link link = linkTo(Friend.class).slash("/api/friends").withSelfRel();
        friend.setLink(link);
    }

    @Then("Received links of friends")
    public void receviedFriendsLinks() {
        Link link = linkTo(Friend.class).slash("/api/friends").withSelfRel();
        assertEquals(link, friend.getLink());
    }
    @When("Provides friends links")
    public void providesFriendsLinks() {
        Link link = linkTo(Friend.class).slash("/api/friends").withSelfRel();
        friend.setLink(link);
    }

    @Then("Links of friends sent")
    public void linksFriendsSent() {
        Link link = linkTo(Friend.class).slash("/api/friends").withSelfRel();
        assertEquals(link, friend.getLink());
    }
}
