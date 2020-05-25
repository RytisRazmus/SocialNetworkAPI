package lt.viko.eif.fivehorsemen.SocialNetworkAPI.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.Link;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class FriendInviteTest {

    private FriendInvite friendInv;

    @BeforeEach
    void setUp(){
        friendInv = new FriendInvite("9", "Not Bruce", "Not Wayne", null, "36");
        HttpServletRequest httpServletRequestMock = new MockHttpServletRequest();
        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(httpServletRequestMock);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);
    }

    @Test
    public void getInviteId() {
        String response = friendInv.getInviteId();
        assertEquals("36",response);
    }

    @Test
    public void getLink() {
        Link link = linkTo(Friend.class).slash("/api/friendInvites").withSelfRel();
        friendInv.setLink(link);
        Link response = friendInv.getLink();
        assertEquals(link,response);
    }

    @Test
    public void setLink() {
        Link link = linkTo(Friend.class).slash("/api/friendInvites").withSelfRel();
        friendInv.setLink(link);
        assertEquals(link,friendInv.getLink());
    }
}