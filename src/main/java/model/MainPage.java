package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;

public class MainPage {

    private WebDriver driver;

    //Кнопка "заказать" для создания заказа
    private By createOrderButton = By.xpath(".//button[text()='Заказать']");

    //Кнопка "да все привыкли"
    private By rccConfirmButton = By.id("rcc-confirm-button");

    //Раздел вопросы о важном
    private By accordionItems = By.xpath(".//div[@class='accordion__item']");

    //Открытие главной страницы сайта
    public void openPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Клик по кнопке Заказать, в завимости от индекса - верхняя или нижняя кнопка
    public void clickCreateOrderButton(int index) {
        driver.findElements(createOrderButton).get(index).click();
    }

    //Клик по кнопке "да все привыкли"
    public void clickRccConfirmButton() {
        driver.findElement(rccConfirmButton).click();
    }

    //Выбор определенного вопроса
    public void clickAccordionItem(int index) {
        driver.findElements(accordionItems).get(index).click();
    }

    //Проверка, что соответствующий выпадающий список отображается
    public void checkAccordionItemPanelDisplayed(int index) {
        WebElement accordionItemPanel = driver.findElements(accordionItems).get(index)
                .findElement(By.xpath(".//div[@class='accordion__panel']"));

        assertTrue(accordionItemPanel.isDisplayed());
    }

    //Проверка, что текст в выпадающем списке соответствует ожидаемому
    public void checkAccordionItemText(int index, String expectedText) {
        String accordionItemText = driver.findElements(accordionItems).get(index)
                .findElement(By.xpath(".//div[@class='accordion__panel']/p"))
                .getText();

        assertTrue(accordionItemText.equals(expectedText));
    }

    //Объединяем в шаг, проверка что выпадающий список отображается и текст соответствует
    public void checkAccordionItemOpenAndShowExpectedText(int index, String expectedText) {
        clickAccordionItem(index);
        checkAccordionItemPanelDisplayed(index);
        checkAccordionItemText(index, expectedText);
    }
}
