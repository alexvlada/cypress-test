package page;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MailinatorInboxPage extends BaseTest {
    public MailinatorInboxPage() {
        PageFactory.initElements(driver, this);
    }

   @FindBy(xpath = "//td[@class=\"ng-binding\"][contains(text(), 'Aktivacija naloga na CT Shop')]")
    WebElement haloOglasiAktivacioniMail;

    public void haloOglasiAktivacioniMailClick() {
        wdWait.until(ExpectedConditions.elementToBeClickable(haloOglasiAktivacioniMail)).click();
    }
}
