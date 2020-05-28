package lt.viko.eif.fivehorsemen.SocialNetworkAPI.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The RestErrorHandler class tests
 *
 * @author Laurynas Zlatkus
 * @author Rytis Razmus
 * @author Jonas Zemaitis
 * @author Evaldas Tamutis
 * @author Evaldas Zalnierius
 */

public class RestErrorHandlerTest {

    /**
     * Test of handleAllException from RestErrorHandler class
     */

    @Test
    public void handleAllExceptions() {
        NotFoundException notFoundException = new NotFoundException("Error", 500);
        RestErrorHandler restErrorHandler = new RestErrorHandler();
        ResponseEntity<Object> responseEntity = restErrorHandler.handleAllExceptions(notFoundException);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.valueOf(500));
    }
}