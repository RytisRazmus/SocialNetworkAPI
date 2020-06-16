package lt.viko.eif.fivehorsemen.SocialNetworkAPI.cucumber.exception;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.exception.NotFoundException;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.exception.RestErrorHandler;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The RestErrorHandler class tests
 *
 * @author Laurynas Zlatkus
 * @author Rytis Razmus
 * @author Jonas Zemaitis
 * @author Evaldas Tamutis
 * @author Evaldas Zalnierius
 */

public class RestErrorHandlerStepDefs {

    private NotFoundException notFoundException;
    private RestErrorHandler restErrorHandler;
    private ResponseEntity<Object> responseEntity;

    @Given("NotFoundException exists")
    public void notfoundexception_exists() {
        notFoundException = new NotFoundException("Error", 500);
    }

    @Given("RestErrorHandler exists")
    public void resterrorhandler_exists() {
        restErrorHandler = new RestErrorHandler();
    }

    @When("ResponseEntity has been created")
    public void responseentity_has_been_created() {
        responseEntity = restErrorHandler.handleAllExceptions(notFoundException);
    }

    @Then("Get ResponseEntity status code")
    public void get_ResponseEntity_status_code() {
        assertEquals(responseEntity.getStatusCode(), HttpStatus.valueOf(500));
    }

}