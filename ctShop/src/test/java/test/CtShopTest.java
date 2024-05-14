package test;

import base.BaseTest;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CtShopTest extends BaseTest {
    CtShopHomePage ctShopHomePage;
    CtShopRegistrationPage ctShopRegistrationPage;
    CtShopLoginPage ctShopLoginPage;
    MailinatorHomePage mailinatorHomePage;
    MailinatorInboxPage mailinatorInboxPage;
    MailinatorCtShopEmailPage mailinatorCtShopEmailPage;
    MyProfilePage myProfilePage;
    LaptopsPage laptopsPage;
    LaptopPage laptopPage;
    AddToBasketPopupPage addToBasketPopupPage;
    BasketPage basketPage;
    CommonPage commonPage;

    ElektricniSporetiPage elektricniSporetiPage;

    SporetPage sporetPage;


    Random random = new Random();
    String firstName = "Fizicko";
    String lastName = "Lice";
    String username = firstName.toLowerCase()+lastName.toLowerCase() + random.nextInt(999999);
    String password = "Getitnow2024!";
    String emailAddress = username+"@mailinator.com";
    String mailinatorUrl = "https://www.mailinator.com/";

    String successfulRegistrationMessageText = "Hvala na registraciji. Molimo proverite email i aktivirajte Vaš nalog.";
    String successfulActivationMessageText = "Uspešno ste aktivirali Vaš nalog. Možete se ulogovati na sistem.";

    String userProfileContactInformation = "Dobrodošli, Fizicko Lice!";//"Fizicko Lice\nE: "+username+"@mailinator.com\nT:\n\nPromeni Lozinku";

    String dangerAlertMessageText = "Uneta email adresa i lozinka se ne poklapaju.";

    String basketTitle = "Proizvod je dodat u korpu";
    String productName = "Lenovo Yoga 7 14ARP8 (82YM005HYA) 2u1 laptop 14\" 2.8K OLED touch AMD Ryzen 7 7735U 16GB 1TB SSD Radeon 680M Win11 Pro sivi";

    String productName2u1 = "Lenovo Yoga 7 14ARP8 (82YM005DYA) 2u1 laptop 14\" WUXGA touch AMD Ryzen 5 7535U 16GB 512GB SSD Radeon Graphics Win11 sivi";

    String productNameLegion = "Asus Zenbook 14 UM3402YAR-KP521W laptop 14\" WQXGA AMD Ryzen 5 7530U 16GB 1TB SSD Radeon Graphics Win11 crni";

    String productNameSporet = "Gorenje GEC5C61WG staklokeramički šporet";

    String emptyBasketMessageText = "Vaša korpa je trenutno prazna.";

    @Before
    public void setUpTest() {
        ctShopHomePage = new CtShopHomePage();
        ctShopRegistrationPage = new CtShopRegistrationPage();
        ctShopLoginPage = new CtShopLoginPage();
        mailinatorHomePage = new MailinatorHomePage();
        mailinatorInboxPage = new MailinatorInboxPage();
        mailinatorCtShopEmailPage = new MailinatorCtShopEmailPage();
        myProfilePage = new MyProfilePage();
        laptopsPage = new LaptopsPage();
        laptopPage = new LaptopPage();
        addToBasketPopupPage = new AddToBasketPopupPage();
        basketPage = new BasketPage();
        commonPage = new CommonPage();
        elektricniSporetiPage = new ElektricniSporetiPage();
        sporetPage = new SporetPage();
    }

    @Test
    public void _01_successfulRegistrationTest() throws InterruptedException {

        ctShopHomePage.otvoriNalogButtonClick();
        ctShopRegistrationPage.pravnoLiceSelectListCheckFalse();
        ctShopRegistrationPage.firstNameInputFieldSendKeys(firstName);
        ctShopRegistrationPage.lastNameInputFieldSendKeys(lastName);
        ctShopRegistrationPage.emailAddressInputFieldSendKeys(emailAddress);
        ctShopRegistrationPage.passwordInputFieldSendKeys(password);
        ctShopRegistrationPage.confirmPasswordInputFieldSendKeys(password);
        ctShopRegistrationPage.isSubscribedCheckboxUncheck();
        ctShopRegistrationPage.rememberMeCheckboxUncheck();
        ctShopRegistrationPage.sendButtonClick();
        //ctShopLoginPage.switchToNewTab(1);
        assertTrue(ctShopLoginPage.successfulRegistrationMessageIsDisplayed());
        assertEquals(successfulRegistrationMessageText, ctShopLoginPage.successfulRegistrationMessageGetText());
        driver.get(mailinatorUrl);
        mailinatorHomePage.mailinatorInputFieldSendKeys(username);
        mailinatorHomePage.milinatorInputFieldSendKeyboardKeys(Keys.ENTER);
        mailinatorInboxPage.haloOglasiAktivacioniMailClick();
        mailinatorCtShopEmailPage.switchFocusToIframeBody();
        mailinatorCtShopEmailPage.aktivirajNalogButtonClick();
        ctShopLoginPage.switchToNewTab(1);
        assertTrue(ctShopLoginPage.successfulRegistrationMessageIsDisplayed());
        assertEquals(successfulActivationMessageText, ctShopLoginPage.successfulRegistrationMessageGetText());
        ctShopLoginPage.emailOrUserNameInputFieldSendKeys(emailAddress);
        ctShopLoginPage.passwordInputFieldSendKeys(password);
        ctShopLoginPage.rememberMeCheckBoxUnchecked();
        ctShopLoginPage.sendButtonClick();
        assertTrue(myProfilePage.profileTitleIsDisplayed());
        assertEquals("Moj nalog", myProfilePage.profileTitleGetText());
        assertTrue(myProfilePage.contactInformationIsDisplayed());
        assertEquals(userProfileContactInformation, myProfilePage.contactInformationGetText());
        assertTrue(firstName, myProfilePage.firstnameIsDisplayed());
        assertEquals(firstName, myProfilePage.firstnameGetValue());
        assertTrue(lastName, myProfilePage.lastnameIsDisplayed());
        assertEquals(lastName, myProfilePage.lastnameGetValue());
        assertTrue(emailAddress, myProfilePage.emailIsDisplayed());
        assertEquals(emailAddress, myProfilePage.emailGetValue());
        System.out.println(emailAddress);
        System.out.println(password);
        CommonPage.GlobalVariables.username = emailAddress;
        CommonPage.GlobalVariables.password = password;
    }

    @Test
    public void _02_loginWrongCredentials() throws InterruptedException {

        ctShopHomePage.prijaviSeButtonClick();
        ctShopLoginPage.emailOrUserNameInputFieldSendKeys(CommonPage.GlobalVariables.username);
        ctShopLoginPage.passwordInputFieldSendKeys("fdsfwerwerffsdfdsfsdg");
        ctShopLoginPage.rememberMeCheckBoxUnchecked();
        ctShopLoginPage.sendButtonClick();
        assertTrue(ctShopLoginPage.dangerAlertIsDisplayed());
        assertEquals(dangerAlertMessageText, ctShopLoginPage.dangerAlertGetText());
    }

    @Test
    public void _03_getToProductPriceAndRatingStatus() throws InterruptedException {
        Double oldPrice = null;
        Double newPrice = null;
        Integer raitingNumber = null;
        ctShopHomePage.prijaviSeButtonClick();
        ctShopLoginPage.emailOrUserNameInputFieldSendKeys(CommonPage.GlobalVariables.username);
        ctShopLoginPage.passwordInputFieldSendKeys(CommonPage.GlobalVariables.password);
        ctShopLoginPage.rememberMeCheckBoxUnchecked();
        ctShopLoginPage.sendButtonClick();
        assertTrue(ctShopLoginPage.profileTitleIsDisplayed());
        ctShopHomePage.mainMenuDropDownHover();
        ctShopHomePage.getCategoryHover("laptopoviTableti");
        ctShopHomePage.getSubCategoryClick("laptopRacunari");
        ctShopHomePage.getProductGroupClick("2-u-1laptopovi");
        laptopsPage.lenovoCheckboxClick();
        //laptopsPage.processorCheckboxClick();
        laptopPage.laptopClick("2u1");
        assertTrue(laptopPage.productNameIsDisplayed());
        assertEquals(productName2u1,laptopPage.productNameGetText());
        assertTrue(laptopPage.oldPriceIsDisplayed());
        assertTrue(laptopPage.newPriceIsDisplayed());
        oldPrice = Double.parseDouble(laptopPage.oldPriceGetText());
        newPrice = Double.parseDouble(laptopPage.newPriceGetText());
        System.out.println("Old price: " +oldPrice);
        System.out.println("New price: " +newPrice);
        assertTrue("New price is less than old price", newPrice < oldPrice);
        //assertEquals("66.666",laptopPage.oldPriceGetText());
        //assertEquals("59.999",laptopPage.newPriceGetText());
        laptopPage.ocenaTabClick();
        laptopPage.ratingNumberisDisplayed();
        raitingNumber = Integer.parseInt(laptopPage.ratingNumberGetText());
        assertTrue("Raiting number has to be greater than 4", raitingNumber > 4);

    }

    @Test
    public void _04_addAndRemoveFromBasket() throws InterruptedException {
        ctShopHomePage.prijaviSeButtonClick();
        ctShopLoginPage.emailOrUserNameInputFieldSendKeys(CommonPage.GlobalVariables.username);
        ctShopLoginPage.passwordInputFieldSendKeys(CommonPage.GlobalVariables.password);
        ctShopLoginPage.rememberMeCheckBoxUnchecked();
        ctShopLoginPage.sendButtonClick();
        assertTrue(ctShopLoginPage.profileTitleIsDisplayed());
        ctShopHomePage.mainMenuDropDownHover();
        ctShopHomePage.getCategoryHover("laptopoviTableti");
        ctShopHomePage.getSubCategoryClick("laptopRacunari");
        ctShopHomePage.getProductGroupClick("laptopovi");
        laptopsPage.lenovoCheckboxClick();
        laptopsPage.processorCheckboxClick();
        laptopPage.laptopClick("legion");
        assertTrue(commonPage.basketNumberOfItemsIsDisplayed());
        assertEquals(0,Integer.parseInt(commonPage.basketNumberOfItemsGetText()));
        assertTrue(laptopPage.productNameIsDisplayed());
        assertEquals(productNameLegion,laptopPage.productNameGetText());
        laptopPage.addToBasketButtonClick();
        assertEquals(1,Integer.parseInt(commonPage.basketNumberOfItemsGetText()));
        assertTrue(addToBasketPopupPage.addToBasketPopupIsDisplayed());
        assertEquals(basketTitle,addToBasketPopupPage.addToBasketPopupGetText());
        addToBasketPopupPage.goToBasketButtonClick();
        assertTrue(basketPage.activeTabIsDisplayed());
        assertEquals("1. Korpa",basketPage.activeTabGetText());
        assertTrue(basketPage.basketTitleIsDisplayed());
        assertEquals("Korpa",basketPage.basketTitleGetText());
        assertTrue(basketPage.productNameIsDisplayed());
        assertEquals(productNameLegion.toUpperCase(),basketPage.productNameGetText(1));
        basketPage.confirmButtonClick();
        assertEquals("2. Naručivanje",basketPage.activeTabGetText());
        basketPage.backButtonClick();
        basketPage.removeItemButtonClick();
        assertTrue(basketPage.emptyBasketMessageIsDisplayed());
        assertEquals(emptyBasketMessageText,basketPage.emptyBasketMessageGetText());
        assertEquals(0,Integer.parseInt(commonPage.basketNumberOfItemsGetText()));
    }

    @Test
    public void _05_addToBasketIncreaseDecreaseAmountByArrow() throws InterruptedException {
        ctShopHomePage.prijaviSeButtonClick();
        ctShopLoginPage.emailOrUserNameInputFieldSendKeys(CommonPage.GlobalVariables.username);
        ctShopLoginPage.passwordInputFieldSendKeys(CommonPage.GlobalVariables.password);
        ctShopLoginPage.rememberMeCheckBoxUnchecked();
        ctShopLoginPage.sendButtonClick();
        assertTrue(ctShopLoginPage.profileTitleIsDisplayed());
        ctShopHomePage.mainMenuDropDownHover();
        ctShopHomePage.getCategoryHover("laptopoviTableti");
        ctShopHomePage.getSubCategoryClick("laptopRacunari");
        ctShopHomePage.getProductGroupClick("laptopovi");
        laptopsPage.lenovoCheckboxClick();
        laptopsPage.processorCheckboxClick();
        laptopPage.laptopClick("legion");
        assertTrue(commonPage.basketNumberOfItemsIsDisplayed());
        assertEquals(0,Integer.parseInt(commonPage.basketNumberOfItemsGetText()));
        assertTrue(laptopPage.productNameIsDisplayed());
        assertEquals(productNameLegion,laptopPage.productNameGetText());
        laptopPage.addToBasketButtonClick();
        assertEquals(1,Integer.parseInt(commonPage.basketNumberOfItemsGetText()));
        assertTrue(addToBasketPopupPage.addToBasketPopupIsDisplayed());
        assertEquals(basketTitle,addToBasketPopupPage.addToBasketPopupGetText());
        addToBasketPopupPage.goToBasketButtonClick();
        assertTrue(basketPage.activeTabIsDisplayed());
        assertEquals("1",basketPage.currentAmountGetValue("1"));
        basketPage.arrowUpButtonClick();
        assertEquals("2",basketPage.currentAmountGetValue("2"));
        basketPage.arrowDownButtonClick();
        assertEquals("1",basketPage.currentAmountGetValue("1"));
    }

    @Test
    public void _06_addToBasketChangeAmountByInputField() throws InterruptedException {
        ctShopHomePage.prijaviSeButtonClick();
        ctShopLoginPage.emailOrUserNameInputFieldSendKeys(CommonPage.GlobalVariables.username);
        ctShopLoginPage.passwordInputFieldSendKeys(CommonPage.GlobalVariables.password);
        ctShopLoginPage.rememberMeCheckBoxUnchecked();
        ctShopLoginPage.sendButtonClick();
        assertTrue(ctShopLoginPage.profileTitleIsDisplayed());
        ctShopHomePage.mainMenuDropDownHover();
        ctShopHomePage.getCategoryHover("laptopoviTableti");
        ctShopHomePage.getSubCategoryClick("laptopRacunari");
        ctShopHomePage.getProductGroupClick("laptopovi");
        laptopsPage.lenovoCheckboxClick();
        laptopsPage.processorCheckboxClick();
        laptopPage.laptopClick("legion");
        assertTrue(commonPage.basketNumberOfItemsIsDisplayed());
        assertEquals(0,Integer.parseInt(commonPage.basketNumberOfItemsGetText()));
        assertTrue(laptopPage.productNameIsDisplayed());
        assertEquals(productNameLegion,laptopPage.productNameGetText());
        laptopPage.addToBasketButtonClick();
        assertEquals(1,Integer.parseInt(commonPage.basketNumberOfItemsGetText()));
        assertTrue(addToBasketPopupPage.addToBasketPopupIsDisplayed());
        assertEquals(basketTitle,addToBasketPopupPage.addToBasketPopupGetText());
        addToBasketPopupPage.goToBasketButtonClick();
        assertTrue(basketPage.activeTabIsDisplayed());
        basketPage.amountInputFieldSendKeys("5");
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertEquals("5",basketPage.currentAmountGetValue());
    }

    @Test
    public void _07_productSearchOneMatchingResult() throws InterruptedException {
        String searchCriteria = "8C9R4EA/WIN11";
        ctShopHomePage.prijaviSeButtonClick();
        ctShopLoginPage.emailOrUserNameInputFieldSendKeys(CommonPage.GlobalVariables.username);
        ctShopLoginPage.passwordInputFieldSendKeys(CommonPage.GlobalVariables.password);
        ctShopLoginPage.rememberMeCheckBoxUnchecked();
        ctShopLoginPage.sendButtonClick();
        assertTrue(ctShopLoginPage.profileTitleIsDisplayed());
        commonPage.searchInputFieldSendKeys(searchCriteria);
        assertTrue(commonPage.searchResultIsDisplayed());
        assertEquals(1,Integer.parseInt(commonPage.searchResultGetText()));
    }

    @Test
    public void _08_productSearchMoreMatchingResults() throws InterruptedException {
        String searchCriteria = "Lenovo IdeaPad slim 3";
        ctShopHomePage.prijaviSeButtonClick();
        ctShopLoginPage.emailOrUserNameInputFieldSendKeys(CommonPage.GlobalVariables.username);
        ctShopLoginPage.passwordInputFieldSendKeys(CommonPage.GlobalVariables.password);
        ctShopLoginPage.rememberMeCheckBoxUnchecked();
        ctShopLoginPage.sendButtonClick();
        assertTrue(ctShopLoginPage.profileTitleIsDisplayed());
        commonPage.searchInputFieldSendKeys(searchCriteria);
        assertTrue(commonPage.searchResultIsDisplayed());
        assertTrue("More than 1 results", Integer.parseInt(commonPage.searchResultGetText()) > 1);
    }

    @Test
    public void _09_productSearchNoMatching() throws InterruptedException {
        String searchCriteria = "qweqewrerytugyugjhgtyufd";
        String noResultsMessage = "Nažalost nismo pronašli ništa što bi se podudarilo sa "+'"'+searchCriteria+'"';
        ctShopHomePage.prijaviSeButtonClick();
        ctShopLoginPage.emailOrUserNameInputFieldSendKeys(CommonPage.GlobalVariables.username);
        ctShopLoginPage.passwordInputFieldSendKeys(CommonPage.GlobalVariables.password);
        ctShopLoginPage.rememberMeCheckBoxUnchecked();
        ctShopLoginPage.sendButtonClick();
        assertTrue(ctShopLoginPage.profileTitleIsDisplayed());
        commonPage.searchInputFieldSendKeys(searchCriteria);
        assertTrue(commonPage.searchResultIsNotDisplayed());
        assertEquals(noResultsMessage,commonPage.noSearchResultGetText());
    }

    @Test
    public void _10_addMoreProductsToBasket() throws InterruptedException {
        ctShopHomePage.prijaviSeButtonClick();
        ctShopLoginPage.emailOrUserNameInputFieldSendKeys(CommonPage.GlobalVariables.username);
        ctShopLoginPage.passwordInputFieldSendKeys(CommonPage.GlobalVariables.password);
        ctShopLoginPage.rememberMeCheckBoxUnchecked();
        ctShopLoginPage.sendButtonClick();
        assertTrue(ctShopLoginPage.profileTitleIsDisplayed());
        ctShopHomePage.mainMenuDropDownHover();
        ctShopHomePage.getCategoryHover("laptopoviTableti");
        ctShopHomePage.getSubCategoryClick("laptopRacunari");
        ctShopHomePage.getProductGroupClick("laptopovi");
        laptopsPage.lenovoCheckboxClick();
        laptopsPage.processorCheckboxClick();
        laptopPage.laptopClick("legion");
        assertTrue(laptopPage.productNameIsDisplayed());
        assertTrue(commonPage.basketNumberOfItemsIsDisplayed());
        assertEquals(0,Integer.parseInt(commonPage.basketNumberOfItemsGetText()));
        laptopPage.addToBasketButtonClick();
        assertEquals(1,Integer.parseInt(commonPage.basketNumberOfItemsGetText()));
        addToBasketPopupPage.continueShoppingButtonClick();
        ctShopHomePage.mainMenuDropDownHover();
        ctShopHomePage.getCategoryHover("belaTehnika");
        ctShopHomePage.getSubCategoryClick("sporeti");
        ctShopHomePage.getProductGroupClick("elektricniSporeti");
        elektricniSporetiPage.gorenjeCheckboxClick();
        elektricniSporetiPage.staklokeramikaCheckboxClick();
        sporetPage.sporetClick();
        commonPage.addToBasketButtonClick();
        assertEquals(2,Integer.parseInt(commonPage.basketNumberOfItemsGetText()));
        addToBasketPopupPage.goToBasketButtonClick();
        assertTrue(basketPage.productNameIsDisplayed());
        System.out.println("Position 1: "+basketPage.productNameGetText(1));
        System.out.println("Position 2: "+basketPage.productNameGetText(2));
        assertEquals(basketPage.productNameGetText(1),productNameLegion.toUpperCase());
        assertEquals(basketPage.productNameGetText(2),productNameSporet.toUpperCase());
        basketPage.confirmButtonClick();
    }

    @Test
    public void _11_getHighRankingProduct() throws InterruptedException {
        ctShopHomePage.prijaviSeButtonClick();
        ctShopLoginPage.emailOrUserNameInputFieldSendKeys(CommonPage.GlobalVariables.username);
        ctShopLoginPage.passwordInputFieldSendKeys(CommonPage.GlobalVariables.password);
        ctShopLoginPage.rememberMeCheckBoxUnchecked();
        ctShopLoginPage.sendButtonClick();
        assertTrue(ctShopLoginPage.profileTitleIsDisplayed());
        ctShopHomePage.mainMenuDropDownHover();
        ctShopHomePage.getCategoryHover("laptopoviTableti");
        ctShopHomePage.getSubCategoryClick("laptopRacunari");
        ctShopHomePage.getProductGroupClick("laptopovi");
        laptopsPage.lenovoCheckboxClick();
        //laptopsPage.processorCheckboxClick();
        Thread.sleep(3000);
        laptopsPage.getHighRankingProduct();
    }

}
