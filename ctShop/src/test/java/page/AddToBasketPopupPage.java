package page;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddToBasketPopupPage extends BaseTest {

    public AddToBasketPopupPage() { PageFactory.initElements(driver, this); }

    @FindBy(css = "h3[class='modal-title']")
    WebElement addToBasketPopup;

    @FindBy(xpath = "//a[contains(text(), 'Idi u korpu')]")
    WebElement goToBasketButton;

    @FindBy(xpath = "//button[contains(text(), 'Nastavi kupovinu')]")
    WebElement continueShopping;

    public boolean addToBasketPopupIsDisplayed() {
        wdWait.until(ExpectedConditions.visibilityOf(addToBasketPopup));
        return addToBasketPopup.isDisplayed();
    }

    public String addToBasketPopupGetText() {
        wdWait.until(ExpectedConditions.visibilityOf(addToBasketPopup));
        return addToBasketPopup.getText();
    }

    public void goToBasketButtonClick() {
        wdWait.until(ExpectedConditions.elementToBeClickable(goToBasketButton));
        goToBasketButton.click();
    }

    public void continueShoppingButtonClick() {
        wdWait.until(ExpectedConditions.elementToBeClickable(continueShopping));
        continueShopping.click();
    }

}
