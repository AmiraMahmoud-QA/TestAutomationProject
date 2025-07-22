package com.swaglabs.drivers;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import java.util.Map;
public class EdgeFactory extends AbstractDriver implements WebDriverOptionsAbstract<EdgeOptions>{
    @Override
    public EdgeOptions getOptions() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("start-maximized");
        edgeOptions.addArguments("--disable-extensions");
        edgeOptions.addArguments("--disable-notifications");
        edgeOptions.addArguments("--disable-infobars"); //اخفاء شريط المعلومات اللي يظهر أعلى المتصفح
        edgeOptions.addArguments("--remote-allow-origins=*");//الاتصالات من أي عنوان
//edgeOptions.addArguments("--headless");//شغل المتصفح في الوضع "الخفي" بدون واجهة رسومية
        Map<String, Object> prefsEdge = Map.of(
                "profile.default_content_setting_values.notifications", 2,
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false
        );
        edgeOptions.setExperimentalOption("prefs", prefsEdge);
        return edgeOptions;
    }


    // EdgeFactory.java
    @Step("Starting EdgeDriver with custom options")
    public WebDriver startDriver() {
        return new EdgeDriver(getOptions());
    }

}
