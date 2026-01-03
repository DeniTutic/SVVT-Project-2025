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
        Thread.sleep(500); // -- cekamo da search engine predlozi
        searchbar.sendKeys("Burch");
        Thread.sleep(2000);
        String actualTitle = webDriver.getTitle();
        String expectedTitle = "KK Igman Burch - SportSport.ba";
        assertEquals(expectedTitle, actualTitle, "Failed");
    }
    @Test
    public void SearchEngineCapitalletters() throws InterruptedException{
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebElement searchbar = webDriver.findElement(By.id("search-input"));
        searchbar.click();
        searchbar.clear();
        searchbar.sendKeys("kK iGMan ");
        Thread.sleep(500); // -- 
        searchbar.sendKeys("buRCH");
        Thread.sleep(2000);
        String actualTitle = webDriver.getTitle();
        String expectedTitle = "KK Igman Burch - SportSport.ba";
        assertEquals(expectedTitle, actualTitle, "Failed");
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
        String actualTitle = webDriver.getTitle();
        String expectedTitle = "Pretraga - SportSport.ba";
        assertEquals(expectedTitle, actualTitle, "Failed");
    }
    @Test
    public void SearchEnginePhone() throws InterruptedException{
        webDriver.manage().window().maximize();
        webDriver.manage().window().setSize(new Dimension(400, 701));
        webDriver.get(baseUrl);
        Thread.sleep(20000);
        WebElement hambugrgerbar = webDriver.findElement(By.id("hamburger-btn"));
        WebElement searchbar = webDriver.findElement(By.id("search-input"));
        WebElement searchButton = webDriver.findElement(By.cssSelector("button[type=submit]"));
        hambugrgerbar.click();
        Thread.sleep(1500);
        searchbar.click();
        searchbar.sendKeys("FK Sarajevo");
        Thread.sleep(500);
        searchButton.click();
        Thread.sleep(2000); 
        String actualTitle = webDriver.getTitle();
        String expectedTitle = "FK Sarajevo - SportSport.ba";
        assertEquals(expectedTitle, actualTitle, "Failed");
        // u slucaj kad se smanji secreen hamburher menu ne radi pa treba kliknuti na search ikonicu

    }
}
