import com.codeborne.selenide.Configuration;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.openqa.selenium.chrome.ChromeOptions.CAPABILITY;

public class BaseTest {
    @Before
    public void configureDriver() {
        if (System.getProperty("browser").equals("yandex")) {
            String path = System.getenv("PROGRAMFILES(X86)");
            Configuration.browserSize = "1920x1080";
            DesiredCapabilities cup = new DesiredCapabilities();
            ChromeOptions options = new ChromeOptions();
            options.setBinary(path + "\\Yandex\\YandexBrowser\\Application\\browser.exe");
            cup.setCapability(CAPABILITY, options);
            Configuration.browserCapabilities = cup;
        } else {
            Configuration.browserSize = "1920x1080";
            Configuration.browser = "chrome";
        }
    }
}