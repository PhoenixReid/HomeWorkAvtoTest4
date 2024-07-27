
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
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
        $("[data-test-id='name'] input").setValue("Артем");
        $("[data-test-id='phone'] input").setValue("+79532376054");
        $("[class='checkbox__text']").click();
        $("[class = 'button__content']").click();
        $("[class='notification__title']")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Успешно"));



    }


}