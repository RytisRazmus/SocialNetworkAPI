package lt.viko.eif.fivehorsemen.SocialNetworkAPI.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NotFoundExceptionTest {

    private NotFoundException notFoundException;

    @BeforeEach
    void setUp(){
        notFoundException = new NotFoundException("Error", 500);
    }

    @Test
    public void setErrorCode() {
        notFoundException.setErrorCode(502);
        assertEquals(502, notFoundException.getErrorCode());
    }

    @Test
    public void getErrorCode() {
        int response = notFoundException.getErrorCode();
        assertEquals(500, response);
    }

    @Test
    public void setErrorMessage() {
        notFoundException.setErrorMessage("Errors!");
        assertEquals("Errors!", notFoundException.getErrorMessage());
    }

    @Test
    public void getErrorMessage() {
        String response = notFoundException.getErrorMessage();
        assertEquals("Error", response);
    }
}