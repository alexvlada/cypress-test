import { onBasketPage } from "./page_objects/basketPage"
import { onCommonPage } from "./page_objects/commonPage"
import { onCtShopLoginPage } from "./page_objects/ctShopLoginPage"
import { onCtShopRegistrationPage } from "./page_objects/ctShopRegistrationPage"
import { onMailinatorCtShopEmailPage } from "./page_objects/mailinatorCtShopEmailPage"
import { onMailinatorHomePage } from "./page_objects/mailinatorHomePage"
import { onMailinatorInboxPage } from "./page_objects/mailinatorInboxPage"
import { onProductPage } from "./page_objects/productPage"

const baseUrl = 'https://www.ctshop.rs/'
const mailinatorUrl = 'https://www.mailinator.com/v4/public/inboxes.jsp'
const ctShopLoginPage = 'https://www.ctshop.rs/customer/account/login'
let randomNumber = onCommonPage.getRandomNumber() 
const eMailAddress = 'fizickolice' + randomNumber
const eMailAddressPl = 'pravnolice' + randomNumber
let eMailAddressActivate = ''
const eMailAddressMailinator = eMailAddress + '@mailinator.com'
const eMailAddressMailinatorPl = eMailAddressPl + '@mailinator.com'
const ime = 'Fizicko'
const prezime = 'Lice'
const PlIme = 'Pravno'
const PlPrezime = 'Lice'
const pib = '100000104'
const password = 'Getitnow2024!'
const successfulRegistrationMessageText = 'Hvala na registraciji. Molimo proverite email i aktivirajte Vaš nalog.'
const successfulActivationMessageText = 'Uspešno ste aktivirali Vaš nalog. Možete se ulogovati na sistem.'
const wrongCredentialLoginMessageText = 'Uneta email adresa i lozinka se ne poklapaju.'
const laptop = 'laptop'
const sporet = 'sporet'


context('CT shop - test suit', () => {
     
    before(() => {
      
    }) 
  
    beforeEach(() => {

    })

    afterEach(() => {
     
    })
  
    it('Create account - Fizicko lice', () => { 
    
        cy.visit(baseUrl)
        onCommonPage.getRegisterLink().click()

        cy.wait(5000)
        
        onCtShopRegistrationPage.checkPravnoLice(0)
        onCtShopRegistrationPage.pravnoLiceNo()      
        onCtShopRegistrationPage.getIme().type(ime, { force: true })
        onCtShopRegistrationPage.getPrezime().type(prezime, { force: true })
        onCtShopRegistrationPage.getEmailAddress().type(eMailAddressMailinator, { force: true })
        cy.wait(1000)
        onCtShopRegistrationPage.getCheckBox().eq(0).uncheck();

        onCtShopRegistrationPage.getPassword().type(password, { force: true })
        onCtShopRegistrationPage.getConfirmPassword().type(password, { force: true })
        onCtShopRegistrationPage.sendButton().click()
        cy.wait(5000)
        onCtShopLoginPage.getSuccessfulActivationRegistrationMessage().should('contain',successfulRegistrationMessageText)
    
    })

    it('Activate account - Fizicko lice', () => { 
      
      cy.visit(mailinatorUrl)
      cy.wait(5000)
      onMailinatorHomePage.getMailinatorEmailSearchField().type(eMailAddress, { force: true })
      cy.wait(5000)
      onMailinatorHomePage.getGoButton().click()
      cy.wait(10000)

      onMailinatorInboxPage.getAktivacijaLink().click()
      cy.wait(5000)
      
      onMailinatorCtShopEmailPage.getLinkTab().click()
      onMailinatorCtShopEmailPage.getToLink()
      cy.wait(5000)
      //onCtShopLoginPage.getSuccessfulActivationRegistrationMessage().should('contain',successfulActivationMessageText)
    })

    it('Successful account login - Fizicko lice', () => { 
      
      cy.visit(baseUrl)
      onCommonPage.getLoginLink().click()
      cy.wait(5000)
      onCtShopLoginPage.getEmail().type(eMailAddressMailinator, { force: true })
      onCtShopLoginPage.getPassword().type(password, { force: true })
      onCtShopLoginPage.getConfirmButton().click()

      onCtShopLoginPage.getSuccessfulLogin('F')
    })

    it('Wrong credentials - Fizicko lice', () => { 
      
      cy.visit(baseUrl)
      onCommonPage.getLoginLink().click()
      cy.wait(5000)
      onCtShopLoginPage.getEmail().type(eMailAddressMailinator, { force: true })
      onCtShopLoginPage.getPassword().type("fdsfwerwerffsdfdsfsdg", { force: true })
      onCtShopLoginPage.getConfirmButton().click()
      
      onCtShopLoginPage.getWrongCredentialsLoginMessage().should('contain',wrongCredentialLoginMessageText)
    })

    it('Create account - Pravno lice', () => { 
    
      cy.visit(baseUrl)
      onCommonPage.getRegisterLink().click()

      cy.wait(5000)
   
      onCtShopRegistrationPage.getPib().should('not.be.visible')
      onCtShopRegistrationPage.checkPravnoLice(1)
      onCtShopRegistrationPage.pravnoLiceYes()
      onCtShopRegistrationPage.selectPravnoLice()
      onCtShopRegistrationPage.getPib().should('be.visible')
      onCtShopRegistrationPage.getPib().type(pib, { force: true })
      onCtShopRegistrationPage.getIme().type(PlIme, { force: true })
      onCtShopRegistrationPage.getPrezime().type(PlPrezime, { force: true })
      onCtShopRegistrationPage.getEmailAddress().type(eMailAddressMailinatorPl, { force: true })
      cy.wait(5000)
      onCtShopRegistrationPage.getCheckBox().eq(0).uncheck();
      onCtShopRegistrationPage.getPassword().type(password, { force: true })
      onCtShopRegistrationPage.getConfirmPassword().type(password, { force: true })
      onCtShopRegistrationPage.sendButton().click()
      cy.wait(5000)
      onCtShopLoginPage.getSuccessfulActivationRegistrationMessage().should('contain',successfulRegistrationMessageText)
  })

  it('Activate account - Pravno lice', () => { 
      
    cy.visit(mailinatorUrl)
    cy.wait(5000)
    onMailinatorHomePage.getMailinatorEmailSearchField().type(eMailAddressPl, { force: true })
    cy.wait(5000)
    onMailinatorHomePage.getGoButton().click()
    cy.wait(10000)
    onMailinatorInboxPage.getAktivacijaLink().click()
    cy.wait(5000)
    
    onMailinatorCtShopEmailPage.getLinkTab().click()
    onMailinatorCtShopEmailPage.getToLink()
    //onCtShopLoginPage.getSuccessfulActivationRegistrationMessage().should('contain',successfulActivationMessageText)
  })

  it('Successful account login - Pravno lice', () => { 
      
    cy.visit(baseUrl)
    onCommonPage.getLoginLink().click()
    cy.wait(5000)
    onCtShopLoginPage.getEmail().type(eMailAddressMailinatorPl, { force: true })
    onCtShopLoginPage.getPassword().type(password, { force: true })
    onCtShopLoginPage.getConfirmButton().click()

    onCtShopLoginPage.getSuccessfulLogin('P')
  })


  it('Fizicko lice - Product - Price & rating status', () => { 

    const searchProductCriteria = 'GEIT6C60XPG' 
    const searchProductName = 'Gorenje GEIT6C60XPG staklokeramički šporet'
    
    cy.visit(baseUrl)
    //cy.loginCtShopFl('F')
    onCtShopLoginPage.loginToAccount(eMailAddressMailinator,password)

    onCommonPage.searchProduct(searchProductCriteria, searchProductName, 1)

    onProductPage.getOldPriceVisible()
    onProductPage.getNewPriceVisible()
    
    onProductPage.getPriceComparationStatus()

    onProductPage.getProductDetails().should('contain','Tip proizvoda') 
    onProductPage.getProductDetails().should('contain','Samostojeći šporet') 

    onProductPage.getRaitingStatus()
  })

  it('Fizicko lice - Products - Add to and remove from basket', () => { 

    cy.visit(baseUrl)
    //cy.loginCtShopFl('F')
    onCtShopLoginPage.loginToAccount(eMailAddressMailinator,password)
  
    onCommonPage.getToProduct(laptop)
    onCommonPage.getCurrentAmount(0)
    onCommonPage.addProductToBasket(laptop)
    onBasketPage.saveAndContinue()
    onCommonPage.getCurrentAmount(1)
    onBasketPage.removeFromBasket()
    onCommonPage.getCurrentAmount(0)
    
  })

  it('Fizicko lice - Products - Add to basket and increase / decrease amount by arrows', () => { 

    cy.visit(baseUrl)
    //cy.loginCtShopFl('F')
    onCtShopLoginPage.loginToAccount(eMailAddressMailinator,password)
    
    onCommonPage.getToProduct(laptop)
    onCommonPage.addProductToBasket(laptop)
    onBasketPage.increaseAmountByArrow()
    onBasketPage.decreaseAmountByArrow()
  })

  it('Fizicko lice - Products - Add to basket and change amount by input field', () => { 
    
    cy.visit(baseUrl)
    //cy.loginCtShopFl('F')
    onCtShopLoginPage.loginToAccount(eMailAddressMailinator,password)

    onCommonPage.getToProduct(laptop)
    onCommonPage.addProductToBasket(laptop)  
    onBasketPage.changeAmountByInputField(5)
    onBasketPage.saveAndContinue()
  })


  it('Fizicko lice - Products - Get more products', () => { 
    
    cy.visit(baseUrl)
    //cy.loginCtShopFl('F')
    onCtShopLoginPage.loginToAccount(eMailAddressMailinator,password)
    
    onCommonPage.getToProduct(laptop)
    onCommonPage.addProductToBasket(laptop)
    onCommonPage.getCurrentAmount(1)
    onCommonPage.getToProduct(sporet)
    onCommonPage.addProductToBasket(sporet)
    onCommonPage.getCurrentAmount(2)
    onBasketPage.saveAndContinue() 
  })

  it('Fizicko lice - Products - Search - 1 matching', () => { 
    
    const searchProductCriteria = '82RN00DWYA' 
    const searchProductName = 'Lenovo IdeaPad 3 15ABA7 (82RN00DWYA) laptop 15.6" FHD AMD Ryzen 7 5825U 8GB 512GB SSD Radeon Graphics sivi'
    
    cy.visit(baseUrl)
    //cy.loginCtShopFl('F')
    onCtShopLoginPage.loginToAccount(eMailAddressMailinator,password)
    onCommonPage.searchProduct(searchProductCriteria, searchProductName, 1)
  })

  it('Fizicko lice - Products - Search - Multiply matching', () => { 
    const searchProductCriteria = 'Gorenje GEC5C61WG staklokeramički šporet' 
    const searchProductName = '' 

    cy.visit(baseUrl)
    //cy.loginCtShopFl('F')
    onCtShopLoginPage.loginToAccount(eMailAddressMailinator,password)
    onCommonPage.searchProduct(searchProductCriteria, searchProductName, 3)
  })

  it('Fizicko lice - Products - Search - No matching', () => { 
    const searchProductCriteria = 'qweqewrerytugyugjhgtyufd' 
    const searchProductName = ''

    cy.visit(baseUrl)
    //cy.loginCtShopFl('F')
    onCtShopLoginPage.loginToAccount(eMailAddressMailinator,password)
    onCommonPage.searchProduct(searchProductCriteria, searchProductName, 0)
  })

})