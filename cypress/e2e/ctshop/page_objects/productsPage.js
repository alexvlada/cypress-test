import { onCommonPage } from "./commonPage";


export class ProductsPage {

    getDisplayedProducts(){
        return cy.get('a[class = "product-image ajax-loading-stripes"]')
    }  

getHighRankingProduct() {
    cy.wait(5000);

    cy.get('body').find('a.product-image.ajax-loading-stripes').its('length').then((numberOfElements) => {
        const checkReviewAndNavigate = (index) => {
            // Stop condition for the recursion
            if (index >= numberOfElements) {
                return;
            }

            cy.get('span[class="number-of-reviews"]').eq(index).then(($num) => {    
                
                let numberOfReviews = $num.text();
               
                if (numberOfReviews !== ' (0)')  {

                    cy.get('h4[class="product-name"]').eq(index).then(($link) => {
                        // $link is a jQuery object of your element
                        const textValue = $link.text();
                        // Now you can use textValue as needed
                        cy.log(textValue); // Logs the text content to the Cypress command log
                        Cypress.myGlobalLaptopName = textValue
                      }); 



                    cy.get('a.product-image.ajax-loading-stripes').eq(index).invoke('attr', 'href').then((href) => {
                        cy.log(onCommonPage.getBaseUrl() + href);
                        cy.visit(onCommonPage.getBaseUrl() + href);
                        
                        
                    });
                    // Exiting after the first non-zero review product is found and navigated to,
                    // so no recursive call is made in this case.
                } else {
                    // If the current product does not match the condition, check the next one.
                    checkReviewAndNavigate(index + 1);
                }
            });
        };

        // Start checking from the first element
        checkReviewAndNavigate(0);
    });
}


}
export const onProductsPage = new ProductsPage()