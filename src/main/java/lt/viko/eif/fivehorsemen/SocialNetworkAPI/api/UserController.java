package lt.viko.eif.fivehorsemen.SocialNetworkAPI.api;

import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.Friend;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.FriendPost;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.User;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.database.MySqlConnection;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("users")
@RestController
@ResponseBody
public class UserController {

    MySqlConnection mySqlConnection = new MySqlConnection();

    @GetMapping(path = "/user")
    public User user(@RequestParam(name = "email") String email, @RequestParam(name = "pass") String pass){
        return mySqlConnection.getUser(email, pass);
    }

    @GetMapping(path = "/search")
    public Friend search(@RequestParam(name = "fullname") String fullname){
        return mySqlConnection.searchUser(fullname);
    }

    @DeleteMapping(path = "/del")
    public void del(@RequestParam(name = "id") String id){
        mySqlConnection.deleteFriendInv(id);
    }

    @PostMapping(path = "/friend")
    public void friend(){
        mySqlConnection.acceptFriendInvite("8","9");
    }

    @GetMapping(path = "/posts")
    public List<FriendPost> posts() {
        return mySqlConnection.getFriendPosts("3");
    }
}
