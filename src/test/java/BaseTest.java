import com.codeborne.selenide.Configuration;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.openqa.selenium.chrome.ChromeOptions.CAPABILITY;

public class BaseTest {
    @Before
    public void configureDriver() {
        if (System.getProperty("browser").equals("yandex")) {
            Configuration.browserSize = "1920x1080";
            System.setProperty("webdriver.chrome.driver", "/Users/kamillarakhmatulina/Downloads/WebDriver/bin");
            DesiredCapabilities cup = new DesiredCapabilities();
            ChromeOptions options = new ChromeOptions();
            options.setBinary("/Users/kamillarakhmatulina/Downloads/Local/Yandex/YandexBrowser/Application/browser.exe");
            cup.setCapability(CAPABILITY, options);
            Configuration.browserCapabilities = cup;
        } else {
            Configuration.browserSize = "1920x1080";
            Configuration.browser = "chrome";
        }
    }
}