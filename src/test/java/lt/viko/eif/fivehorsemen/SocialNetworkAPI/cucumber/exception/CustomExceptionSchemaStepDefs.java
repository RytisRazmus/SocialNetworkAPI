package lt.viko.eif.fivehorsemen.SocialNetworkAPI.cucumber.exception;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.CucumberOptions;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.exception.CustomExceptionSchema;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The CustomExceptionSchema class tests
 *
 * @author Laurynas Zlatkus
 * @author Rytis Razmus
 * @author Jonas Zemaitis
 * @author Evaldas Tamutis
 * @author Evaldas Zalnierius
 */

public class CustomExceptionSchemaStepDefs {

    private CustomExceptionSchema CES;

    private String message;
    private int code;

    @Given("Exception exists")
    public void exception_exists() {
        CES = new CustomExceptionSchema("Not Found",404);
    }

    @When("Message is requested")
    public void message_is_requested() {
        message = CES.getMessage();
    }

    @Then("Receive error message")
    public void receive_error_message() {
        assertEquals("Not Found",message);
    }

    @When("Error code is requested")
    public void error_code_is_requested() {
        code = CES.getErrorCode();
    }

    @Then("Receive error code")
    public void receive_error_code() {
        assertEquals(404,code);
    }

}