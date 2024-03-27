
const oldPriceAfterDiscount = 'span[class="price-after-discount"]'
const oldOnlinePrice = 'span[class=" strikethrough  old-online-price"]' 
const newOnLinePrice = 'span[class="price new-online-price"]'
const newPrice = 'span[class="new-price"]' 
const productDetails = 'div[class="features"]'

export class ProductPage {

    getAddToBusketButton() {
        return cy.get('#product-addtocart-button')
    }

    getOldPriceVisible() {

        cy.get('body').then($body => {
            if ($body.find('span.strikethrough.old-online-price').is('.visible')) {
              // The element is visible, do something here
              cy.get(oldOnlinePrice).should('be.visible')
            } else {
              // The element is not visible, do something else here
              cy.get(oldPriceAfterDiscount).should('be.visible')
            }
          });   
    }

    getNewPriceVisible() {
        //cy.get(newPrice).should('be.visible')
        cy.get('body').then($body => {
            if ($body.find('span.strikethrough.old-online-price').is('.visible')) {
              // The element is visible, do something here
              cy.get(newOnLinePrice).should('be.visible')
            } else {
              // The element is not visible, do something else here
              cy.get(newPrice).should('be.visible')
            }
          });   
    }
    
    getProductDetails() {
      return  cy.get(productDetails)
    }

    checkProductDetails() {

        cy.get(productDetails).eq(0).then($input => {
            this.getProductDetails().eq(0).should('contain','Tip proizvoda')
            const val = $input.val();
            if (val === 'Laptop računari') {
              // Perform actions if the value matches
              this.getProductDetails().eq(0).should('contain','Laptop računari')
            } else {
              this.getProductDetails().eq(0).should('contain','Hibridni (2-u-1)')
              // Perform actions if the value does not match
            }
          });
    }


    getPriceComparationStatus() {


        cy.get('body').then($body => {
            if ($body.find('span.strikethrough.old-online-price').is('.visible')) {
              // The element is visible, do something here
             // oldPrice = oldOnlinePrice

              cy.get(oldOnlinePrice).then(($el) => {
                const oldPrice = parseInt($el.text());
                cy.log(oldPrice)
            
                cy.get(newPrice).then(($el) => {
                    const newPrice = parseInt($el.text());
                    cy.log(newPrice)
                    cy.wrap(oldPrice).should('be.greaterThan',newOnLinePrice)
                    })
                })

            } else {
              // The element is not visible, do something else here
             // oldPrice = oldPriceAfterDiscount

              cy.get(oldPriceAfterDiscount).then(($el) => {
                const oldPrice = parseInt($el.text());
                cy.log(oldPrice)
            
                cy.get(newPrice).then(($el) => {
                    const newPrice = parseInt($el.text());
                    cy.log(newPrice)
                    cy.wrap(oldPrice).should('be.greaterThan',newPrice)
                    })
                })

            }
          });   
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