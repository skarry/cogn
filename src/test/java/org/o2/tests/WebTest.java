package org.o2.tests;

import org.o2.driver.DriverUtil;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class WebTest {
	private static final Logger logger=LoggerFactory.getLogger(WebTest.class);
	protected WebDriver driver;
	private DriverUtil driverUtil;
	@BeforeSuite
	public void setUp(){
		logger.debug("Setting up new Webdriver");
		driverUtil=new DriverUtil();
		driver=driverUtil.getDriver();
		
	}

    @AfterSuite
	public void tearDown(){
		logger.debug("In Teardown");
		driver.close();
		driver.quit();
	}
	

    public void pause(long timeOut){
        try {
            Thread.sleep(timeOut);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
