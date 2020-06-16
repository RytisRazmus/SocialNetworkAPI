package lt.viko.eif.fivehorsemen.SocialNetworkAPI.cucumber.database;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.User;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.database.MySqlConnection;
import org.junit.jupiter.api.BeforeAll;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MySqlConnectionStepDefs {

    private static MySqlConnection mySqlConnection;
    private static Connection connection;

    private User user;

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

    @Given("User exists")
    public void userExists() {
        user = mySqlConnection.getUser("gogelis.mogelis@yahoo.com", "11223369");
    }

    @When("User enters credentials")
    public void userEntersCredentials() {
    }

    @Then("User receives user")
    public void userReceivesUser() {
    }
}
