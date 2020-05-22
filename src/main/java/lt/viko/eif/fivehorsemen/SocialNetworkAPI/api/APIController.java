package lt.viko.eif.fivehorsemen.SocialNetworkAPI.api;


import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.*;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.repository.APIRepositoryImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

@RequestMapping("/")
@RestController
@ResponseBody
public class APIController implements ErrorController {

    private APIRepositoryImpl repository = new APIRepositoryImpl();
    private static final String PATH = "/error";

    @Value("${api.weatherKey}")
    private String weatherApiKey;

    @GetMapping(path = "/friendInvites")
    public ArrayList<FriendInvite> getFriendInvites(@RequestParam("id") String userId){
        return repository.getFriendInvites(userId);
    }

    @PostMapping(path = "/users")
    public boolean addUser(@RequestBody User user){
        return repository.addUser(user);
    }

    @PostMapping(path = "/friendInvites")
    public boolean sendFriendInvite(@RequestBody Map<String, String> json){
        String toUser = json.get("toUser");
        String fromUser = json.get("fromUser");
        return repository.insertFriendInvite(toUser, fromUser);
    }

    @GetMapping(path = "/friends")
    public ArrayList<Friend> getFriends(@RequestParam("id") String userId, HttpServletRequest request){
        String remoteAddress = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getRemoteAddr();
        System.out.println(remoteAddress);
        return repository.getFriends(userId);
    }

    @PostMapping(path = "/posts")
    public boolean addPost(@RequestBody Post post){
        return repository.addPost(post);
    }

    @GetMapping(path = "/users")
    public User getUser(@RequestParam(name = "email") String email, @RequestParam(name = "pass") String pass){
        return repository.getUser(email, pass);
    }

    @GetMapping(path = "/friend")
    public Friend searchForFriend(@RequestParam(name = "fullname") String fullname){
        return repository.searchUser(fullname);
    }

    @DeleteMapping(path = "/friendInvites")
    public void deleteFriendInv(@RequestParam(name = "id") String id){
        repository.deleteFriendInv(id);
    }

    @PostMapping(path = "/friends")
    public void acceptFriend(@RequestParam(name = "toUser") String toUser, @RequestParam(name = "fromUser") String fromUser){
        repository.acceptFriendInvite(toUser,fromUser);
    }

    @GetMapping(path = "/posts")
    public ArrayList<FriendPost> posts(@RequestParam(name = "id") String id) {
        return repository.getFriendPosts(id);
    }

    @GetMapping(path = "/weather")
    public String getWeather(@RequestParam(name = "id") String userId) {

        String city = repository.getCity(userId);
        String uri = "https://api.weatherbit.io/v2.0/current?city=" + city + "&key=" + weatherApiKey;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        return result;
    }


    @RequestMapping(value = PATH)
    public String error() {
        return "No such url." ;
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

}
