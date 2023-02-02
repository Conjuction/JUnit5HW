package shopDNS;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SearchDNS {

    @BeforeAll
    static void simpleTest() {
        Configuration.browserSize = "1920x1080";
        open("https://www.dns-shop.kz/");
        $(".v-confirm-city__buttons_qv9").$(byText("Сменить город")).click();
        $(".base-ui-input-search__input_YOW").setValue("Аксу").pressEnter();
    }

    @ValueSource(
            strings = {"холодильник", "Ноутбук", "Телевизор"}
    )

    @ParameterizedTest(name = "Наличие выдачи по ключу:{0}")
    @Tags({@Tag("Critical"), @Tag("UI_TEST")})
    void searchEquipment(String equipment) {
        $(".presearch__input").setValue(equipment).pressEnter();
        $(".products-list__group-title").shouldHave(text(equipment));
        $(".presearch__input").clear();
    }

}
