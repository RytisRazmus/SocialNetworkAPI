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

class FriendTest{

    private Friend friend;
    @BeforeEach
    void setUp(){
        friend = new Friend("1","Evaldas","Tamutis",
                "https://www.cutoutme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg");
        HttpServletRequest httpServletRequestMock = new MockHttpServletRequest();
        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(httpServletRequestMock);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);
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
    @Test
    void getLink(){
        Link link = linkTo(Friend.class).slash("/api/friends").withSelfRel();
        friend.setLink(link);
        Link response = friend.getLink();
        assertEquals(link,response);
    }

    @Test
    void setLink(){
        Link link = linkTo(Friend.class).slash("/api/friends").withSelfRel();
        friend.setLink(link);
        assertEquals(link,friend.getLink());

    }
}