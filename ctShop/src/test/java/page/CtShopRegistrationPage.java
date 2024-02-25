package page;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CtShopRegistrationPage extends BaseTest {
    public CtShopRegistrationPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "pravnolice")
    WebElement pravnoLiceSelectList;
    @FindBy(id = "firstname")
    WebElement firstNameInputField;

    @FindBy(id = "lastname")
    WebElement lastNameInputField;

    @FindBy(id = "email_address")
    WebElement emailAddressInputField;

    @FindBy(id = "password")
    WebElement passwordInputField;
    @FindBy(id = "confirmation")
    WebElement confirmPasswordInputField;
    @FindBy(xpath = "//button[text() = 'Prihvatam']")
    WebElement confirmButton;
    @FindBy(xpath = "//span[text() = 'Pošalji']")
    WebElement sendButton;

    @FindBy(xpath = "//input[@value=\"person\"]")
    WebElement fizickoLiceRadioButton;
    @FindBy(id = "UserName")
    WebElement korisnickoImeInputField;

    @FindBy(id = "HasAgreedToGetFiscalReceiptByEmail")
    WebElement saglasnostCheckbox;

    @FindBy(id = "is_subscribed")
    WebElement checkbox0;

    @FindBy(id = "remember_meGgNW83udlh")
    WebElement checkbox1;

    public void pravnoLiceSelectListCheckFalse() {
        wdWait.until(ExpectedConditions.visibilityOf(pravnoLiceSelectList));
        Select pravnoLice = new Select(pravnoLiceSelectList);
        String value = pravnoLice.getFirstSelectedOption().getText();
        if (value.equals("Da"))
        {
            pravnoLice.selectByVisibleText("Ne");
        }
    }

    public void firstNameInputFieldSendKeys(String firstName) {
        wdWait.until(ExpectedConditions.visibilityOf(firstNameInputField)).clear();
        firstNameInputField.sendKeys(firstName);
    }

    public void lastNameInputFieldSendKeys(String lastName) {
        wdWait.until(ExpectedConditions.visibilityOf(lastNameInputField)).clear();
        lastNameInputField.sendKeys(lastName);
    }

    public void emailAddressInputFieldSendKeys(String emailAddress) {
        wdWait.until(ExpectedConditions.visibilityOf(emailAddressInputField)).clear();
        emailAddressInputField.sendKeys(emailAddress);
    }

    public void passwordInputFieldSendKeys(String password) {
        wdWait.until(ExpectedConditions.visibilityOf(passwordInputField)).clear();
        passwordInputField.sendKeys(password);
    }

    public void confirmPasswordInputFieldSendKeys(String confirmPassword) {
        wdWait.until(ExpectedConditions.visibilityOf(confirmPasswordInputField)).clear();
        confirmPasswordInputField.sendKeys(confirmPassword);
    }

    public void isSubscribedCheckboxUncheck() {
        if(checkbox0.isSelected())
            checkbox0.click();
    }

    public void rememberMeCheckboxUncheck() {
        if(checkbox1.isSelected())
            checkbox1.click();
    }

    public void confirmButtonClick() {
        wdWait.until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
        //wdWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text() = 'Prihvatam']"))).click();
    }

    public void sendButtonClick() {
       wdWait.until(ExpectedConditions.elementToBeClickable(sendButton)).click();
    }

}



