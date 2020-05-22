package lt.viko.eif.fivehorsemen.SocialNetworkAPI.data;

public class FriendInvite extends Friend {

    private String inviteId;

    public FriendInvite(String id, String name, String surname, String imageUrl, String inviteId) {
        super(id, name, surname, imageUrl);
        this.inviteId = inviteId;
    }

    public String getInviteId() {
        return inviteId;
    }
}
