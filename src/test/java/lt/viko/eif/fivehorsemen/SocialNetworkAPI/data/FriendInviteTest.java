package lt.viko.eif.fivehorsemen.SocialNetworkAPI.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class FriendInviteTest {

    @BeforeEach
    void setUp(){
        //friend = new Friend("1","Evaldas","Tamutis",
        //        "https://www.cutoutme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg");
        HttpServletRequest httpServletRequestMock = new MockHttpServletRequest();
        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(httpServletRequestMock);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);
    }

    @Test
    public void getInviteId() {
    }

    @Test
    public void getLink() {
    }

    @Test
    public void setLink() {
    }
}