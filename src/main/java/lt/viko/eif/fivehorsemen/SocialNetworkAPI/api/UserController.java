package lt.viko.eif.fivehorsemen.SocialNetworkAPI.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
@RequestMapping("User")
@RestController
public class UserController {

    @GetMapping(path = "/hello")
    public String test(){
        return "hello";
    }
}
