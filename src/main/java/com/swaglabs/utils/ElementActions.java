package com.swaglabs.utils;
import com.swaglabs.drivers.GuiDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
public class ElementActions {
    private final GuiDriver driver;
    private final Waits waits;
    private final Scrolling scrolling;
    public ElementActions(GuiDriver driver) {
    this.driver=driver;
    waits=new Waits(driver);
    scrolling=new Scrolling(driver);
    }
  //send keys to an element

    @Step("Send keys: {1} to element: {0}")
    public  void sendKeys( By locator, String text) {
        waits.waitForElementToBeVisible( locator);
        scrolling.scrollToElement(locator);
       findElement(locator).sendKeys(text);
       LogsUtil.logInfo("Sent keys: " + text + " in the field: " + locator.toString());
    }
    // click on an element
    @Step("Click on element: {0}")
    public  void clickElement( By locator) {
        waits.waitForElementToBeClickable(locator);

        findElement(locator).click();
        LogsUtil.logInfo("Getting text from element: " + locator.toString()+"text",findElement(locator).getText());
    }
    @Step("Get text from element: {0}")
    public  String getText(By locator){
        waits.waitForElementToBeVisible(locator);
        scrolling.scrollToElement(locator);

        return findElement(locator).getText();
    }
//find an element
    public  WebElement findElement( By locator) {
        LogsUtil.logInfo("Finding element: " + locator.toString());
        return GuiDriver.getInstance().findElement(locator);
    }
    public  String getTextFromInputField( By locator) {
        waits.waitForElementToBeVisible(locator);
        scrolling.scrollToElement(locator);
        LogsUtil.logInfo("Getting text from input field: " + locator.toString() + " text: " + findElement(locator).getDomAttribute("value"));
        return findElement( locator).getDomAttribute("value");
    }
}
