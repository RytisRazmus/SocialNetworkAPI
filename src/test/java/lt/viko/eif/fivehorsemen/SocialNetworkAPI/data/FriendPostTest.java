package lt.viko.eif.fivehorsemen.SocialNetworkAPI.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.Link;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * The FriendPost class tests
 *
 * @author Laurynas Zlatkus
 * @author Rytis Razmus
 * @author Jonas Zemaitis
 * @author Evaldas Tamutis
 * @author Evaldas Zalnierius
 */

class FriendPostTest {

    private FriendPost friendPost;

    /**
     * SetUp for tests
     */

    @BeforeEach
    void setUp() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = format.parse("2020-05-14");

        friendPost = new FriendPost(myDate,"Laurynas","Zlatkus","1",
                "Maciau gera pana.","https://upload.wikimedia.org/wikipedia/commons/0/0c/Cow" +
                "_female_black_white.jpg","https://i.pinimg.com/474x/ff/5d/06/ff5d0624c089399d5736f8ce" +
                "0cc5ebeb.jpg");

        HttpServletRequest httpServletRequestMock = new MockHttpServletRequest();
        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(httpServletRequestMock);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);
    }

    /**
     * Test of getDate from FriendPost class
     */

    @Test
    void getDate() {
        SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");

        String response = dmyFormat.format(friendPost.getDate());
        assertEquals("2020-05-14",response);
    }

    /**
     * Test of getProfileImage from FriendPost class
     */

    @Test
    void getProfileImage() {
        String response = friendPost.getProfileImage();
        assertEquals("https://i.pinimg.com/474x/ff/5d/06/ff5d0624c089399d5736f8ce0cc5ebeb.jpg",response);
    }

    /**
     * Test of getName from FriendPost class
     */

    @Test
    void getName() {
        String response = friendPost.getName();
        assertEquals("Laurynas",response);
    }

    /**
     * Test of getSurname from FriendPost class
     */

    @Test
    void getSurname() {
        String response = friendPost.getSurname();
        assertEquals("Zlatkus",response);
    }

    /**
     * Test of getLink from FriendPost class
     */

    @Test
    void getLink(){
        Link link = linkTo(FriendPost.class).slash("/api/posts").withSelfRel();
        friendPost.setLink(link);
        Link response = friendPost.getLink();
        assertEquals(link,response);
    }

    /**
     * Test of setLink from FriendPost class
     */

    @Test
    void setLink(){
        Link link = linkTo(FriendPost.class).slash("/api/posts").withSelfRel();
        friendPost.setLink(link);
        assertEquals(link,friendPost.getLink());
    }
}