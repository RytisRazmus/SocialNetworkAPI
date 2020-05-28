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
 * The User class tests
 *
 * @author Laurynas Zlatkus
 * @author Rytis Razmus
 * @author Jonas Zemaitis
 * @author Evaldas Tamutis
 * @author Evaldas Zalnierius
 */

class UserTest {

    private User user;

    /**
     * SetUp for tests
     */

    @BeforeEach
    void setUp(){
        user = new User("1","laurynas.zlatkus@gmail.com","Laurynas","Zlatkus",
                "911","2020-01-15 18:25:16","2020-05-15","123456");
        HttpServletRequest httpServletRequestMock = new MockHttpServletRequest();
        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(httpServletRequestMock);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);
    }

    /**
     * Test of getPassword from User class
     */

    @Test
    void getPassword() {
        String response = user.getPassword();
        assertEquals("123456",response);
    }

    /**
     * Test of getLastSeen from User class
     */

    @Test
    void getLastSeen() {
        String response = user.getLastSeen();
        assertEquals("2020-01-15 18:25:16",response);
    }

    /**
     * Test of getDateOfBirth from User class
     */

    @Test
    void getDateOfBirth() {
        String response = user.getDateOfBirth();
        assertEquals("2020-05-15",response);
    }

    /**
     * Test of getId from User class
     */

    @Test
    void getId() {
        String response = user.getId();
        assertEquals("1",response);
    }

    /**
     * Test of getEmail from User class
     */

    @Test
    void getEmail() {
        String response = user.getEmail();
        assertEquals("laurynas.zlatkus@gmail.com",response);
    }

    /**
     * Test of getName from User class
     */

    @Test
    void getName() {
        String response = user.getName();
        assertEquals("Laurynas",response);
    }

    /**
     * Test of getSurname from User class
     */

    @Test
    void getSurname() {
        String response = user.getSurname();
        assertEquals("Zlatkus",response);
    }

    /**
     * Test of getPhoneNumber from User class
     */

    @Test
    void getPhoneNumber() {
        String response = user.getPhoneNumber();
        assertEquals("911",response);
    }

    /**
     * Test of getLink from User class
     */

    @Test
    void getLink(){
        Link link = linkTo(User.class).slash("/api/login").withSelfRel();
        user.setLink(link);
        Link response = user.getLink();
        assertEquals(link,response);
    }

    /**
     * Test of setLink from User class
     */

    @Test
    void setLink(){
        Link link = linkTo(User.class).slash("/api/login").withSelfRel();
        user.setLink(link);
        Link response = user.getLink();
        assertEquals(link,response);
    }
}