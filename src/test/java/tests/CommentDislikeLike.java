package tests;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.CookieUtils;

public class CommentDislikeLike {
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
    public void CommentTest() throws InterruptedException{
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        CookieUtils.loadCookies(webDriver, "cookies.data");
        WebElement searchbar = webDriver.findElement(By.cssSelector("input[type=text]"));
        searchbar.click();
        searchbar.clear();
        searchbar.sendKeys("FK ");
        Thread.sleep(500); // -- We wait so the Search engine can suggest the correct thing
        searchbar.sendKeys("Sarajevo");
        Thread.sleep(2000);
        WebElement link = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("FK Sarajevo postigao dogovor s Poreznom upravom!")));
        link.click();
        js.executeScript("window.scrollBy(0,2400)");
        Thread.sleep(4000);
        WebElement comments = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='flex items-center px-2 mt-5']")));
        comments.click();
        WebElement comment_box = wait.until(ExpectedConditions.elementToBeClickable(By.id("comment-main")));
        comment_box.sendKeys("Drzavi se mora platiti. Sad ce lavordjice u Strazbur da podnesu apelaciju hahahahaha");
        WebElement button = webDriver.findElement(By.className("send-comment"));
        button.click();
    }

    @Test
    public void CommentNotRegisterd() throws InterruptedException{
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebElement searchbar = webDriver.findElement(By.cssSelector("input[type=text]"));
        searchbar.click();
        searchbar.clear();
        searchbar.sendKeys("FK ");
        Thread.sleep(500); 
        searchbar.sendKeys("Sarajevo");
        Thread.sleep(2000); 
        WebElement link = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("FK Sarajevo postigao dogovor s Poreznom upravom!")));
        link.click();
        js.executeScript("window.scrollBy(0,2400)");
        Thread.sleep(4000);
        WebElement comments = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='flex items-center px-2 mt-5']")));
        comments.click();
        WebElement comment_box = wait.until(ExpectedConditions.elementToBeClickable(By.id("comment-main")));
        comment_box.sendKeys("Drzavi se mora platiti.");
        WebElement button = webDriver.findElement(By.className("send-comment"));
        button.click();
        Thread.sleep(1000);
        Alert alert = webDriver.switchTo().alert();
        String alertX = alert.getText();
        assertEquals("Unauthenticated.",alertX);

    }
    @Test
    public void Dislike() throws InterruptedException{
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        CookieUtils.loadCookies(webDriver, "cookies.data");
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebElement searchbar = webDriver.findElement(By.cssSelector("input[type=text]"));
        searchbar.click();
        searchbar.clear();
        searchbar.sendKeys("FK ");
        Thread.sleep(500); 
        searchbar.sendKeys("Sarajevo");
        Thread.sleep(2000); 
        WebElement link = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("FK Sarajevo postigao dogovor s Poreznom upravom!")));
        link.click();
        js.executeScript("window.scrollBy(0,2400)");
        Thread.sleep(4000);
        WebElement comments = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='flex items-center px-2 mt-5']")));
        comments.click();
        WebElement dislike = wait.until((ExpectedConditions.elementToBeClickable(By.className("downvote"))));
        dislike.click();
    }

    @Test
    public void DislikeUnReg() throws InterruptedException{
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebElement searchbar = webDriver.findElement(By.cssSelector("input[type=text]"));
        searchbar.click();
        searchbar.clear();
        searchbar.sendKeys("FK ");
        Thread.sleep(500); 
        searchbar.sendKeys("Sarajevo");
        Thread.sleep(2000); 
        WebElement link = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("FK Sarajevo postigao dogovor s Poreznom upravom!")));
        link.click();
        js.executeScript("window.scrollBy(0,2400)");
        Thread.sleep(4000);
        WebElement comments = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='flex items-center px-2 mt-5']")));
        comments.click();
        WebElement dislike = wait.until((ExpectedConditions.elementToBeClickable(By.className("downvote"))));
        dislike.click();
        //functional but a pop up add can break the test here
        WebElement text = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(), 'NISTE PRIJAVLJENI')]")));
        String realTEXT = text.getText();
        assertEquals("NISTE PRIJAVLJENI",realTEXT);
    }

    @Test
    public void Like() throws InterruptedException{
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        CookieUtils.loadCookies(webDriver, "cookies.data");
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebElement searchbar = webDriver.findElement(By.cssSelector("input[type=text]"));
        searchbar.click();
        searchbar.clear();
        searchbar.sendKeys("FK ");
        Thread.sleep(500); // -- We wait so the Search engine can suggest the correct thing
        searchbar.sendKeys("Sarajevo");
        Thread.sleep(2000);
        WebElement link = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("FK Sarajevo postigao dogovor s Poreznom upravom!")));
        link.click();
        js.executeScript("window.scrollBy(0,2400)");
        Thread.sleep(4000);
        WebElement comments = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='flex items-center px-2 mt-5']")));
        comments.click();
        WebElement like = wait.until((ExpectedConditions.elementToBeClickable(By.className("upvote"))));
        like.click();
    }

    @Test
    public void LikeUnReg() throws InterruptedException{
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebElement searchbar = webDriver.findElement(By.cssSelector("input[type=text]"));
        searchbar.click();
        searchbar.clear();
        searchbar.sendKeys("FK ");
        Thread.sleep(500); 
        searchbar.sendKeys("Sarajevo");
        Thread.sleep(2000); 
        WebElement link = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("FK Sarajevo postigao dogovor s Poreznom upravom!")));
        link.click();
        js.executeScript("window.scrollBy(0,2400)");
        Thread.sleep(4000);
        WebElement comments = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='flex items-center px-2 mt-5']")));
        comments.click();
        WebElement like = wait.until((ExpectedConditions.elementToBeClickable(By.className("upvote"))));
        like.click();
        WebElement text = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(), 'NISTE PRIJAVLJENI')]")));
        String realTEXT = text.getText();
        assertEquals("NISTE PRIJAVLJENI",realTEXT);
    }

    
}
