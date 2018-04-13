package ro.ing.automation.configuration;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@ComponentScan("ro")
public class CodeChallengeConfiguration {


    private Properties properties;
    protected static WebDriver driver;


    public CodeChallengeConfiguration(){
        BufferedReader reader;
        String CONFIG_FILE = ".\\\\src\\\\main\\\\java\\\\configs\\\\configuration.properties";
        try {
            reader = new BufferedReader(new FileReader(CONFIG_FILE));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + CONFIG_FILE);
        }
    }

    protected String getGoogleURL() {
        return properties.getProperty("URL");
    }

    public String getSearchString(){
        return properties.getProperty("SEARCH_STRING");
    }

    private String getBrowserType() {
        return properties.getProperty("BROWSER_TYPE");
    }

    private String getResourceFolder() {
        return properties.getProperty("RESOURCE_PATH");
    }

    private String fullScreen() {
        return properties.getProperty("FULL_SCREEN");
    }

    protected WebDriver getDriver(){
        if (driver == null) {
            driver = ConfigureBrowser();
        }
        return driver;
    }

    public void closeDriver(){
        driver.quit();
        driver = null;

    }

    private WebDriver ConfigureBrowser() {
        switch (getBrowserType()) {
            case "firefox":
                String ffPath = getResourceFolder() +
                        "geckodriver.exe";
                System.setProperty("webdriver.gecko.driver", ffPath);
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setCapability("marionette", true);
                driver = new FirefoxDriver(firefoxOptions);
                if (fullScreen().equals("1")) {
                    driver.manage().window().maximize();
                }
                break;
            case "chrome":
                String chrPath = getResourceFolder() +
                        "chromedriver.exe";
                System.setProperty("webdriver.chrome.driver", chrPath);
                ChromeOptions options = new ChromeOptions();
                if (fullScreen().equals("1")) {
                    options.addArguments("--start-maximized");
                }
                driver = new ChromeDriver(options);
                break;
            default:
                System.setProperty("webdriver.gecko.driver", ".\\src\\test\\resources\\drivers\\geckodriver.exe");
                driver = new ChromeDriver();
                break;
        }
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return driver;
    }

    @Bean
    public AtomicInteger getAtomicInteger(){

        return new AtomicInteger();
    }

}


