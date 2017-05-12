package com.fobsolutions.ui.tests;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class SimpleMobileTest {

    IOSDriver driver;
    WebDriverWait wait;

    @Before
    public void setup() throws MalformedURLException {
        File appDir = new File("src/test/resources/applications");
        File app = new File(appDir, "HERESuite.ipa");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device", "iOS");

        //mandatory capabilities
        capabilities.setCapability("deviceName", "iOS");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("udid", "69af005bd93a0371f06a88ca0cf367a53168956c");
        capabilities.setCapability("automationName", "XCUITest");

        //other caps
        capabilities.setCapability("app", app.getAbsolutePath());
        driver = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        wait = new WebDriverWait(driver, 20);
    }

    @Test
    public void openHereApplication() {

        // Launch App
        driver.launchApp();

        // Accept alerts
        autoAcceptAlerts();

        // Close First time usage
        wait.until(ExpectedConditions.elementToBeClickable(By.id("Go!")));
        driver.findElement(By.id("Go!")).click();

        // Open sidebar
        driver.findElement(By.id("topBarHamburgerItem")).click();
        driver.findElement(MobileBy.AccessibilityId("About")).click();

        // Verify about page
        assert driver.findElement(MobileBy.AccessibilityId("Improvement Program")).isDisplayed() &&
                driver.findElement(MobileBy.AccessibilityId("Notices")).isDisplayed() &&
                driver.findElement(MobileBy.AccessibilityId("Terms & Privacy")).isDisplayed();

    }

    /**
     * Automatically dismiss alerts
     */
    private void autoAcceptAlerts() {
        boolean accept = true;

        while (accept) {
            try {

                wait.until((ExpectedCondition<Boolean>) d -> {
                    try {
                        driver.switchTo().alert().accept();
                        return true;
                    } catch (Exception ignored) {
                        return false;
                    }
                });
            } catch (Exception ignored) {
                accept = false;
            }
        }
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}
