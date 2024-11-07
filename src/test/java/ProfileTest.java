import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import api.User;
import pom.*;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProfileTest extends BaseTest {
    private User validUserData;

    @Before
    @DisplayName("Создание случайного пользователя и авторизация")
    public void setUp() {
        validUserData = User.getRandomUserValidData();

        open(Registration.URL, Registration.class)
                .fillNameInput(validUserData.getName())
                .fillEmailInput(validUserData.getEmail())
                .fillPasswordInput(validUserData.getPassword())
                .clickButtonReg()
                .regPageDisappear();

        open(Login.URL, Login.class)
                .fillEmailInput(validUserData.getEmail())
                .fillPasswordInput(validUserData.getPassword())
                .clickButtonLogIn()
                .logInPageDisappear();

        page(Header.class).clickAccountLinkHeaderButton();
    }

    @Test
    @DisplayName("Проверка перехода на страницу страницу Конструктора из Профиля пользователя")
    public void checkNavigateFromProfileToConstructor() {
        page(Header.class)
                .clickConstructorHeaderButton();
        boolean isMainPageLoaded = page(Main.class)
                .mainPageLoaded()
                .isMainPageLoaded();
        String currentURL = webdriver().driver().url();
        assertEquals("Не верная ссылка", Main.URL, currentURL);
        assertTrue("Главная страница не загружена", isMainPageLoaded);
    }

    @Test
    @DisplayName("Проверка перехода на Главную страницу по клику на логотип из Профиля пользователя")
    public void checkNavigateFromProfileToMainPageLogo() {
        page(Header.class)
                .clickLogoHeaderButton();
        boolean isMainPageLoaded = page(Main.class)
                .mainPageLoaded()
                .isMainPageLoaded();
        String currentURL = webdriver().driver().url();
        assertEquals("Не верная ссылка", Main.URL, currentURL);
        assertTrue("Главная страница не загружена", isMainPageLoaded);
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