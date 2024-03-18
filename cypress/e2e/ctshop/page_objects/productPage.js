
const oldPrice = 'span[class="price-after-discount"]'  //'span[class=" strikethrough  old-online-price"]' 
const newPrice = 'span[class="new-price"]' //'span[class="price new-online-price"]'  

export class ProductPage {

    getAddToBusketButton() {
        return cy.get('#product-addtocart-button')
    }

    getOldPriceVisible() {
        cy.get(oldPrice).should('be.visible')
    }

    getNewPriceVisible() {
        cy.get(newPrice).should('be.visible')
    }
    
    getProductDetails() {
      return  cy.get('div[class="features"]')
    }

    getPriceComparationStatus() {
    cy.get(oldPrice).then(($el) => {
        const oldPrice = parseInt($el.text());
        cy.log(oldPrice)
    
        cy.get(newPrice).then(($el) => {
              const newPrice = parseInt($el.text());
              cy.log(newPrice)
              cy.wrap(oldPrice).should('be.greaterThan',newPrice)
              })
        })
    }  
    
    getRaitingStatus() {
        cy.get('a[href="#ocene"]').click()

        cy.get('div[class="rating-number "]').then(($el) => {
         const score = parseInt($el.text());
         cy.log(score)
         cy.wrap(score).should('be.greaterThan',4)
         })
    }

}
export const onProductPage = new ProductPage()