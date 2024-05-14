package page;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;

public class CtShopLoginPage extends BaseTest {
    public CtShopLoginPage() {
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "email")
    WebElement emailInputField;

    @FindBy(id = "password-login")
    WebElement passwordInputField;

    @FindBy(css = "i[class='fa fa-sign-in']")
    WebElement sendButton;

    @FindBy(id = "remember_mevN22iqxPF7")
    WebElement rememberMeCheckBox;

    /*
    @FindBy(css = "div[class='alert alert-success'][contains (text(), 'Uspešno ste aktivirali Vaš nalog. Možete se ulogovati na sistem.')]")
    WebElement successfulRegistrationMessage;
   */
    @FindBy(css = "div[class='alert alert-success']")  //className = "alert alert-success"
    WebElement successfulRegistrationMessage;

    @FindBy(css = "div[class='page-title']")  //className = "alert alert-success"
    WebElement profileTitle;


    //@FindBy(xpath = "//div[@class=\"box-content\"]//p[contains(text(), \"Fizicko Lice\")]")
    //@FindBy(css = "div[class='box-content']")
    @FindBy(css = "p[class='hello']")
    WebElement contactInformation;

    @FindBy(css = "div[class='alert alert-danger']")
    WebElement dangerAlert;

    public void emailOrUserNameInputFieldSendKeys(String emailOrUserName) {
        wdWait.until(ExpectedConditions.visibilityOf(emailInputField)).clear();
        emailInputField.sendKeys(emailOrUserName);
    }

    public void passwordInputFieldSendKeys(String password) {
        wdWait.until(ExpectedConditions.visibilityOf(passwordInputField)).clear();
        passwordInputField.sendKeys(password);
    }

    public void rememberMeCheckBoxUnchecked() {
        wdWait.until(ExpectedConditions.elementToBeClickable(rememberMeCheckBox));
        if (rememberMeCheckBox.isSelected()) {
            rememberMeCheckBox.click();
        }
    }

    public void sendButtonClick() {
        wdWait.until(ExpectedConditions.elementToBeClickable(sendButton)).click();
       // sendButton.click();
    }

    public boolean successfulRegistrationMessageIsDisplayed() {
        wdWait.until(ExpectedConditions.visibilityOf(successfulRegistrationMessage));
        return successfulRegistrationMessage.isDisplayed();
    }

    public String successfulRegistrationMessageGetText() {
        wdWait.until(ExpectedConditions.visibilityOf(successfulRegistrationMessage));
        return successfulRegistrationMessage.getText();
    }

    public boolean profileTitleIsDisplayed() {
        wdWait.until(ExpectedConditions.visibilityOf(profileTitle));
        return profileTitle.isDisplayed();
    }

    public String profileTitleGetText() {
        wdWait.until(ExpectedConditions.visibilityOf(profileTitle));
        return profileTitle.getText();
    }

    public boolean contactInformationIsDisplayed() {
        wdWait.until(ExpectedConditions.visibilityOf(contactInformation));
        return contactInformation.isDisplayed();
    }

    public String contactInformationGetText() {
        wdWait.until(ExpectedConditions.visibilityOf(profileTitle));
        return contactInformation.getText();
    }

    public boolean dangerAlertIsDisplayed() {
        wdWait.until(ExpectedConditions.visibilityOf(dangerAlert));
        return dangerAlert.isDisplayed();
    }

    public String dangerAlertGetText() {
        wdWait.until(ExpectedConditions.visibilityOf(dangerAlert));
        return dangerAlert.getText();
    }

    public void switchToNewTab(int tab) {
        wdWait.until(ExpectedConditions.numberOfWindowsToBe(2));
        ArrayList<String> numberOfTabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(numberOfTabs.get(tab));
    }

}
