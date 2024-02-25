

export class MailinatorInboxPage {

    getAktivacijaLink() {
        return cy.contains('Aktivacija naloga na CT Shop')
    }
}
export const onMailinatorInboxPage = new MailinatorInboxPage()