package lt.viko.eif.fivehorsemen.SocialNetworkAPI.data;

import org.springframework.hateoas.Link;

import java.util.Date;

/**
 * This class is for holding Friend post information
 * this class extends post class
 *
 * @author Laurynas Zlatkus
 * @author Rytis Razmus
 * @author Jonas Zemaitis
 * @author Evaldas Tamutis
 * @author Evaldas Zalnierius
 */

public class FriendPost extends Post {

    private String name;
    private String surname;
    private Date date;
    private String profileImage;
    private Link link;

    /**
     * FriendPost constructor
     * @param date date of post
     * @param name name of friend
     * @param surname surname of friend
     * @param friendId friend id
     * @param description post description
     * @param imageUrl post image link
     * @param profileImage friend profile image link
     */

    public FriendPost(Date date, String name, String surname, String friendId, String description,
                      String imageUrl, String profileImage) {
        super(friendId, description, imageUrl);
        this.date = date;
        this.name = name;
        this.surname = surname;
        this.profileImage = profileImage;
    }

    @Override
    public String toString() {
        return "FriendPost{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", date=" + date +
                ", profileImage='" + profileImage + '\'' +
                ", link=" + link +
                '}';
    }
  
    /**
     * Gets date of post
     * @return a String
     */

    public Date getDate() {
        return date;
    }

    /**
     * Gets Profile image link
     * @return a String
     */

    public String getProfileImage() {
        return profileImage;
    }

    /**
     * Gets a friend name
     * @return a String
     */

    public String getName() {
        return name;
    }

    /**
     * Gets a friend surname
     * @return a String
     */

    public String getSurname() {
        return surname;
    }

    /**
     * Gets a link to access post
     * @return a Link
     */

    public Link getLink() {
        return link;
    }

    /**
     * Sets a link to access post
     * @param link
     */

    public void setLink(Link link) {
        this.link = link;
    }
}
