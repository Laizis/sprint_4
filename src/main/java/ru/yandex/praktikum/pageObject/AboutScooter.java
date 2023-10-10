package ru.yandex.praktikum.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AboutScooter {
    private final WebDriver driver;

    private final By scooterHeader = By.className("Order_Header__BZXOb");
    private final By rentDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By durationRent = By.xpath(".//span[@class= 'Dropdown-arrow']");

    private final By colourBlack = By.id("black");
    private final By colourGrey = By.id("grey");

    private final By comment = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    private final By createOrder = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public AboutScooter(WebDriver driver){
        this.driver = driver;
    }

    //метод ожидания загрузки страницы
    public AboutScooter waitAboutRentHeader() {
        new WebDriverWait(driver, 10).until(driver -> (driver.findElement(scooterHeader).getText() != null
                && !driver.findElement(scooterHeader).getText().isEmpty()
        ));
        return this;
    }

    public AboutScooter inputDate(String newDate){
        WebElement element = driver.findElement(rentDate);
        element.sendKeys(newDate);
        return this;
    }

    public AboutScooter inputDuration(String newDuration) {
        driver.findElement(durationRent).click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(By.xpath(".//div[@class='Dropdown-menu']")))
                .findElements(By.xpath(".//div[@class='Dropdown-option']"))
                .stream()
                .filter(webElement -> webElement.getText().equals(newDuration)).findFirst().get().click();
        return this;
    }

    public AboutScooter changeColour(String colour) {
        if (colour.equals("BLACK")) {
            driver.findElement(colourBlack).click();
        } else if (colour.equals("GREY")) {
            driver.findElement(colourGrey).click();
        }
        return this;
    }

    public AboutScooter inputComment(String newComment) {
        driver.findElement(comment).sendKeys(newComment);
        return this;
    }

    public void clickButtonCreateOrder() {
        driver.findElement(createOrder).click();
    }
}
