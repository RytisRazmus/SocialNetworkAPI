package lt.viko.eif.fivehorsemen.SocialNetworkAPI.data;

import java.util.Date;

public class Post {
    private Date date;
    private String name;
    private String surname;
    private String friendId;
    private String description;
    private String imageUrl;
    private String profileImage;

    public Post(Date date, String name, String surname, String friendId, String description,
                String imageUrl, String profileImage) {
        this.date = date;
        this.name = name;
        this.surname = surname;
        this.friendId = friendId;
        this.description = description;
        this.imageUrl = imageUrl;
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

    public String getFriendId() {
        return friendId;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
