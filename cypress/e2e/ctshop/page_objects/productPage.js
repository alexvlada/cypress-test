
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

        const $element = $body.find(oldPriceAfterDiscount);
        if ($element.length > 0 && $element.is(':visible')) 
            {
              cy.get(oldPriceAfterDiscount).should('be.visible')
            } 
        else 
            {
              cy.get(oldOnlinePrice).should('be.visible')
            }
      }); 
    }

    getNewPriceVisible() {

        cy.get('body').then($body => {
        const $element = $body.find(newPrice);
           // if ($body.find('span.strikethrough.old-online-price').is('.visible')) 
            if ($element.length > 0 && $element.is(':visible'))
                {
                  cy.get(newPrice).should('be.visible')
                } 
            else 
                {
                  cy.get(newOnLinePrice).should('be.visible')
                }
          });   
    }
    
    getProductDetails() {
      return  cy.get(productDetails)
    }

    checkProductDetails() {

        cy.get(productDetails).eq(0).then($input => {
            this.getProductDetails().eq(0).should('contain','Tip proizvoda')
            let val = $input.text();
            let val1 = $input.val('attr', 'title');

            cy.get('.features').then(($features) => {
              // $features is the jQuery object representing elements with the class "features"
              const hasText = $features.find('[title="Laptop računari"]').length > 0;
        
              // Use the variable hasText as needed. This does not assert, just checks presence.
              if (hasText) 
                  {
                    // If needed, you can perform actions here or log to the console
                    this.getProductDetails().eq(0).should('contain','Laptop računari')
                  } 
              else 
                  {
                    this.getProductDetails().eq(0).should('contain','Hibridni (2-u-1)')
                  }
            });
    
/*
            if (val === 'Laptop računari' || val == 'Laptop računari') {
              // Perform actions if the value matches
              this.getProductDetails().eq(0).should('contain','Laptop računari')
            } else {
              this.getProductDetails().eq(0).should('contain','Hibridni (2-u-1)')
              // Perform actions if the value does not match
            }
 */
          });
    }


    getPriceComparationStatus() {


        cy.get('body').then($body => {

            const $element = $body.find(oldOnlinePrice);

            if ($element.length > 0 && $element.is(':visible'))
            //if ($body.find('span.strikethrough.old-online-price').is('.visible')) 
              {
                // The element is visible, do something here
              // oldPrice = oldOnlinePrice

                cy.get(oldOnlinePrice).then(($el) => {
                  const oldPrice = parseFloat($el.text());
                  cy.log(oldPrice)
              
                  cy.get(newOnLinePrice).then(($el) => {
                      const newPrice = parseFloat($el.text());
                      cy.log(newPrice)
                      cy.wrap(oldPrice).should('be.greaterThan',newPrice)
                      })
/*
                  cy.get(newPrice).then(($el) => {
                        const newPrice = parseInt($el.text());
                        cy.log(newPrice)
                        cy.wrap(oldPrice).should('be.greaterThan',newOnLinePrice)
                      })    
*/
                  })

                } 
            else 
                {
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