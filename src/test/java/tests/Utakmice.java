package tests;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.CookieUtils;

public class Utakmice {
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
    public void PlayerTesting() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebElement searchbar = webDriver.findElement(By.cssSelector("input[type=text]"));
        searchbar.click();
        searchbar.clear();
        searchbar.sendKeys("FK ");
        Thread.sleep(500); 
        searchbar.sendKeys("Sarajevo");
        WebElement suggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='autocomplete-suggestion' and contains(., 'FK Sarajevo')]")));
        suggestion.click();
        WebElement Igraci = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("IGRAČI")));
        Igraci.click();
        Select select = new Select(webDriver.findElement(By.name("season_id")));
        select.selectByValue("2025/2026");
        WebElement player = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Beganović Amar")));
        assertEquals("Beganović Amar", player.getText());
        player.click();
        WebElement playernews = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("VIJESTI")));
        playernews.click();
        WebElement getText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".text-5xl.font-bold.leading-tight.dark\\:text-white")));
        assertEquals("Amar Beganović", getText.getText());
    }

    @Test
    public void GamesTest() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebElement searchbar = webDriver.findElement(By.cssSelector("input[type=text]"));
        searchbar.click();
        searchbar.clear();
        searchbar.sendKeys("FK ");
        Thread.sleep(500); // 
        searchbar.sendKeys("Sarajevo");
        WebElement suggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='autocomplete-suggestion' and contains(., 'FK Sarajevo')]")));
        suggestion.click();
        WebElement Games = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("UTAKMICE")));
        Games.click();
        Select select = new Select(webDriver.findElement(By.name("season_id")));
        select.selectByValue("2021/2022");
        WebElement table = webDriver.findElement(By.xpath("//tbody//tr[10]//td[7]"));
        table.click();
        assertEquals("https://sportsport.ba/utakmica/fk-sarajevo-fk-zeljeznicar-10-kolo-wwin-liga-bih-20212022/55370", webDriver.getCurrentUrl());

    }
}
