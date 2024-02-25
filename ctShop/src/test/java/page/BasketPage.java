package page;

import base.BaseTest;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class BasketPage extends BaseTest {

    public BasketPage() { PageFactory.initElements(driver, this); }

    @FindBy(css = "div[class='col-md-4 active']")
    WebElement activeTab;



   // <div class="col-md-4 active">2. Naručivanje</div>

    @FindBy(xpath = "//b[text() = 'Korpa']")
    WebElement basketTitle;

    @FindBy(css = "a[class='name-of-product']")
    WebElement productName;

    @FindBy(xpath = "//button[text() = 'Sačuvaj i nastavi']")
    WebElement confirmButton;

    @FindBy(xpath = "//a[text() = 'Nazad']")
    WebElement backButton;

    @FindBy(css = "i[class='fa fa-times']")
    WebElement removeItemButton;

    @FindBy(xpath = "//h3[text() = 'Vaša korpa je trenutno prazna.']")
    WebElement emptyBasketMessage;

    @FindBy(css = "i[class='fa fa-caret-up']")
    WebElement arrowUpButton;

    @FindBy(css = "i[class='fa fa-caret-down']")
    WebElement arrowDownButton;

    @FindBy(css = "input[title='Qty']")
    WebElement currentAmount;

    public boolean activeTabIsDisplayed() {
        wdWait.until(ExpectedConditions.visibilityOf(activeTab));
        return activeTab.isDisplayed();
    }

    public String activeTabGetText() {
        wdWait.until(ExpectedConditions.visibilityOf(activeTab));
        return activeTab.getText();
    }

    public boolean basketTitleIsDisplayed() {
        wdWait.until(ExpectedConditions.visibilityOf(basketTitle));
        return basketTitle.isDisplayed();
    }

    public String basketTitleGetText() {
        wdWait.until(ExpectedConditions.visibilityOf(basketTitle));
        return basketTitle.getText();
    }

    public boolean productNameIsDisplayed() {
        wdWait.until(ExpectedConditions.visibilityOf(productName));
        return productName.isDisplayed();
    }

    public String productNameGetText() {
        wdWait.until(ExpectedConditions.visibilityOf(productName));
        return productName.getText();
    }

    public void confirmButtonClick() {
        wdWait.until(ExpectedConditions.elementToBeClickable(confirmButton));
        confirmButton.click();
    }

    public void backButtonClick() {
        wdWait.until(ExpectedConditions.elementToBeClickable(backButton));
        backButton.click();
    }

    public void removeItemButtonClick() {
        wdWait.until(ExpectedConditions.elementToBeClickable(removeItemButton));
        removeItemButton.click();
    }

    public boolean emptyBasketMessageIsDisplayed() {
        wdWait.until(ExpectedConditions.visibilityOf(emptyBasketMessage));
        return emptyBasketMessage.isDisplayed();
    }

    public String emptyBasketMessageGetText() {
        wdWait.until(ExpectedConditions.visibilityOf(emptyBasketMessage));
        return emptyBasketMessage.getText();
    }

    public void arrowUpButtonClick() {
        wdWait.until(ExpectedConditions.elementToBeClickable(arrowUpButton));
        arrowUpButton.click();
    }

    public void arrowDownButtonClick() {
        wdWait.until(ExpectedConditions.elementToBeClickable(arrowDownButton));
        arrowDownButton.click();
    }

    public boolean currentAmountIsDisplayed() {
        wdWait.until(ExpectedConditions.visibilityOf(currentAmount));
        return currentAmount.isDisplayed();
    }

    public String currentAmountGetValue() {
        wdWait.until(ExpectedConditions.visibilityOf(currentAmount));
        return currentAmount.getAttribute("value");
    }

    public String currentAmountGetValue(String expectedValue) {

        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until((ExpectedCondition<Boolean>) driver -> {
            String fieldValue = currentAmount.getAttribute("value");
            return fieldValue.equals(expectedValue);

            //assertEquals(currentAmount.getAttribute("value"),expectedValue))
         });


        wdWait.until(ExpectedConditions.visibilityOf(currentAmount));
        return currentAmount.getAttribute("value");
    }

    public void currentAmountGetValue1(String expectedValue) {

        WebDriverWait wait = new WebDriverWait(driver, 10);

        wdWait.until(ExpectedConditions.visibilityOf(currentAmount));
        wait.until((ExpectedCondition<Boolean>) driver -> {
            String fieldValue = currentAmount.getAttribute("value");
            return fieldValue.equals(expectedValue);

        });

        assertEquals(currentAmount.getAttribute("value"),expectedValue);

       // return currentAmount.getAttribute("value");
    }

    public void amountInputFieldSendKeys(String amount) {
        wdWait.until(ExpectedConditions.visibilityOf(currentAmount)).clear();
        currentAmount.sendKeys(amount);
    }

}
