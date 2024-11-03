import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pom.Main;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertEquals;

public class ConstructorTest extends BaseTest {
    @Test
    @DisplayName("Проверка переключения на вкладку 'Булки'")
    public void checkClickBuns() {
        String expectedTab = "Булки";
        open(Main.URL, Main.class).clickTabFillings();
        page(Main.class).clickTabBun();

        String currentTab = page(Main.class).getCurrentTab();
        assertEquals("Вкладка не переключилась", expectedTab, currentTab);
    }

    @Test
    @DisplayName("Проверка переключения на вкладку 'Соусы'")
    public void checkClickSouce() {
        String expectedTab = "Соусы";
        open(Main.URL, Main.class).clickTabSouce();

        String currentTab = page(Main.class).getCurrentTab();
        assertEquals("Вкладка не поменялась", expectedTab, currentTab);
    }

    @Test
    @DisplayName("Проверка переключения на вкладку 'Начинки'")
    public void checkClickFilling() {
        String expectedTab = "Начинки";
        open(Main.URL, Main.class).clickTabFillings();

        String currentTab = page(Main.class).getCurrentTab();
        assertEquals("Вкладка не поменялась", expectedTab, currentTab);
    }
}