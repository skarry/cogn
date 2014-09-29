package org.o2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ContactUsPage {
    WebDriver driver;
    private String iHaveGotATQLocator = "I've got a technical question";
    private String preferToSpeakSomeoneLocator = "#contacts-q26>a";
    private String payAndGoTeamLocator = "Pay & Go Team";
    private String openingTimesLocator = "div.outer[style='display: block;'] h4.paygoteam+div.outer[style='display: block;'] div.times>table>tbody>tr";

    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectIHaveGotATechnicalQuestion() {
        driver.findElement(By.linkText(iHaveGotATQLocator)).click();
    }

    public void selectPreferToSpeakToSomeone() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(preferToSpeakSomeoneLocator)));
        driver.findElement(By.cssSelector(preferToSpeakSomeoneLocator)).click();
    }

    public void selectPayAndGoTeamLink() {
        driver.findElement(By.linkText(payAndGoTeamLocator)).click();
    }

    public Map<String, String> getOpeningTimes() {
        List<WebElement> timesList = driver.findElements(By.cssSelector(openingTimesLocator));
        System.out.println("Opening Times List: " + timesList.size());
        Map<String, String> timesMap = new HashMap<String, String>();
        for (WebElement row : timesList) {
            List<WebElement> tdList = row.findElements(By.tagName("td"));
            timesMap.put(tdList.get(0).getText(), tdList.get(1).getText());
        }

        for (Map.Entry<String, String> entry : timesMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        return timesMap;
    }

}
