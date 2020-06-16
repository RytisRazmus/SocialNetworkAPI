package lt.viko.eif.fivehorsemen.SocialNetworkAPI.cucumber.data;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.User;
import org.springframework.hateoas.Link;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * User profile object test
 *
 * @author Laurynas Zlatkus
 * @author Rytis Razmus
 * @author Jonas Zemaitis
 * @author Evaldas Tamutis
 * @author Evaldas Zalnierius
 */

public class UserStepdefs {

    private User user;
    private String result;

    @Given("Existing user")
    public void existingUser() {
        user = new User("99", "albatrosas@kaunas.lt", "Joseph", "Stalin",
                "+340567261", "1953-03-05", "1878-12-18", "pazhalsta");
        HttpServletRequest httpServletRequestMock = new MockHttpServletRequest();
        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(httpServletRequestMock);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);
    }

    @When("Asks for password")
    public void asksForPassword() {
        result = user.getPassword();
    }

    @Then("Received password")
    public void receivedPassword() {
        assertEquals(result, "pazhalsta");
    }

    @When("Asks last seen date")
    public void asksLastSeenDate() {
        result = user.getLastSeen();
    }

    @Then("Last seen date received")
    public void lastSeenDateReceived() {
        assertEquals(result, "1953-03-05");
    }

    @When("Asks for birthday")
    public void asksForBirthday() {
        result = user.getDateOfBirth();
    }

    @Then("Birthday date received")
    public void birthdayDateReceived() {
        assertEquals(result, "1878-12-18");
    }

    @When("Asks for Id")
    public void asksForId() {
        result = user.getId();
    }

    @Then("Received Id")
    public void receivedId() {
        assertEquals(result, "99");
    }

    @When("Asks for email")
    public void asksForEmail() {
        result = user.getEmail();
    }

    @Then("Received email")
    public void receivedEmail() {
        assertEquals(result, "albatrosas@kaunas.lt");
    }

    @When("Asks for name")
    public void asksForName() {
        result = user.getName();
    }

    @Then("Received name")
    public void receivedName() {
        assertEquals(result, "Joseph");
    }

    @When("Asks for surname")
    public void asksForSurname() {
        result = user.getSurname();
    }

    @Then("Received surname")
    public void receivedSurname() {
        assertEquals(result, "Stalin");
    }

    @When("Asks for Phone number")
    public void asksForPhoneNumber() {
        result = user.getPhoneNumber();
    }

    @Then("Received Phone number")
    public void receivedPhoneNumber() {
        assertEquals(result, "+340567261");
    }

    @When("Asks for links")
    public void asksForLinks() {
        Link link = linkTo(User.class).slash("/api/login").withSelfRel();
        user.setLink(link);
    }

    @Then("Received links")
    public void receviedLinks() {
        Link link = linkTo(User.class).slash("/api/login").withSelfRel();
        assertEquals(link, user.getLink());
    }
    @When("Provides links")
    public void proviedsLinks() {
        Link link = linkTo(User.class).slash("/api/login").withSelfRel();
        user.setLink(link);
    }

    @Then("Links sent")
    public void linksSent() {
        Link link = linkTo(User.class).slash("/api/login").withSelfRel();
        assertEquals(link, user.getLink());
    }
}
