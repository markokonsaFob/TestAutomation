package com.fobsolutions.ui.steps;


import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SimpleStepDefinitions {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setup() {
        FirefoxDriverManager.getInstance().setup();
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
    }

    @Given("^user opens the \"([^\"]*)\" with browser$")
    public void userOpensTheWithBrowser(String url) throws Throwable {
        // Open the URL
        driver.get(url);
    }

    @Then("^dictionary page should be visible$")
    public void dictionaryPageShouldBeVisible() throws Throwable {
        if (!driver.findElement(By.tagName("h2")).getText().equals("Eesti - Inglise - Eesti sõnaraamat")) {
            throw new NotFoundException("Cannot find Eesti - Inglise - Eesti sõnaraamat from h2 tag");
        }
    }

    @And("^estonian language should be selected$")
    public void estonianLanguageShouldBeSelected() throws Throwable {
        if (!driver.findElement(By.id("langee")).isSelected()) {
            throw new InvalidElementStateException("Estonian language should be selected!");
        }
    }

    @When("^user enters \"([^\"]*)\" into search bar$")
    public void userEntersIntoSearchBar(String text) throws Throwable {
        driver.findElement(By.name("query")).sendKeys(text);
    }

    @And("^user clicks submit button$")
    public void userClicksSubmitButton() throws Throwable {
        driver.findElement(By.name("otsi")).click();
    }

    @Then("^\"([^\"]*)\" should be visible as a result$")
    public void shouldBeVisibleAsAResult(String text) throws Throwable {

        wait.until((ExpectedCondition<Boolean>) d -> {
            try {
                return driver.findElement(By.tagName("body")).getText().contains("Otsingu tulemused sõnale");
            } catch (Exception ignored) {
                return false;
            }
        });

        List<WebElement> results = driver.findElements(By.tagName("tr"));

        for (WebElement result : results) {
            if (result.findElement(By.tagName("td")).getText().equals(text)) {
                return;
            }
        }
        throw new NotFoundException("Cannot find any results with parameter " + text);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
