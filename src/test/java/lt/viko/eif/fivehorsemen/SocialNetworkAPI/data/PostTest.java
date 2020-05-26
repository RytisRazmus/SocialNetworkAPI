package lt.viko.eif.fivehorsemen.SocialNetworkAPI.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostTest {

    private Post post;

    @BeforeEach
    void setUp(){
        post = new Post("2", "I feel like I'm on fiiiiire.",
                "https://static.eurovision.tv/hb-cgi/images/8fd8837b-fb88-461b-8ac7-412b3612146e/hero.jpeg");
    }

    @Test
    public void getUserId() {
        String response = post.getUserId();
        assertEquals("2",response);
    }

    @Test
    public void getImageUrl() {
        String response = post.getImageUrl();
        assertEquals("https://static.eurovision.tv/hb-cgi/images/8fd8837b-fb88-461b-8ac7-412b3612146e/hero.jpeg"
                ,response);
    }

    @Test
    public void getDescription() {
        String response = post.getDescription();
        assertEquals("I feel like I'm on fiiiiire.",response);
    }
}