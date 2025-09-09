package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

	
	private  WebDriver driver;

	    private By firstNameField = By.id("first-name");
	    private By lastNameField = By.id("last-name");
	    private By postalCodeField = By.id("postal-code");
	    private By continueButton = By.id("continue");
	    private By finishButton = By.id("finish");
	    private By orderCompleteMessage = By.xpath("//h2[text()='THANK YOU FOR YOUR ORDER']");

	    public CheckoutPage(WebDriver driver) {
	        this.driver = driver;
	    }

	    public void enterDetails(String firstName, String lastName, String postalCode) {
	        driver.findElement(firstNameField).sendKeys(firstName);
	        driver.findElement(lastNameField).sendKeys(lastName);
	        driver.findElement(postalCodeField).sendKeys(postalCode);
	        driver.findElement(continueButton).click();
	    }

	    public void completePurchase() {
	        driver.findElement(finishButton).click();
	    }

	    public boolean isOrderComplete() {
	    	//System.out.println(orderCompleteMessage);
	        return driver.findElement(orderCompleteMessage).isDisplayed();
	    }
}
