package page;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SporetPage extends BaseTest {
    public SporetPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(text(), 'GEC5C61WG')]")
    WebElement sporet;

    public void sporetClick() {
        wdWait.until(ExpectedConditions.elementToBeClickable(sporet));
        sporet.click();
    }
}
