package ro.ing.automation.glue;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import ro.ing.automation.configuration.CodeChallengeConfiguration;
import ro.ing.automation.page.GooglePage;
import ro.ing.automation.page.INGPage;
import ro.ing.automation.page.WebPage;

import java.util.concurrent.atomic.AtomicInteger;


@ContextConfiguration(classes = CodeChallengeConfiguration.class)
public class StepDefinition {

    @Autowired
    private AtomicInteger atomicInteger;

    private WebPage webPage = new WebPage();
    private GooglePage googlePage = new GooglePage();
    private INGPage ingPage = new INGPage();
    private CodeChallengeConfiguration Config = new CodeChallengeConfiguration();

    @Given("^I go to the Google search page$")
    public void goToGoogleSearchPage() {
        googlePage.goTo();
    }

    @When("^I insert some string in the search input field$")
    public void insertStringInInputField(){
        googlePage.insertString(Config.getSearchString());
    }

    @And("^I click on the Google Search button$")
    public void clickSearchButton(){
        googlePage.clickSearch();
    }

    @Then("^The browers displays list o search results$")
    public void checkResults(){
        googlePage.checkResults();
    }

    @Given("^I click on First Result$")
    public void clickFirstResult() {
        googlePage.clickFirst();
    }

    @Given("^I accessed the ING Homebank link as first link from Google search$")
    public void goToGoogleSearchForSomething(){
        googlePage.goTo();
        googlePage.insertString(Config.getSearchString());
        googlePage.clickSearch();
        googlePage.clickFirst();
        ingPage.checkStartingURL();
    }

    @When("^I click the Create Account link, I get to see the Create Account page$")
    public void clickCreateAccountLink(){
        ingPage.clickCreateAccountLink();
        ingPage.checkCreateAccountPageContents();
    }

    @Then("^the Create Account page contains some expected fields$")
    public void checkCreateAccountElements(){
        ingPage.checkCreateAccountPageContents();
    }

    @After
    public void tearDown() {
        webPage.closeDriver();
    }
}
