package lt.viko.eif.fivehorsemen.SocialNetworkAPI.api;


import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.Friend;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.FriendInvite;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.Post;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.User;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.repository.APIRepositoryImpl;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RequestMapping("/")
@RestController
@ResponseBody
public class APIController {

    private APIRepositoryImpl repository = new APIRepositoryImpl();

    @GetMapping(path = "/getFriendInvites")
    public ArrayList<FriendInvite> getFriendInvites(@RequestParam("id") String userId){
        return repository.getFriendInvites(userId);
    }

    @PostMapping(path = "/addUser")
    public boolean addUser(@RequestBody User user){
        return repository.addUser(user);
    }

    @PostMapping(path = "/sendFriendInvite")
    public boolean sendFriendInvite(@RequestBody Map<String, String> json){
        String toUser = json.get("toUser");
        String fromUser = json.get("fromUser");
        return repository.insertFriendInvite(toUser, fromUser);
    }

    @GetMapping(path = "/getFriends")
    public ArrayList<Friend> getFriends(@RequestParam("id") String userId){
        return repository.getFriends(userId);
    }

    @PostMapping(path = "/addPost")
    public boolean addPost(@RequestBody Post post){
        return repository.addPost(post);
    }
}
