package lt.viko.eif.fivehorsemen.SocialNetworkAPI.data;

import org.springframework.hateoas.Link;

/**
 * This class is for holding friend information
 *
 * @author Laurynas Zlatkus
 * @author Rytis Razmus
 * @author Jonas Zemaitis
 * @author Evaldas Tamutis
 * @author Evaldas Zalnierius
 */

public class Friend {
    private String id;
    private String name;
    private String surname;
    private String imageUrl;
    private Link link;

    /**
     * Friend constructor
     * @param id friend id
     * @param name name of friend
     * @param surname surname of friend
     * @param imageUrl image link
     */

    public Friend(String id, String name, String surname, String imageUrl) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.imageUrl = imageUrl;
    }

    /**
     * Gets friends id
     * @return a String
     */

    public String getId() {
        return id;
    }

    /**
     * Gets friends name
     * @return a String
     */

    public String getName() {
        return name;
    }

    /**
     * Gets friend username
     * @return a String
     */

    public String getSurname() {
        return surname;
    }

    /**
     * Gets friend image link
     * @return a String
     */

    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets link to access friend profile
     * @param link a link
     */

    public void setLink(Link link) {
        this.link = link;
    }

    /**
     * Get link for access friend profile
     * @return a Link
     */

    public Link getLink() {
        return link;
    }
}
