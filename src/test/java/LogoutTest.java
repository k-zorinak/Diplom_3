import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import api.User;
import pom.Header;
import pom.Login;
import pom.Profile;
import pom.Registration;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LogoutTest extends BaseTest {
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
    @Description("Проверка выхода и аккаунта")
    public void checkLogOutFromProfile() {
        page(Profile.class)
                .profilePageLoaded()
                .clickLogOutButton()
                .profilePageDisappear();
        boolean isLogInPageLoaded = page(Login.class).isLogInPageLoaded();
        String currentUrl = webdriver().driver().url();
        assertEquals("Не верная ссылка", Login.URL, currentUrl);
        assertTrue("Страница авторизации не загружена", isLogInPageLoaded);
    }

    @After
    @DisplayName("Удаление пользователя и очистка cookies")
    public void cleanDate() {
        if (validUserData != null) {
            validUserData.deleteUser();
        }
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }
}