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

class UserTest {

    private User user;
    @BeforeEach
    void setUp(){
        user = new User("1","laurynas.zlatkus@gmail.com","Laurynas","Zlatkus",
                "911","2020-01-15 18:25:16","2020-05-15","123456");
        HttpServletRequest httpServletRequestMock = new MockHttpServletRequest();
        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(httpServletRequestMock);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);
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

    @Test
    void getLink(){
        Link link = linkTo(User.class).slash("/api/login").withSelfRel();
        user.setLink(link);
        Link response = user.getLink();
        assertEquals(link,response);
    }

    @Test
    void setLink(){
        Link link = linkTo(User.class).slash("/api/login").withSelfRel();
        user.setLink(link);
        Link response = user.getLink();
        assertEquals(link,response);
    }
}