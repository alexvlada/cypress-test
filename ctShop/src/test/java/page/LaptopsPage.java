package page;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LaptopsPage extends BaseTest {

    public LaptopsPage() {
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//label[text()='Lenovo']")
    WebElement lenovoFilter;

    @FindBy(xpath = "//label[text()='AMD RYZEN 7']")
    WebElement processorFilter;

    public void lenovoCheckboxClick() {
        wdWait.until(ExpectedConditions.elementToBeClickable(lenovoFilter));
        lenovoFilter.click();
    }

    public void processorCheckboxClick() {
        wdWait.until(ExpectedConditions.elementToBeClickable(processorFilter));
        processorFilter.click();
    }

}
