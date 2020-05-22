package lt.viko.eif.fivehorsemen.SocialNetworkAPI.api;

import lt.viko.eif.fivehorsemen.SocialNetworkAPI.database.MySqlConnection;
import org.springframework.web.bind.annotation.*;


@RequestMapping("User")
@RestController
public class UserController {

    MySqlConnection mySqlConnection = new MySqlConnection();


}
