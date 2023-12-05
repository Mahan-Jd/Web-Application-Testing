package uk.co.automationtesting;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.ShopContentPanel;
import PageObjects.ShopHomePage;
import PageObjects.ShopProductPage;
import PageObjects.ShopingCart;
import base.BasePage;

public class AddRemoveItemBasketTest extends BasePage{

	public AddRemoveItemBasketTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@BeforeTest
	public void setup() throws IOException {
		driver = getDriver();
		driver.get(getUrl());
	}

	@AfterTest
	public void tearDown() {
		driver.close();
		driver = null;
	}

	@Test
	public void addRemoveItem() throws IOException, InterruptedException {
		HomePage home = new HomePage(driver);
		
//		JavascriptExecutor jse = (JavascriptExecutor)getDriver();
//		jse.executeScript("arguments[0].scrollIntoView()", home.getTestStoreLink()); 
//		home.getTestStoreLink().click();

		home.getCookie().click();
		home.getTestStoreLink().click();

		ShopHomePage shopHome = new ShopHomePage(driver);
		shopHome.getProdOne().click();

		ShopProductPage shopProd = new ShopProductPage(driver);
		Select option = new Select(shopProd.getSizeOption());
		option.selectByVisibleText("M");
		shopProd.getQuantIncrease().click();;
		shopProd.getAddToCartBtn().click();

		Thread.sleep(3000);

		ShopContentPanel cPanel = new ShopContentPanel(driver);
		cPanel.getContinueShopBtn().click();
		shopProd.getHomepageLink().click();
		shopHome.getProdTwo().click();
		shopProd.getAddToCartBtn().click();
		cPanel.getCheckoutBtn().click();
		
		ShopingCart cart = new ShopingCart(driver);
		cart.getDeleteItemTwo().click();
		
		WebDriverWait wait = new WebDriverWait(driver,120);
		wait.until(ExpectedConditions.invisibilityOf(cart.getDeleteItemTwo()));
		System.out.println(cart.getTotalAmount().getText());
		
		Assert.assertEquals(cart.getTotalAmount().getText(), "$45.24");
	}

}
