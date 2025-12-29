package util;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CookieUtils {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

    public static void loadCookies(WebDriver webDriver, String filePath) {
        try {
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(";");

                String name = parts[0];
                String value = parts[1];
                String domain = parts[2];
                String path = parts[3];
                Date expiry = null;

                if (!parts[4].equals("null")) {
                    expiry = dateFormat.parse(parts[4]);
                }

                boolean isSecure = Boolean.parseBoolean(parts[5]);

                // Build and add the cookie
                Cookie cookie = new Cookie.Builder(name, value)
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

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
