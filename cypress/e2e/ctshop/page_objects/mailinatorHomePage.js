

export class MailinatorHomePage {

    getMailinatorEmailSearchField() {
        return cy.get('#inbox_field')
    }

    getGoButton() {
        return cy.contains('button[class="primary-btn"]', 'GO')
    }

}
export const onMailinatorHomePage = new MailinatorHomePage()