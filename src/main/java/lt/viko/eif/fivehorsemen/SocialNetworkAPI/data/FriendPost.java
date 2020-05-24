package lt.viko.eif.fivehorsemen.SocialNetworkAPI.data;

import org.springframework.hateoas.Link;

import java.util.Date;

public class FriendPost extends Post {

    private String name;
    private String surname;
    private Date date;
    private String profileImage;
    private Link link;

    public FriendPost(Date date, String name, String surname, String friendId, String description,
                      String imageUrl, String profileImage) {
        super(friendId, description, imageUrl);
        this.date = date;
        this.name = name;
        this.surname = surname;
        this.profileImage = profileImage;
    }


    public Date getDate() {
        return date;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
