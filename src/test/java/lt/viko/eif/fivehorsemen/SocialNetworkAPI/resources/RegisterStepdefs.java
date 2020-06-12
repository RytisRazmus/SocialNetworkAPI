package lt.viko.eif.fivehorsemen.SocialNetworkAPI.resources;


import com.fasterxml.jackson.core.JsonProcessingException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.api.APIController;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.User;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.repository.APIRepositoryImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegisterStepdefs {

    @InjectMocks
    private APIController apiController;

    @Mock
    private APIRepositoryImpl repository;
    private User user;

    @Given("User enters his information")
    public void userEntersHisInformation() {
        user = new User("99", "albatrosas@kaunas.lt", "Joseph", "Stalin",
                "+340567261", "1953-03-05", "1878-12-18", "pazhalsta");
    }

    @When("User presses register")
    public void userPressesRegister() throws JsonProcessingException, UnsupportedEncodingException {
        MockitoAnnotations.initMocks(this);
        when(repository.addUser(user)).thenReturn(true);
    }

    @Then("New User created")
    public void newUserCreated() throws IOException {
        String result = apiController.register(user);
        assertEquals("User added.", result);
    }

}
