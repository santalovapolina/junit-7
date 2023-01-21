package guru.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ParameterizedSampleTest {

    @Test
    void sampleOne(){
        String productName = "apple";
        String productSearchResult = "Смартфоны";
        open("https://www.citilink.ru");
        Configuration.browserSize = "1920x1080";
        $("input[type=search]").setValue(productName).pressEnter();
        $(".BrandCategories__brand-category-header").$("h2").shouldHave(text(productSearchResult));

    }

}
