const currentAmountEl = 'span[class="sharkskin-cart-items"]';

export class BasketPage {


    basketTabsStatuses(tab) {

        if (tab == 'korpa') 
          {
            cy.get('div[class="col-md-4 active"]').should('contain','1. Korpa')
            cy.get('div[class="col-md-4 "]').should('contain','2. Naručivanje')
            cy.get('div[class="col-md-4 "]').should('contain','3. Kraj')
          }
        else if (tab == 'narucivanje') 
          {
            cy.get('div[class="col-md-4 "]').should('contain','1. Korpa')
            cy.get('div[class="col-md-4 active"]').should('contain','2. Naručivanje')
            cy.get('div[class="col-md-4 "]').should('contain','3. Kraj')
          }
        else if (tab == 'kraj') 
          {
            cy.get('div[class="col-md-4 "]').should('contain','1. Korpa')
            cy.get('div[class="col-md-4 "]').should('contain','2. Naručivanje')
            cy.get('div[class="col-md-4 active"]').should('contain','3. Kraj')
          }  
        
    }

    removeFromBasket() {

        cy.get(currentAmountEl).then(($el) => {
            let amount = parseInt($el.text());
            cy.wrap(amount).should('be.equal',1)
            cy.log('Current amount is '+amount)  
        })

        cy.get('a[class="btn btn-primary open-rating-form cart-bg-special"]').contains('Nazad').click() 

        cy.get('i[class="fa fa-times"]').click()
        
        cy.wait(1000)

        cy.get(currentAmountEl).then(($el) => {
            let amount = parseInt($el.text());
            cy.wrap(amount).should('be.equal',0)
            cy.log('Current amount is '+amount)  
        })
    }


    increaseAmountByArrow() {

        cy.get('i[class="fa fa-caret-up"]').click()
        cy.wait(1000)
        cy.get('input[title="Qty"]').then(($el) => {
            let amount = parseInt($el.attr('value'));
            cy.wrap(amount).should('be.equal',2)
            cy.log('Current amount is '+2)  
        })
    }

    decreaseAmountByArrow() {

        cy.get('i[class="fa fa-caret-down"]').click()
        cy.wait(1000)
        cy.get('input[title="Qty"]').then(($el) => {
            let amount = parseInt($el.attr('value'));
            cy.wrap(amount).should('be.equal',1)
            cy.log('Current amount is '+1)  
        }) 
    }

    changeAmountByInputField(amount) {
       cy.get('input[title="Qty"]').clear().type(amount)
       cy.wait(1000)  
    }

    saveAndContinue() {
        cy.get('button[class="nastavi"]').should('contain', 'Sačuvaj i nastavi').click()  
        cy.wait(1000)
        this.basketTabsStatuses('narucivanje')
    }

    getBasketTitle() {
        cy.get('div[class="clearfix"]').should('contain','Korpa')
    }

    getProductName(rowNumber) {

        if (rowNumber == null) 
            {
                return cy.get('a[class="name-of-product"]').eq(0)
            }
        else
            {
                return cy.get('a[class="name-of-product"]').eq(rowNumber)
            }

        
    }



    






}
export const onBasketPage = new BasketPage()