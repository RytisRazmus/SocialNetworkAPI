package lt.viko.eif.fivehorsemen.SocialNetworkAPI.api;

import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.*;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.exception.NotFoundException;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.repository.APIRepositoryImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Map;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * The APIController class is for handling SocialNetworkAPI logic
 *
 * @author Laurynas Zlatkus
 * @author Rytis Razmus
 * @author Jonas Zemaitis
 * @author Evaldas Tamutis
 * @author Evaldas Zalnierius
 */

@RequestMapping(value = "/api/")
@RestController
public class APIController implements ErrorController {

    private APIRepositoryImpl repository = new APIRepositoryImpl();
    private static final String PATH = "/error";

    @Value("${api.weatherKey}")
    private String weatherApiKey;

    @Value("${api.loveKey}")
    private String loveApiKey;

    @Value("${api.languageKey}")
    private String languageKey;

    /**
     * Gets a list of new friends invites
     * @param userId the id of user
     * @return list of friend invites objects
     * @throws NotFoundException in case if could not find friend invites
     */

    @GetMapping(path = "/friendInvites")
    public ArrayList<FriendInvite> getFriendInvites(@RequestParam("id") String userId) throws NotFoundException {
        ArrayList<FriendInvite> friendInvites = repository.getFriendInvites(userId);
        if (friendInvites.isEmpty())
            throw new NotFoundException("Could not find friend invites.", 404);
        else {
            for (FriendInvite x : friendInvites) {
                Link link = linkTo(FriendInvite.class).slash("/api/friendInvites").withSelfRel();
                x.setLink(link);
            }

            return friendInvites;
        }
    }

    /**
     * Creates a new user account
     * @param user object which contains information about new user
     * @return String "User added." if user is successfully created
     * @throws NotFoundException in case of error creating new user
     */

    @PostMapping(path = "/register")
    public String register(@RequestBody User user) {
        boolean success = repository.addUser(user);
        if (!success) {
            throw new NotFoundException("Could not insert new User.", 406);
        }
        return "User added.";
    }

    /**
     * Sends friend request
     * @param json map of json format containing user names of sender and getter of friend request
     * @return String of successful completed action
     * @throws NotFoundException in case of error sending friend request
     */

    @PostMapping(path = "/friendInvites")
    public String sendFriendInvite(@RequestBody Map<String, String> json){
        String toUser = json.get("toUser");
        String fromUser = json.get("fromUser");
        boolean success = repository.insertFriendInvite(toUser, fromUser);

        if (!success) {
            throw new NotFoundException("Could not send friend invite.", 406);
        }
        else {
            return "Invite sent.";
        }
    }

    /**
     * Gets a list of user friends
     * @param userId the id of user
     * @return list of user friends
     */

    @GetMapping(path = "/friends")
    public ArrayList<Friend> getFriends(@RequestParam("id") String userId){
        Link link = linkTo(Friend.class).slash("/api/friends").withSelfRel();
        ArrayList<Friend> friends = repository.getFriends(userId);
        for (Friend f: friends) {
            f.setLink(link);
        }
        return friends;
    }

    /**
     * Creating a new post
     * @param post object containing information about post
     * @return String of successful completed action
     * @throws NotFoundException in case of error posting a new post
     */

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

    /**
     * Login into account
     * @param json map of json format containing user email and password
     * @return user object
     * @throws NotFoundException in case if user do not exist
     */

    @PostMapping(path = "/login")
    public User login(@RequestBody Map<String, String> json){
        String email = json.get("email");
        String password = json.get("password");
        User user = repository.getUser(email, password);

        if (user == null) {
            throw new NotFoundException("No such user.", 404);
        }
        Link link = linkTo(User.class).slash("/api/login").withSelfRel();
        user.setLink(link);
        return user;
    }

    /**
     * Search for friends
     * @param fullname full name of user you are looking for
     * @return friends with name you are looking for
     */
    @GetMapping(path = "/friend")
    public ArrayList<Friend> searchForFriend(@RequestParam(name = "fullname") String fullname) {
        ArrayList<Friend> friends = repository.searchUser(fullname);
        Link link = linkTo(Friend.class).slash("/api/friend").withSelfRel();
        for (Friend f: friends) {
            f.setLink(link);
        }
        return friends;
    }

    /**
     * Declining friend invite
     * @param id of declined friend request
     * @return true if successfully deleted friend request
     * @throws NotFoundException in case if friend request was not successfully rejected
     */

    @DeleteMapping(path = "/friendInvites")
    public boolean deleteFriendInv(@RequestParam(name = "id") String id){
        boolean success = repository.deleteFriendInv(id);
        if (!success){
            throw new NotFoundException("Could not reject friend invite.", 500);
        }
        return success;
    }

    /**
     * Accepting friend request
     * @param toUser name of user who sent friend request
     * @param fromUser name of user who accepted friend request
     * @return true if successfully accepted friend request
     * @throws NotFoundException in case if friend invite was not successfully accepted
     */

    @PostMapping(path = "/friends")
    public boolean acceptFriend(@RequestParam(name = "toUser") String toUser,
                                @RequestParam(name = "fromUser") String fromUser){
        boolean success = repository.acceptFriendInvite(toUser,fromUser);
        if (!success) {
            throw new NotFoundException("Could not accept invite.", 500);
        }
        return success;
    }

    /**
     * Checking of friend posts
     * @param id of friend
     * @return list of friend posts
     * @throws NotFoundException in case if list of post is empty
     */

    @GetMapping(path = "/posts")
    public ArrayList<FriendPost> posts(@RequestParam(name = "id") String id) {
        ArrayList<FriendPost> posts = repository.getFriendPosts(id);

        if (posts.isEmpty()) {
            throw new NotFoundException("Could not find friends posts.", 404);
        } else {
            for (FriendPost x : posts) {
                Link link = linkTo(FriendPost.class).slash("/api/posts").withSelfRel();
                x.setLink(link);
            }

            return posts;
        }
    }

    /**
     * Get weather of your city
     * @param userId id of user
     * @return link to your city weather
     * @throws NotFoundException in case if user have not specified city
     */

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

    /**
     * Detecting language of post
     * @param json contains text of post
     * @return link to detect a language
     * @throws NotFoundException in case of wrong json format
     */

    @PostMapping(path = "/language-detect")
    public @ResponseBody String detectlanguage(@RequestBody Map<String, String> json) throws NotFoundException {

        String text = json.get("text");

        if (text == null){
            throw new NotFoundException("Wrong json format.", 400);
        } else {
            text = text.replace(" ", "20%");
            String uri = "http://api.languagelayer.com/detect?access_key=" + languageKey +
                    "&query=" + text;
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(uri, String.class);

            return result;
        }
    }

    /**
     * Get a love calculated
     * @param userId id of user
     * @param loverId id of lover
     * @return link to love calculation
     * @throws NotFoundException in case if one of names is null
     */

    @GetMapping(path = "/love")
    public String getLove(@RequestParam(name = "id") String userId, @RequestParam(name = "loveId") String loverId) {

        String name = repository.identifyUser(userId).getName();
        String secName = repository.identifyUser(loverId).getName();
        if (name == null || secName == null) {
            throw new NotFoundException("There are no specified users.", 404);
        } else {
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

    /**
     * Verify user email
     * @param email user email
     * @return link to verify email
     * @throws NotFoundException in case if user is not specified
     */

    @GetMapping(path = "/verifyMail")
    public String verifyMail(@RequestParam(name = "email") String email) {

        if (email == "") {
            throw new NotFoundException("There are no specified users.", 404);
        } else {
            String uri = "https://api.mailboxvalidator.com/v1/validation/single?key=" + "W99YN42B0IJQXL3B5LYR" +
                    "&format=json&email=" + email;
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(uri, String.class);

            return result;
        }

    }

    /**
     * Get an error
     * @return string verification
     */

    @RequestMapping(value = PATH)
    public String error() {
        return "No such url." ;
    }

    /**
     * Get an error
     * @return Path of error
     */

    @Override
    public String getErrorPath() {
        return PATH;
    }


}
