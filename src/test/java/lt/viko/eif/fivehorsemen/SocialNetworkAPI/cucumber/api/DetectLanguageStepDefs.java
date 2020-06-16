package lt.viko.eif.fivehorsemen.SocialNetworkAPI.cucumber.api;

import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lt.viko.eif.fivehorsemen.SocialNetworkAPI.repository.APIRepositoryImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Detect post language test
 *
 * @author Laurynas Zlatkus
 * @author Rytis Razmus
 * @author Jonas Zemaitis
 * @author Evaldas Tamutis
 * @author Evaldas Zalnierius
 */

public class DetectLanguageStepDefs {

    private String result;
    private Map<String, String> map = new HashMap<>();

    @InjectMocks
    private lt.viko.eif.fivehorsemen.SocialNetworkAPI.api.APIController apiController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        HttpServletRequest httpServletRequestMock = new MockHttpServletRequest();
        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(httpServletRequestMock);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);
    }

    @When("Client enters some text")
    public void clientEntersSomeText() {
        map = new HashMap<>();
        map.put("text", "Aš žinau ką veikei aną vasarą.");
    }

    @Then("The language is returned based on the text")
    public void languageIsReturned() {
        result = apiController.detectlanguage(map);
        assertThat(result).isNotNull();
    }
}
