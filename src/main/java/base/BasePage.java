package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BasePage 
{
	public static WebDriver driver;
	private String url;
	private Properties prop;


	public BasePage() throws IOException {
		prop = new Properties();
		FileInputStream data = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\config.properties");
		prop.load(data);
	}


	public WebDriver getDriver() throws IOException {


		if(prop.getProperty("browser").equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\java\\driverss\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(prop.getProperty("browser").equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "\\src\\main\\java\\driverss\\geckodriver.exe");

			driver = new FirefoxDriver();
		}
		else {
			System.setProperty("webdriver.edge.driver",System.getProperty("user.dir") + "\\src\\main\\java\\driverss\\msedgedriver.exe");

			 driver = new EdgeDriver();
		}


		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return driver;
	}

	public String getUrl() throws IOException {

		url = prop.getProperty("url");

		return url;
	}

	public void takeScreenShot(String name) throws IOException {

		File  srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		File desFile = new File(System.getProperty("user.dir") +"\\target\\Screenshots\\"+timeDate()+".png");

		FileUtils.copyFile(srcFile, desFile);
	}

	public  String timeDate() {
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-SS").format(new Date());
	}

}
