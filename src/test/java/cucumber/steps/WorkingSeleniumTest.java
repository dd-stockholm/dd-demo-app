package cucumber.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.dd.demoapp.App;
import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.core.FluentAdapter;
import static org.fluentlenium.core.filter.FilterConstructor.withText;
import static org.junit.Assert.assertFalse;

public class WorkingSeleniumTest extends FluentTest {
    
    @When("^I go to it and click on omröstning$")
    public void i_go_to_it_and_click_on_omröstning() throws Throwable {
        FluentAdapter browser = StepsUtils.getBrowser();
        
        browser.goTo("http://localhost:18090/");
        browser.find("a", withText("Omröstning")).click();
    }

    @Then("^I should see the list of omröstningar$")
    public void i_should_see_the_list_of_omröstningar() throws Throwable {
        FluentAdapter browser = StepsUtils.getBrowser();
        
        assertFalse(browser.find(withText("Aktiva frågor")).isEmpty());
    }
    
}
