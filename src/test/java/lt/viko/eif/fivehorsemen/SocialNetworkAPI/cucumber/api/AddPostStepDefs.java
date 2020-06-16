package lt.viko.eif.fivehorsemen.SocialNetworkAPI.cucumber.api;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.Post;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.repository.APIRepositoryImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Add new post test
 *
 * @author Laurynas Zlatkus
 * @author Rytis Razmus
 * @author Jonas Zemaitis
 * @author Evaldas Tamutis
 * @author Evaldas Zalnierius
 */

public class AddPostStepDefs {

    private Post post;
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

    @Given("User is in the system")
    public void userIsInTheSystem() {
        userId = "1";
    }

    @When("User enters necessary post data")
    public void userEntersNecessaryPostData() {
        post = new Post(userId,"Pavargau" ,"https://www.cuto" +
                "utme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg");
        when(repository.addPost(post)).thenReturn(true);
    }

    @Then("User adds a post")
    public void userAddsAPost() {
        String result = apiController.addPost(post);
        assertEquals(result,"Post added.");
    }

}
