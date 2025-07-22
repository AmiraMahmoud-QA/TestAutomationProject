
package com.swaglabs.utils;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class Validations {
    private final WebDriver driver;
    private final BrowserActions browserActions;

    public Validations(WebDriver driver) {
        this.driver = driver;
        this.browserActions = new BrowserActions(driver);
    }

    @Step("Validate that condition is true")
    public void validateTrue(boolean condition, String message) {
        Assert.assertTrue(message, condition);
    }

    @Step("Validate that condition is false")
    public void validateFalse(boolean condition, String message) {
        Assert.assertFalse(message, condition);
    }

    @Step("Validate that actual value equals expected")
    public void validateEquals(String expected, String actual, String message) {
        Assert.assertEquals(message, expected, actual);
    }

    @Step("Validate current page URL: {expectedUrl}")
    public void validatePageUrl(String expectedUrl) {
        String actualUrl = browserActions.getCurrentUrl();
        Assert.assertEquals("Page URL is not as expected", expectedUrl, actualUrl);
    }

    @Step("Validate current page title: {expectedTitle}")
    public void validatePageTitle(String expectedTitle) {
        String actualTitle = browserActions.getPageTitle();
        Assert.assertEquals("Page title is not as expected", expectedTitle, actualTitle);
    }
}

