package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {
    public static WebDriver driver;
    public static WebDriverWait wdWait;
    public static Actions actions;

    @Before
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        wdWait = new WebDriverWait(driver, 30);
        actions = new Actions(driver);
        driver.get("https://www.ctshop.rs/");
        driver.manage().window().maximize();
    }

    @After
    public void tearDown(){
      // TODO odkomentarisati kada testovi budu napisani
      driver.quit();
    }
}
