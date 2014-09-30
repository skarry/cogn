package org.o2.tests;

import org.o2.pages.ContactUsPage;
import org.o2.pages.HomePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class OpeningTimesTest extends WebTest {
    private static final Logger logger = LoggerFactory.getLogger(OpeningTimesTest.class);

    /**
     * 1) Launch Browser and navigate to "http://www.o2.co.uk/"
     * 2) Print Help Options
     * 3) Click on ContactUs link from Help Options drop down
     * 4) Select "I've got a technical question"
     * 5) Select "Prefer to speak to someone?" radio button
     * 6) Select "Pay & Go Team"
     * 7) Get Opening Times and Verify
     */

    @Test
    public void openingTimesTest() {
        logger.debug("Starting openingTimesTest()");
        HomePage homepage = new HomePage(driver);
        homepage = homepage.load("http://www.o2.co.uk/");

        homepage.printHelpOptions();

        ContactUsPage contactUsPage = homepage.clickOnContactLink();

        contactUsPage.selectIHaveGotATechnicalQuestion();
        contactUsPage.selectPreferToSpeakToSomeone();
        contactUsPage.selectPayAndGoTeamLink();

        Map<String, String> actualOpeningTimes = contactUsPage.getOpeningTimes();
        Assert.assertEquals(actualOpeningTimes, getExpectedOpeningTimes());
    }

    /**
     * Method to get Expected Opening Times
     */
    public Map<String, String> getExpectedOpeningTimes() {
        Map<String, String> expectedOpeningTimes = new HashMap<String, String>();
        expectedOpeningTimes.put("Monday - Friday", "08:00 - 21:00");
        expectedOpeningTimes.put("Saturday", "08:00 - 20:00");
        expectedOpeningTimes.put("Sunday", "09:00 - 18:00");
        expectedOpeningTimes.put("Monday - Sunday", "24 hours");
        return expectedOpeningTimes;
    }

}
