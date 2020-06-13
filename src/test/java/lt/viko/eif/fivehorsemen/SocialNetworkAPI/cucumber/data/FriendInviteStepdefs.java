package lt.viko.eif.fivehorsemen.SocialNetworkAPI.cucumber.data;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.Friend;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.FriendInvite;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.FriendPost;
import org.springframework.hateoas.Link;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class FriendInviteStepdefs {

    private FriendInvite friendInv;
    private String result;

    @Given("Existing user and friend invite")
    public void existingUserAndFriendInvite() {
        friendInv = new FriendInvite("9", "Not Bruce", "Not Wayne", null, "36");

        HttpServletRequest httpServletRequestMock = new MockHttpServletRequest();
        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(httpServletRequestMock);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);
    }

    @When("Asks for future friend's id")
    public void asksForFutureFriendsId() {
        result = friendInv.getInviteId();
    }

    @Then("Received future friend's id")
    public void receivedFutureFriendsId() {
        assertEquals(result, "36");
    }

    @When("Asks for friend invite's links")
    public void asksForFriendsPostsLinks() {
        Link link = linkTo(Friend.class).slash("/api/friendInvites").withSelfRel();
        friendInv.setLink(link);
    }

    @Then("Received friend invite's links")
    public void receviedFriendsInvitesLinks() {
        Link link = linkTo(Friend.class).slash("/api/friendInvites").withSelfRel();
        assertEquals(link, friendInv.getLink());
    }
    @When("Provides friend invite's links")
    public void proviedsFriendsInvitesLinks() {
        Link link = linkTo(Friend.class).slash("/api/friendInvites").withSelfRel();
        friendInv.setLink(link);
    }

    @Then("Links for friend invite sent")
    public void linksToFriendsInvitesSent() {
        Link link = linkTo(Friend.class).slash("/api/friendInvites").withSelfRel();
        assertEquals(link, friendInv.getLink());
    }
}
