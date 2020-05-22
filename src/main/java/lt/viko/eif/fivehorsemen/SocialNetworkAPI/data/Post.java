package lt.viko.eif.fivehorsemen.SocialNetworkAPI.data;

public class Post {
    private String name;
    private String surname;
    private String userId;
    private String imageUrl;

    public Post(String name, String surname, String userId, String imageUrl) {
        this.name = name;
        this.surname = surname;
        this.userId = userId;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUserId() {
        return userId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

}
