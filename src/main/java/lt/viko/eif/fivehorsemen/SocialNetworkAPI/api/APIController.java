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
    public ArrayList<Friend> getFriends(@RequestParam("id") String userId){
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
    public boolean deleteFriendInv(@RequestParam(name = "id") String id){
        return repository.deleteFriendInv(id);
    }

    @PostMapping(path = "/friends")
    public boolean acceptFriend(@RequestParam(name = "toUser") String toUser, @RequestParam(name = "fromUser") String fromUser){
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
