package lt.viko.eif.fivehorsemen.SocialNetworkAPI.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;
    @BeforeEach
    void setUp(){
        user = new User("1","laurynas.zlatkus@gmail.com","Laurynas","Zlatkus",
                "911","2020-01-15 18:25:16","2020-05-15","123456");
    }

    @Test
    void getPassword() {
        String response = user.getPassword();
        assertEquals("123456",response);
    }

    @Test
    void getLastSeen() {
        String response = user.getLastSeen();
        assertEquals("2020-01-15 18:25:16",response);
    }

    @Test
    void getDateOfBirth() {
        String response = user.getDateOfBirth();
        assertEquals("2020-05-15",response);
    }

    @Test
    void getId() {
        String response = user.getId();
        assertEquals("1",response);
    }

    @Test
    void getEmail() {
        String response = user.getEmail();
        assertEquals("laurynas.zlatkus@gmail.com",response);
    }

    @Test
    void getName() {
        String response = user.getName();
        assertEquals("Laurynas",response);
    }

    @Test
    void getSurname() {
        String response = user.getSurname();
        assertEquals("Zlatkus",response);
    }

    @Test
    void getPhoneNumber() {
        String response = user.getPhoneNumber();
        assertEquals("911",response);
    }
}