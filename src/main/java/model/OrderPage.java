package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {

    private WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //Поле ввода имени при заказе самоката
    private By firstNameInput = By.xpath(".//input[@placeholder='* Имя']");

    //Поле ввода фамилии при заказе самоката
    private By lastNameInput = By.xpath(".//input[@placeholder='* Фамилия']");

    //Поле ввода адреса при заказе самоката
    private By addressInput = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    //Поле выбора станции метро при заказе самоката
    private By metroStationInput = By.xpath(".//input[@placeholder='* Станция метро']");

    //Поле ввода номер телефона для курьера
    private By phoneNumberInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //Кнопка Далее на форме заполнения "для кого самокат"
    private By nextButton = By.xpath(".//div[contains(@class, 'Order_Content')]//button[text()='Далее']");

    //Поле ввода даты "когда привезти самокат"
    private By dateInput = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    //Поле срока аренды
    private By rentPeriodInput = By.xpath(".//span[@class='Dropdown-arrow']");

    //Список возможного выбора срока аренды
    private By dropDownList = By.xpath(".//div[contains(@class, 'Dropdown-menu')]");

    //Выбор чекбокса черного цвета самоката
    private By blackColorInput = By.id("black");

    //Выбор чекбокса серого цвета самоката
    private By greyColorInput = By.id("grey");

    //Поле комментарий для круьера
    private By commentInput = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    //Кнопка "заказать" для завершения оформления заказа
    private By orderButton = By.xpath(".//div[contains(@class, 'Order_Content')]//button[text()='Заказать']");

    //Кнопка подтвеждения заказа
    private By confirmOrderButton = By.xpath(".//div[contains(@class, 'Order_Content')]//button[text()='Да']");

    //В модальном окне присутствует надпись "заказ оформлен"
    private By checkOrderStatus = By.xpath(".//div[contains(@class, 'Order_Content')]//div[text()='Заказ оформлен']");

    //Кнопка "посмотреть статус" в модальном окне когда заказ оформлен
    private By checkStatusButton = By.xpath(".//div[contains(@class, 'Order_Content')]//button[text()='Посмотреть статус']");

    //Заполнение поля Имя
    public void setFirstName(String firstName) {
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    //Заполнение поля Фамилия
    public void setLastName(String lastName) {
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    //Заполнение поля Адреса
    public void setAddress(String address) {
        driver.findElement(addressInput).sendKeys(address);
    }

    //Заполнение поля Станции метро
    public void setMetroStationInput(String clientMetro) {
        driver.findElement(metroStationInput).sendKeys(clientMetro);
    }

    //Выбор станции метро из списка
    public void clickMetroStationSelect(String clientMetro) {
        driver.findElement(
                By.xpath(".//div[contains(@class, 'Order_Content')]//div[contains(@class, 'Order_Text') and text()='" + clientMetro + "']")
        ).click();
    }

    //Заполнение поля номера телефона
    public void setMobileNumber(String mobileNumber) {
        driver.findElement(phoneNumberInput).sendKeys(mobileNumber);
    }

    //Клик по кнопке Далее после заполнения формы "для кого самокат"
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    //Заполнение поля даты привоза самоката
    public void setDateInput(String date) {
        driver.findElement(dateInput).sendKeys(date);
    }

    //Вызов списка доступного срока аренды
    public void clickRentPeriod() {
        driver.findElement(rentPeriodInput).click();
    }

    //Выбор срока аренды из списка
    public void clickRentPeriodSelect(String option) {
        driver.findElement(dropDownList).findElement(By.xpath(".//div[text()='" + option + "']")).click();
    }

    //Выбор цвета самоката
    public void clickColor(String color) {
        if (color.equals("black")) {
            driver.findElement(blackColorInput).click();
        } else {
            driver.findElement(greyColorInput).click();
        }
    }

    //Заполнение поля с комментарием
    public void setComment(String comment) {
        driver.findElement(commentInput).sendKeys(comment);
    }

    //Клик по кнопке "заказать" для завершения оформления заказа
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    //Подтверждение оформления заказа
    public void clickConfirmOrderButton() {
        driver.findElement(confirmOrderButton).click();
    }

    //Проверка отображения кнопки "посмотреть статус"
    public boolean isStatusButtonDisplayed() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(driver.findElement(checkStatusButton)));
        return driver.findElement(checkStatusButton).isDisplayed();
    }

    //проверка отображения текста "заказ оформлен"
    public boolean isOrderStatusDisplayed() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(driver.findElement(checkOrderStatus)));
        return driver.findElement(checkOrderStatus).isDisplayed();
    }

    //Объединяем в шаг заполнение полей блока "для кого самокат"
    public void setFieldsBlockForWhomTheScooter(String name, String lastName, String address, String metroStation, String phone) {
        setFirstName(name);
        setLastName(lastName);
        setAddress(address);
        setMetroStationInput(metroStation);
        clickMetroStationSelect(metroStation);
        setMobileNumber(phone);
    }

    //Объединяем в шаг заполнение полей блока "про аренду"
    public void setFieldsBlockAboutRent(String date, String rentPeriod, String color, String comment) {
        setDateInput(date);
        clickRentPeriod();
        clickRentPeriodSelect(rentPeriod);
        clickColor(color);
        setComment(comment);
    }
}