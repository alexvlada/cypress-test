package page;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CtShopHomePage extends BaseTest {
    public CtShopHomePage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Otvori nalog")
    WebElement otvoriNalogButton;

    @FindBy(linkText = "Prijavi se")
    WebElement prijaviSeButton;

    //@FindBy(className = "am-opener sharkskin-collapse")
    @FindBy(css = "div[class='am-container no-mega-menu']")
    WebElement mainMenuDropDown;

    @FindBy(linkText = "Laptopovi-Tableti")
    WebElement getCategory;

    @FindBy(linkText = "Bela tehnika")
    WebElement getCategoryBelaTehnika;


    @FindBy(linkText = "Laptop računari")
    WebElement getSubCategory;

    @FindBy(linkText = "Šporeti")
    WebElement getSubCategorySporeti;

    @FindBy(linkText = "Laptopovi")
    WebElement getProductGroup;

    @FindBy(linkText = "Električni šporeti")
    WebElement getProductGroupElektricniSporeti;



    public void otvoriNalogButtonClick() {
        wdWait.until(ExpectedConditions.elementToBeClickable(otvoriNalogButton)).click();
    }

    public void prijaviSeButtonClick() {
        wdWait.until(ExpectedConditions.elementToBeClickable(prijaviSeButton)).click();
    }

    public void mainMenuDropDownHover() {
        wdWait.until(ExpectedConditions.elementToBeClickable(mainMenuDropDown));
        actions.moveToElement(mainMenuDropDown).perform();
    }

    public void getCategoryHover() {
        wdWait.until(ExpectedConditions.elementToBeClickable(getCategory));
        actions.moveToElement(getCategory).perform();
    }

    public void getCategoryHover(String category) {
        if (category == "laptopoviTableti")
            {
            wdWait.until(ExpectedConditions.elementToBeClickable(getCategory));
            actions.moveToElement(getCategory).perform();
            }
        else if (category == "belaTehnika")
            {
            wdWait.until(ExpectedConditions.elementToBeClickable(getCategoryBelaTehnika));
            actions.moveToElement(getCategoryBelaTehnika).perform();
            }
    }

    public void getSubCategoryClick() {
        wdWait.until(ExpectedConditions.elementToBeClickable(getSubCategory)).click();
    }

    public void getSubCategoryClick(String subCategory) {
        if (subCategory == "laptopRacunari")
            {
                wdWait.until(ExpectedConditions.elementToBeClickable(getSubCategory)).click();
            }
        else if (subCategory == "sporeti")
            {
                wdWait.until(ExpectedConditions.elementToBeClickable(getSubCategorySporeti)).click();
            }
    }

    public void getProductGroupClick() {
        wdWait.until(ExpectedConditions.elementToBeClickable(getProductGroup)).click();
    }

    public void getProductGroupClick(String productGroup) {
        if (productGroup == "laptopovi")
        {
            wdWait.until(ExpectedConditions.elementToBeClickable(getProductGroup)).click();
        }
        else if (productGroup == "elektricniSporeti")
        {
            wdWait.until(ExpectedConditions.elementToBeClickable(getProductGroupElektricniSporeti)).click();;
        }

    }

}
