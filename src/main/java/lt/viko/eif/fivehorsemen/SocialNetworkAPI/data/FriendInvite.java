package lt.viko.eif.fivehorsemen.SocialNetworkAPI.data;

import org.springframework.hateoas.Link;

/**
 * This class is for holding friend invites information
 * this class extends friends class
 *
 * @author Laurynas Zlatkus
 * @author Rytis Razmus
 * @author Jonas Zemaitis
 * @author Evaldas Tamutis
 * @author Evaldas Zalnierius
 */

public class FriendInvite extends Friend {

    private String inviteId;
    private Link link;

    /**
     * FriendInvite constructor
     * @param id friend id
     * @param name friend name
     * @param surname friend surname
     * @param imageUrl friend image link
     * @param inviteId friend invitation request id
     */

    public FriendInvite(String id, String name, String surname, String imageUrl, String inviteId) {
        super(id, name, surname, imageUrl);
        this.inviteId = inviteId;
    }

    /**
     * Gets invite id
     * @return a String
     */

    public String getInviteId() {
        return inviteId;
    }

    /**
     * Gets link to friend request
     * @return a String
     */

    public Link getLink() {
        return link;
    }

    /**
     * Sets link to friend request
     * @param link friend request link
     */

    public void setLink(Link link) {
        this.link = link;
    }
}
