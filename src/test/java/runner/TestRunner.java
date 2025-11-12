package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src\\test\\resources\\features",
    glue = "steps",
    plugin = { "pretty" , "html:target/cucumber-reports" },
    tags = "@Cart"
)

public class TestRunner {  
}