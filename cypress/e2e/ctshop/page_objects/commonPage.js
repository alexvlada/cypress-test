import { onAddToBasketPopupPage } from "./addToBasketPopupPage";
import { onBasketPage } from "./basketPage";
import { onProductPage } from "./productPage";
import { onProductsPage } from "./productsPage";

const baseUrl = 'https://www.ctshop.rs/'
const checkboxEl = "[type='checkbox']";
const laptopTabletMainMenu = 'laptopovi-tableti';
const laptopRacunariSubMenu = 'a[href*="laptop-racunari"]';
const laptopovi = 'a[href*="laptopovi"]';
//const laptopName = 'Lenovo Legion 5 Pro 16ARX8 (82WM00D0RM) gejmerski laptop 16" WQXGA AMD Ryzen 7 7745HX 32GB 1TB SSD GeForce RTX4060 sivi';
const laptopName = 'Asus VivoBook 15 M1502YA-BQ161 laptop 15.6" FHD AMD Ryzen 7 7730U 16GB 512GB SSD Radeon Graphics srebrni'


const sporeti = 'a[href*="sporeti"]';
const elektricniSporeti = 'a[href*="elektricni-sporeti"]';
const sporetName = 'Gorenje GEC5C61WG staklokeramički šporet';
const sporetName1 = 'Gorenje GEIT6C60XPG staklokeramički šporet';

export class CommonPage {

    getRandomNumber() {
        //return Math.random().toString().substr(2, 5) 
        return Cypress.env('randomNumber')
      
    }

    getBaseUrl() {
        return ('https://www.ctshop.rs/')
    }

    getGlobalLaptopName() {
       return (Cypress.myGlobalLaptopName)
    }


    getRegisterLink() {
        return cy.contains('Registaracija')  
    }

    getLoginLink() {
        return cy.contains('Prijava')   
    }

    searchProduct(searchCriteria,searchResult, expectedNumberOfResults) {
     
        cy.get('#search-input-header').clear({ force: true }).type(searchCriteria, { force: true })
      //  cy.get('#search-input-header').clear().type(searchCriteria, { force: true }).type('{enter}')
        cy.get("#search-submit-header").click({ force: true })
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


    getToProduct(product, option) {

        // option 1 - High Ranking Product
        // option 2 - 1st listed

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

        if (option == 1) 
          {
            onProductsPage.getHighRankingProduct()
          }
        else
          {
            onProductsPage.getDisplayedProducts().eq(0).then(($el) => {    
            const href = $el.attr('href');
            cy.log(href)
            cy.visit(baseUrl+href)
            })
          }          
          
    }

    getCurrentAmount(expectedAmount) {
        cy.get('span[class="sharkskin-cart-items"]').then(($el) => {
            let amount = parseInt($el.text());
            cy.log(amount)
            cy.wrap(amount).should('be.equal',expectedAmount)  
            })            
    }
    
    addProductToBasket(product, continueShopping) {
       
        cy.wait(1000)
        onProductPage.getAddToBusketButton().click()  
        
        if (continueShopping == 1)
          {
            onAddToBasketPopupPage.getContinueShopingButton().click()
          } 
        else
          {  
            onAddToBasketPopupPage.getGoToBasketButton().click({force: true})
            cy.wait(1000)
            onBasketPage.getBasketTitle()
            onBasketPage.basketTabsStatuses('korpa')
            
            if (product == 'laptop')
            {
                onBasketPage.getProductName().should('contain',laptopName) 
            }
            else if (product == 'sporet')
            {
                onBasketPage.getProductName(1).should('contain',sporetName) 
            }
          }

    }

    getBusketButton() {
        return cy.get('img[alt="percent"]')
    }

}
export const onCommonPage = new CommonPage()