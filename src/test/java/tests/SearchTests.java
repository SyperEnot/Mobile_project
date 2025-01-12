
package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.*;
import static io.qameta.allure.Allure.step;

@Epic(value = "Тестирование мобильного приложения Wikipedia")
@Owner("yorohov")
@Tag("remote")
public class SearchTests extends TestBase {

    private final static String SEARCH_WORD = "Appium",
            DESCRIPTION = "Automation for Apps";
    @Test
    @DisplayName("Проверка работы поиска")
    void androidSuccessfulSearchTest() {
        step("Вписать в поиск 'Appium'", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Проверить поиск статей", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    @DisplayName("Открываем первую найденную ссылку")
    void successfulSearchTest() {
        step("Отправляем запрос в Википедии", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys(SEARCH_WORD);
        });

        step("Кликаем на первую найденную ссылку", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title")).first().click());

        step("Проверяем получение ошибки", () ->
                $(id("org.wikipedia.alpha:id/view_wiki_error_text")).shouldBe(visible));
    }

    @Test
    @DisplayName("Проверка краткого описания статьи")
    void checkDescriptionTest() {
        step("Отправляем запрос в Википедии", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys(SEARCH_WORD);
        });

        step("Проверяем, что найденный заголовок имеет корректное описание", () ->
                $(id("org.wikipedia.alpha:id/page_list_item_description"))
                        .shouldHave(text(DESCRIPTION)));
    }
}