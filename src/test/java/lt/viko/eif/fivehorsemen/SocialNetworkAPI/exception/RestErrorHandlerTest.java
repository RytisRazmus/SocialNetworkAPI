package lt.viko.eif.fivehorsemen.SocialNetworkAPI.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class RestErrorHandlerTest {

    @Test
    public void handleAllExceptions() {
        NotFoundException notFoundException = new NotFoundException("Error", 500);
        RestErrorHandler restErrorHandler = new RestErrorHandler();
        ResponseEntity<Object> responseEntity = restErrorHandler.handleAllExceptions(notFoundException);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.valueOf(500));
    }
}