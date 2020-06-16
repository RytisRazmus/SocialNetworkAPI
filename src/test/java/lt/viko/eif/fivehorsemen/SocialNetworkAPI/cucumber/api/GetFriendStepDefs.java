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
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GetFriendStepDefs {

    private ArrayList<Friend> friends = new ArrayList<>();
    private String userId;

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

    @Given("User is registered")
    public void userHasAnId() {
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
}
