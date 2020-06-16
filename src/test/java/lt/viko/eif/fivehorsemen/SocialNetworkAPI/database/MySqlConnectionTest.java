package lt.viko.eif.fivehorsemen.SocialNetworkAPI.database;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import io.cucumber.junit.CucumberOptions;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The MySqlConnection class tests
 *
 * @author Laurynas Zlatkus
 * @author Rytis Razmus
 * @author Jonas Zemaitis
 * @author Evaldas Tamutis
 * @author Evaldas Zalnierius
 */

public class MySqlConnectionTest {

    private static MySqlConnection mySqlConnection;
    private static Connection connection;

    /**
     * SetUp for tests
     */

    @BeforeAll
    public static void setUp() throws ManagedProcessException {
        mySqlConnection = new MySqlConnection();

        DB database = DB.newEmbeddedDB(3306);
        database.start();

        database.createDB("cityTransport");
        mySqlConnection.setTesting(true);

        setupDatabase();
    }

    /**
     * Test of setupDatabase from MySqlConnection class
     */

    private static void setupDatabase(){
        try {
            File f = new File(System.getProperty("user.dir") +
                    "/src/test/java/lt/viko/eif/fivehorsemen/SocialNetworkAPI/resources/Dump.sql");

            connection = DriverManager.getConnection("jdbc:mysql://localhost/cityTransport?serverTimezone=GMT",
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

    /**
     * Test of getUser from MySqlConnection class
     */

    @Test
    public void getUser() {
        User response = mySqlConnection.getUser("gogelis.mogelis@yahoo.com", "11223369");
        assertEquals("Googelis", response.getName());
    }

    /**
     * Test of deleteFriendInv from MySqlConnection class
     */

    @Test
    public void deleteFriendInv() {
        boolean result = mySqlConnection.deleteFriendInv("1");
        assertTrue(result);
    }

    /**
     * Test of searchUser from MySqlConnection class
     */

    @Test
    public void searchUser() {
        ArrayList<Friend> results = mySqlConnection.searchUser("Googelis");
        assertTrue(results.size() > 0);
    }

    /**
     * Test of acceptFriendInvite from MySqlConnection class
     */

    @Test
    public void acceptFriendInvite() {
        boolean result = mySqlConnection.acceptFriendInvite("3", "9");
        assertTrue(result);
    }

    /**
     * Test of getFriendPosts from MySqlConnection class
     */

    @Test
    public void getFriendPosts() {
        ArrayList<FriendPost> results = mySqlConnection.getFriendPosts("2");
        assertTrue(results.get(0).getDescription().contains("su merginomis"));
    }

    /**
     * Test of getFriendInvites from MySqlConnection class
     */

    @Test
    public void getFriendInvites() {
        ArrayList<FriendInvite> results = mySqlConnection.getFriendInvites("3");
        assertEquals("36", results.get(0).getInviteId());
    }

    /**
     * Test of addUser from MySqlConnection class
     */

    @Test
    public void addUser() {
        User newUser = new User("16", "sdfdkds", "dfds", "dsfsd", "65556",
                null, "2000-01-28", "111");
        assertTrue(mySqlConnection.addUser(newUser));
    }

    /**
     * Test of insertFriendInvite from MySqlConnection class
     */

    @Test
    public void insertFriendInvite() {
        boolean result = mySqlConnection.insertFriendInvite("1", "2");
        assertTrue(result);
    }

    /**
     * Test of getFriends from MySqlConnection class
     */

    @Test
    public void getFriends() {
        ArrayList<Friend> result = mySqlConnection.getFriends("2");
        assertEquals("Rytis", result.get(1).getName());
    }

    /**
     * Test of addPost from MySqlConnection class
     */

    @Test
    public void addPost() {
        Post newPost = new Post("2", "Testing!", "www.google.com");
        boolean result = mySqlConnection.addPost(newPost);
        assertTrue(result);
    }

    /**
     * Test of getCity from MySqlConnection class
     */

    @Test
    public void getCity() {
        String result = mySqlConnection.getCity("2");
        assertEquals("Vilnius", result);
    }

    /**
     * Test of identifyUser from MySqlConnection class
     */

    @Test
    public void identifyUser() {
        User response = mySqlConnection.identifyUser("2");
        assertEquals("Jonas", response.getName());
    }
}