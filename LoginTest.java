package day6;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class LoginTest {
    WebDriver chromeDriver;
    WebDriver firefoxDriver;
    @BeforeMethod
    public void setup() {
        chromeDriver = new ChromeDriver();
        firefoxDriver = new FirefoxDriver();
        chromeDriver.manage().window().maximize();
        firefoxDriver.manage().window().maximize();
        chromeDriver.get("https://practicetestautomation.com/practice-test-login/");
        firefoxDriver.get("https://practicetestautomation.com/practice-test-login/");
    }
    @Test
    public void positiveLoginTest() {
        runLoginTest(chromeDriver);
        runLoginTest(firefoxDriver);
    }
    @Test
    public void negativeUsernameTest() {
        runNegativeUsernameTest(chromeDriver);
        runNegativeUsernameTest(firefoxDriver);
    }
    @Test
    public void negativePasswordTest() {
        runNegativePasswordTest(chromeDriver);
        runNegativePasswordTest(firefoxDriver);
    }
    public void runLoginTest(WebDriver driver) {
        driver.findElement(By.id("username")).sendKeys("student");
        driver.findElement(By.id("password")).sendKeys("Password123");
        driver.findElement(By.id("submit")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("practicetestautomation.com/logged-in-successfully/"));
        Assert.assertTrue(driver.getPageSource().contains("Congratulations") || driver.getPageSource().contains("successfully logged in"));
        Assert.assertTrue(driver.findElement(By.linkText("Log out")).isDisplayed());
    }
    public void runNegativeUsernameTest(WebDriver driver) {
        driver.findElement(By.id("username")).sendKeys("incorrectUser");
        driver.findElement(By.id("password")).sendKeys("Password123");
        driver.findElement(By.id("submit")).click();
        String errorText = driver.findElement(By.id("error")).getText();
        Assert.assertEquals(errorText, "Your username is invalid!");
    }
    public void runNegativePasswordTest(WebDriver driver) {
        driver.findElement(By.id("username")).sendKeys("student");
        driver.findElement(By.id("password")).sendKeys("incorrectPassword");
        driver.findElement(By.id("submit")).click();
        String errorText = driver.findElement(By.id("error")).getText();
        Assert.assertEquals(errorText, "Your password is invalid!");
    }
    @AfterMethod
    public void tearDown() {
        if (chromeDriver != null) {
            chromeDriver.quit();
        }
        if (firefoxDriver != null) {
            firefoxDriver.quit();
        }
    }
}