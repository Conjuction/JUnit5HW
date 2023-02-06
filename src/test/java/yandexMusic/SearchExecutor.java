package yandexMusic;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class SearchExecutor {
    @BeforeAll
    static void simpleTest() {
        Configuration.browserSize = "1920x1080";
        open("https://music.yandex.kz/home");
        $(".pay-promo-close-btn").click();
        $(".d-search__button__container").click();
    }

    @CsvSource({
            "нойз, Noize MC",
            "улица, Улица Восток",
            "анаконда, Anacondaz"
    })

    @ParameterizedTest(name = "В выпадающем списке должен быть исполнитель {1} при вводе {0}")
    @Tags({@Tag("Critical"), @Tag("Search_TEST")})
    void findMusician(
            String str,
            String Musician
    ) {

        $(".d-input__field").setValue(str);
        $(".d-suggest__item-artist .d-suggest-item__title-main").shouldHave(text(Musician));
    }

}
