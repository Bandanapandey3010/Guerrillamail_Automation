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

public class Checkout_withGiftCard {
	static String baseUrl = "";
  @Test
  public void checkout_withgiftcard() throws IOException, InterruptedException {
		File file = new File("src/properties_file/input_value.properties");
		//File file = new File("class_properties.properties");
		FileInputStream fileInput = new FileInputStream(file);
		Properties properties = new Properties();
		properties.load(fileInput);
		String value = properties.getProperty("ChromeDriverPath");
		System.out.println("value is " + value );
		System.setProperty(
				"webdriver.chrome.driver",
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
//		driver.manage().window().maximize();
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
			jse.executeScript("document.getElementsByName('email1')[0].setAttribute('type', 'text');");
			driver.findElement(By.xpath("//input[@name='email1']")).isEnabled();
			driver.findElement(By.xpath("//input[@name='email1']")).clear();
			Thread.sleep(5000);
			String email1=properties.getProperty("signin_email");
			System.out.println(email1);
			driver.findElement(By.xpath("//input[@name='email1']")).sendKeys(properties.getProperty("signin_email"));

		

			jse.executeScript("document.getElementsByName('LOGIN<>password')[0].setAttribute('type', 'password');");
			driver.findElement(By.xpath("//input[@name='LOGIN<>password']"))
					.isEnabled();
			driver.findElement(By.xpath("//input[@name='LOGIN<>password']"))
					.clear();
			driver.findElement(By.xpath("//input[@name='LOGIN<>password']")).sendKeys(
					properties.getProperty("signin_password"));


			WebElement continueCheckout = driver.findElement(By
					.cssSelector("#signIn_submit"));
			actions.moveToElement(continueCheckout);
			actions.click().perform();
			System.out.println("Bandana");
			new WebDriverWait(driver, 10).until(
					ExpectedConditions.presenceOfElementLocated(By
							.id("addShippingAddress"))).click();
			new WebDriverWait(driver, 10).until(
					ExpectedConditions.presenceOfElementLocated(By

			.id("ea_firstName"))).sendKeys(properties.getProperty("ship_tofirstname"));
			
			new WebDriverWait(driver, 10).until(
					ExpectedConditions.presenceOfElementLocated(By

			.id("ea_lastName"))).sendKeys(properties.getProperty("ship_tolastname"));
			new WebDriverWait(driver, 10).until(
					ExpectedConditions.presenceOfElementLocated(By

			.id("ea_address1"))).sendKeys(properties.getProperty("ship_toaddress1"));
			new WebDriverWait(driver, 10).until(
					ExpectedConditions.presenceOfElementLocated(By
						//	.id("ea_city"))).sendKeys("San Francisco");
			.id("ea_city"))).sendKeys(properties.getProperty("ship_tocity"));

			// WebElement identifier = driver.findElement(By.id("state_cd_2"));
			// Select sc = new Select (identifier);

			Select value1 = new Select(driver.findElement(By.id("state_cd_2")));
	//		value1.selectByVisibleText("California");
			value1.selectByVisibleText(properties.getProperty("ship_tostate"));
			new WebDriverWait(driver, 10).until(
					ExpectedConditions.presenceOfElementLocated(By
						//	.id("ea_postal"))).sendKeys("94501");
			.id("ea_postal"))).sendKeys(properties.getProperty("ship_topostalcode"));
			new WebDriverWait(driver, 10).until(
					ExpectedConditions.presenceOfElementLocated(By
						//	.id("ea_phone"))).sendKeys("5104587505");
			.id("ea_phone"))).sendKeys(properties.getProperty("ship_tophone"));

			driver.findElement(By.id("ea_indDefaultBillTo")).click();
			driver.findElement(By.id("editShippingAddress_Submit")).click();
			new WebDriverWait(driver, 10).until(
					ExpectedConditions.presenceOfElementLocated(By
							.id("QAS_AcceptOriginal"))).click();

			// By by = By.cssSelector("#shipping_SelectContinue");
			// WebDriverWait w = new WebDriverWait(driver, 20);
			// WebElement element =
			// w.until(ExpectedConditions.elementToBeClickable(by));
			// element.click();
			Thread.sleep(5000);
			WebElement shipping_continue = driver.findElement(By
					.cssSelector("#shipping_SelectContinue"));
			System.out.println("shipping_continue"
					+ shipping_continue.getText());
			actions.moveToElement(shipping_continue);
			actions.click().perform();
			Thread.sleep(5000);
			WebElement shipping_continue1 = driver.findElement(By
					.cssSelector("#QAS_AcceptOriginal"));
			System.out.println("shipping_continue"
					+ shipping_continue1.getText());
			actions.moveToElement(shipping_continue1);
			actions.click().perform();
			Thread.sleep(5000);
			/* Billing with Gift Card */
			
			driver.findElement(By.xpath("//input[@name='SVS_ACCOUNT<>accountNumber']")).sendKeys(properties.getProperty("gift_card_no"));
			driver.findElement(By.xpath("//input[@name='SVS_ACCOUNT<>sva_pin']")).sendKeys(properties.getProperty("gift_card_pin"));
			WebElement gc_apply = driver.findElement(By
					.cssSelector("#redeemGiftCardBtn"));
			actions.moveToElement(gc_apply);
			actions.click().perform();
			
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

