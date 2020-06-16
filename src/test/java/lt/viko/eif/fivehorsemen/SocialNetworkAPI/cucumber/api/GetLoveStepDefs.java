package lt.viko.eif.fivehorsemen.SocialNetworkAPI.cucumber.api;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.Friend;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.User;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetLoveStepDefs {

    private User user;
    private Friend friend;
    private HttpHeaders headers = new HttpHeaders();
    private int statusCode;
    private String loveApiKey = "045de38290mshb58ec6d51d4e6a9p1d0760jsn01c573420a6a";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        HttpServletRequest httpServletRequestMock = new MockHttpServletRequest();
        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(httpServletRequestMock);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);
    }

    @Given("User and his friend have an id")
    public void userAndFriendHaveId()  {
        user = new User("99", "albatrosas@kaunas.lt", "Joseph", "Stalin",
                "+340567261", "1953-03-05", "1878-12-18", "pazhalsta");
        friend = new Friend("9", "Evaldas", "Tamutis",
                "https://www.cutoutme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg");
    }

    @When("User presses calculate")
    public void userPressesCalculate() {
        String uri = "https://love-calculator.p.rapidapi.com/getPercentage?fname=" + user.getName() +
                "&sname=" + friend.getName();
        RestTemplate restTemplate = new RestTemplate();

        headers = new HttpHeaders();
        headers.set("x-rapidapi-host", "love-calculator.p.rapidapi.com");
        headers.set("x-rapidapi-key", loveApiKey);

        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                uri, HttpMethod.GET, entity, String.class);
        statusCode = response.getStatusCodeValue();
    }

    @Then("Love percent is returned")
    public void loveIsReturned() {
        assertEquals(200, statusCode);
    }
}
