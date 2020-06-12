package lt.viko.eif.fivehorsemen.SocialNetworkAPI.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.Link;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * The Friend class tests
 *
 * @author Laurynas Zlatkus
 * @author Rytis Razmus
 * @author Jonas Zemaitis
 * @author Evaldas Tamutis
 * @author Evaldas Zalnierius
 */

class FriendTest{

    private Friend friend;

    /**
     * SetUp for tests
     */

    @BeforeEach
    void setUp(){
        friend = new Friend("1","Evaldas","Tamutis",
                "https://www.cutoutme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg");
        HttpServletRequest httpServletRequestMock = new MockHttpServletRequest();
        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(httpServletRequestMock);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);
    }

    /**
     * Test of getId from Friend class
     */

    @Test
    void getId() {
        String response = friend.getId();
        assertEquals("1",response);
    }

    /**
     * Test of getName from Friend class
     */

    @Test
    void getName() {
        String response = friend.getName();
        assertEquals("Evaldas",response);
    }

    /**
     * Test of getSurname from Friend class
     */

    @Test
    void getSurname() {
        String response = friend.getSurname();
        assertEquals("Tamutis",response);
    }

    /**
     * Test of getImageUrl from Friend class
     */

    @Test
    void getImageUrl() {
        String response = friend.getImageUrl();
        assertEquals("https://www.cutoutme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg",response);
    }

    /**
     * Test of getLink from Friend class
     */

    @Test
    void getLink(){
        Link link = linkTo(Friend.class).slash("/api/friends").withSelfRel();
        friend.setLink(link);
        Link response = friend.getLink();
        assertEquals(link,response);
    }

    /**
     * Test of setLink from Friend class
     */

    @Test
    void setLink(){
        Link link = linkTo(Friend.class).slash("/api/friends").withSelfRel();
        friend.setLink(link);
        assertEquals(link,friend.getLink());
    }
}