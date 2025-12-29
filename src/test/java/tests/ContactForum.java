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
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.CookieUtils;

public class ContactForum {
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
    public void testSend() {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebElement forumButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-tb-region='Btn:Forum']")));
        forumButton.click();
        assertEquals("https://forum.sportsport.ba/",webDriver.getCurrentUrl());
        WebElement Contact = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fa-envelope")));
        js.executeScript("arguments[0].scrollIntoView();", Contact);
        Contact.click();
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        email.sendKeys("deni12@gmail.com");
        WebElement name = webDriver.findElement(By.id("name"));
        name.sendKeys("deni12");
        WebElement subject = webDriver.findElement(By.id("subject"));
        subject.sendKeys("deni12");
        WebElement message = webDriver.findElement(By.id("message"));
        message.sendKeys("deni12deni12deni12");
        WebElement submit = webDriver.findElement(By.id("submit"));
        //submit.click();
    }

    @Test
    public void testContactwithouEmail() {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        Wait<WebDriver> wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebElement forumButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-tb-region='Btn:Forum']")));
        forumButton.click();
        assertEquals("https://forum.sportsport.ba/",webDriver.getCurrentUrl());
        WebElement Contact = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fa-envelope")));
        js.executeScript("arguments[0].scrollIntoView();", Contact);
        Contact.click();
        js.executeScript("window.scrollBy(0,400)");
        WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        name.sendKeys("deni12");
        WebElement subject = webDriver.findElement(By.id("subject"));
        subject.sendKeys("deni12");
        WebElement message = webDriver.findElement(By.id("message"));
        message.sendKeys("deni12deni12deni12");
        WebElement submit = webDriver.findElement(By.className("button1"));
        submit.click();
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error")));
        String fullText = error.getText();
        String firstSentence = fullText.split("\\.")[0] + ".";
        assertEquals("Adresa e-maila koju si upisao/la je prekratka.",firstSentence);
    }

    @Test
    public void testContactwithouUsername() {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        Wait<WebDriver> wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebElement forumButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-tb-region='Btn:Forum']")));
        forumButton.click();
        assertEquals("https://forum.sportsport.ba/",webDriver.getCurrentUrl());
        WebElement Contact = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fa-envelope")));
        js.executeScript("arguments[0].scrollIntoView();", Contact);
        Contact.click();
        js.executeScript("window.scrollBy(0,400)");
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        email.sendKeys("deni12@gmail.com");
        WebElement subject = webDriver.findElement(By.id("subject"));
        subject.sendKeys("deni12");
        WebElement message = webDriver.findElement(By.id("message"));
        message.sendKeys("deni12deni12deni12");
        WebElement submit = webDriver.findElement(By.className("button1"));
        submit.click();
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error")));
        String fullText = error.getText();
        String firstSentence = fullText.split("\\.")[0] + ".";
        assertEquals("Upiši ime pošiljatelja/ice.",firstSentence);
    }

    @Test
    public void testContactwithouSubject() {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        Wait<WebDriver> wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebElement forumButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-tb-region='Btn:Forum']")));
        forumButton.click();
        assertEquals("https://forum.sportsport.ba/",webDriver.getCurrentUrl());
        WebElement Contact = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fa-envelope")));
        js.executeScript("arguments[0].scrollIntoView();", Contact);
        Contact.click();
        js.executeScript("window.scrollBy(0,400)");
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        email.sendKeys("deni12@gmail.com");
        WebElement name = webDriver.findElement(By.id("name"));
        name.sendKeys("deni12");
        WebElement message = webDriver.findElement(By.id("message"));
        message.sendKeys("deni12deni12deni12");
        WebElement submit = webDriver.findElement(By.className("button1"));
        submit.click();
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error")));
        String fullText = error.getText();
        String firstSentence = fullText.split("\\.")[0] + ".";
        assertEquals("Upiši naslov [predmet] poruke.",firstSentence);
    }

    @Test
    public void testContactwithouMessage() {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        Wait<WebDriver> wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebElement forumButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-tb-region='Btn:Forum']")));
        forumButton.click();
        assertEquals("https://forum.sportsport.ba/",webDriver.getCurrentUrl());
        WebElement Contact = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fa-envelope")));
        js.executeScript("arguments[0].scrollIntoView();", Contact);
        Contact.click();
        js.executeScript("window.scrollBy(0,400)");
        WebElement name = webDriver.findElement(By.id("name"));
        name.sendKeys("deni12");
        WebElement subject = webDriver.findElement(By.id("subject"));
        subject.sendKeys("deni12");
        WebElement submit = webDriver.findElement(By.className("button1"));
        submit.click();
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error")));
        String fullText = error.getText();
        String firstSentence = fullText.split("\\.")[0] + ".";
        assertEquals("Upiši tekst [sadržaj] poruke.",firstSentence);
    }




}
