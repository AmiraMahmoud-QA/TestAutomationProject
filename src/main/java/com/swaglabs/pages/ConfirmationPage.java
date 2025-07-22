package com.swaglabs.pages;

import com.swaglabs.drivers.GuiDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ConfirmationPage {
    private GuiDriver driver;

    public ConfirmationPage(GuiDriver driver) {
        this.driver = driver;
    }

    private final By confirmationMessage = By.cssSelector(".complete-header");

    @Step("Get confirmation message from page")
    public String getConfirmationMessage() {
        return driver.elementActions().getText(confirmationMessage);
    }

    @Step("Assert confirmation message: {expectedMessage}")
    public void assertConfirmationMessage(String expectedMessage) {
        String actualResult = getConfirmationMessage();
        if (!actualResult.equals(expectedMessage)) {
            driver.validations().validateEquals(expectedMessage, actualResult, "confirmation message mismatch");
        }
    }
}

