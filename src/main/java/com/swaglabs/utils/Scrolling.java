package com.swaglabs.utils;
import com.swaglabs.drivers.GuiDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Scrolling {
    private final WebDriver webDriver;

    public Scrolling(GuiDriver driver) {
        this.webDriver = driver.get();
    }

    @Step("Scroll to element: {0}")
    public void scrollToElement(By locator) {
        LogsUtil.logInfo("Scrolling to element: " + locator.toString());
        WebElement element = webDriver.findElement(locator);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
