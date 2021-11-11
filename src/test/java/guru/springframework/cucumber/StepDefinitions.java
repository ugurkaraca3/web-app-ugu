package guru.springframework.cucumber;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StepDefinitions {
	WebDriver driver;

	@Before
	public void setUp() {
		WebDriverManager.chromedriver().setup();
	}

	@AfterMethod
	public void tOut() throws InterruptedException {
		Thread.sleep(500);
	}

	@Given("Browser is opened")
	public void browser_is_opened() {
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@When("Guru is opened")
	public void guru_is_opened() {
		driver.get("http://localhost:8090/");
		Assert.assertTrue(driver.getTitle().equals("Spring Framework Guru"));
//		Assert.assertTrue(driver.getTitle().equals("PetClinic :: a Spring Framework demonstration"));
	}

	@When("Product is clicked")
	public void product_is_clicked() {
		Assert.assertEquals(driver.findElement(By.cssSelector("a[href=\"/products\"]")).getText(), "Products");
		driver.findElement(By.cssSelector("a[href=\"/products\"]")).click();

	}

	@When("Verify title")
	public void verify_title() {
		Assert.assertEquals(driver.findElement(By.xpath("/html/body/div/div[2]/h2")).getText(), "Product List");
	}

	@When("Verify product information")
	public void verify_product_information(io.cucumber.datatable.DataTable dataTable) {
		List<List<String>> data = dataTable.asLists();
		Assert.assertEquals(driver.findElement(By.xpath("/html/body/div/div[2]/table/tbody/tr[2]/td[2]")).getText(),
				data.get(1).get(0));
		Assert.assertEquals(driver.findElement(By.xpath("/html/body/div/div[2]/table/tbody/tr[3]/td[2]")).getText(),
				data.get(2).get(0));
		Assert.assertEquals(driver.findElement(By.xpath("/html/body/div/div[2]/table/tbody/tr[2]/td[3]")).getText(),
				data.get(1).get(1));
		Assert.assertEquals(driver.findElement(By.xpath("/html/body/div/div[2]/table/tbody/tr[3]/td[3]")).getText(),
				data.get(2).get(1));
		Assert.assertEquals(driver.findElement(By.xpath("/html/body/div/div[2]/table/tbody/tr[2]/td[4]")).getText(),
				data.get(1).get(2));
		Assert.assertEquals(driver.findElement(By.xpath("/html/body/div/div[2]/table/tbody/tr[3]/td[4]")).getText(),
				data.get(2).get(2));
	}

	@After
	public void cleanUp() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}
}
