package lt.viko.eif.fivehorsemen.SocialNetworkAPI.cucumber.api;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.FriendInvite;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.repository.APIRepositoryImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * See friend invites test
 *
 * @author Laurynas Zlatkus
 * @author Rytis Razmus
 * @author Jonas Zemaitis
 * @author Evaldas Tamutis
 * @author Evaldas Zalnierius
 */

@RunWith(Cucumber.class)
public class FriendInvitesStepDefs {

    @InjectMocks
    private lt.viko.eif.fivehorsemen.SocialNetworkAPI.api.APIController apiController;

    @Mock
    private APIRepositoryImpl repository;

    private String userId;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        HttpServletRequest httpServletRequestMock = new MockHttpServletRequest();
        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(httpServletRequestMock);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);
    }

    @Given("User is registered in the system")
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
}
