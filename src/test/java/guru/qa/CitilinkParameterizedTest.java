package guru.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CitilinkParameterizedTest {

    @BeforeEach
    void beforeEach() {
        open("https://www.citilink.ru");
        Configuration.browserSize = "1920x1080";
    }


    @CsvFileSource(resources = "/testData.csv")


    @ParameterizedTest(name = "В результате поиска продукции бренда {0} должен появиться раздел {1}")
    @Tags({@Tag("CRITICAL"), @Tag("UI_TEST")})
    void categorySearchTest(String productBrandName, String productCategory) {

        $("input[type=search]").setValue(productBrandName).pressEnter();
        $(".BrandCategories__brand-category-header").$("h2").shouldHave(text(productCategory));
    }

}
