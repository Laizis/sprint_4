package ru.yandex.praktikum.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AboutRenter {
    private final WebDriver driver;

    private final By orderHeader = By.className("Order_Header__BZXOb");
    private final By orderName = By.xpath(".//input[@placeholder='* Имя']");
    private final By orderSurname = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By orderAddresses = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By orderTelephone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By buttonNext = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final By stateMetro = By.className("select-search__input");
    private final String nameStateMetro = ".//button[@value='%s']";

    public AboutRenter(WebDriver driver) {
        this.driver = driver;
    }

    //метод ожидания загруки страницы заказа
    public AboutRenter waitForLoadOrderPage() {
        new WebDriverWait(driver, 10).until(driver -> (driver.findElement(orderHeader).getText() != null
                && !driver.findElement(orderHeader).getText().isEmpty()
        ));
        return this;
    }

    public AboutRenter inputName(String newName) {
        driver.findElement(orderName).sendKeys(newName);
        return this;
    }

    public AboutRenter inputSurname(String newSurname) {
        driver.findElement(orderSurname).sendKeys(newSurname);
        return this;
    }

    public AboutRenter changeStateMetro(String stateNumber) {
        driver.findElement(stateMetro).click();
        By newStateMetro = By.xpath(String.format(nameStateMetro, stateNumber));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(newStateMetro));
        driver.findElement(newStateMetro).click();
        return this;
    }

    public AboutRenter inputAddresses(String newAddresses) {
        driver.findElement(orderAddresses).sendKeys(newAddresses);
        return this;
    }

    public AboutRenter inputTelephone(String newTelephone) {
        driver.findElement(orderTelephone).sendKeys(newTelephone);
        return this;
    }

    public void clickNext() {
        driver.findElement(buttonNext).click();
    }

}
