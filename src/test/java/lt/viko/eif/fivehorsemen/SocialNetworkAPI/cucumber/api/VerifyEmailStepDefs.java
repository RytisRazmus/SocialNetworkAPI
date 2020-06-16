package lt.viko.eif.fivehorsemen.SocialNetworkAPI.cucumber.api;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.User;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;

public class VerifyEmailStepDefs {
    private User user;

    private String result;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        HttpServletRequest httpServletRequestMock = new MockHttpServletRequest();
        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(httpServletRequestMock);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);
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
