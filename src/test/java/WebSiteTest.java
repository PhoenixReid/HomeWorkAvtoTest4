import com.codeborne.selenide.Selectors;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.DELETE;
import static org.openqa.selenium.Keys.ENTER;

//java -jar ./artifacts/app-card-delivery.jar
public class WebSiteTest {
    private WebDriver driver;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
    String data = LocalDate.now().plusDays(7).format(formatter);

    ChromeOptions options = new ChromeOptions();

    {
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
    }

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999/");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

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


}