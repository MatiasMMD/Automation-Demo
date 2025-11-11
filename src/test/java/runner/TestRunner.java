package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src\\test\\resources\\features",
    glue = "steps",
    plugin = { "pretty" , "html:build/cucumber-reports/index.html" },
    tags = "@AddToCart"
)

public class TestRunner {  
}