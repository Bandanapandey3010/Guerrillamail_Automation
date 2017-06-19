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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Order_Cancellation {
	static String baseUrl = "";

	@Test
	public void order_cancellation() throws IOException, InterruptedException {
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

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("document.getElementsByName('email1')[0].setAttribute('type', 'text');");
		driver.findElement(By.xpath("//input[@name='email1']")).isEnabled();
		driver.findElement(By.xpath("//input[@name='email1']")).clear();
		driver.findElement(By.xpath("//input[@name='email1']")).sendKeys(
				properties.getProperty("signin_email"));

		jse.executeScript("document.getElementsByName('LOGIN<>password')[0].setAttribute('type', 'password');");
		driver.findElement(By.xpath("//input[@name='LOGIN<>password']"))
				.isEnabled();
		driver.findElement(By.xpath("//input[@name='LOGIN<>password']"))
				.clear();
		driver.findElement(By.xpath("//input[@name='LOGIN<>password']"))
				.sendKeys(properties.getProperty("signin_password"));
		driver.findElement(
				By.cssSelector("*[class^='col-xs-7 submit-button std-btn']"))
				.click();
		WebElement track_order = driver.findElement(By.id("gbl-track-order"));

		actions.moveToElement(track_order).click().perform();
		Thread.sleep(2000);
		WebElement cancel_order = driver.findElement(By
				.linkText("• Need To Cancel?"));
		actions.moveToElement(cancel_order).click().perform();
		Thread.sleep(5000);
		WebElement cancel_order_confirmation = driver.findElement(By
				.cssSelector("*[class^='std-btn pull-right']"));
		actions.moveToElement(cancel_order_confirmation).click().perform();
		String currentWindowHandle = driver.getWindowHandle();
		driver.switchTo().window(currentWindowHandle);
		
		Thread.sleep(10000);
		System.out.println("Bandana");

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By
						.xpath("//input[@name='submit']"))).click();
		
	}
}
