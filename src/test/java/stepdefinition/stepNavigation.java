package stepdefinition;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepNavigation {

	WebDriver driver;
    WebDriverWait wait;
   
	@Given("User is on Home page")
	public void user_is_on_home_page() {
			driver = new ChromeDriver();
	   	    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	   	   driver.get("https://westfloridaahec.org/");
	        driver.manage().window().maximize();
	        
	}
	@When("User clicked on health programs and navigated different health programs")
	public void user_clicked_on_health_programs_and_navigated_different_health_programs() {
		WebElement programsLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath( "//*[@id=\"menu-item-264\"]/a/span[1]")));
	    programsLink.click();
	    List<WebElement> dropdownOptions = driver.findElements(By.xpath("//*[@id='menu-item-264']/ul/li/a"));   
	    for (WebElement options : dropdownOptions) {
	    	String optionText=options.getText();
	        System.out.println("Navigating to: " +  optionText+" health program");
	        wait.until(ExpectedConditions.elementToBeClickable(options));
	        options.click();
	       
	        driver.navigate().back();
	       // programsLink.click();
	       
	        
		}
	}
	@Then("User able to navigate Home successfully")
	public void user_able_to_navigate_home_successfully() {
		
		WebElement home =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"menu-item-207\"]/a/span")));
		home.click();
		String act_url ="https://westfloridaahec.org/";
		String expect_url =driver.getCurrentUrl();
		org.junit.Assert.assertEquals(expect_url, act_url);
		driver.quit();
	}
	
	



}

