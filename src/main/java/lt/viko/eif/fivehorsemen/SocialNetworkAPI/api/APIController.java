package lt.viko.eif.fivehorsemen.SocialNetworkAPI.api;


import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.*;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.exception.NotFoundException;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.repository.APIRepositoryImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Map;


@RequestMapping("/api/")
@RestController
public class APIController implements ErrorController {

    private APIRepositoryImpl repository = new APIRepositoryImpl();
    private static final String PATH = "/error";

    @Value("${api.weatherKey}")
    private String weatherApiKey;

    @Value("${api.loveKey}")
    private String loveApiKey;

    @GetMapping(path = "/friendInvites")
    public ArrayList<FriendInvite> getFriendInvites(@RequestParam("id") String userId) throws NotFoundException {
        ArrayList<FriendInvite> friendInvites = repository.getFriendInvites(userId);
        if (friendInvites.isEmpty())
            throw new NotFoundException("Could not find friend invites.", 404);
        else {
            return friendInvites;
        }
    }

    @PostMapping(path = "/register")
    public String register(@RequestBody User user) {
        boolean success = repository.addUser(user);
        if (!success) {
            throw new NotFoundException("Could not insert new User.", 406);
        }
        return "User added.";
    }

    @PostMapping(path = "/friendInvites")
    public String sendFriendInvite(@RequestBody Map<String, String> json){
        String toUser = json.get("toUser");
        String fromUser = json.get("fromUser");
        boolean success = repository.insertFriendInvite(toUser, fromUser);

        if (!success) {
            throw new NotFoundException("Could not send friend invites.", 406);
        }
        else {
            return "Invite sent.";
        }
    }

    @GetMapping(path = "/friends")
    public ArrayList<Friend> getFriends(@RequestParam("id") String userId){
        return repository.getFriends(userId);
    }

    @PostMapping(path = "/posts")
    public String addPost(@RequestBody Post post){
        boolean success = repository.addPost(post);

        if (!success) {
            throw new NotFoundException("Could not add post.", 406);
        }
        else {
            return "Post added.";
        }
    }

    @PostMapping(path = "/login")
    public User login(@RequestBody Map<String, String> json){
        String email = json.get("email");
        String password = json.get("password");
        User user = repository.getUser(email, password);

        if (user == null) {
            throw new NotFoundException("No such user", 404);
        }
        return user;
    }

    @GetMapping(path = "/friend")
    public Friend searchForFriend(@RequestParam(name = "fullname") String fullname) {
        Friend friend = repository.searchUser(fullname);

        if (friend == null) {
            throw new NotFoundException("No such user found", 404);
        } else {
            return friend;
        }
    }

    @DeleteMapping(path = "/friendInvites")
    public boolean deleteFriendInv(@RequestParam(name = "id") String id){
        boolean success = repository.deleteFriendInv(id);
        if (!success){
            throw new NotFoundException("Could not reject friend invite.", 500);
        }
        return success;
    }

    @PostMapping(path = "/friends")
    public boolean acceptFriend(@RequestParam(name = "toUser") String toUser,
                                @RequestParam(name = "fromUser") String fromUser){
        return repository.acceptFriendInvite(toUser,fromUser);
    }

    @GetMapping(path = "/posts")
    public ArrayList<FriendPost> posts(@RequestParam(name = "id") String id) {
        return repository.getFriendPosts(id);
    }

    @GetMapping(path = "/weather")
    public @ResponseBody String getWeather(@RequestParam(name = "id") String userId) throws NotFoundException {

        String city = repository.getCity(userId);
        if (city == null){
            throw new NotFoundException("User has not specified the city.", 404);
        } else {
            String uri = "https://api.weatherbit.io/v2.0/current?city=" + city + "&key=" + weatherApiKey;
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(uri, String.class);

            return result;
        }
    }


    @RequestMapping(value = PATH)
    public String error() {
        return "No such url." ;
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    @GetMapping(path = "/love")
    public String getLove(@RequestParam(name = "id") String userId, @RequestParam(name = "loveId") String loverId) {

        String name = repository.identifyUser(userId).getName();
        String secName = repository.identifyUser(loverId).getName();
        String uri = "https://love-calculator.p.rapidapi.com/getPercentage?fname=" + name +
                "&sname=" + secName;
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-host", "love-calculator.p.rapidapi.com");
        headers.set("x-rapidapi-key", loveApiKey);

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                uri, HttpMethod.GET, entity, String.class);

        return response.getBody();

    }

}
