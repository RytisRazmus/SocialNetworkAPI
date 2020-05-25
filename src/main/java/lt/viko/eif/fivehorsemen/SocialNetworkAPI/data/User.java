package lt.viko.eif.fivehorsemen.SocialNetworkAPI.data;

import org.springframework.hateoas.Link;

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

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", lastSeen='" + lastSeen + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", password='" + password + '\'' +
                ", link=" + link +
                '}';
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

    public void setLink(Link link) {
        this.link = link;
    }

    public Link getLink() {
        return link;
    }
}
