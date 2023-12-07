package uk.co.automationtesting;

import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.OrderFormDelivery;
import PageObjects.OrderFormPayment;
import PageObjects.OrderFormPersInfo;
import PageObjects.OrderFormShipping;
import PageObjects.ShopContentPanel;
import PageObjects.ShopHomePage;
import PageObjects.ShopProductPage;
import PageObjects.ShopingCart;
import base.BasePage;

@Listeners(resources.Listeners.class)

public class OrderCompleteTest extends BasePage
{

	public OrderCompleteTest() throws IOException {
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
	public void endToEndTest() throws IOException, InterruptedException {
		HomePage home = new HomePage(driver);
		
		home.getCookie().click();
		home.getTestStoreLink().click();
		
		ShopHomePage shopHome = new ShopHomePage(driver);
		shopHome.getProdOne().click();
		
		Thread.sleep(3000);
		
		ShopProductPage shopProd = new ShopProductPage(driver);
		Select option = new Select(shopProd.getSizeOption());
		option.selectByVisibleText("M");
		shopProd.getQuantIncrease().click();;
		shopProd.getAddToCartBtn().click();
		
		Thread.sleep(3000);
		
		ShopContentPanel cPanel = new ShopContentPanel(driver);
		cPanel.getCheckoutBtn().click();
		
		ShopingCart cart = new ShopingCart(driver);
		cart.getHavePromo().click();
		cart.getPromoTextbox().sendKeys("20OFF");
		cart.getPromoAddBtn().click();
		cart.getProceedCheckoutBtn().click();
		
		Thread.sleep(5000);
		
		OrderFormPersInfo pInfo = new OrderFormPersInfo(driver);
		pInfo.getGenderMr().click();
		pInfo.getFirstNameField().sendKeys("mahan");
		pInfo.getLastnameField().sendKeys("jadiyavaar");
		pInfo.getEmailField().sendKeys("ma123@gmail.com");
		pInfo.getTermsConditionsCheckbox().click();
		pInfo.getContinueBtn().click();
		
		Thread.sleep(5000);
		
		OrderFormDelivery orderDelivery = new OrderFormDelivery(driver);
		orderDelivery.getAddressField().sendKeys("123 main street");
		orderDelivery.getCityField().sendKeys("bangalore");
		Select state = new Select(orderDelivery.getStateDropdown());
		state.selectByVisibleText("AA");
		orderDelivery.getPostcodeField().sendKeys("11111");		
		orderDelivery.getContinueBtn().click();
		Thread.sleep(5000);
		
		OrderFormShipping ship = new OrderFormShipping(driver);
		ship.getDeliveryMsgTextbox().sendKeys("I am a disco dancer");
		ship.getContinueBtn().click();
		
		OrderFormPayment orderPay = new OrderFormPayment(driver);
		orderPay.getPayByCheckRadioBtn().click();
		orderPay.getTermsConditionsCheckbox().click();
		orderPay.getOrderBtn().click();
		Thread.sleep(3000);
	}
	

}
