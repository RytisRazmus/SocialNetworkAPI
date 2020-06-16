package lt.viko.eif.fivehorsemen.SocialNetworkAPI.cucumber.database;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.CucumberOptions;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.*;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.database.MySqlConnection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@CucumberOptions(features = {"resources/database/"})
public class MySqlConnectionStepDefs {

    private static MySqlConnection mySqlConnection;
    private static Connection connection;
    private static boolean running = false;

    private User user;
    private boolean result;
    private ArrayList<Friend> friends = new ArrayList<>();
    private String friendId;
    private String userId;
    private ArrayList<FriendPost> friendPosts = new ArrayList<>();
    private ArrayList<FriendInvite> friendInvites = new ArrayList<>();
    private User newUser;
    private Post newPost;

    /**
     * SetUp for tests
     */

    @Before
    public static void setUp() throws ManagedProcessException {
        if(!running) {
            System.out.println("Starting Database!");
            mySqlConnection = new MySqlConnection();

            DB database = DB.newEmbeddedDB(3306);
            database.start();

            database.createDB("cityTransport");
            mySqlConnection.setTesting(true);

            setupDatabase();
            running = true;
        }
    }

    /**
     * Test of setupDatabase from MySqlConnection class
     */

    private static void setupDatabase(){
        try {
            File f = new File(System.getProperty("user.dir") +
                    "/src/test/java/lt/viko/eif/fivehorsemen/SocialNetworkAPI/resources/Dump.sql");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/cityTransport?serverTimezone=GMT",
                    "root", "");

            BufferedReader bf = new BufferedReader(new FileReader(f));
            String line = null,old="";
            line = bf.readLine();
            Statement stmt = connection.createStatement();
            while (line != null) {
                if(line.endsWith(";")){
                    stmt.executeUpdate(old+line);
                    old="";
                }
                else
                    old=old+"\n"+line;
                line = bf.readLine();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @When("User enters credentials")
    public void userEntersCredentials() {
        user = mySqlConnection.getUser("gogelis.mogelis@yahoo.com", "11223369");
    }

    @Then("User receives requested user")
    public void userReceivesRequestedUser() {
        assertEquals("Googelis", user.getName());
    }

    @When("User enters invite id")
    public void userEntersInviteId() {
        result = false;
        result = mySqlConnection.deleteFriendInv("1");
    }

    @Then("User receives deletion status")
    public void userReceivesDeletionStatus() {
        assertTrue(result);
    }

    @When("User enters a name")
    public void user_enters_a_name() {
        friends = mySqlConnection.searchUser("Googelis");
    }

    @Then("User receives friends")
    public void user_receives_friends() {
        assertTrue(friends.size() > 0);
    }

    @When("User enters friends id \"([^\"]*)\"$")
    public void user_enters_friends_id(String arg1) {
        friendId = arg1;
    }

    @Then("User receives invitation status")
    public void user_receives_invitation_status() {
        result = mySqlConnection.acceptFriendInvite(friendId, "9");
        assertTrue(result);
    }

    @Then("User receives friends posts")
    public void user_receives_friends_posts() {
        friendPosts = mySqlConnection.getFriendPosts(friendId);
        assertTrue(friendPosts.get(0).getDescription().contains("su merginomis"));
    }

    @Then("User receives friend invites")
    public void user_receives_friend_invites() {
        friendInvites = mySqlConnection.getFriendInvites(friendId);
        assertTrue(friendInvites.isEmpty());
    }

    @When("User enters required credentials")
    public void user_enters_required_credentials() {
        newUser = new User("16", "sdfdkds", "dfds", "dsfsd", "65556",
                null, "2000-01-28", "111");
    }

    @Then("User receives addition status")
    public void user_receives_addition_status() {
        assertTrue(mySqlConnection.addUser(newUser));
    }

    @When("User enters his id \"([^\"]*)\"$")
    public void user_enters_his_id(String arg1) {
        userId = arg1;
    }

    @And("User sends request for friends")
    public void user_sends_request_for_friends() {
        friends = mySqlConnection.getFriends(userId);
    }

    @When("User enters post information")
    public void user_enters_post_information() {
        newPost = new Post("2", "Testing!", "www.google.com");
    }

    @Then("User receives post status")
    public void user_receives_post_status() {
        result = mySqlConnection.addPost(newPost);
        assertTrue(result);
    }

    @Then("User receives city name")
    public void user_receives_city_name() {
        String city = mySqlConnection.getCity(userId);
        assertEquals("Vilnius", city);
    }

    @Then("User receives friend name")
    public void user_receives_friend_name() {
        User response = mySqlConnection.identifyUser(friendId);
        assertEquals("Jonas", response.getName());
    }

}
