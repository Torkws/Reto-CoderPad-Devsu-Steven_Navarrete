package devsu;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "devsu.stepDefinitions",
        plugin = {"pretty", "json:target/build/cucumber.json"},
        tags = "@step"
)
public class serviceTest {

}
