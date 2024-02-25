

export class MailinatorCtShopEmailPage {

    getLinkTab() {
        return cy.get('#pills-links-tab')
    }

    getToLink() {
    cy.get('a[href*="https://www.ctshop.rs/customer/account/activate?account="]').then(($el) => {
        const href = $el.attr('href');
        cy.log(href)
        cy.visit(href)
        })
    }

}
export const onMailinatorCtShopEmailPage = new MailinatorCtShopEmailPage()