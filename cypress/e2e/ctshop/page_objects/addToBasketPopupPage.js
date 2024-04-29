

export class AddToBasketPopupPage {

getGoToBasketButton() {
   return cy.contains("Idi u korpu")
}


getContinueShopingButton() {
   return cy.contains("Nastavi kupovinu")
}

    
}
export const onAddToBasketPopupPage = new AddToBasketPopupPage()