package lt.viko.eif.fivehorsemen.SocialNetworkAPI.data;

public class Friend {
    private String id;
    private String name;
    private String surname;
    private String imageUrl;

    public Friend(String id, String name, String surname, String imageUrl) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
