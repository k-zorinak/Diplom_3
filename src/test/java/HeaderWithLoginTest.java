import api.UserAction;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import api.User;
import pom.*;
import resources.UserCard;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

public class HeaderWithLoginTest extends BaseTest {
    private User validUserData;

    @Before
    @DisplayName("Создание случайного пользователя и авторизация")
    public void setUp() {
        validUserData = User.getRandomUserValidData();

        resources.UserCard userCard = new UserCard();

        userCard.setName(validUserData.getName());
        userCard.setEmail(validUserData.getEmail());
        userCard.setPassword(validUserData.getPassword());


        UserAction.postRequestCreateUser(userCard);

//        open(Registration.URL, Registration.class)
//                .fillNameInput(validUserData.getName())
//                .fillEmailInput(validUserData.getEmail())
//                .fillPasswordInput(validUserData.getPassword())
//                .clickButtonReg()
//                .regPageDisappear();

        open(Login.URL, Login.class)
                .fillEmailInput(validUserData.getEmail())
                .fillPasswordInput(validUserData.getPassword())
                .clickButtonLogIn()
                .logInPageDisappear();
        page(Main.class).mainPageLoaded();
    }

    @Test
    @DisplayName("Проверка клика в шапке главной страницы на переход в 'Личный кабинет' авторизованного пользователя")
    public void checkHeaderAccountButtonClickWithLogIn() {
        page(Header.class).clickAccountLinkHeaderButton();
        page(Profile.class).profilePageLoaded();
        String currentUrl = webdriver().driver().url();
        assertEquals("Не верная ссылка", Profile.URL, currentUrl);
    }

    @Test
    @DisplayName("Проверка клика в шапке главной страницы на переход в 'Лента заказов' авторизованного пользователя")
    public void checkHeaderFeedButtonClickWithLogIn() {
        page(Header.class).clickFeedHeaderButton();
        page(FeedOrders.class).feedOrdersPageLoaded();
        String currentUrl = webdriver().driver().url();
        assertEquals("Не верная ссылка", FeedOrders.URL, currentUrl);
    }

    @Test
    @DisplayName("Проверка клика в шапке главной страницы на переход в 'Конструктор' авторизованного пользователя")
    public void ckeckHeaderConstructorButtonClickWithLogIn() {
        page(Header.class).clickConstructorHeaderButton();
        page(Main.class).mainPageLoaded();
        String currentUrl = webdriver().driver().url();
        assertEquals("Не верная ссылка", Main.URL, currentUrl);
    }

    @Test
    @DisplayName("Проверка клика в шапке главной страницы на Логотип авторизованного пользователя")
    public void checkHeaderLogoClickWithLogIn() {
        page(Header.class).clickLogoHeaderButton();
        page(Main.class).mainPageLoaded();
        String currentUrl = webdriver().driver().url();
        assertEquals("Не верная ссылка", Main.URL, currentUrl);
    }

    @After
    @DisplayName("Переход в личный кабинет 'выход из профиля'+'удаление пользователя'+'очистка cookies'")
    public void cleanData() {
        page(Header.class).clickAccountLinkHeaderButton();
        page(Profile.class)
                .profilePageLoaded()
                .clickLogOutButton()
                .profilePageDisappear();
        if (validUserData != null) {
            validUserData.deleteUser();
        }
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }
}