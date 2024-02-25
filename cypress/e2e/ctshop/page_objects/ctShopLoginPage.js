import { onCommonPage } from "./commonPage"

export class CtShopLoginPage {

    getEmail() {
        return cy.get('#email')  
    }

    getPassword() {
        return cy.get('#pass')  
    }

    getConfirmButton() {
        return cy.get('#send2')  
    }
    
    loginToAccount(username,password) 
    {
        onCommonPage.getLoginLink().click()
        cy.wait(1000)
        this.getEmail().type(username)
        this.getPassword().type(password)
        this.getConfirmButton().click()
    } 

    getSuccessfulActivationRegistrationMessage() {
        return cy.get('div[class="alert alert-success"]') 
    }

    getSuccessfulLogin(flPl) {
        
        cy.contains('Moj Panel').should('be.visible')

        if (flPl == 'F') 
            {
                cy.contains('Dobrodošli, Fizicko Lice!').should('be.visible')
            }
        else 
            {
                cy.contains('Dobrodošli, Pravno Lice!').should('be.visible')
            } 
    }

    getWrongCredentialsLoginMessage() {
        return cy.get('div[class="alert alert-danger"]')
    }
}
export const onCtShopLoginPage = new CtShopLoginPage()