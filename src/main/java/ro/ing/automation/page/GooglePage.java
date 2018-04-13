package ro.ing.automation.page;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import ro.ing.automation.utils.ResultsFile;

import java.util.List;

@Component
public class GooglePage extends WebPage {

    public void goTo() {

        open(getGoogleURL());
        Assert.isTrue(driver.getCurrentUrl().contains("google"), "Unable to open google page");
    }

    public void insertString(String someString) {
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys(someString);
        element.sendKeys(Keys.ESCAPE);
    }

    public void clickSearch() {
        WebElement okBtn = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.name("btnK")));
        okBtn.click();
    }

    public void checkResults() {
        WebElement resultsStats = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));

        List<WebElement> findElements = driver.findElements(By.xpath("//*[@id='rso']//h3/a"));
        // Test assumes desired term returns a needed list of links.
        // Test defines list of expected results as a user-modifiable file
        // File is parsed into a list of hrefs, then the hrefs get compared to whatever the google search finds
        String resultsFileLocation = ".\\src\\test\\resources\\expected_results\\expected_results_google_search.txt";
        List expectedResults = ResultsFile.read(resultsFileLocation);
        for (WebElement webElement : findElements)
        {
            String href = webElement.getAttribute("href");
            // Test fails whenever we encounter an unexpected link in the search results
            // And it will fail depending on time of day/google indexing etc.
            // I guess I did it more to prove DDT
            // Yes I know I could have done better.
            Assert.isTrue(expectedResults.contains(href), "Could not find "+ href + " in expected results list");
        }
    }

    public void clickFirst() {
        WebElement resultsStats = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));
        WebElement firstLink = driver.findElements(By.xpath("//*[@id='rso']//h3/a")).get(0);
        firstLink.click();
    }
}
