package ru.yandex.praktikum.pageObject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class HomePageTest {


    private WebDriver driver;
    private static final String url = "https://qa-scooter.praktikum-services.ru/";


    @BeforeEach
    public void startUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url);

        /*
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get(url);
        */

    }

    @ParameterizedTest
    @CsvSource({
            "accordion__heading-0,accordion__panel-0,accordion__panel-0,'Сутки — 400 рублей. Оплата курьеру — наличными или картой.'",
            "accordion__heading-1,accordion__panel-1,accordion__panel-1,'Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.'",
            "accordion__heading-2,accordion__panel-2,accordion__panel-2,'Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.'",
            "accordion__heading-3,accordion__panel-3,accordion__panel-3,'Только начиная с завтрашнего дня. Но скоро станем расторопнее.'",
            "accordion__heading-4,accordion__panel-4,accordion__panel-4,'Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.'",
            "accordion__heading-5,accordion__panel-5,accordion__panel-5,'Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.'",
            "accordion__heading-6,accordion__panel-6,accordion__panel-6,'Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.'",
            "accordion__heading-7,accordion__panel-7,accordion__panel-7,'Да, обязательно. Всем самокатов! И Москве, и Московской области.'",
    })
    public void checkQuestionsList(String questions, String answer, String labelResult, String expectedResult) {
        HomePage homePage = new HomePage(driver);
        homePage.waitForLoadProfileData();
        homePage.scrollToQuestions();
        homePage.clickQuestion(By.id(questions));
        homePage.waitLoadAfterClickQuestion(By.id(labelResult));
        String result = driver.findElement(By.id(answer)).getText();

        Assertions.assertEquals(expectedResult, result);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
