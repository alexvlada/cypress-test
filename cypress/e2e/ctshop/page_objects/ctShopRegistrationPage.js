

export class CtShopRegistrationPage {

    pravnoLiceNo() {
        cy.get('select#pravnolice option:selected').should('have.text', 'Ne')
    }

    selectPravnoLice() {
        cy.get('select#pravnolice').select('Da')
    }
    

    pravnoLiceYes() {
        cy.get('select#pravnolice option:selected').should('have.text', 'Da')
    }

    checkPravnoLice(yesNo) {
        const checkboxLocator = 'select#pravnolice'
        if (yesNo == 1) {
            cy.get(checkboxLocator).select('Da')
        }
        else
        {
            cy.get(checkboxLocator).select('Ne') 
        }
    }    

    getIme() {
        return cy.get('#firstname')   
    }

    getPrezime() {
        return cy.get('#lastname')   
    }

    getEmailAddress() {
        return cy.get('#email_address')   
    }

    getPib() {
        return cy.get('#pib')   
    }

    getCheckBox() {
        return cy.get('input[type="checkbox"]:checked')   
    }

    getPassword() {
        return cy.get('#password')   
    }

    getConfirmPassword() {
        return cy.get('#confirmation')   
    }

    sendButton() {
        return cy.contains('Pošalji') 
    }
    
}
export const onCtShopRegistrationPage = new CtShopRegistrationPage()