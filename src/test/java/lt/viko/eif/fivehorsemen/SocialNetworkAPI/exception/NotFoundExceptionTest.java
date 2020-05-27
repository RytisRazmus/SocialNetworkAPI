package lt.viko.eif.fivehorsemen.SocialNetworkAPI.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The NotFoundException class tests
 *
 * @author Laurynas Zlatkus
 * @author Rytis Razmus
 * @author Jonas Zemaitis
 * @author Evaldas Tamutis
 * @author Evaldas Zalnierius
 */

public class NotFoundExceptionTest {

    private NotFoundException notFoundException;

    /**
     * SetUp for tests
     */

    @BeforeEach
    void setUp(){
        notFoundException = new NotFoundException("Error", 500);
    }

    /**
     * Test of setErrorCode from NotFoundException class
     */

    @Test
    public void setErrorCode() {
        notFoundException.setErrorCode(502);
        assertEquals(502, notFoundException.getErrorCode());
    }

    /**
     * Test of getErrorCode from NotFoundException class
     */

    @Test
    public void getErrorCode() {
        int response = notFoundException.getErrorCode();
        assertEquals(500, response);
    }

    /**
     * Test of setErrorMessage from NotFoundException class
     */

    @Test
    public void setErrorMessage() {
        notFoundException.setErrorMessage("Errors!");
        assertEquals("Errors!", notFoundException.getErrorMessage());
    }

    /**
     * Test of getErrorMessage from NotFoundException class
     */

    @Test
    public void getErrorMessage() {
        String response = notFoundException.getErrorMessage();
        assertEquals("Error", response);
    }
}