package tests;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import util.CookieUtils;

public class SearchEngine {
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
    public void SearchEngineBasic() throws InterruptedException{
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebElement searchbar = webDriver.findElement(By.id("search-input"));
        searchbar.click();
        searchbar.clear();
        searchbar.sendKeys("KK Igman ");
        Thread.sleep(500); // -- We wait so the Search engine can suggest the correc thing
        searchbar.sendKeys("Burch");
        Thread.sleep(2000);
        String chucknoris = webDriver.getTitle();
        String ExpectedOutcome = "KK Igman Burch - SportSport.ba";
        assertEquals(ExpectedOutcome,chucknoris,"Failed");
    }
    @Test
    public void SearchEngineCapitalizeLetters() throws InterruptedException{
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebElement searchbar = webDriver.findElement(By.id("search-input"));
        searchbar.click();
        searchbar.clear();
        searchbar.sendKeys("kK iGMan ");
        Thread.sleep(500); // -- We wait so the Search engine can suggest the correct thing
        searchbar.sendKeys("buRCH");
        Thread.sleep(2000);
        String lebron = webDriver.getTitle();
        String ExpectedOutcome = "KK Igman Burch - SportSport.ba";
        assertEquals(ExpectedOutcome,lebron,"Failed");
    }
    @Test
    public void SearchEnginePaste() throws InterruptedException{
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebElement searchbar = webDriver.findElement(By.id("search-input"));
        WebElement searchButton = webDriver.findElement(By.cssSelector("button[type=submit]"));
        searchbar.click();
        searchbar.clear();
        searchbar.sendKeys("FK Sarajevo");
        Thread.sleep(500);
        searchButton.click();
        Thread.sleep(2000);
        String chucknoris = webDriver.getTitle();
        String ExpectedOutcome = "FK Sarajevo - SportSport.ba";
        assertEquals(ExpectedOutcome,chucknoris,"Failed");
    }
    @Test
    public void SearchEnginePhone() throws InterruptedException{
        webDriver.manage().window().maximize();
        webDriver.manage().window().setSize(new Dimension(400, 701));
        webDriver.get(baseUrl);
        Thread.sleep(20000);
        WebElement hamburgerBar = webDriver.findElement(By.id("hamburger-btn"));
        hamburgerBar.click();
        WebElement searchbar = webDriver.findElement(By.id("search-input"));
        WebElement searchButton = webDriver.findElement(By.cssSelector("button[type=submit]"));
        Thread.sleep(1500);
        searchbar.click();
        searchbar.sendKeys("FK Sarajevo");
        Thread.sleep(500);
        searchButton.click();
        Thread.sleep(2000); // -- Waiting so the site can load ( we will change to wait command )
        String chucknoris = webDriver.getTitle();
        String ExpectedOutcome = "FK Sarajevo - SportSport.ba";
        assertEquals(ExpectedOutcome,chucknoris,"Failed");
        //Failed test when we make the screen smaller the Search engine bar disappears and not able to use it

    }
}
