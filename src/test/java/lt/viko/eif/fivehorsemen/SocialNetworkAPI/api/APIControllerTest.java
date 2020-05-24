package lt.viko.eif.fivehorsemen.SocialNetworkAPI.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.Friend;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.repository.APIRepositoryImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;

import java.net.URI;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class APIControllerTest {

    @InjectMocks
    APIController apiController;

    @Mock
    APIRepositoryImpl repository;

    @Test
    @Before
    private void setUp(){
        HttpServletRequest httpServletRequestMock = new MockHttpServletRequest();
        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(httpServletRequestMock);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);

    }
    @Test
    void getFriendInvites() {

    }

    @Test
    void register() {
    }

    @Test
    void sendFriendInvite() {
    }

    @Test
    void getFriends() throws Exception {

        Friend friend = new Friend("1","Evaldas","Tamutis",
                "https://www.cutoutme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg");
        Friend friend1 = new Friend("2","Andrius","Rimiškis",
                "https://www.cutoutme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg");
        Link link = linkTo(Friend.class).slash("/api/friends").withSelfRel();
        friend.setLink(link);
        ArrayList<Friend> friends = new ArrayList<>();
        friends.add(friend);
        friends.add(friend1);
        when(repository.getFriends("1")).thenReturn(friends);

        ArrayList<Friend> result = apiController.getFriends("1");

        assertThat(result.size()).isEqualTo(2);

        assertThat(result.get(0).getName())
                .isEqualTo(friend.getName());
        assertThat(result.get(1).getName())
                .isEqualTo(friend1.getName());

    }

    @Test
    void addPost() {
    }

    @Test
    void login() {
    }

    @Test
    void searchForFriend() {
    }

    @Test
    void deleteFriendInv() {
    }

    @Test
    void acceptFriend() {
    }

    @Test
    void posts() {
    }

    @Test
    void getWeather() {
    }

    @Test
    void detectlanguage() {
    }

    @Test
    void getLove() {
    }

    @Test
    void verifyMail() {
    }

    @Test
    void error() {
    }

    @Test
    void getErrorPath() {
    }
}