package com.swaglabs.pages;
import com.swaglabs.drivers.GuiDriver;
import com.swaglabs.utils.CustomSoftAssertion;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InformationPage {
    //variables
    private GuiDriver driver;

    //constructor
    public InformationPage(GuiDriver driver) {
        this.driver = driver;
    }
    //locators
    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By continueButton = By.id("continue");


    //actions
@Step("Fill in information page with first name, last name and postal code")
    public InformationPage fillInformationPage(String firstName, String lastName, String postalCode) {
    driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    driver.elementActions().sendKeys(this.firstNameField, firstName);

driver.elementActions().sendKeys( this.lastNameField, lastName);

    driver.elementActions().sendKeys( this.postalCodeField, postalCode);
        return this;
    }


    @Step("Click on Continue button")
    public OverViewPage clickContinueButton() {
        WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        driver.scrolling().scrollToElement( continueButton);
        btn.click();
        return new OverViewPage(driver);
    }

    //validations
@Step("Assert information page ")
    public void assertInformationPageFilled(String firstName, String lastName, String postalCode) {
    CustomSoftAssertion.softAssertion.assertEquals(driver.elementActions().getTextFromInputField( firstNameField), firstName, "First name does not match");
CustomSoftAssertion.softAssertion.assertEquals(driver.elementActions().getTextFromInputField(lastNameField),lastName,"Last name does not match");
    CustomSoftAssertion.softAssertion.assertEquals(driver.elementActions().getTextFromInputField(postalCodeField),postalCode,"postalCode does not match");
}
}

