package com.swaglabs.tests;
import com.swaglabs.drivers.GuiDriver;
import com.swaglabs.listeners.TestNGListeners;
import com.swaglabs.pages.*; import com.swaglabs.utils.*;
import io.qameta.allure.*;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.*;
@Listeners({TestNGListeners.class, AllureTestNg.class})
public class E2e { GuiDriver driver;
    JsonUtils testData;
    @BeforeClass
    public void beforeClass() {
    testData = new JsonUtils("test-data.json");
    driver = new GuiDriver(PropertiesUtils.getProperty("browserType"));
    new LoginPage(driver).navigateToLoginPage();
}
@Test(description = "Verify that user can login with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Description("User should be able to login with valid username and password")
    public void successfulLoginTest() { new LoginPage(driver)
            .enterUsername(testData.getJsonData("login-credentials.username"))
            .enterPassword(testData.getJsonData("login-credentials.password"))
            .clickLoginButton()
            .assertSuccessfullLogin();
        AllureUtils.attachLogsAsText();
    }
    @Test(dependsOnMethods = "successfulLoginTest",
            description = "Add a product to the shopping cart")
    @Severity(SeverityLevel.NORMAL)
    @Description("User adds a specific product to the cart and verifies it was added")
    public void addProductToCart() {
        new HomePage(driver)
                .addProductToCart(testData.getJsonData("product-details.item2.name"))
                .assertProductAddedToCart(testData.getJsonData("product-details.item2.name"));
        AllureUtils.attachLogsAsText();
    } @Test(dependsOnMethods = "addProductToCart",
            description = "Proceed to checkout and verify product details")
    @Severity(SeverityLevel.NORMAL)
    @Description("User goes to cart and checks product name and price")
    public void checkOutProduct() { new HomePage(driver)
            .clickCartIcon()
            .asserProductDetails( testData.getJsonData("product-details.item2.name"),
                    testData.getJsonData("product-details.item2.price"));
        AllureUtils.attachLogsAsText();
    } @Test(dependsOnMethods = "checkOutProduct",
            description = "Fill checkout information page")
    @Severity(SeverityLevel.NORMAL)
    @Description("User fills personal details in checkout form and verifies input")
    public void fillInformationPage() {
        new CartPage(driver)
                .clickCheckoutButton()
                .fillInformationPage( testData.getJsonData("information-form.first-name"),
                        testData.getJsonData("information-form.last-name"),
                        testData.getJsonData("information-form.postal-code"))
                .assertInformationPageFilled( testData.getJsonData("information-form.first-name"),
                        testData.getJsonData("information-form.last-name"),
                        testData.getJsonData("information-form.postal-code"));
        AllureUtils.attachLogsAsText();
    }
    @Test(dependsOnMethods = "fillInformationPage",
            description = "Finish checkout and verify confirmation message")
    @Severity(SeverityLevel.CRITICAL)
    @Description("User completes order and checks confirmation message")
    public void finishCheckOut() { 
        new InformationPage(driver)
            .clickContinueButton()
            .clickOnFinishButton()
            .assertConfirmationMessage(testData.getJsonData("confirmation-message"));
        AllureUtils.attachLogsAsText();
    }
    @AfterClass public void tearDown() {
        driver.browserActions().closeBrowser();

}

}