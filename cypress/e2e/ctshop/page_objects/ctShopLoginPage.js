import { onCommonPage } from "./commonPage"

export class CtShopLoginPage {

    getEmail() {
        return cy.get('#email')  
    }

    getPassword() {
        return cy.get('#password-login')  
    }

    getConfirmButton() {
        return cy.get('i[class="fa fa-sign-in"]')
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
        
        cy.get('div[class="page-title"]').should('be.visible')
        cy.contains('Moj nalog').should('be.visible')

        if (flPl == 'F') 
            {
                cy.get('p[class="hello"]').should('be.visible').contains('Dobrodošli, Fizicko Lice!')
            }
        else 
            {
                cy.get('p[class="hello"]').should('be.visible').contains('Dobrodošli, Pravno Lice!')
            } 
    }

    getWrongCredentialsLoginMessage() {
        return cy.get('div[class="alert alert-danger"]')
    }
}
export const onCtShopLoginPage = new CtShopLoginPage()