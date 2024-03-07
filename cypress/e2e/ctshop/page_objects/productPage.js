

export class ProductPage {

    getAddToBusketButton() {
        return cy.get('#product-addtocart-button')
    }

    getOldPriceVisible() {
        cy.get('span[class=" strikethrough  old-online-price"]').should('be.visible')
    }

    getNewPriceVisible() {
        cy.get('span[class="price new-online-price"]').should('be.visible')
    }
    
    getProductDetails() {
      return  cy.get('div[class="features"]')
    }

    getPriceComparationStatus() {
    cy.get('span[class=" strikethrough  old-online-price"]').then(($el) => {
        const oldPrice = parseInt($el.text());
        cy.log(oldPrice)
    
        cy.get('span[class="price new-online-price"]').then(($el) => {
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