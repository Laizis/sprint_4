package ru.yandex.praktikum.pageObject;

import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage {
    WebDriver driver;

    private By homeHeader = By.className("Home_Header__iJKdX");

    private By questionsHeader = By.className("Home_FourPart__1uthg");

    private By downButton = By.className("Button_Button__ra12g Button_Middle__1CSJM");

    private By upButton = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[@class='Button_Button__ra12g']");
    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    // метод ожидания прогрузки данных главной страница
    public void waitForLoadProfileData() {
        new WebDriverWait(driver, 15).until(driver -> (driver.findElement(homeHeader).getText() != null
                && !driver.findElement(homeHeader).getText().isEmpty()
        ));
    }

    //метод ожидания загрузки ответа на вопрос
    public void waitLoadAfterClickQuestion(By accordionLabel) {
        new WebDriverWait(driver, 10).until(driver -> (driver.findElement(accordionLabel).getText() != null
                && !driver.findElement(accordionLabel).getText().isEmpty()
        ));
    }

    //метод прокрутки к блоку "Вопросы о важном"
    public HomePage scrollToQuestions() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(questionsHeader));
        return this;
    }

    public HomePage clickQuestion(By question) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(question))
                .click();
        return this;
    }

    public HomePage scrollDownOrderButton(){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(downButton));
        return this;
    }

    public HomePage clickUpButton(){
        driver.findElement(upButton).click();
        return this;
    }

    public HomePage clickDownButton(){
        driver.findElement(downButton).click();
        return this;
    }

    public void clickCreateOrderButton(String button) {
        if (button.equals("UP_BUTTON")) {
            clickUpButton();
        } else if (button.equals("DOWN_BUTTON")) {
            scrollDownOrderButton();
            clickDownButton();
        }
    }



}
