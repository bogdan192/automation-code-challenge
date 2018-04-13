package ro.ing.automation;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"json:target/report/cucumber.json"},
        features = "src/test/resources/features")
public class RunCodeChallenge {


}
