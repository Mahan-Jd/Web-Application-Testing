package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

	public WebDriver driver;
	
	By toggle = By.cssSelector(".toggle");
	By homepageLink = By.linkText("HOMEPAGE");
	By accordionLink = By.linkText("ACCORDION");
}
