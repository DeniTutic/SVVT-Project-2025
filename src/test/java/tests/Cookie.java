package tests;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Set;

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

public class Cookie {
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
    public void CookieSave() throws IOException {

        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebElement LoginIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='/login']"))); // Loading start of website
        LoginIcon.click();
        WebElement email = webDriver.findElement(By.name("email"));
        WebElement password = webDriver.findElement(By.name("password"));
        email.sendKeys("tarik@gmail.com");
        password.sendKeys("tarik123");
        WebElement loginButton = webDriver.findElement(By.xpath("//button[@class='bg-black bg-opacity-5 w-full hover:bg-red-600 hover:text-white px-4 py-2 font-trebuchet rounded-md text-md uppercase font-normal dark:bg-white dark:bg-opacity-20 h-10.5 flex justify-center items-center transition-all']"));
        loginButton.click();
        // Get cookies and save them to a file
        Set<org.openqa.selenium.Cookie> cookies = webDriver.manage().getCookies();
        File file = new File("cookies.data");
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (org.openqa.selenium.Cookie cookie : cookies) {
            bufferedWriter.write(cookie.getName() + ";" + cookie.getValue() + ";" + cookie.getDomain() + ";" +
                    cookie.getPath() + ";" + cookie.getExpiry() + ";" + cookie.isSecure());
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
        fileWriter.close();
    }
    @Test
    public void CookieLoad() throws InterruptedException, IOException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(2000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        File file = new File("cookies.data");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] parts = line.split(";");
            // Parse cookie fields
            String name = parts[0];
            String value = parts[1];
            String domain = parts[2];
            String path = parts[3];
            Date expiry = null;
            if (!parts[4].equals("null")) {
                try {
                    expiry = dateFormat.parse(parts[4]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            boolean isSecure = Boolean.parseBoolean(parts[5]);

            // Build and add the cookie
            org.openqa.selenium.Cookie cookie = new org.openqa.selenium.Cookie.Builder(name, value)
                    .domain(domain)
                    .path(path)
                    .expiresOn(expiry)
                    .isSecure(isSecure)
                    .build();
            webDriver.manage().addCookie(cookie);
        }
        bufferedReader.close();
        fileReader.close();
        webDriver.navigate().refresh();
    }
}
