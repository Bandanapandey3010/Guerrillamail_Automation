package guerrillamail_flow_validation;

/* Automated test for Mail E2E Flow */
import org.testng.annotations.Test;

import guerrillamail_flow_validation.HtmlReport;

import java.lang.Object;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.*;

public class Guerrillamail_Flow_Automation {

	// For http requests
	private CloseableHttpClient client = HttpClientBuilder.create().build();
	private HttpGet request;
	private HttpResponse response;
	private HttpEntity entity;

	// API Address
	private final String apiAddr = "http://api.guerrillamail.com/ajax.php?f=";
	private String check_email_api_url;
	private String delele_email_api_url;
	private String setuser_api_url;

	// GuerrillaMail and Selenium attribute
	private String email_id;
	private String content;
	private JsonElement sid_token;
	private JsonElement alias;
	private String sid_token_string_val;
	private String scramble_address;
	private String mail_subject;
	private String mail_excerpt;
	private String mail_sender_matching_status;
	private String mail_subject_matching_status;
	private String mail_body_matching_status;
	private String delete_email_status;
	private WebDriver driver;
	private String baseUrl;
	private File file;
	private FileInputStream fileInput;
	private Properties properties;
	private String sign_in_name;
	private Select select_host;

	// Json Parser, Object, Element
	private JsonParser parser = new JsonParser();
	private Object resultObject;
	private JsonObject obj;

	@Test
	// Compose mail automation using Selenium Script
	public void email_automation_flow() throws IOException,
			InterruptedException {

		baseUrl = "https://www.guerrillamail.com/inbox";
		file = new File("src/resource/input_value.properties");
		fileInput = new FileInputStream(file);
		properties = new Properties();
		properties.load(fileInput);
		System.setProperty("webdriver.chrome.driver",
				properties.getProperty("ChromeDriverPath"));
		driver = new ChromeDriver();
		driver.get(baseUrl);
		sign_in_name = driver.findElement(By.id("inbox-id")).getText();
		select_host = new Select(driver.findElement(By.id("gm-host-select")));
		select_host.selectByVisibleText(properties.getProperty("domain_name"));
		email_id = sign_in_name + "@" + properties.getProperty("domain_name");
		driver.findElement(By.cssSelector("a[href*='/compose']")).click();
		new WebDriverWait(driver, 10).until(
				ExpectedConditions.presenceOfElementLocated(By.name("to")))
				.sendKeys(email_id);
		driver.findElement(By.name("subject")).sendKeys(
				properties.getProperty("mail_subject"));
		new WebDriverWait(driver, 10).until(
				ExpectedConditions.presenceOfElementLocated(By.name("body")))
				.sendKeys(properties.getProperty("mail_body"));
		driver.findElement(By.id("send-button")).click();
		Thread.sleep(5000);
		driver.close();
		try {
			// Call to set user API
			_setEmailUser(email_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Set user using JGuerrillaMail API call

	private void _setEmailUser(String email_id) throws Exception {
		setuser_api_url = apiAddr + "set_email_user&"
				+ "ip=127.0.0.1&email_user=" + email_id + "&lang=en-US";
		Thread.sleep(15000);
		request = new HttpGet(setuser_api_url);
		response = client.execute(request);
		entity = response.getEntity();
		content = EntityUtils.toString(entity);
		System.out.println("Content of setuser_api " + content);
		resultObject = parser.parse(content);
		obj = (JsonObject) resultObject;
		sid_token = obj.get("sid_token");
		sid_token_string_val = sid_token.toString().replace("\"", "");
		alias = obj.get("alias");
		scramble_address = alias.toString().replace("\"", "");
		try {
			// Call to Check Email Content of user API
			_checkEmailContent(sid_token_string_val);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check Email Content of user API

	private void _checkEmailContent(String sid_token) throws Exception {
		check_email_api_url = apiAddr + "check_email&ip=127.0.0.1&sid="
				+ sid_token + "&seq=1";
		request = new HttpGet(check_email_api_url);
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();

		content = EntityUtils.toString(entity);
		System.out.println("Content of check_email_api " + content);

		parser = new JsonParser();
		resultObject = parser.parse(content);
		obj = (JsonObject) resultObject;
		JsonArray jArr = (JsonArray) obj.getAsJsonArray("list");
		int length = jArr.toString().length();
		System.out.println("List Array Length is " + length);
		Iterator i = jArr.iterator();
		if (length > 2) {

			while (i.hasNext()) {
				JsonObject elements = (JsonObject) i.next();

				// Sender email_id validation

				String sender = elements.get("mail_from").toString();
				String id_of_email = elements.get("mail_id").toString()
						.replace("\"", "");
				if (sender.contains(scramble_address)) {
					mail_sender_matching_status = "Pass";
				}

				// Email Subject validation
				mail_subject = elements.get("mail_subject").toString()
						.replace("\"", "");
				if (properties.getProperty("mail_subject").equals(mail_subject)) {
					mail_subject_matching_status = "Pass";
				}

				// Email Body validation
				mail_excerpt = elements.get("mail_excerpt").toString()
						.replace("\"", "");
				if (mail_excerpt.contains(properties.getProperty("mail_body"))) {
					mail_body_matching_status = "Pass";
				}

				// Delete email from inbox

				delele_email_api_url = apiAddr + "del_email&ip=127.0.0.1&sid="
						+ sid_token_string_val + "&email_ids[]=" + id_of_email;
				request = new HttpGet(delele_email_api_url);
				response = client.execute(request);
				entity = response.getEntity();

				content = EntityUtils.toString(entity);
				System.out.println("Content of delele_email_api " + content);

				resultObject = parser.parse(content);

				obj = (JsonObject) resultObject;

				JsonObject auth = obj.getAsJsonObject("auth");
				JsonElement response_code = auth.get("success");
				if (response_code.toString().equals("true")) {
					delete_email_status = "Pass";
				}

			}

		} else {
			mail_sender_matching_status = "Fail";
			mail_subject_matching_status = "Fail";
			mail_body_matching_status = "Fail";
			delete_email_status = "Fail";
		}
		try {
			// Call to HtmlReport class's method
			HtmlReport obj1 = new HtmlReport();
			obj1.html_report(mail_sender_matching_status,
					mail_subject_matching_status, mail_body_matching_status,
					delete_email_status);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
