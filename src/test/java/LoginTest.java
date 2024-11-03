import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pom.Registration;
import api.User;
import pom.*;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

public class LoginTest extends BaseTest {
    private User validUserData;

    @Before
    @DisplayName("Создание случайного пользователя")
    public void setUp() {
        validUserData = User.getRandomUserValidData();

        open(Registration.URL, Registration.class)
                .fillNameInput(validUserData.getName())
                .fillEmailInput(validUserData.getEmail())
                .fillPasswordInput(validUserData.getPassword())
                .clickButtonReg()
                .regPageDisappear();
    }

    @Test
    @DisplayName("Проверка может ли пользователь с валидный данными авторизоваться через Личный кабинет")
    public void checkHeaderAccountButtonLogInWithValidData() {
        open(Main.URL, Header.class).clickAccountLinkHeaderButton();
        checkLogInUserWithValidData();
    }

    @Test
    @DisplayName("Проверка может ли пользователь с валидными данными авторизоваться по кнопке Войти в аккаунт")
    public void checkLogInToAccountButtonLogInWithValidData() {
        open(Main.URL, Main.class).clickLogInButton();
        checkLogInUserWithValidData();
    }

    @Test
    @DisplayName("Проверка может ли пользователь с валидными данными авторизоваться со страницы Регистрации по ссылке Войти")
    public void checkLinkLogInInRegistrationPageWithValidData() {
        open(Registration.URL, Registration.class).clickLinkLogIn();
        checkLogInUserWithValidData();
    }

    @Test
    @DisplayName("Проверка может ли пользователь с валидными данными авторизоваться со страницы Забыли пароль по ссылке Войти")
    public void checkLinkLogInInForgotPasswordPageWithValidData() {
        open(ForgotPassword.URL, ForgotPassword.class).clickLogInLink();
        checkLogInUserWithValidData();
    }

    @After
    @DisplayName("Переход в личный кабинет 'выход из профиля'+'удаление пользователя'+'очистка cookies'")
    public void cleanDate() {
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


    private void checkLogInUserWithValidData() {
        page(Login.class)
                .fillEmailInput(validUserData.getEmail())
                .fillPasswordInput(validUserData.getPassword())
                .clickButtonLogIn()
                .logInPageDisappear()
                .mainPageLoaded();
        String currentUrl = webdriver().driver().url();
        assertEquals("Залогиниться не удалось", Main.URL, currentUrl);

        page(Header.class).clickAccountLinkHeaderButton();
        String actualLogin = page(Profile.class).getLogInInput();
        assertEquals("Логин не совпадает", validUserData.getEmail(), actualLogin);
    }
}