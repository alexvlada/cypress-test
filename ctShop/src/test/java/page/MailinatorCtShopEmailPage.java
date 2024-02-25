package page;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MailinatorCtShopEmailPage extends BaseTest {
    public MailinatorCtShopEmailPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "p[style='font-size: 12px ; line-height: 20px ; margin: 20px 0'] a[href*='https://www.mailinator.com/linker?linkid=']")
    WebElement aktivirajNalogButton;
    @FindBy(id = "html_msg_body")
    WebElement iframeBody;

    public void aktivirajNalogButtonClick() {
        aktivirajNalogButton.click();
    }

    public void switchFocusToIframeBodyWithWdWait() {
        wdWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeBody));
    }

    public void switchFocusToIframeBody() {
        wdWait.until(ExpectedConditions.visibilityOf(iframeBody));
        driver.switchTo().frame(iframeBody);
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }
}

