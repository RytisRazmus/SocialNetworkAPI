package lt.viko.eif.fivehorsemen.SocialNetworkAPI.api;

import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.User;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.database.MySqlConnection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
