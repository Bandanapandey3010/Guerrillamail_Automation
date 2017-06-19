package gymboree_pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Guestuser_Checkout {
	static String baseUrl = "";

	@Test
	public void guestuser_checkout() throws IOException, InterruptedException {

		File file = new File("src/properties_file/input_value.properties");

		FileInputStream fileInput = new FileInputStream(file);
		Properties properties = new Properties();
		properties.load(fileInput);
		String value = properties.getProperty("ChromeDriverPath");
		System.out.println("value is " + value);
		System.setProperty("webdriver.chrome.driver",
				properties.getProperty("ChromeDriverPath"));

		WebDriver driver = new ChromeDriver();
		/* Server Check */
		String actual_server_name=null;
		do
		{
			driver.manage().deleteAllCookies() ;
			driver.get("http://test.gymboree.com/server.jsp");
				WebElement mytable = driver.findElement(By.xpath("//table/tbody/tr[3]/td"));
				actual_server_name=mytable.getText();
				//String actual_server_name=null;
				//boolean contains = actual_server_name.contains("289");
				System.out.println("value is " + mytable.getText());
				Thread.sleep(2000);
				
			
		}
		while(!(actual_server_name.contains(properties.getProperty("app_server_name"))));


		/* Server Check ends here */
		System.out.println("TEST");
		baseUrl = "http://test.gymboree.com/";
		System.out.println(baseUrl);
		ChromeOptions options = new ChromeOptions();
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		options.addArguments("start-maximized");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(capabilities);
		driver.get(baseUrl);
		//driver.manage().window().maximize();
		WebElement close_button = driver.findElement(By
				.cssSelector("button.close"));
		Actions actions = new Actions(driver);
		actions.moveToElement(close_button).click().perform();
		Thread.sleep(100);

		WebElement girl_link = driver.findElement(By.id("gbl-kid-girl"));
		actions.moveToElement(girl_link);

		actions.click(girl_link).perform();
		WebElement item_link = driver.findElement(By.linkText("Just In"));
		actions.moveToElement(item_link);
		actions.click().perform();

		WebElement firstITEM = driver.findElement(By
				.cssSelector("div[class*='item col']:nth-child(1) a img"));
		actions.moveToElement(firstITEM);
		actions.click().perform();

		WebElement sizeButton = driver.findElement(By.id("pdp-size-option-2"));
		actions.moveToElement(sizeButton);
		actions.click().perform();
		WebElement quantityButton = driver.findElement(By
				.id("pdp-quantity-plus"));
		actions.moveToElement(quantityButton);
		actions.click().perform();
		WebElement quantityButton1 = driver.findElement(By
				.id("pdp-quantity-plus"));
		actions.moveToElement(quantityButton1);
		actions.click().perform();
		WebElement addtoBag = driver.findElement(By.linkText("Add to Bag"));
		actions.moveToElement(addtoBag);
		actions.click().perform();

		Thread.sleep(10000);
		WebElement checkOut1 = driver
				.findElement(By
						.cssSelector("a[href*='https://test.gymboree.com/checkout/shoppingbag.jsp?shipping=single&chkoutMethod=&formerTemplate=checkout%2Fshoppingbag.jsp']"));
		actions.moveToElement(checkOut1);
		actions.click(checkOut1).perform();

		WebElement checkOut2 = driver.findElement(By
				.cssSelector("a.submit-button.std-btn"));
		actions.moveToElement(checkOut2);
		actions.click().perform();

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {

			Thread.sleep(10000);
			jse.executeScript("document.getElementsByName('email2')[0].setAttribute('type', 'text');");
			driver.findElement(By.xpath("//input[@name='email2']")).isEnabled();
			driver.findElement(By.xpath("//input[@name='email2']")).clear();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//input[@name='email2']")).sendKeys(
					properties.getProperty("guestuser_email"));

			WebElement continueCheckout = driver.findElement(By
					.cssSelector("#signIn_submit"));
			actions.moveToElement(continueCheckout);
			actions.click().perform();
			System.out.println("Bandana");
			new WebDriverWait(driver, 10).until(
					ExpectedConditions.presenceOfElementLocated(By

					.id("firstName_3"))).sendKeys(
					properties.getProperty("ship_tofirstname"));

			new WebDriverWait(driver, 10).until(
					ExpectedConditions.presenceOfElementLocated(By

					.id("lastName_3"))).sendKeys(
					properties.getProperty("ship_tolastname"));
			new WebDriverWait(driver, 10).until(
					ExpectedConditions.presenceOfElementLocated(By

					.name("ADDRESS<>address1"))).sendKeys(
					properties.getProperty("ship_toaddress1"));
			new WebDriverWait(driver, 10).until(
					ExpectedConditions.presenceOfElementLocated(By

					.name("ADDRESS<>city"))).sendKeys(
					properties.getProperty("ship_tocity"));

			Select value1 = new Select(driver.findElement(By
					.name("ADDRESS<>state_cd")));

			value1.selectByVisibleText(properties.getProperty("ship_tostate"));
			new WebDriverWait(driver, 10).until(
					ExpectedConditions.presenceOfElementLocated(By

					.name("ADDRESS<>postal"))).sendKeys(
					properties.getProperty("ship_topostalcode"));
			new WebDriverWait(driver, 10).until(
					ExpectedConditions.presenceOfElementLocated(By

					.name("ADDRESS<>phone"))).sendKeys(
					properties.getProperty("ship_tophone"));

			driver.findElement(By.id("indDefaultBillTo3")).click();
			driver.findElement(By.id("shipping_AddContinue")).click();
			new WebDriverWait(driver, 10).until(
					ExpectedConditions.presenceOfElementLocated(By
							.id("QAS_AcceptOriginal"))).click();

			Thread.sleep(5000);
			Select credit_Card = new Select(driver.findElement(By
					.id("aba_cardBrand_cd")));

			credit_Card.selectByVisibleText(properties
					.getProperty("credit_cardbrand"));

			jse.executeScript("document.getElementsByName('CREDIT_CARD<>cardNum')[0].setAttribute('type', 'text');");
			driver.findElement(
					By.xpath("//input[@name='CREDIT_CARD<>cardNum']"))
					.isEnabled();
			driver.findElement(
					By.xpath("//input[@name='CREDIT_CARD<>cardNum']")).clear();
			driver.findElement(
					By.xpath("//input[@name='CREDIT_CARD<>cardNum']"))

			.sendKeys(properties.getProperty("credit_cardno"));

			Select expiry_Month = new Select(driver.findElement(By
					.id("aba_cardMonth_cd")));

			expiry_Month.selectByVisibleText(properties
					.getProperty("cc_expiry_month"));

			Select expiry_Year = new Select(driver.findElement(By
					.id("aba_cardYear_cd")));

			expiry_Year.selectByVisibleText(properties
					.getProperty("cc_expiry_year"));

			jse.executeScript("document.getElementsByName('CREDIT_CARD<>cardVerifyNum')[0].setAttribute('type', 'text');");
			driver.findElement(
					By.xpath("//input[@name='CREDIT_CARD<>cardVerifyNum']"))
					.isEnabled();
			driver.findElement(
					By.xpath("//input[@name='CREDIT_CARD<>cardVerifyNum']"))
					.clear();
			driver.findElement(
					By.xpath("//input[@name='CREDIT_CARD<>cardVerifyNum']"))

			.sendKeys(properties.getProperty("cc_verifyno"));

			WebElement billing_continue = driver.findElement(By
					.cssSelector("#editBillingAddressContinue"));
			System.out
					.println("shipping_continue" + billing_continue.getText());
			actions.moveToElement(billing_continue);
			actions.click().perform();

			Thread.sleep(20000);
			System.out.println("TESTING");
			WebElement place_order = driver.findElement(By
					.cssSelector("*[class^='pull-right std-btn']"));

			actions.moveToElement(place_order);

			System.out.println("place_order" + place_order);

			actions.moveToElement(place_order);
			actions.click().perform();
			
		} catch (Exception e) {
			System.out.println("Exception is " + e);
		}

	}

}
