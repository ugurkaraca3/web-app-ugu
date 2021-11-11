package guru.springframework.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "src/test/features" })
public class guruTest extends AbstractTestNGCucumberTests {

}
