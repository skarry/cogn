package org.o2.driver;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class DriverUtil {
    private static final Logger logger = LoggerFactory.getLogger(DriverUtil.class);
    private Properties envProperties = new Properties();
    private WebDriver driver;
    InputStream in;

    public DriverUtil() {
        try {
            in = getClass().getClassLoader().getResourceAsStream("environment.properties");
            envProperties.load(in);
        } catch (IOException ioe) {
            logger.debug("error loading file with msg:", ioe);
        }
    }

    /**
     * Method to get WebDriver instance depending on browser.type property in environment.properties file
     *
     * @return {@link org.openqa.selenium.WebDriver}
     */
    public WebDriver getDriver() {
        if (envProperties.getProperty("browser.type").equals("firefox")) {
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("network.cookie.cookieBehavior", 2);
            driver = new FirefoxDriver(profile);
        } else if (envProperties.getProperty("browser.type").equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("profile.default_content_settings.cookies", 2);
            options.setExperimentalOption("prefs", prefs);
            driver = new ChromeDriver(options);
        }
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
        return driver;
    }


    /**
     * Util method to mouse over on given element
     *
     * @param driver
     * @param element
     */
    public static void mouseOverOnElement(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    /**
     * @param driver
     */
    public static void printCookies(WebDriver driver) {
        Set<Cookie> cookies = driver.manage().getCookies();
        for (Cookie cookie : cookies) {
            System.out.println("Cookie Name: " + cookie.getName() + ", Cookie Value: " + cookie.getValue() + ", Cookie Expiry: " + cookie.getExpiry());
        }
    }
}
