package lt.viko.eif.fivehorsemen.SocialNetworkAPI.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The CustomExceptionSchema class tests
 *
 * @author Laurynas Zlatkus
 * @author Rytis Razmus
 * @author Jonas Zemaitis
 * @author Evaldas Tamutis
 * @author Evaldas Zalnierius
 */

class CustomExceptionSchemaTest {

    private CustomExceptionSchema CES;

    /**
     * SetUp for tests
     */

    @BeforeEach
    void setUp(){
        CES = new CustomExceptionSchema("Not Found",404);
    }

    /**
     * Test of getMessage from CustomExceptionSchema class
     */

    @Test
    void getMessage() {
        String response = CES.getMessage();
        assertEquals("Not Found",response);
    }

    /**
     * Test of getErrorCode from CustomExceptionSchema class
     */

    @Test
    void getErrorCode() {
        int response = CES.getErrorCode();
        assertEquals(404,response);
    }
}