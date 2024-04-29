package page;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class LaptopsPage extends BaseTest {

    public LaptopsPage() {
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//label[text()='Lenovo']")
    WebElement lenovoFilter;
/*
    @FindBy(xpath = "//label[text()='AMD RYZEN 7']")
    WebElement processorFilter;
*/
    @FindBy(id = "filter_data_filter_3547")
    WebElement processorFilter;

    @FindBy(css = "a[class='product-image ajax-loading-stripes']")
    WebElement laptop;

    public void lenovoCheckboxClick() {
        wdWait.until(ExpectedConditions.elementToBeClickable(lenovoFilter));
        lenovoFilter.click();
    }

    public void processorCheckboxClick() {
        /*
        wdWait.until(ExpectedConditions.elementToBeClickable(processorFilter));
        processorFilter.click();
         */
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", processorFilter);
    }

    public void getHighRankingProduct() {

        //List<WebElement> elements = driver.findElements(By.xpath("(//span[@class=\"number-of-reviews\"])[3]"));
        List<WebElement> elements = driver.findElements(By.xpath("//span[@class=\"number-of-reviews\"]"));

       // @FindBy(xpath = "(//p[@class=\"user-display-name\"])[1]")
        System.out.println("number of elements: "+elements.size());

        // If you need the index, you can also use a traditional for loop
        for (int i = 0; i < elements.size(); i++) {
            WebElement element = elements.get(i);
            // Perform actions with element
            //System.out.println("Element at index " + i);
            String numberOfReviewsStr = element.getText().replaceAll("[()]", "");;//.substring(1,2);
            System.out.println("number of reviews: "+numberOfReviewsStr);
            int numberOfReviews = Integer.parseInt(numberOfReviewsStr);

            String raitingStr = element.getText().substring(1,2);

            int raiting = Integer.parseInt(raitingStr);

            if (numberOfReviews > 10) {
                List<WebElement> elements2 = driver.findElements(By.cssSelector("a[class='product-image ajax-loading-stripes']"));
                elements2.get(i).click(); // List is zero-indexed, so index 2 is the third element
                break;
            }
        }
    }
}
