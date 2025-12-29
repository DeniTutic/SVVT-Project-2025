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

public class FollowBlockAccount {
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

    // Sadly do not want to report someone just because i might get banned because 4-5 accounts have been already banned!

    @Test
    public void FollowUnFollowUserTEST() {
        webDriver.get("https://sportsport.ba/profil/K1Fighter");
        webDriver.manage().window().maximize();
        CookieUtils.loadCookies(webDriver, "cookies.data");
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebElement Follow = webDriver.findElement(By.className("js-follow-user"));
        Follow.click();
        WebElement UnFollow = wait.until(ExpectedConditions.elementToBeClickable(By.className("js-unfollow-user")));
        UnFollow.click();


    }

    @Test
    public void BlockUnBlockUserTEST() {
        webDriver.get("https://sportsport.ba/profil/K1Fighter");
        webDriver.manage().window().maximize();
        CookieUtils.loadCookies(webDriver, "cookies.data");
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebElement Block = webDriver.findElement(By.className("js-block-user"));
        Block.click();
        WebElement UnBlock = wait.until(ExpectedConditions.elementToBeClickable(By.className("js-unblock-user")));
        UnBlock.click();


    }

    //
}
