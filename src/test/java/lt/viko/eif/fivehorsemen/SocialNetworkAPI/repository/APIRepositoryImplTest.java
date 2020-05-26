package lt.viko.eif.fivehorsemen.SocialNetworkAPI.repository;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.Friend;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.User;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.database.MySqlConnection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.Link;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class APIRepositoryImplTest {

    @InjectMocks
    private APIRepositoryImpl repository;

    @Mock
    private MySqlConnection mySqlConnection;

    @Test
    void getUser() {
        User user = new User("1", "laurynas.zlatkus@gmail.com", "Laurynas", "Zlatkus",
                "911", "2020-01-15 18:25:16", "2020-05-15", "123456");
        when(mySqlConnection.getUser("laurynas.zlatkus@gmail.com","123465")).thenReturn(user);
        User result = repository.getUser("laurynas.zlatkus@gmail.com","123465");
        assertEquals(user,result);

    }

    @Test
    void deleteFriendInv() {
        when(mySqlConnection.deleteFriendInv("1")).thenReturn(true);
        Boolean result = repository.deleteFriendInv("1");
        assertThat(result).isTrue();
    }

    @Test
    void searchUser() {
        Friend friend = new Friend("1", "Evaldas", "Tamutis",
                "https://www.cutoutme.com.au/wp-content/uploads/2018/07/Single-CHls.jpg");
        ArrayList<Friend> friends = new ArrayList<>();
        friends.add(friend);
        when(mySqlConnection.searchUser("Evaldas")).thenReturn(friends);
        ArrayList<Friend> result = repository.searchUser("Evaldas");
        assertEquals(result,friends);
    }

    @Test
    void acceptFriendInvite() {
    }

    @Test
    void getFriendPosts() {
    }

    @Test
    void getFriendInvites() {
    }

    @Test
    void addUser() {

    }

    @Test
    void insertFriendInvite() {
    }

    @Test
    void getFriends() {
    }

    @Test
    void addPost() {
    }

    @Test
    void getCity() {
    }

    @Test
    void identifyUser() {
    }
}