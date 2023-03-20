import model.MainPage;
import model.OrderPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

@RunWith(Parameterized.class)
public class OrderScooterTest {

    private WebDriver driver;

    private final int mainPageOrderButtonIndex;
    private final String name;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String date;
    private final String rentPeriod;
    private final String color;
    private final String comment;

    public OrderScooterTest(int mainPageOrderButtonIndex, String name, String lastName, String address, String metroStation, String phone, String date, String rentPeriod, String color, String comment) {
        this.mainPageOrderButtonIndex = mainPageOrderButtonIndex;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.date = date;
        this.rentPeriod = rentPeriod;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][] {
                {0, "Иван", "Петров", "г. Москва, ул. Седова, д. 1", "Динамо", "88007555555", "23.03.2023", "сутки", "black", "1 коммент"},
                {1, "Сергей", "Иванов", "г. Москва, ул. Мира, д. 23", "Алтуфьево", "+78994562123", "23.03.2024", "трое суток", "grey", "2 коммент"},
                {0, "Федор", "Круг", "г. Москва, Каретный переулок, д. 125", "Сокольники", "88008008082", "02.04.2023", "двое суток", "black", "3 коммент"}
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void orderScooterTest() {
        MainPage page = new MainPage(driver);
        page.openPage();
        page.clickRccConfirmButton();
        page.clickCreateOrderButton(mainPageOrderButtonIndex);

        OrderPage orderPage = new OrderPage(driver);
        orderPage.setFieldsBlockForWhomTheScooter(name, lastName, address, metroStation, phone);
        orderPage.clickNextButton();
        orderPage.setFieldsBlockAboutRent(date, rentPeriod, color, comment);
        orderPage.clickOrderButton();
        orderPage.clickConfirmOrderButton();
        orderPage.checkOrderStatusDisplayed();
        orderPage.checkStatusButtonDisplayed();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
