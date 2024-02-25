

export class AddToBasketPopupPage {

getGoToBasketButton() {
   return cy.contains("Idi u korpu")
}
    
}
export const onAddToBasketPopupPage = new AddToBasketPopupPage()