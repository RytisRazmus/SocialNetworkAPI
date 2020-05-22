package lt.viko.eif.fivehorsemen.SocialNetworkAPI.api;


import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.*;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.repository.APIRepositoryImpl;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RequestMapping("/")
@RestController
@ResponseBody
public class APIController {

    private APIRepositoryImpl repository = new APIRepositoryImpl();

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

}
