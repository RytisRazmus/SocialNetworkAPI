package lt.viko.eif.fivehorsemen.SocialNetworkAPI.data;

import org.springframework.hateoas.Link;

public class FriendInvite extends Friend {

    private String inviteId;
    private Link link;

    public FriendInvite(String id, String name, String surname, String imageUrl, String inviteId) {
        super(id, name, surname, imageUrl);
        this.inviteId = inviteId;
    }

    @Override
    public String toString() {
        return "FriendInvite{" +
                "inviteId='" + inviteId + '\'' +
                ", link=" + link +
                '}';
    }

    public String getInviteId() {
        return inviteId;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
