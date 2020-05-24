package lt.viko.eif.fivehorsemen.SocialNetworkAPI.data;

import org.springframework.hateoas.Link;

/**
 * This class is for holding user information
 *
 * @author Laurynas Zlatkus
 * @author Rytis Razmus
 * @author Jonas Zemaitis
 * @author Evaldas Tamutis
 * @author Evaldas Zalnierius
 */

public class User {
    private String id;
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
    private String lastSeen;
    private String dateOfBirth;
    private String password;
    private Link link;

    /**
     * User constructor
     * @param id user id
     * @param email user email
     * @param name user name
     * @param surname user surname
     * @param phoneNumber user phone number
     * @param lastSeen date of last time online
     * @param dateOfBirth user date of birth
     * @param password user password
     */

    public User(String id, String email, String name, String surname, String phoneNumber,
                String lastSeen, String dateOfBirth, String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.lastSeen = lastSeen;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
    }

    /**
     * Gets user password
     * @return a String
     */

    public String getPassword() {
        return password;
    }

    /**
     * Gets date of last time online
     * @return a String
     */

    public String getLastSeen() {
        return lastSeen;
    }

    /**
     * Gets user date of birth
     * @return a String
     */

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Gets user id
     * @return a String
     */

    public String getId() {
        return id;
    }

    /**
     * Gets user email
     * @return a String
     */

    public String getEmail() {
        return email;
    }

    /**
     * Gets user name
     * @return a String
     */

    public String getName() {
        return name;
    }

    /**
     * Gets user surname
     * @return a String
     */

    public String getSurname() {
        return surname;
    }

    /**
     * Gets user phone number
     * @return a String
     */

    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets a link to access user data
     * @param link a link
     */

    public void setLink(Link link) {
        this.link = link;
    }

    /**
     * Gets a link to access user data
     * @return a link
     */

    public Link getLink() {
        return link;
    }
}
