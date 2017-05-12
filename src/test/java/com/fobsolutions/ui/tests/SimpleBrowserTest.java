package com.fobsolutions.ui.tests;

import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SimpleBrowserTest {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setup() {
        FirefoxDriverManager.getInstance().setup();
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
    }

    @Test
    public void openTookohapohineOpeTallinnaPolutehnikumis() {

        // Open the URL
        driver.get("http://www.tptlive.ee/");

        // Wait for link
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Projektid »")));

        // Click on the link
        driver.findElement(By.linkText("Projektid »")).click();

        // Wait for element
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(text(),'Töökohapõhine õpe')])[2]")));

        // Click on the link
        driver.findElement(By.xpath("(//a[contains(text(),'Töökohapõhine õpe')])[2]")).click();

        // Assert that text is displayed
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h4 > span")));
        assert driver.findElement(By.cssSelector("h4 > span")).getText().contains("Töökohapõhine õpe Tallinna Polütehnikumis.");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
