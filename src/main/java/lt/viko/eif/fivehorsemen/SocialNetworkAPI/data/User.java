package lt.viko.eif.fivehorsemen.SocialNetworkAPI.data;

import com.fasterxml.jackson.annotation.JsonProperty;


public class User {

    private String id;
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
    private String lastSeen;
    private String dateOfBirth;
    private String password;

    /**
     * default constructor for JSON.
     */
    public User() {

    }

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

    public String getPassword() {
        return password;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
