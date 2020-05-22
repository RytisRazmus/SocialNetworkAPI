package lt.viko.eif.fivehorsemen.SocialNetworkAPI.api;

import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.User;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.database.MySqlConnection;
import org.springframework.web.bind.annotation.*;

@RequestMapping("User")
@RestController
public class UserController {

    MySqlConnection mySqlConnection = new MySqlConnection();

    @GetMapping(path = "/hello")
    public String test(){
        return "hello";
    }

    @GetMapping(path = "/user")
    public String user(){
        return mySqlConnection.getUser("laurynas.zlatkus@gmail.com", "123456").getName();
    }

    @GetMapping(path = "/search")
    public String search(){
        return mySqlConnection.searchUser("rytis razmus").getSurname();
    }

    @DeleteMapping(path = "/del")
    public void del(){
        mySqlConnection.deleteFriendInv("33");
    }

    @PostMapping(path = "/friend")
    public void friend(){
        mySqlConnection.acceptFriendInvite("8","9");
    }
}
