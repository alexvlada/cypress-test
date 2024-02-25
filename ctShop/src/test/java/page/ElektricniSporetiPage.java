package page;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ElektricniSporetiPage extends BaseTest {
    public ElektricniSporetiPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//label[text()='Gorenje']")
    WebElement gorenjeFilter;

    @FindBy(xpath = "//label[text()='Staklokeramicka']")
    WebElement staklokeramikaFilter;

    public void gorenjeCheckboxClick() {
        wdWait.until(ExpectedConditions.elementToBeClickable(gorenjeFilter));
        gorenjeFilter.click();
    }

    public void staklokeramikaCheckboxClick() {
        wdWait.until(ExpectedConditions.elementToBeClickable(staklokeramikaFilter));
        staklokeramikaFilter.click();
    }

}
