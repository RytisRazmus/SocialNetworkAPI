package lt.viko.eif.fivehorsemen.SocialNetworkAPI.database;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

public class MySqlConnectionTest {

    private MySqlConnection mySqlConnection;
    private Connection connection;

    @BeforeEach
    public void setUp() throws ManagedProcessException {
        mySqlConnection = new MySqlConnection();

        DB database = DB.newEmbeddedDB(3306);
        database.start();

        database.createDB("cityTransport");
        mySqlConnection.setTesting(true);

        setupDatabase();
    }

    private void setupDatabase(){
        try {
            File f = new File(System.getProperty("user.dir") +
                    "/src/test/java/lt/viko/eif/fivehorsemen/SocialNetworkAPI/resource/Dump.sql");

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

    @Test
    public void getUser() {
    }

    @Test
    public void deleteFriendInv() {
    }

    @Test
    public void searchUser() {
    }

    @Test
    public void acceptFriendInvite() {
    }

    @Test
    public void getFriendPosts() {
    }

    @Test
    public void getFriendInvites() {
    }

    @Test
    public void addUser() {
    }

    @Test
    public void insertFriendInvite() {
    }

    @Test
    public void getFriends() {
    }

    @Test
    public void addPost() {
    }

    @Test
    public void getCity() {
    }

    @Test
    public void identifyUser() {
    }
}