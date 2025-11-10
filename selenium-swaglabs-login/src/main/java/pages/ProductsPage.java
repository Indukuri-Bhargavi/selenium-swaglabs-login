package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {

	private WebDriver driver;

	private By firstProductAddButton = By.id("add-to-cart-sauce-labs-backpack");

	private By cartIcon = By.id("shopping_cart_container");

	public ProductsPage(WebDriver driver) {
		this.driver = driver;
	}

	public void addFirstProductToCart() {
		driver.findElement(firstProductAddButton).click();
	}

	public void goToCart() {
		driver.findElement(cartIcon).click();
	}

}
