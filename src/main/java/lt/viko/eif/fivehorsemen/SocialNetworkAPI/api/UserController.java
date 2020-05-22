package lt.viko.eif.fivehorsemen.SocialNetworkAPI.api;

import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.Friend;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.FriendInvite;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.User;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.database.MySqlConnection;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RequestMapping("User")
@RestController
public class UserController {

    MySqlConnection mySqlConnection = new MySqlConnection();

    @GetMapping(path = "/hello")
    public String test(){
        return "hello";
    }

    @GetMapping(path = "/db")
    public String test2(){
        return mySqlConnection.fetchUser().getName();
    }

    @GetMapping(path = "/getFriendInvites")
    public ArrayList<FriendInvite> getFriendInvites(@RequestParam("id") String userId){
        return mySqlConnection.getFriendInvites(userId);
    }

    @PostMapping(path = "/addUser")
    public boolean addUser(@RequestBody User user){
        System.out.println(user.getName());
        return mySqlConnection.addUser(user);
    }

    @PostMapping(path = "/sendFriendInvite")
    public boolean sendFriendInvite(@RequestBody Map<String, String> json){
        String toUser = json.get("toUser");
        String fromUser = json.get("fromUser");
        return mySqlConnection.insertFriendInvite(toUser, fromUser);
    }

    @GetMapping(path = "/getFriends")
    public ArrayList<Friend> getFriends(@RequestParam("id") String userId){
        return mySqlConnection.getFriends(userId);
    }
}
