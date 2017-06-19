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

public class Reward_Signup {
	static String baseUrl = "";
  @Test
  public void rewar_signup() throws IOException, InterruptedException {
		File file = new File("src/properties_file/input_value.properties");
		FileInputStream fileInput = new FileInputStream(file);
		Properties properties = new Properties();
		properties.load(fileInput);
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
//		driver.manage().window().maximize();
		WebElement close_button = driver.findElement(By
				.cssSelector("button.close"));
		Actions actions = new Actions(driver);
		actions.moveToElement(close_button).click().perform();
		Thread.sleep(10000);

		new WebDriverWait(driver, 10).until(
				ExpectedConditions.presenceOfElementLocated(By
						.cssSelector("#flagAdjust > a.sign-in"))).click();

		new WebDriverWait(driver, 10).until(
				ExpectedConditions.presenceOfElementLocated(By
						.cssSelector("#create-account"))).click();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("document.getElementsByName('USER_ACCOUNT<>email')[0].setAttribute('type', 'text');");
		driver.findElement(By.xpath("//input[@name='USER_ACCOUNT<>email']"))
				.isEnabled();
		driver.findElement(By.xpath("//input[@name='USER_ACCOUNT<>email']"))
				.clear();
		driver.findElement(By.xpath("//input[@name='USER_ACCOUNT<>email']"))
				.sendKeys(properties.getProperty("new_useremail"));

		jse.executeScript("document.getElementsByName('USER_ACCOUNT<>password')[0].setAttribute('type', 'password');");
		driver.findElement(By.xpath("//input[@name='USER_ACCOUNT<>password']"))
				.isEnabled();
		driver.findElement(By.xpath("//input[@name='USER_ACCOUNT<>password']"))
				.clear();
		driver.findElement(By.xpath("//input[@name='USER_ACCOUNT<>password']"))
				.sendKeys(properties.getProperty("signin_password"));
		jse.executeScript("document.getElementsByName('USER_ACCOUNT<>confirmPassword')[0].setAttribute('type', 'password');");
		driver.findElement(
				By.xpath("//input[@name='USER_ACCOUNT<>confirmPassword']"))
				.isEnabled();
		driver.findElement(
				By.xpath("//input[@name='USER_ACCOUNT<>confirmPassword']"))
				.clear();
		driver.findElement(
				By.xpath("//input[@name='USER_ACCOUNT<>confirmPassword']"))
				.sendKeys(properties.getProperty("signin_confirm_password"));
		jse.executeScript("document.getElementsByName('USER_ACCOUNT<>ATR_USER_Password_Hint')[0].setAttribute('type', 'text');");
		driver.findElement(
				By.xpath("//input[@name='USER_ACCOUNT<>ATR_USER_Password_Hint']"))
				.isEnabled();
		driver.findElement(
				By.xpath("//input[@name='USER_ACCOUNT<>ATR_USER_Password_Hint']"))
				.clear();
		driver.findElement(
				By.xpath("//input[@name='USER_ACCOUNT<>ATR_USER_Password_Hint']"))
				.sendKeys(properties.getProperty("password_hint"));
		driver.findElement(By.cssSelector("*[class^='submit pull-left']"))
				.click();

		WebElement reward_link1 = driver.findElement(By
				.linkText("Learn more about Rewards"));
		actions.moveToElement(reward_link1);
		actions.click().perform();
		WebElement reward_link2 = driver.findElement(By.linkText("Enroll Now"));
		actions.moveToElement(reward_link2);
		actions.click().perform();
		jse.executeScript("document.getElementsByName('USER_ACCOUNT<>firstName')[0].setAttribute('type', 'text');");
		driver.findElement(By.xpath("//input[@name='USER_ACCOUNT<>firstName']"))
				.isEnabled();
		driver.findElement(By.xpath("//input[@name='USER_ACCOUNT<>firstName']"))
				.clear();
		driver.findElement(By.xpath("//input[@name='USER_ACCOUNT<>firstName']"))
				.sendKeys(properties.getProperty("reward_firstname"));
		jse.executeScript("document.getElementsByName('USER_ACCOUNT<>lastName')[0].setAttribute('type', 'text');");
		driver.findElement(By.xpath("//input[@name='USER_ACCOUNT<>lastName']"))
				.isEnabled();
		driver.findElement(By.xpath("//input[@name='USER_ACCOUNT<>lastName']"))
				.clear();
		driver.findElement(By.xpath("//input[@name='USER_ACCOUNT<>lastName']"))
				.sendKeys(properties.getProperty("reward_lastname"));
		jse.executeScript("document.getElementsByName('LOYALTY_ADDRESS<>address1')[0].setAttribute('type', 'text');");
		driver.findElement(
				By.xpath("//input[@name='LOYALTY_ADDRESS<>address1']"))
				.isEnabled();
		driver.findElement(
				By.xpath("//input[@name='LOYALTY_ADDRESS<>address1']")).clear();
		driver.findElement(
				By.xpath("//input[@name='LOYALTY_ADDRESS<>address1']"))
				.sendKeys(properties.getProperty("reward_address1"));
		jse.executeScript("document.getElementsByName('LOYALTY_ADDRESS<>city')[0].setAttribute('type', 'text');");
		driver.findElement(By.xpath("//input[@name='LOYALTY_ADDRESS<>city']"))
				.isEnabled();
		driver.findElement(By.xpath("//input[@name='LOYALTY_ADDRESS<>city']"))
				.clear();
		driver.findElement(By.xpath("//input[@name='LOYALTY_ADDRESS<>city']"))
				.sendKeys(properties.getProperty("reward_city"));
		Select value1 = new Select(driver.findElement(By.id("state")));
		// value1.selectByVisibleText("California");
		value1.selectByVisibleText(properties.getProperty("reward_state"));
		jse.executeScript("document.getElementsByName('LOYALTY_ADDRESS<>postal')[0].setAttribute('type', 'text');");
		driver.findElement(By.xpath("//input[@name='LOYALTY_ADDRESS<>postal']"))
				.isEnabled();
		driver.findElement(By.xpath("//input[@name='LOYALTY_ADDRESS<>postal']"))
				.clear();
		driver.findElement(By.xpath("//input[@name='LOYALTY_ADDRESS<>postal']"))
				.sendKeys(properties.getProperty("reward_postalcode"));
		jse.executeScript("document.getElementsByName('LOYALTY_ADDRESS<>phone')[0].setAttribute('type', 'text');");
		driver.findElement(By.xpath("//input[@name='LOYALTY_ADDRESS<>phone']"))
				.isEnabled();
		driver.findElement(By.xpath("//input[@name='LOYALTY_ADDRESS<>phone']"))
				.clear();
		driver.findElement(By.xpath("//input[@name='LOYALTY_ADDRESS<>phone']"))
				.sendKeys(properties.getProperty("reward_phone"));
		driver.findElement(By.id("accept")).click();
		WebElement reward_lin3 = driver.findElement(By
				.linkText("Enroll in Gymboree Rewards"));
		actions.moveToElement(reward_lin3);
		actions.click().perform();
		
	}

  }

