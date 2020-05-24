package lt.viko.eif.fivehorsemen.SocialNetworkAPI.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomExceptionSchemaTest {

    CustomExceptionSchema CES;
    @BeforeEach
    void setUp(){
        CES = new CustomExceptionSchema("Not Found",404);
    }

    @Test
    void getMessage() {
        String response = CES.getMessage();
        assertEquals("Not Found",response);
    }

    @Test
    void getErrorCode() {
        int response = CES.getErrorCode();
        assertEquals(404,response);
    }
}