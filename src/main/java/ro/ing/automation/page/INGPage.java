package ro.ing.automation.page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class INGPage extends WebPage {

    public void checkStartingURL(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement ingLogo = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("ing")));
        Assert.isTrue(driver.getCurrentUrl().contains("https://www.homebank.ro/public/HomeBankLogin/home?lang=ro"),
                "Unable to find expected browser address after clicking first link");
    }

    public void clickCreateAccountLink() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement createAccountLink = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href*='javascript: createAccount()']")));
        createAccountLink.click();
    }

    public void checkCreateAccountPageContents() {
        String cardSeriesElementId = "17_21__313";
        String cardNumberElementId = "17_22__314";
        String emailElementId = "17_25__306";
        String continueButtonClass = "buton-continua";
        WebDriverWait wait = new WebDriverWait(driver, 1);
        WebElement cardSeriesElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id(cardSeriesElementId)));
        Assert.isTrue(cardSeriesElement.getAttribute("placeholder").contains("Seria"),
                "Unable to find expected placeholder string in element ID " + cardSeriesElementId);
        cardSeriesElement.sendKeys("1");
        WebElement cardNumberElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id(cardNumberElementId)));
        Assert.isTrue(cardNumberElement.getAttribute("placeholder").contains("Numărul"),
                "Unable to find expected placeholder string in element ID " + cardNumberElementId);
        cardNumberElement.sendKeys("2");
        WebElement emailElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id(emailElementId)));
        Assert.isTrue(emailElement.getAttribute("type").contains("text"),
                "Element with id " + emailElementId + "is not of expected type: text");
        emailElement.sendKeys("test@test.com");
        WebElement continueButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className(continueButtonClass)));
        Assert.isTrue(continueButton.getAttribute("type").contains("button"),
                "Element " + continueButtonClass + "is not of expected type: button");
        continueButton.click();
        // TODO: Validate received errors... another time
    }
}
