package page;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LaptopPage extends BaseTest {
    public LaptopPage() {
        PageFactory.initElements(driver, this);
    }

    //@FindBy(xpath = "//a[contains(text(), '82YM005HYA')]")
    @FindBy(css = "img[alt='Lenovo Yoga 7 14ARP8 (82YM005HYA) 2u1 laptop 14\" 2.8K OLED touch AMD Ryzen 7 7735U 16GB 1TB SSD Radeon 680M Win11 Pro sivi']")
    WebElement laptop;

    @FindBy(css = "img[alt='Lenovo IdeaPad Flex 5 14ABR8 (82XX00B7YA) 2u1 laptop 14\" WUXGA touch AMD Ryzen 7 7730U 16GB 512GB SSD Radeon Graphics Win11 sivi']")

    WebElement laptop2u1;

    //@FindBy(css = "img[alt='Lenovo Legion 5 Pro 16ARX8 (82WM00D0RM) gejmerski laptop 16\" WQXGA AMD Ryzen 7 7745HX 32GB 1TB SSD GeForce RTX4060 sivi']")
    @FindBy(css = "img[alt='Asus Zenbook 14 UM3402YAR-KP521W laptop 14\" WQXGA AMD Ryzen 5 7530U 16GB 1TB SSD Radeon Graphics Win11 crni']")

    WebElement laptopLegion;

    //

    @FindBy(css = "div[class='product-name'] h1")
    WebElement productName;

    //@FindBy(css = "span[class=' strikethrough  old-online-price']")
    @FindBy(css = "span[class='price-after-discount']")
    WebElement oldPrice;

    //@FindBy(css = "span[class='price new-online-price']")
    @FindBy(css = "span[class='new-price']")
    WebElement newPrice;

    @FindBy(xpath = "//a[contains(text(), 'Ocene')]")
    WebElement oceneTab;

    @FindBy(css = "div[class='rating-number ']")
    WebElement ratingNumber;

    @FindBy(id = "product-addtocart-button")
    WebElement addToBasketButton;

    public void laptopClick(String category) {
        if (category == "2u1")
            {
                wdWait.until(ExpectedConditions.elementToBeClickable(laptop2u1));
                laptop2u1.click();
            }
        else if (category == "legion")
            {
                wdWait.until(ExpectedConditions.elementToBeClickable(laptopLegion));
                laptopLegion.click();
            }
        else
            {
                wdWait.until(ExpectedConditions.elementToBeClickable(laptop));
                laptop.click();
            }

    }

    public boolean productNameIsDisplayed() {
        wdWait.until(ExpectedConditions.visibilityOf(productName));
        return productName.isDisplayed();
    }

    public String productNameGetText() {
        wdWait.until(ExpectedConditions.visibilityOf(productName));
        return productName.getText();
    }

    public boolean oldPriceIsDisplayed() {
        wdWait.until(ExpectedConditions.visibilityOf(oldPrice));
        return oldPrice.isDisplayed();
    }

    public String oldPriceGetText() {
        wdWait.until(ExpectedConditions.visibilityOf(oldPrice));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "return arguments[0].childNodes[0].nodeValue.trim();";
        String priceValue = (String) js.executeScript(script, oldPrice);
        return priceValue;
    }

    public boolean newPriceIsDisplayed() {
        wdWait.until(ExpectedConditions.visibilityOf(newPrice));
        return newPrice.isDisplayed();
    }

    public String newPriceGetText() {
        wdWait.until(ExpectedConditions.visibilityOf(newPrice));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "return arguments[0].childNodes[0].nodeValue.trim();";
        String priceValue = (String) js.executeScript(script, newPrice);
        return priceValue;
    }

    public void ocenaTabClick() {
        wdWait.until(ExpectedConditions.elementToBeClickable(oceneTab));
        oceneTab.click();
    }

    public boolean ratingNumberisDisplayed() {
        wdWait.until(ExpectedConditions.visibilityOf(ratingNumber));
        return ratingNumber.isDisplayed();
    }
    public String ratingNumberGetText() {
        wdWait.until(ExpectedConditions.visibilityOf(ratingNumber));
        return ratingNumber.getText();
    }

    public void addToBasketButtonClick() {
        wdWait.until(ExpectedConditions.elementToBeClickable(addToBasketButton));
        addToBasketButton.click();
    }

}
