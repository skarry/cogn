package org.o2.tests;

import java.util.HashMap;
import java.util.Map;

import org.o2.pages.ContactUsPage;
import org.o2.pages.HomePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OpeningTimesTest extends WebTest {
    private static final Logger logger = LoggerFactory.getLogger(OpeningTimesTest.class);

    @Test
    public void openingTimesTest() {
        logger.debug("Starting openingTimesTest()");
        HomePage homepage = new HomePage(driver);
        homepage = homepage.load("http://www.o2.co.uk/");

        homepage.printHelpOptions();

        ContactUsPage contactus = homepage.clickOnContactLink();

        contactus.selectIHaveGotATechnicalQuestion();
        contactus.selectPreferToSpeakToSomeone();
        contactus.selectPayAndGoTeamLink();
        Map<String, String> actualOpeningTimes = contactus.getOpeningTimes();

        Assert.assertEquals(actualOpeningTimes, getExpectedOpeningTimes());

    }

    public Map<String, String> getExpectedOpeningTimes() {
        Map<String, String> expectedOpeningTimes = new HashMap<String, String>();
        expectedOpeningTimes.put("Monday - Friday", "08:00 - 21:00");
        expectedOpeningTimes.put("Saturday", "08:00 - 20:00");
        expectedOpeningTimes.put("Sunday", "09:00 - 18:00");
        expectedOpeningTimes.put("Monday - Sunday", "24 hours");
        return expectedOpeningTimes;
    }

}
