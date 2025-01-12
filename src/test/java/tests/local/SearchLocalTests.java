package tests.local;

import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

@Epic(value = "Тестирование мобильного приложения Wikipedia")
@Owner("yorohov")
@Tag("local")
public class SearchLocalTests extends TestBase {

    private final static String SEARCH_WORD = "Appium";

    @Test
    @DisplayName("Открытие первой найденной ссылки")
    void successfulSearchTest() {
        back();
        step("Отправляем запрос в википедии", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys(SEARCH_WORD);
        });

        step("Проверяем, что обнаружены результаты поиска", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }
}
