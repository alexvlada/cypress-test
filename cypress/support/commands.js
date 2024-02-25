/// <reference types="cypress" />
// ***********************************************
// This example commands.ts shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//
//
// -- This is a parent command --
// Cypress.Commands.add('login', (email, password) => { ... })
//
//
// -- This is a child command --
// Cypress.Commands.add('drag', { prevSubject: 'element'}, (subject, options) => { ... })


Cypress.Commands.add('getRandomNumber', () => {
    randomNumber = Math.random().toString().substr(2, 5) 
    return randomNumber
})


Cypress.Commands.add('loginCtShopFl', (flPl) => {
        cy.visit('https://www.ctshop.rs/customer/account/login')
        cy.wait(1000)
        if (flPl == 'F') 
          {
            cy.get('#email').type('alexvlada6674@mailinator.com')
            cy.get('#pass').type('Getitnow2023!')
            cy.get('#send2').click()
            cy.contains('Moj Panel').should('be.visible')
            cy.contains('Dobrodošli, Vladimir Aleksic!').should('be.visible')
          } 
        else
          {
            cy.get('#email').type('pravnolice6678@mailinator.com')
            cy.get('#pass').type('Getitnow2023!')
            cy.get('#send2').click()
            cy.contains('Moj Panel').should('be.visible')
            cy.contains('Dobrodošli, Pravno Lice!').should('be.visible')
          }    
    })

 

//
//
// -- This is a dual command --
// Cypress.Commands.add('dismiss', { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite('visit', (originalFn, url, options) => { ... })
//
// declare global {
//   namespace Cypress {
//     interface Chainable {
//       login(email: string, password: string): Chainable<void>
//       drag(subject: string, options?: Partial<TypeOptions>): Chainable<Element>
//       dismiss(subject: string, options?: Partial<TypeOptions>): Chainable<Element>
//       visit(originalFn: CommandOriginalFn, url: string, options: Partial<VisitOptions>): Chainable<Element>
//     }
//   }
// }