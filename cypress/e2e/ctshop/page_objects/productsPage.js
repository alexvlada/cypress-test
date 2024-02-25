

export class ProductsPage {

    getDisplayedProducts(){
        return cy.get('a[class = "product-image ajax-loading-stripes"]')
    }  
}
export const onProductsPage = new ProductsPage()