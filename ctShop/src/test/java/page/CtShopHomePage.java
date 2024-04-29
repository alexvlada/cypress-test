package page;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;

import java.util.concurrent.Executor;

public class CtShopHomePage extends BaseTest {
    public CtShopHomePage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Registaracija")
    WebElement otvoriNalogButton;

    @FindBy(linkText = "Prijava")
    WebElement prijaviSeButton;

    //@FindBy(className = "am-opener sharkskin-collapse")
    @FindBy(css = "div[class='am-container no-mega-menu']")
    WebElement mainMenuDropDown;

    @FindBy(linkText = "Laptopovi-Tableti")
    WebElement getCategory;

    @FindBy(linkText = "Bela tehnika i klime")
    WebElement getCategoryBelaTehnika;


    @FindBy(linkText = "Laptop računari")
    WebElement getSubCategory;

    //@FindBy(linkText = "Šporeti")
    @FindBy(css = "a[href='/sporeti']")
    WebElement getSubCategorySporeti;

    //@FindBy(linkText = "Laptopovi")
    @FindBy(css = "img[alt='Laptopovi']")
    WebElement getProductGroup;

    @FindBy(css = "img[alt='2-u-1 laptopovi']")
    WebElement getProductGroup1;

    @FindBy(css = "img[alt='Električni šporeti']")
    WebElement getProductGroupElektricniSporeti;

    @FindBy(id = "mCSB_5_dragger_vertical")
    WebElement processorSrollBar;

    @FindBy(id = "filter_data_filter_3547")
    WebElement processorFilter;

    @FindBy(css = "div[class='mCSB_draggerContainer']")
    WebElement processorFilterScrollDown;



    public void otvoriNalogButtonClick() {
        //wdWait.until(ExpectedConditions.elementToBeClickable(otvoriNalogButton)).click();
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", otvoriNalogButton);
    }

    public void prijaviSeButtonClick() {
      //  wdWait.until(ExpectedConditions.elementToBeClickable(prijaviSeButton)).click();
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", prijaviSeButton);
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
                wdWait.until(ExpectedConditions.elementToBeClickable(getSubCategory));
                getSubCategory.click();
            }
        else if (subCategory == "sporeti")
            {
                wdWait.until(ExpectedConditions.elementToBeClickable(getSubCategorySporeti));
                getSubCategorySporeti.click();
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
        else if (productGroup == "2-u-1laptopovi")
        {
            wdWait.until(ExpectedConditions.elementToBeClickable(getProductGroup1)).click();;
        }
        else if (productGroup == "elektricniSporeti")
        {
            wdWait.until(ExpectedConditions.elementToBeClickable(getProductGroupElektricniSporeti)).click();;
        }

    }

}
