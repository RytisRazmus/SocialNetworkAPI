package lt.viko.eif.fivehorsemen.SocialNetworkAPI.cucumber.data;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.Post;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.data.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostStepdefs {

    Post post;
    String result;

    @Given("Existing post")
    public void existingPost() {
        post = new Post("2", "I feel like I'm on fiiiiire.",
                "https://static.eurovision.tv/hb-cgi/images/8fd8837b-fb88-461b-8ac7-412b3612146e/hero.jpeg");
    }

    @When("Asks for user's id")
    public void askForUsersId() {
        result = post.getUserId();
    }

    @Then("Received id")
    public void receivedId() {
        assertEquals(result, "2");
    }

    @When("Asks for image")
    public void askForImage() {
        result = post.getImageUrl();
    }

    @Then("Received image")
    public void receivedImage() {
        assertEquals(result, "https://static.eurovision.tv/hb-cgi/images/8fd8837b-fb88-461b-8ac7-412b3612146e/hero.jpeg");
    }

    @When("Asks for description")
    public void askForDescription() {
        result = post.getDescription();
    }

    @Then("Received description")
    public void receivedDescription() {
        assertEquals(result, "I feel like I'm on fiiiiire.");
    }
}
