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
import junit.framework.Assert;

public class stepForm {

    WebDriver driver;
    WebDriverWait wait;

    @Given("User is on the form submission page")
    public void user_is_on_the_form_submission_page() {
        
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get("https://westfloridaahec.org/");
        WebElement programsLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"menu-item-264\"]/a/span[1]")));
        programsLink.click();
        List<WebElement> dropdownOptions = driver.findElements(By.xpath("//*[@id='menu-item-264']/ul/li/a"));
        for (WebElement option : dropdownOptions) {
            if (option.getText().equalsIgnoreCase("Healthy Aging")) {
                wait.until(ExpectedConditions.elementToBeClickable(option)).click();
                break;
            }
        }
    }

    @When("User enters {string} and {string} and {string} and {string}")
    public void user_enters_and_and_and(String firstName, String lastName, String phone, String email) {
        WebElement firstname = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"cog-input-auto-0\"]")));
        firstname.sendKeys(firstName);

        WebElement lastname = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"cog-input-auto-1\"]")));
        lastname.sendKeys(lastName);

        WebElement phoneno = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"cog-1\"]")));
        phoneno.sendKeys(phone);

        WebElement emailid = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"cog-2\"]")));
        emailid.sendKeys(email);

        WebElement country = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"post-500\"]/div/div[1]/div/div[1]/div/form/div/div/div[1]/div/div[4]/fieldset[2]/div[1]/div[1]/div/label[1]/span[1]/span")));
        country.click();

        WebElement submit = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[type='submit']")));
        submit.click();
    }

    @Then("User should redirected to the secure area")
    public void user_should_redirected_to_the_secure_area() {
        WebElement msg1= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='cog-confirmation__message cog-content cog-html cog-input' and @role='alert']")));
        Assert.assertEquals("Thank you for filling out the form. Your response has been recorded.", msg1.getText());
        driver.quit();
    }
    
  
    @Then("An error message should displayed for the invalid email")
    public void an_error_message_should_displayed_for_the_invalid_email() {
    	WebElement msg3 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"post-500\"]/div/div[1]/div/div[1]/div/form/div/div/div[1]/div/div[3]/div[2]/div[3]")));
        Assert.assertEquals("Email must be formatted as name@address.xyz.", msg3.getText());
        driver.quit();
    }

}
