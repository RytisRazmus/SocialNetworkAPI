package lt.viko.eif.fivehorsemen.SocialNetworkAPI.cucumber.exception;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.database.MySqlConnection;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.exception.CustomExceptionSchema;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.exception.NotFoundException;

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

public class NotFoundExceptionStepDefs {

    private static NotFoundException notFoundException;

    private String message;
    private int code;
    static boolean exists = false;

    @Before
    public static void setUp() {
        if(!exists) {
            notFoundException = new NotFoundException("Error", 500);
            exists = true;
        }
    }

    @Given("NotFound Exception exists")
    public void notfound_Exception_exists() {
        if(!exists){
            setUp();
            exists = true;
        }
    }

    @Then("Set error code")
    public void set_error_code() {
        notFoundException.setErrorCode(502);
    }

    @Then("Get error code")
    public void get_error_code() {
        assertEquals(502, notFoundException.getErrorCode());
    }

    @Then("Set error message")
    public void set_error_message() {
        notFoundException.setErrorMessage("Errors!");
    }

    @Then("Get error message")
    public void get_error_message() {
        assertEquals("Errors!", notFoundException.getErrorMessage());
    }

}