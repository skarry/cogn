package org.o2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ContactUsPage extends WebPage {

    private String iHaveGotATQLocator = "I've got a technical question";
    private String preferToSpeakSomeoneLocator = "#contacts-q26>a";
    private String payAndGoTeamLocator = "Pay & Go Team";
    private String openingTimesLocator = "div.outer[style='display: block;'] h4.paygoteam+div.outer[style='display: block;'] div.times>table>tbody>tr";

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Method to select I Have Got A Technical Question
     */

    public void selectIHaveGotATechnicalQuestion() {
        driver.findElement(By.linkText(iHaveGotATQLocator)).click();
    }

    /**
     * Method to select Prefer to Speak to Someone
     */

    public void selectPreferToSpeakToSomeone() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(preferToSpeakSomeoneLocator)));
        driver.findElement(By.cssSelector(preferToSpeakSomeoneLocator)).click();
    }

    /**
     * Method to Select Pay and GO Team
     */

    public void selectPayAndGoTeamLink() {
        driver.findElement(By.linkText(payAndGoTeamLocator)).click();
    }

    /**
     * Method to get Opening Times Map
     */
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
