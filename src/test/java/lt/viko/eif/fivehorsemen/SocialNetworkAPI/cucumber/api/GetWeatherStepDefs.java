package lt.viko.eif.fivehorsemen.SocialNetworkAPI.cucumber.api;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.repository.APIRepositoryImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;

public class GetWeatherStepDefs {

    private String city;
    private RestTemplate restTemplate;
    private String weatherApiKey = "9692b48fc9c249b4a960cd898a147223";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        HttpServletRequest httpServletRequestMock = new MockHttpServletRequest();
        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(httpServletRequestMock);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);
    }

    @Given("User has specified his living location")
    public void userHasSpecifiedHisLivingLocation() {
        city = "Vilnius";
    }

    @When("User wants to see the weather")
    public void userWantsToSeeTheWeather() {
        restTemplate = new RestTemplate();
    }

    @Then("User gets weather information")
    public void userGetsWeatherInformation() {
        String uri = "https://api.weatherbit.io/v2.0/current?city=" + city + "&key=" + weatherApiKey;
        String result = restTemplate.getForObject(uri, String.class);
        assertThat(result).isNotNull();
    }
}
