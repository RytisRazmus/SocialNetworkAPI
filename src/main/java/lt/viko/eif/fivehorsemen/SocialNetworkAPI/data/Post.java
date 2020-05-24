package lt.viko.eif.fivehorsemen.SocialNetworkAPI.data;

/**
 * This class is for holding Post information
 *
 * @author Laurynas Zlatkus
 * @author Rytis Razmus
 * @author Jonas Zemaitis
 * @author Evaldas Tamutis
 * @author Evaldas Zalnierius
 */

public class Post {
    private String userId;
    private String imageUrl;
    private String description;

    /**
     * Post constructor
     * @param userId post user id
     * @param description post description
     * @param imageUrl image link of post
     */

    public Post(String userId, String description, String imageUrl) {
        this.userId = userId;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    /**
     * Gets post user id
     * @return a String
     */

    public String getUserId() {
        return userId;
    }

    /**
     * Gets image link of post
     * @return a String
     */

    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Gets post description
     * @return a String
     */

    public String getDescription() {
        return description;
    }

}
