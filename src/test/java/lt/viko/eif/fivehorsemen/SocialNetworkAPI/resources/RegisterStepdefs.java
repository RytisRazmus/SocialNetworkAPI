package lt.viko.eif.fivehorsemen.SocialNetworkAPI.resources;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.User;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.apache.http.util.EntityUtils;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.Assert.*;

public class RegisterStepdefs {

    private WireMockServer wireMockServer = new WireMockServer(options().dynamicPort());
    private CloseableHttpClient httpClient = HttpClients.createDefault();
    private HttpPost request;

    private User user;


    @Given("User enters his information")
    public void userEntersHisInformation() {
        user = new User("99", "albatrosas@kaunas.lt", "Joseph", "Stalin",
                "+340567261","1953-03-05", "1878-12-18", "pazhalsta");
    }

    @When("User presses register")
    public void userPressesRegister() throws JsonProcessingException, UnsupportedEncodingException {
        wireMockServer.start();

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(user);

        request = new HttpPost("http://localhost:" + wireMockServer.port() + "/api/register");
        StringEntity entity = new StringEntity(jsonString);
        request.addHeader("content-type", "application/json");
        request.setEntity(entity);

    }

    @Then("New User created")
    public void newUserCreated() throws IOException {
        HttpResponse response = httpClient.execute(request);
        assertEquals(404, response.getStatusLine().getStatusCode());

        wireMockServer.stop();
    }

    private String convertResponseToString(HttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();

        // Read the contents of an entity and return it as a String.
        String content = EntityUtils.toString(entity);
        System.out.println("makarena " + content);
        return content;
    }
}
