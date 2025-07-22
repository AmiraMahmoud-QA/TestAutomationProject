package com.swaglabs.drivers;
import io.qameta.allure.Step;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Map;

public class ChromeFactory extends AbstractDriver implements WebDriverOptionsAbstract<ChromeOptions>{

    @Override
    public ChromeOptions getOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars"); //اخفاء شريط المعلومات اللي يظهر أعلى المتصفح
        options.addArguments("--remote-allow-origins=*");//الاتصالات من أي عنوان
        //options.addArguments("--headless");//شغل المتصفح في الوضع "الخفي" بدون واجهة رسومية
        Map<String, Object> prefs = Map.of(
                "profile.default_content_setting_values.notifications", 2,
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false
        );
        options.setExperimentalOption("prefs", prefs);
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        return options;
    }


    @Step("Starting ChromeDriver with custom options")
    public WebDriver startDriver() {
        return new ChromeDriver(getOptions());
    }

}
