package lt.viko.eif.fivehorsemen.SocialNetworkAPI.data;

import lt.viko.eif.fivehorsemen.SocialNetworkAPI.repository.APIRepositoryImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FriendTest {

    private Friend friend;
    @BeforeEach
    void setUp(){
        friend = new Friend("1","Evaldas","Tamutis",
                "https://www.cutoutme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg");
    }

    @Test
    void getId() {

        String response = friend.getId();
        assertEquals("1",response);
    }

    @Test
    void getName() {
        String response = friend.getName();
        assertEquals("Evaldas",response);
    }

    @Test
    void getSurname() {
        String response = friend.getSurname();
        assertEquals("Tamutis",response);
    }

    @Test
    void getImageUrl() {
        String response = friend.getImageUrl();
        assertEquals("https://www.cutoutme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg",response);
    }
}