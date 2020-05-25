package lt.viko.eif.fivehorsemen.SocialNetworkAPI.data;

public class Post {
    private String userId;
    private String imageUrl;
    private String description;

    public Post(String userId, String description, String imageUrl) {
        this.userId = userId;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Post{" +
                "userId='" + userId + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

}
