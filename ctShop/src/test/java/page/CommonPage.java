package page;

import base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CommonPage extends BaseTest {

    public class GlobalVariables {
        public static String username = "fizickolice181214@mailinator.com";
        public static String password = "Getitnow2024!";
    }
    public CommonPage() { PageFactory.initElements(driver, this); }

    @FindBy(css = "span[class='sharkskin-cart-items']")
    WebElement basketNumberOfItems;

    @FindBy(id = "search-input-header")
    WebElement searchInputField;

    @FindBy(xpath = "//span[@class='ais-Stats-text']/b")
    WebElement searchResult;

    //@FindBy(css = "span[class='sharkskin-cart-items']")
    @FindBy(xpath = "//div[@class='no-results col-xs-12'] //div[@class='text']/h3")
    //span[@class='ais-Stats-text']/b
    WebElement noSearchResult;

    @FindBy(id = "product-addtocart-button")
    WebElement addToBasketButton;

    public boolean basketNumberOfItemsIsDisplayed() {
        wdWait.until(ExpectedConditions.visibilityOf(basketNumberOfItems));
        return basketNumberOfItems.isDisplayed();
    }

    public String basketNumberOfItemsGetText() {
        wdWait.until(ExpectedConditions.visibilityOf(basketNumberOfItems));
        return basketNumberOfItems.getText();
    }

    public void searchInputFieldSendKeys(String searchCriteria) {
        wdWait.until(ExpectedConditions.visibilityOf(searchInputField)).clear();
        searchInputField.sendKeys(searchCriteria);
        searchInputField.sendKeys(Keys.ENTER);
    }

    public boolean searchResultIsDisplayed() {
        wdWait.until(ExpectedConditions.visibilityOf(searchResult));
        return searchResult.isDisplayed();
    }

    public String searchResultGetText() {
        wdWait.until(ExpectedConditions.visibilityOf(searchResult));
        return searchResult.getText();
    }

    public boolean searchResultIsNotDisplayed() {
        wdWait.until(ExpectedConditions.visibilityOf(noSearchResult));
        return noSearchResult.isDisplayed();
    }

    public String noSearchResultGetText() {
        wdWait.until(ExpectedConditions.visibilityOf(noSearchResult));
        return noSearchResult.getText();
    }

    public void addToBasketButtonClick() {
        wdWait.until(ExpectedConditions.elementToBeClickable(addToBasketButton));
        addToBasketButton.click();
    }

}
