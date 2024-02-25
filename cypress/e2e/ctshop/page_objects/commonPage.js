import { onAddToBasketPopupPage } from "./addToBasketPopupPage";
import { onBasketPage } from "./basketPage";
import { onProductPage } from "./productPage";
import { onProductsPage } from "./productsPage";

const baseUrl = 'https://www.ctshop.rs/'
const checkboxEl = "[type='checkbox']";
const laptopTabletMainMenu = 'laptopovi-tableti';
const laptopRacunariSubMenu = 'a[href*="laptop-racunari"]';
const laptopovi = 'a[href*="laptopovi"]';
//const laptopName = 'Lenovo IdeaPad 3 15ABA7 (82RN00DWYA) laptop 15.6" FHD AMD Ryzen 7 5825U 8GB 512GB SSD Radeon Graphics sivi';
const laptopName = 'Lenovo Yoga 7 14ARP8 (82YM005HYA) 2u1 laptop 14" 2.8K OLED touch AMD Ryzen 7 7735U 16GB 1TB SSD Radeon 680M Win11 Pro sivi';

const sporeti = 'a[href*="sporeti"]';
const elektricniSporeti = 'a[href*="elektricni-sporeti"]';
const sporetName = 'Gorenje GEC5C61WG staklokeramički šporet';
const sporetName1 = 'Gorenje GEIT6C60XPG staklokeramički šporet';

export class CommonPage {

    getRandomNumber() {
        //return Math.random().toString().substr(2, 5) 
        return Cypress.env('randomNumber')
      
    }

    getRegisterLink() {
        return cy.contains('Otvori nalog')   
    }

    getLoginLink() {
        return cy.contains('Prijavi se')   
    }

    searchProduct(searchCriteria,searchResult, expectedNumberOfResults) {
        cy.get('#search-input-header').type(searchCriteria).type('{enter}')
        cy.wait(1000)    
        
        if (expectedNumberOfResults == 0)
        { 
            cy.get('div[class="text"]').should('contain','Nažalost nismo pronašli ništa što bi se podudarilo sa '+'"'+searchCriteria+'"')
        }

        else

        {

                cy.get('div[class="col-xs-12 col-sm-3 item"]').then(($num) => {
                    let count = parseInt($num.length);
                    cy.log(count)
                    expect(count).to.eq(expectedNumberOfResults)
                }) 

                if (expectedNumberOfResults == 1)
                    {
                        cy.get('a[class = "product-image ajax-loading-stripes"]').eq(0).then(($el) => {    
                            const href = $el.attr('href');
                            cy.log(href)
                            cy.visit(href)
                        }) 
                
                        cy.get('div[class = "product-name"]').should('contain',searchResult) 
                    } 
        }                

    } 


    getToProduct(product) {

        let mainManu = ''
        let subMenu = ''
        let productGroup = ''
        let checkBox1 = ''
        let checkBox2 = ''

        if (product == 'laptop') 
        {
            mainManu = 'laptopovi-tableti'
            subMenu = 'a[title = "Laptop računari"]'
            productGroup = 'a[title = "Laptopovi"]'
            checkBox1 = '199'
            checkBox2 = '3547'
        }
        else if (product == 'sporet')
        {
            mainManu = 'bela-tehnika'
            subMenu = 'a[title = "Šporeti"]'
            productGroup = 'a[title = "Električni šporeti"]'
            checkBox1 = '142'
            checkBox2 = '3634'
        }

        cy.visit(baseUrl + mainManu)

        cy.get(subMenu).then(($el) => {    
          const href = $el.attr('href');
          cy.log(href)
          cy.visit(baseUrl+href)
        })
        
        cy.get(productGroup).then(($el) => {    
          const href = $el.attr('href');
          cy.log(href)
          cy.visit(baseUrl+href)
        })  

        cy.get(checkboxEl).check(checkBox1, {force: true})
        cy.get(checkboxEl).check(checkBox2, {force: true})

        onProductsPage.getDisplayedProducts().eq(0).then(($el) => {    
          const href = $el.attr('href');
          cy.log(href)
          cy.visit(baseUrl+href)
        })  
           
    }

    getCurrentAmount(expectedAmount) {
        cy.get('span[class="sharkskin-cart-items"]').then(($el) => {
            let amount = parseInt($el.text());
            cy.log(amount)
            cy.wrap(amount).should('be.equal',expectedAmount)  
            })            
    }
    
    addProductToBasket(product) {
        onProductPage.getAddToBusketButton().click()
        cy.wait(1000)
        onAddToBasketPopupPage.getGoToBasketButton().click({force: true})
        cy.wait(1000)
        onBasketPage.getBasketTitle()
        onBasketPage.basketTabsStatuses('korpa')
        
        if (product == 'sporet')
        {
            onBasketPage.getProductName().should('contain',sporetName) 
        }
        else if (product == 'laptop')
        {
            onBasketPage.getProductName().should('contain',laptopName) 
        }

    }

}
export const onCommonPage = new CommonPage()