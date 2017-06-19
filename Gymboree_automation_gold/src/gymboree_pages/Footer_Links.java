package gymboree_pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

public class Footer_Links {
	static String baseUrl = "";
  @Test
  public void footer_links() throws IOException, InterruptedException {
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
		//driver.manage().window().maximize();
		WebElement close_button = driver.findElement(By
				.cssSelector("button.close"));
		Actions actions = new Actions(driver);
		actions.moveToElement(close_button).click().perform();
		Thread.sleep(2000);
		WebElement track_order = driver.findElement(By
				.id("gbl-footer-3"));
		
		actions.moveToElement(track_order).click().perform();
		Thread.sleep(4000);
		WebElement email_preference = driver.findElement(By
				.id("gbl-footer-4"));
		
		actions.moveToElement(email_preference).click().perform();
		Thread.sleep(4000);
		WebElement store_locator = driver.findElement(By
				.id("gbl-footer-7"));
		
		actions.moveToElement(store_locator).click().perform();
		Thread.sleep(2000);
		WebElement shipping_info = driver.findElement(By
				.id("gbl-footer-10"));
		
		actions.moveToElement(shipping_info).click().perform();
		Thread.sleep(2000);
		WebElement gymboree_reward = driver.findElement(By
				.id("gbl-footer-13"));
		
		actions.moveToElement(gymboree_reward).click().perform();
		Thread.sleep(2000);
		WebElement gymbucks = driver.findElement(By
				.id("gbl-footer-15"));
		
		actions.moveToElement(gymbucks).click().perform();
		Thread.sleep(2000);
		WebElement promotion = driver.findElement(By
				.id("gbl-footer-16"));
		
		actions.moveToElement(promotion).click().perform();
		Thread.sleep(2000);
		WebElement refer_friend = driver.findElement(By
				.id("gbl-footer-17"));
		
		actions.moveToElement(refer_friend).click().perform();
		Thread.sleep(2000);
		WebElement gift_cards = driver.findElement(By
				.id("gbl-footer-18"));
		
		actions.moveToElement(gift_cards).click().perform();
		Thread.sleep(2000);
		WebElement gift_services = driver.findElement(By
				.id("gbl-footer-19"));
		
		actions.moveToElement(gift_services).click().perform();
		Thread.sleep(2000);
		
		
		
		
	}
  }
