package com.swaglabs.utils;
import com.swaglabs.drivers.GuiDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Waits {
    private final WebDriver driver;

    public Waits(GuiDriver guiDriver) {
        this.driver = guiDriver.get();
    }

    private final Duration TIMEOUT = Duration.ofSeconds(10);

    public WebElement waitForElementToBePresent(By locator) {
        return new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement waitForElementToBeVisible(By locator) {
        return new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementToBeClickable(By locator) {
        return new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
}

