package ro.ing.automation.page;

import org.openqa.selenium.support.PageFactory;
import ro.ing.automation.configuration.CodeChallengeConfiguration;


public class WebPage extends CodeChallengeConfiguration {


    public WebPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public WebPage open(String URL) {

        driver.get(URL);
        return this;
    }
}
