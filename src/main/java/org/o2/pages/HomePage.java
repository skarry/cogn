package org.o2.pages;


import org.o2.driver.DriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.o2.driver.DriverUtil.mouseOverOnElement;

public class HomePage extends WebPage {

    private static final Logger logger = LoggerFactory.getLogger(HomePage.class);
    private String alertLocator = "#main_wrapper";
    private String helpLocator = "#pn5";
    private String dropDownListLocator = "#pn5>ul>li>a";
    private String contactUsLocator = "a[manual_cm_re^='meganav_Help-_-Contact us']";

    public HomePage(WebDriver driver) {
        super(driver);

    }

    /**
     * Method to load Home page
     * @param url
     * @return {@link org.o2.pages.HomePage}
     */
    public HomePage load(String url) {
        driver.navigate().to(url);
        return this;
    }

    public void printHelpOptions() {
        mouseOverOnElement(driver, driver.findElement(By.cssSelector(helpLocator)));
        System.out.println("************** Printing Help Options *********************");

        List<WebElement> str = driver.findElements(By.cssSelector(dropDownListLocator));
        System.out.println(str.size());
        for (WebElement aStr : str) {
            System.out.println(aStr.getText());
        }
        System.out.println("**********************************************************");
    }

    public ContactUsPage clickOnContactLink() {
        logger.debug("About to click on contact us link");
        try {
            driver.findElement(By.cssSelector(contactUsLocator)).click();
        } catch (ElementNotVisibleException ex) {
            mouseOverOnElement(driver, driver.findElement(By.cssSelector(helpLocator)));
            driver.findElement(By.cssSelector(contactUsLocator)).click();
        }
        return new ContactUsPage(driver);
    }


}  
