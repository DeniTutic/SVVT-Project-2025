package tests;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.CookieUtils;

public class ForgotPassword {
    private static WebDriver webDriver;
    private static String baseUrl;


    @BeforeAll
    public static void setUp() {
        baseUrl = "https://sportsport.ba/";

        ChromeOptions options = new ChromeOptions()
                .addArguments("--no-first-run")
                .addArguments("--disable-extensions-ui")
                .addArguments("--disable-blink-features=AutomationControlled");

        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
    }
    @Test
    public void testLoadCookies() {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();

        // Load cookies
        CookieUtils.loadCookies(webDriver, "cookies.data");

        // Perform your test steps after cookies are loaded
        System.out.println("Cookies loaded successfully!");
    }


    @AfterAll
    public static void tearDown() {
        // Close the browser
        if (webDriver != null) {
            webDriver.quit();
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //NEW LINE
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testForgotPassword()throws InterruptedException{
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(4));
        WebElement LoginIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='/login']"))); // Loading start of website
        LoginIcon.click();
        WebElement forgotPassword = webDriver.findElement(By.linkText("Zaboravili ste lozinku?"));
        forgotPassword.click();
        WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.name("email")));
        email.click();
        email.sendKeys("tarik123@gmail.com");
        WebElement button = webDriver.findElement(By.xpath("(//button[@type='submit'])[2]"));
        button.click();
        Thread.sleep(15000);
        webDriver.navigate().refresh();
        WebElement email1 = webDriver.findElement(By.id("email"));
        email1.click();
        email1.sendKeys("`tarik123@gmail.com");
        WebElement password = webDriver.findElement(By.name("password"));
        WebElement password_confirmation = webDriver.findElement(By.id("password_confirmation"));
        password.click();
        password.sendKeys("denideni12345");
        password_confirmation.click();
        password_confirmation.sendKeys("denideni12345");
        WebElement button2 = webDriver.findElement(By.xpath("(//button[@type='submit'])[2]"));
        button2.click();

    }

}
