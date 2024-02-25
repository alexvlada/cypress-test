package page;

import base.BaseTest;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MailinatorHomePage extends BaseTest {
    public MailinatorHomePage() {
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "search")
    WebElement mailinatorInputField;

    public void mailinatorInputFieldSendKeys(String email) {
        wdWait.until(ExpectedConditions.visibilityOf(mailinatorInputField)).clear();
        mailinatorInputField.sendKeys(email);
    }

    public void milinatorInputFieldSendKeyboardKeys(Keys keyboardButton) {
        wdWait.until(ExpectedConditions.visibilityOf(mailinatorInputField)).sendKeys(keyboardButton);
    }
}
