import io.github.bonigarcia.wdm.WebDriverManager;
import model.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@RunWith(Parameterized.class)
public class AccordionTests {

    private WebDriver driver;

    private final int accordionItemIndex;
    private final String accordionExpectedText;

    public AccordionTests(int accordionItemIndex, String accordionExpectedText) {
        this.accordionItemIndex = accordionItemIndex;
        this.accordionExpectedText = accordionExpectedText;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."}
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void checkAccordionItemOpenAndShowExpectedText() {
        MainPage page = new MainPage(driver);
        page.openPage();
        page.clickRccConfirmButton();
        page.checkAccordionItemOpenAndShowExpectedText(accordionItemIndex, accordionExpectedText);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
