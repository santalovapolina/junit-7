package guru.qa;


import guru.qa.data.Locale;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.open;

public class LibreOfficeParameterizedTest {

    static Stream<Arguments> libreOfficeLocaleDataProvider(){
        return Stream.of(
   Arguments.of(Locale.English, List.of("DISCOVER", "DOWNLOAD", "GET HELP", "IMPROVE IT", "EVENTS", "ABOUT US", "DONATE", " ")),
          Arguments.of(Locale.Deutsch, List.of("HOME", "PRODUKT", "DOWNLOAD", "HILFE", "MITARBEIT", "ÜBER UNS", "SPENDE", " "))
        );
    }

    @MethodSource("libreOfficeLocaleDataProvider")
    @Tag("CRITICAL")
    @ParameterizedTest(name = "Для локали {0} отображаются кнопки {1}")
    void libreOfficeWebsiteShouldContainAllOfButtonsForGivenLocale(Locale locale, List<String> buttons) {

        open("https://www.libreoffice.org/");
        $$x("//div[@class='localisation_available_notification']//a").find(text(locale.name())).click();
        $$x("//ul[@class='nav navbar-nav navbar-right']/li").filter(visible).shouldHave(texts(buttons));



    }


}
