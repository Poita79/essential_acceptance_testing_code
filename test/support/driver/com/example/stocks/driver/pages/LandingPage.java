package com.example.stocks.driver.pages;

import com.example.stocks.driver.Selenium;
import org.hamcrest.Matcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.example.stocks.core.Probes.portfolioValuationFrom;
import static com.google.code.tempusfugit.condition.Conditions.assertion;
import static com.google.code.tempusfugit.temporal.Duration.seconds;
import static com.google.code.tempusfugit.temporal.Timeout.timeout;
import static com.google.code.tempusfugit.temporal.WaitFor.waitFor;

public class LandingPage {

    private final WebDriver driver = Selenium.createWebDriverForPlatform();

    public LandingPage navigateToLandingPage() {
        driver.get("http://localhost:8000/index.html");
        Selenium.verifyPageTitle(driver, "Value your Portfolio");
        return this;
    }

    public LandingPage requestValuationForShares(Integer numberOfShares) {
        driver.findElement(By.id("inputNumberOfShares")).sendKeys(numberOfShares.toString());
        driver.findElement(By.id("requestValuation")).click();
        return this;
    }

    public String getPortfolioValue() {
        return driver.findElement(By.id("requestValuationResult")).getText();
    }

    public void quit() {
        Selenium.stop(driver);
    }

    public void assertThatPortfolioValue(Matcher<String> matcher) throws InterruptedException {
        waitFor(assertion(portfolioValuationFrom(this), matcher), timeout(seconds(5)));
    }
}
