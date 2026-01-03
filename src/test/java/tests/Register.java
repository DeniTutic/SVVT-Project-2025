package tests;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.CookieUtils;

public class Register {
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
    public void testRegister() throws InterruptedException{
        String userName ="TarikH24";
        String passWord ="Tarik123";
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebElement LoginIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='/login']"))); // Loading start of website
        LoginIcon.click();
        WebElement Register = webDriver.findElement(By.linkText("REGISTRACIJA"));
        Register.click();
        WebElement username = webDriver.findElement(By.name("username"));
        WebElement Email = webDriver.findElement(By.name("email"));
        WebElement Password = webDriver.findElement(By.name("password"));
        WebElement RePeat = webDriver.findElement(By.name("password_confirmation"));
        username.sendKeys(userName);
        Email.sendKeys("tarikhamzic22@gmail.com");
        Password.sendKeys(passWord);
        RePeat.sendKeys(passWord);
        Thread.sleep(2000);
        try {webDriver.switchTo().frame(webDriver.findElement(By.xpath("//iframe[@title='reCAPTCHA']")));
            WebElement recaptchaCheckbox = webDriver.findElement(By.cssSelector(".recaptcha-checkbox-border"));
            recaptchaCheckbox.click();
            webDriver.switchTo().defaultContent();
            Thread.sleep(10000);
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Cant handle reCAPTCHA: " + e.getMessage());
        }
        WebElement Registerbutton = webDriver.findElement(By.xpath("//button[@class=\"bg-black bg-opacity-5 " +
                "w-full hover:bg-red-600 hover:text-white px-4 py-2 font-trebuchet rounded-md text-md uppercase font-normal" +
                " dark:bg-white dark:bg-opacity-20 h-10.5 flex justify-center items-center transition-all\"]"));
        Thread.sleep(4000);
        Registerbutton.click();
        Thread.sleep(20000); // 20 Second to go to temp email and get user!
        WebElement email = webDriver.findElement(By.name("email"));
        WebElement password = webDriver.findElement(By.name("password"));
        email.sendKeys(userName);
        password.sendKeys(passWord);
        WebElement loginButton = webDriver.findElement(By.xpath("//button[@class=\"bg-black bg-opacity-5 w-full " +
                "hover:bg-red-600 hover:text-white px-4 py-2 font-trebuchet rounded-md text-md uppercase font-normal dark:bg-white" +
                " dark:bg-opacity-20 h-10.5 flex justify-center items-center transition-all\"]"));
        loginButton.click();
    }

    @Test
    public void testRegisterwEmailExists() throws InterruptedException{
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebElement LoginIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='/login']"))); // Loading start of website
        LoginIcon.click();
        WebElement Register = webDriver.findElement(By.linkText("REGISTRACIJA"));
        Register.click();
        WebElement username = webDriver.findElement(By.name("username"));
        WebElement Email = webDriver.findElement(By.name("email"));
        WebElement Password = webDriver.findElement(By.name("password"));
        WebElement RePeat = webDriver.findElement(By.name("password_confirmation"));
        username.sendKeys("TarikH29");
        Email.sendKeys("tarikhamzic@protonmail.com");
        Password.sendKeys("JimmyBuckets123");
        RePeat.sendKeys("JimmyBuckets123");
        Thread.sleep(2000);
        try {
            webDriver.switchTo().frame(webDriver.findElement(By.xpath("//iframe[@title='reCAPTCHA']")));
            WebElement recaptchaCheckbox = webDriver.findElement(By.cssSelector(".recaptcha-checkbox-border"));
            recaptchaCheckbox.click();
            webDriver.switchTo().defaultContent();
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Cant handle reCAPTCHA: " + e.getMessage());
        }
        Thread.sleep(10000);
        WebElement Registerbutton = webDriver.findElement(By.xpath("//button[@class=\"bg-black bg-opacity-5 w-full hover:bg-red-600 hover:text-white px-4 py-2 font-trebuchet rounded-md text-md uppercase font-normal dark:bg-white dark:bg-opacity-20 h-10.5 flex justify-center items-center transition-all\"]"));
        Registerbutton.click();
        WebElement notmatch = webDriver.findElement(By.cssSelector("li"));
        String actualErrorMessage = notmatch.getText();
        String expectedErrorMessage = "Polje email već postoji.";
        assertEquals(expectedErrorMessage, actualErrorMessage, "Errors occurred.");
    }

    @Test
    public void testRegisterwUsernameExists() throws InterruptedException{
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebDriverWait waitAdd = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebElement LoginIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='/login']"))); // Loading start of website
        LoginIcon.click();
        WebElement Register = webDriver.findElement(By.linkText("REGISTRACIJA"));
        Register.click();
        WebElement username = webDriver.findElement(By.name("username"));
        WebElement Email = webDriver.findElement(By.name("email"));
        WebElement Password = webDriver.findElement(By.name("password"));
        WebElement RePeat = webDriver.findElement(By.name("password_confirmation"));
        username.sendKeys("TH22");
        Email.sendKeys("atdatajjjj@gmail.com");
        Password.sendKeys("JimmyBuckets123");
        RePeat.sendKeys("JimmyBuckets123");
        Thread.sleep(2000);
        try {
            webDriver.switchTo().frame(webDriver.findElement(By.xpath("//iframe[@title='reCAPTCHA']")));
            WebElement recaptchaCheckbox = webDriver.findElement(By.cssSelector(".recaptcha-checkbox-border"));
            recaptchaCheckbox.click();
            webDriver.switchTo().defaultContent();
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Cant handle reCAPTCHA: " + e.getMessage());
        }
        Thread.sleep(10000);
        WebElement Registerbutton = webDriver.findElement(By.xpath("//button[@class=\"bg-black bg-opacity-5 w-full hover:bg-red-600 hover:text-white px-4 py-2 font-trebuchet rounded-md text-md uppercase font-normal dark:bg-white dark:bg-opacity-20 h-10.5 flex justify-center items-center transition-all\"]"));
        Registerbutton.click();
        WebElement notmatch = webDriver.findElement(By.cssSelector("li"));
        String actualErrorMessage = notmatch.getText();
        String expectedErrorMessage = "Odabrano korisničko ime već postoji. Odaberite neko drugo.";
        assertEquals(expectedErrorMessage, actualErrorMessage, "Errors occurred.");
    }

    @Test
    public void testRegisterwPasswordnotmatching() throws InterruptedException{
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebElement LoginIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='/login']"))); // Loading start of website
        LoginIcon.click();
        WebElement Register = webDriver.findElement(By.linkText("REGISTRACIJA"));
        Register.click();
        WebElement username = webDriver.findElement(By.name("username"));
        WebElement Email = webDriver.findElement(By.name("email"));
        WebElement Password = webDriver.findElement(By.name("password"));
        WebElement RePeat = webDriver.findElement(By.name("password_confirmation"));
        username.sendKeys("Tuta333");
        Email.sendKeys("atdatajjjj@gmail.com");
        Password.sendKeys("JimmyBuckets123");
        RePeat.sendKeys("JimmyBuckets");
        Thread.sleep(2000);
        try {
            webDriver.switchTo().frame(webDriver.findElement(By.xpath("//iframe[@title='reCAPTCHA']")));
            WebElement recaptchaCheckbox = webDriver.findElement(By.cssSelector(".recaptcha-checkbox-border"));
            recaptchaCheckbox.click();
            webDriver.switchTo().defaultContent();
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Cant handle reCAPTCHA: " + e.getMessage());
        }
        Thread.sleep(10000);
        WebElement Registerbutton = webDriver.findElement(By.xpath("//button[@class=\"bg-black bg-opacity-5 w-full hover:bg-red-600 hover:text-white px-4 py-2 font-trebuchet rounded-md text-md uppercase font-normal dark:bg-white dark:bg-opacity-20 h-10.5 flex justify-center items-center transition-all\"]"));
        Registerbutton.click();
        WebElement notmatch = webDriver.findElement(By.cssSelector("li"));
        String actualErrorMessage = notmatch.getText();
        String expectedErrorMessage = "Potvrda polja password se ne poklapa.";
        assertEquals(expectedErrorMessage, actualErrorMessage, "Errors occurred.");
    }

    @Test
    public void testRegisterwUsernamedoesntfollowrules() throws InterruptedException{
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebElement LoginIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='/login']"))); // Loading start of website
        LoginIcon.click();
        WebElement Register = webDriver.findElement(By.linkText("REGISTRACIJA"));
        Register.click();
        WebElement username = webDriver.findElement(By.name("username"));
        WebElement Email = webDriver.findElement(By.name("email"));
        WebElement Password = webDriver.findElement(By.name("password"));
        WebElement RePeat = webDriver.findElement(By.name("password_confirmation"));
        username.sendKeys("Tuta 22 !!");
        Email.sendKeys("atdatajjjj@gmail.com");
        Password.sendKeys("JimmyBuckets123");
        RePeat.sendKeys("JimmyBuckets123");
        Thread.sleep(2000);
        try {
            webDriver.switchTo().frame(webDriver.findElement(By.xpath("//iframe[@title='reCAPTCHA']")));
            WebElement recaptchaCheckbox = webDriver.findElement(By.cssSelector(".recaptcha-checkbox-border"));
            recaptchaCheckbox.click();
            webDriver.switchTo().defaultContent();
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Cant handle reCAPTCHA: " + e.getMessage());
        }
        Thread.sleep(10000);
        WebElement Registerbutton = webDriver.findElement(By.xpath("//button[@class=\"bg-black bg-opacity-5 w-full hover:bg-red-600 hover:text-white px-4 py-2 font-trebuchet rounded-md text-md uppercase font-normal dark:bg-white dark:bg-opacity-20 h-10.5 flex justify-center items-center transition-all\"]"));
        Registerbutton.click();
        WebElement notmatch = webDriver.findElement(By.cssSelector("li"));
        String actualErrorMessage = notmatch.getText();
        String expectedErrorMessage = "Korisničko ime sadrži nedozvoljene znakove. Dozovljeni su: slova (a-z, A-Z), brojevi (0-9), te \".\" i \"_\".";
        assertEquals(expectedErrorMessage, actualErrorMessage, "Errors occurred.");
    }
   
}
