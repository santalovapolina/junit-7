package guru.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class CitilinkParameterizedTest {

    @BeforeEach
    void setUp() {
        open("https://www.citilink.ru");
    }


    @CsvFileSource(resources = "/testData.csv")
    @ParameterizedTest(name = "В результате поиска продукции бренда {0} должен появиться раздел {1}")
    @Tags({@Tag("CRITICAL"), @Tag("UI_TEST")})
    void categorySearchTest(String productBrandName, String productCategory) {

        $("input[type=search]").setValue(productBrandName).pressEnter();
        $(".BrandCategories__brand-category-header").$("h2").shouldHave(text(productCategory));
    }

    @ValueSource(strings = {"apple", "samsung"})
    @ParameterizedTest(name = "Количество категорий товаров бренда {0} должно быть больше 10")
    @Tag("UI_TEST")
    void numberOfCategories(String productBrandName) {
        $("input[type=search]").setValue(productBrandName).pressEnter();
        $$("div[class=BrandCategories__brand-category-item]").
                shouldHave(sizeGreaterThan(10), Duration.ofSeconds(3));
    }

}
