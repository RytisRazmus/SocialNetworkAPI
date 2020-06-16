package lt.viko.eif.fivehorsemen.SocialNetworkAPI.cucumber.api;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.Friend;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.repository.APIRepositoryImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.Link;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Search for friend test
 *
 * @author Laurynas Zlatkus
 * @author Rytis Razmus
 * @author Jonas Zemaitis
 * @author Evaldas Tamutis
 * @author Evaldas Zalnierius
 */

public class FriendSearchStepDefs {

    private ArrayList<Friend> friends = new ArrayList<>();
    private Friend friend;

    @InjectMocks
    private lt.viko.eif.fivehorsemen.SocialNetworkAPI.api.APIController apiController;

    @Mock
    private APIRepositoryImpl repository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        HttpServletRequest httpServletRequestMock = new MockHttpServletRequest();
        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(httpServletRequestMock);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);
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
}
