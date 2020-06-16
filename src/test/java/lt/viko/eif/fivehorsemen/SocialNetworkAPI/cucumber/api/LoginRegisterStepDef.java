package lt.viko.eif.fivehorsemen.SocialNetworkAPI.cucumber.api;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.User;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.repository.APIRepositoryImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * New user registration test
 *
 * @author Laurynas Zlatkus
 * @author Rytis Razmus
 * @author Jonas Zemaitis
 * @author Evaldas Tamutis
 * @author Evaldas Zalnierius
 */

public class LoginRegisterStepDef {

    @InjectMocks
    private lt.viko.eif.fivehorsemen.SocialNetworkAPI.api.APIController apiController;

    @Mock
    private APIRepositoryImpl repository;

    private User user;
    private Map<String, String> map = new HashMap<>();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        HttpServletRequest httpServletRequestMock = new MockHttpServletRequest();
        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(httpServletRequestMock);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);
    }

    @Given("User enters his information")
    public void userEntersHisInformation() {

        user = new User("99", "albatrosas@kaunas.lt", "Joseph", "Stalin",
                "+340567261", "1953-03-05", "1878-12-18", "pazhalsta");
    }

    @When("User presses register")
    public void userPressesRegister() {

        when(repository.addUser(user)).thenReturn(true);
    }

    @Then("New User created")
    public void newUserCreated() {
        String result = apiController.register(user);
        assertEquals("User added.", result);
    }

    @Given("User is already registered in the system")
    public void userEntersEmailAndPassword() {
        user = new User("1", "laurynas.zlatkus@gmail.com", "Laurynas", "Zlatkus",
                "911", "2020-01-15 18:25:16", "2020-05-15", "123456");


    }

    @When("User enters email and password")
    public void userTriesToLogin() {
        when(repository.getUser("laurynas.zlatkus@gmail.com","123456")).thenReturn(user);
        map.put("email", "laurynas.zlatkus@gmail.com");
        map.put("password", "123456");
    }

    @Then("User logs in")
    public void userLogsIn() {
        User result = apiController.login(map);
        assertEquals(result, user);
    }

}
