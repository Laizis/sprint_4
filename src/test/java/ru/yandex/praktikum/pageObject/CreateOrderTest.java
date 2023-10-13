package ru.yandex.praktikum.pageObject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CreateOrderTest {
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
        driver.get(url);*/

    }

    @ParameterizedTest
    @CsvSource({
            "UP_BUTTON, Имя Один, Фамилия, Адрес 1, 123, 79991111111, 28.05.2023,шестеро суток, GREY,comments one",
            "UP_BUTTON, Имя Два, Фамилия, Адрес 2, 7, 79992222222, 28.05.2023, пятеро суток, BLACK, comments two",
            "UP_BUTTON, Имя Три, Фамилия, Адрес 3, 10, 79993333333, 28.05.2023, сутки, BLACK, comments three",
            "DOWN_BUTTON, Имя Один, Фамилия, Адрес 1, 123, 79991111111, 28.05.2023, шестеро суток, GREY, comments one",
            "DOWN_BUTTON, Имя Два, Фамилия, Адрес 2, 7, 79992222222, 28.05.2023, пятеро суток, BLACK, comments two",
            "DOWN_BUTTON, Имя Три, Фамилия, Адрес 3, 10, 79993333333, 28.05.2023, сутки, BLACK, comments three"})
    public void testCreateOrder(String button, String name, String surname,
                                String addresses, String stateMetroNumber,
                                String telephoneNumber, String date, String duration,
                                String colour, String comment) {
        HomePage homePage = new HomePage(driver);
        homePage.waitForLoadProfileData();
        homePage.clickCreateOrderButton(button);

        AboutRenter aboutRenter = new AboutRenter(driver);
        aboutRenter
                .waitForLoadOrderPage()
                .inputName(name)
                .inputSurname(surname)
                .inputAddresses(addresses)
                .changeStateMetro(stateMetroNumber)
                .inputTelephone(telephoneNumber)
                .clickNext();

        AboutScooter aboutScooter = new AboutScooter(driver);
        aboutScooter
                .waitAboutRentHeader()
                .inputDate(date)
                .inputDuration(duration)
                .changeColour(colour)
                .inputComment(comment)
                .clickButtonCreateOrder();

        PopUpWindow popUpWindow = new PopUpWindow(driver);
        popUpWindow.clickButtonYes();

        Assertions.assertTrue(popUpWindow.getHeaderAfterCreateOrder().contains("Заказ оформлен"));
    }
}
