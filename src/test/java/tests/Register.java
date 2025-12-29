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
        String userName ="Avion911Porsche";
        String passWord ="Burch123";
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
        Email.sendKeys("tarik12@gmail.com");
        Password.sendKeys("Taretare");
        RePeat.sendKeys("Taretare");
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
        username.sendKeys("lebronjames23");
        Email.sendKeys("lbj2@gmail.com");
        Password.sendKeys("denideni12345");
        RePeat.sendKeys("denideni12345");
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
        String Greska = notmatch.getText();
        String greskakojuzelimo = "Polje email već postoji.";
        assertEquals(greskakojuzelimo, Greska,"Desile su se sljedeće greške.");
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
        username.sendKeys("tare");
        Email.sendKeys("tare@gmail.com");
        Password.sendKeys("tttaaarrreee");
        RePeat.sendKeys("tttaaarrreee");
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
        String Greska = notmatch.getText();
        String greskakojuzelimo = "Odabrano korisničko ime već postoji. Odaberite neko drugo.";
        assertEquals(greskakojuzelimo, Greska,"Desile su se sljedeće greške.");
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
        username.sendKeys("deni");
        Email.sendKeys("deni@gmail.com");
        Password.sendKeys("denitutic");
        RePeat.sendKeys("tuticdeni");
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
        String Greska = notmatch.getText();
        String greskakojuzelimo = "Potvrda polja password se ne poklapa.";
        assertEquals(greskakojuzelimo, Greska,"Desile su se sljedeće greške.");
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
        username.sendKeys("lebronjames23@#$");
        Email.sendKeys("deni@gmail.com");
        Password.sendKeys("deni");
        RePeat.sendKeys("deni");
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
        String Greska = notmatch.getText();
        String greskakojuzelimo = "Korisničko ime sadrži nedozvoljene znakove. Dozovljeni su: slova (a-z, A-Z), brojevi (0-9), te \".\" i \"_\".";
        assertEquals(greskakojuzelimo, Greska,"Desile su se sljedeće greške.");
    }
    @Test
    public void testRegisterwRECAHPTER() throws InterruptedException{
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
        username.sendKeys("tare");
        Email.sendKeys("tarik@gmail.com");
        Password.sendKeys("taretare");
        RePeat.sendKeys("taretare");
        WebElement Registerbutton = webDriver.findElement(By.xpath("//button[@class=\"bg-black bg-opacity-5 w-full hover:bg-red-600 hover:text-white px-4 py-2 font-trebuchet rounded-md text-md uppercase font-normal dark:bg-white dark:bg-opacity-20 h-10.5 flex justify-center items-center transition-all\"]"));
        Registerbutton.click();
        WebElement notmatch = webDriver.findElement(By.cssSelector("li"));
        String Greska = notmatch.getText();
        String greskakojuzelimo = "Polje g-recaptcha-response je obavezno.";
        assertEquals(greskakojuzelimo, Greska,"Desile su se sljedeće greške.");
    }
}
