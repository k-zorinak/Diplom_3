import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pom.FeedOrders;
import pom.Header;
import pom.Login;
import pom.Main;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

public class HeaderWithoutLoginTest extends BaseTest {
    @Test
    @DisplayName("Проверка клика в шапке главной страницы на переход в 'Личный кабинет' не авторизованного пользователя")
    public void checkHeaderAccountButtonClickWithOutLogIn() {
        open(Main.URL, Header.class).clickAccountLinkHeaderButton();
        page(Login.class).logInPageLoaded();
        String currentUrl = webdriver().driver().url();
        assertEquals("Не вiерная ссылка", Login.URL, currentUrl);
    }

    @Test
    @DisplayName("Проверка клика в шапке главной страницы на переход в 'Лента заказов' не авторизованного пользователя")
    public void checkHeaderFeedButtonClickWithOutLogIn() {
        open(Main.URL, Header.class).clickFeedHeaderButton();
        page(FeedOrders.class).feedOrdersPageLoaded();
        String currentUrl = webdriver().driver().url();
        assertEquals("Не верная ссылка", FeedOrders.URL, currentUrl);
    }

    @Test
    @DisplayName("Проверка клика в шапке главной страницы на переход в 'Конструктор' не авторизованного пользователя")
    public void ckeckHeaderConstructorButtonClickWithOutLogIn() {
        open(Main.URL, Header.class).clickConstructorHeaderButton();
        page(Main.class).mainPageLoaded();
        String currentUrl = webdriver().driver().url();
        assertEquals("Не верная ссылка", Main.URL, currentUrl);
    }

    @Test
    @DisplayName("Проверка клика в шапке главной страницы на Логотип не авторизованного пользователя")
    public void checkHeaderLogoClickWithOutLogIn() {
        open(Main.URL, Header.class).clickLogoHeaderButton();
        page(Main.class).mainPageLoaded();
        String currentUrl = webdriver().driver().url();
        assertEquals("Не верная ссылка", Main.URL, currentUrl);
    }
}