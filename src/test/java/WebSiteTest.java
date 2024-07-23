import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.DELETE;
import static org.openqa.selenium.Keys.ENTER;

//java -jar ./artifacts/app-card-delivery.jar
public class WebSiteTest {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
    String data = LocalDate.now().plusDays(7).format(formatter);

    @Test
    void testingTheFormSubmission() {
        open("http://localhost:9999");

        $("[class='input__box'] input").setValue("Псков");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(DELETE);
        $("[data-test-id='date'] input").setValue(data);
        $("[data-test-id='name']").$("[class='input__box'] input").setValue("Артем");
        $("[data-test-id='phone']").$("[class='input__box'] input").setValue("+79532376054");
        $("[class='checkbox__text']").click();
        $("[class = 'button__content']").click();

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String actual = $("[data-test-id=notification]").$("[class='notification__title']").getText();
        String expected = "Успешно!";
        Assertions.assertEquals(expected, actual);

    }


//    @Test
//    void dropDownListTest(){
//        open("http://localhost:9999");
//
//
//        $("[class='input__box'] input").setValue("Пс");
//        $("[class = 'popup popup_direction_bottom-left popup_target_anchor popup_size_m popup_visible popup_height_adaptive popup_theme_alfa-on-white input__popup']").shouldHave(text("Псков")).click();
//        $("[data-test-id='date'] input").doubleClick();
//        $("[data-test-id='date'] input").sendKeys(DELETE);
//        $("[data-test-id='date'] input").setValue(data);
//        $("[data-test-id='name']").$("[class='input__box'] input").setValue("Артем");
//        $("[data-test-id='phone']").$("[class='input__box'] input").setValue("+79532376054");
//        $("[class='checkbox__text']").click();
//        $("[class = 'button__content']").click();
//
//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        String actual = $("[data-test-id=notification]").$("[class='notification__title']").getText();
//        String expected = "Успешно!";
//        Assertions.assertEquals(expected, actual);
//    }
}
