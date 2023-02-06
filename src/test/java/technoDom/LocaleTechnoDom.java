package technoDom;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class LocaleTechnoDom {


    @BeforeAll
    static void simpleTest() {
        Configuration.browserSize = "1920x1080";
        open("https://www.technodom.kz/");
        $(".VerifyCityModal_block__actions__MtNhQ").$(byText("Нет")).click();
        $("#CitiesModalSearch").setValue("Акт");
        $(".CitiesModal_block__list__8D_Jr ").$("[href='/aktobe']").click();
    }


    static Stream<Arguments> technoDomButtons() {
        return Stream.of(
                Arguments.of(Locale.KZ.getDesc(), List.of("Таңдаулы", "Салыстыру", "Хабарламалар", "Қоржын", "Кіру")),
                Arguments.of(Locale.RU.getDesc(), List.of("Избранное", "Сравнить", "Уведомления", "Корзина", "Вход"))
        );
    }

    @MethodSource
    @ParameterizedTest(name =  "Для локализации {0} отображаются кнопки {1}")
    @Tags({@Tag("Critical"), @Tag("UI_TEST")})
    void technoDomButtons(
            Locale locale,
            List<String> buttons
    ) {

        $(".LocalizationSelector_block__QNS8p").click();
        $(".LocalizationSelector_block__QNS8p").$(byText(locale.name())).click();
        $$(".header-middle__links-wrapper a").filter(visible)
                .shouldHave(texts(buttons));

    }


}
