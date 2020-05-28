package lt.viko.eif.fivehorsemen.SocialNetworkAPI.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The Post class tests
 *
 * @author Laurynas Zlatkus
 * @author Rytis Razmus
 * @author Jonas Zemaitis
 * @author Evaldas Tamutis
 * @author Evaldas Zalnierius
 */


public class PostTest {

    private Post post;

    /**
     * SetUp for tests
     */

    @BeforeEach
    void setUp(){
        post = new Post("2", "I feel like I'm on fiiiiire.",
                "https://static.eurovision.tv/hb-cgi/images/8fd8837b-fb88-461b-8ac7-412b3612146e/hero.jpeg");
    }

    /**
     * Test of getUserId from Post class
     */

    @Test
    public void getUserId() {
        String response = post.getUserId();
        assertEquals("2",response);
    }

    /**
     * Test of getImageUrl from Post class
     */

    @Test
    public void getImageUrl() {
        String response = post.getImageUrl();
        assertEquals("https://static.eurovision.tv/hb-cgi/images/8fd8837b-fb88-461b-8ac7-412b3612146e/hero.jpeg"
                ,response);
    }

    /**
     * Test of getDescription from Post class
     */

    @Test
    public void getDescription() {
        String response = post.getDescription();
        assertEquals("I feel like I'm on fiiiiire.",response);
    }
}