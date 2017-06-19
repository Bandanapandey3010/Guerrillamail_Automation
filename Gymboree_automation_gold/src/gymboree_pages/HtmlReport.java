package gymboree_pages;

import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;

public class HtmlReport {
	String s = "";
	String line = "";

	@Test
	public void f() throws IOException {
		FileWriter fw = null;
		BufferedWriter bw = null;

		fw = new FileWriter("consolidated_html_report/report.html");
		bw = new BufferedWriter(fw);
		bw.write("<font size =\"4\"><b>" + "<span style=color:#128CBB>"
				+ "Consolidated Test Status Report - Gymboree Gold"
				+ "</span></b></font>");
		bw.write("<table border='1' style='table-layout: fixed'  width=\"100%\">");
		bw.write("<body bgcolor='#e6ffcc'>");
		bw.write("<tr>");
		bw.write("<tr style=\"BACKGROUND-COLOR: 1289E3\">");
		bw.write("<th><font size =\"4\" color=\"white\">" + "<b>"
				+ "Test Scenario" + "</b></font></th>");

		bw.write("<th><font size =\"4\" color=\"white\">" + "<b>"
				+ "Test Status" + "</b></font></th>");

		bw.write("<th><font size =\"4\" color=\"white\">" + "<b>"
				+ "Test Executed On" + "</b></font></th>");
		bw.write("<th><font size =\"4\" color=\"white\">" + "<b>"
				+ "Test Browser" + "</b></font></th>");
		bw.write("</tr>");

		/* New User Registration */
		bw.write("<tr>");
		bw.write("<tr style=\"BACKGROUND-COLOR: F6FA7E\">");
		bw.write("<td>New User Registration</td>");
		try {

			File file1 = new File(
					"test-output/Newuser_Registration/Newuser_Registration.html");
			FileReader fr = new FileReader(file1);
			BufferedReader br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				if (line.contains("Tests passed/Failed/Skipped")) {
					if (line.contains("1/0/0")) {

						System.out.println("Test Case is Pass");
						bw.write("<td><font color=\"5DAA4B\">" + "<b>"
								+ "Test Case is Pass" + "</b></font></td>");
					}
					if (line.contains("0/1/0")) {
						System.out.println("Test Case is Fail");
						bw.write("<td><font color=\"EB3A13\">" + "<b>"
								+ "Test Case is Fail" + "</b></font></td>");
						// bw.write("<td><font color=\"EB3A13\">"+"<b>"+"Test Case is Fail"+"</b></font></td>");

					}
					
				}
				if (line.contains("Started on:")) {
				//	String string = "It was hot (so hot!) I'm telling you.";
					//String string = "<td>Started on:</td><td>Wed Feb 22 10:07:53 PST 2017</td>";
					String string = line;
					String state = string.substring(string.indexOf("<td>Started on:</td><td>")+24, string.lastIndexOf("</td>"));
					System.out.println("line is" + state);
					bw.write("<td><font color=\"5DAA4B\">" + "<b>"
							+ state + "</b></font></td>");
				}
			}
			fr.close();
			br.close();
		} catch (Exception e) {
			System.out.println("Exception is " + e);
		}
		bw.write("<td>Chrome</td>");
		bw.write("</tr>");

		/* Registered User Signin and Signout */
		bw.write("<tr>");
		bw.write("<tr style=\"BACKGROUND-COLOR: F6FA7E\">");
		bw.write(" <td>Registered User Signin and Signout</td>");
		try {

			File file1 = new File(
					"test-output/Registereduser_Login_flow/Existinguser_Login_Logout.html");
			FileReader fr = new FileReader(file1);
			BufferedReader br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				if (line.contains("Tests passed/Failed/Skipped")) {
					if (line.contains("1/0/0")) {
						System.out.println("Test Case is Pass");
						bw.write("<td><font color=\"5DAA4B\">" + "<b>"
								+ "Test Case is Pass" + "</b></font></td>");
					}
					if (line.contains("0/1/0")) {

						System.out.println("Test Case is Fail");
						bw.write("<td><font color=\"EB3A13\">" + "<b>"
								+ "Test Case is Fail" + "</b></font></td>");

					}
				}
				if (line.contains("Started on:")) {
						String string = line;
						String state = string.substring(string.indexOf("<td>Started on:</td><td>")+24, string.lastIndexOf("</td>"));
						System.out.println("line is" + state);
						bw.write("<td><font color=\"5DAA4B\">" + "<b>"
								+ state + "</b></font></td>");
					}
			}
			
			
			fr.close();
			br.close();
		} catch (Exception e) {
			System.out.println("Exception is " + e);
		}

		bw.write("<td>Chrome</td>");
		bw.write("</tr>");

		/* Category Pages */
		bw.write("<tr>");
		bw.write("<tr style=\"BACKGROUND-COLOR: F6FA7E\">");
		bw.write(" <td>Category Pages browse</td>");
		try {

			File file1 = new File(
					"test-output/Category_Pages/Category_Pages.html");
			FileReader fr = new FileReader(file1);
			BufferedReader br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				if (line.contains("Tests passed/Failed/Skipped")) {
					if (line.contains("1/0/0")) {
						System.out.println("Test Case is Pass");
						bw.write("<td><font color=\"5DAA4B\">" + "<b>"
								+ "Test Case is Pass" + "</b></font></td>");
					}
					if (line.contains("0/1/0")) {

						System.out.println("Test Case is Fail");
						bw.write("<td><font color=\"EB3A13\">" + "<b>"
								+ "Test Case is Fail" + "</b></font></td>");

					}
				}
				if (line.contains("Started on:")) {
					//	String string = "It was hot (so hot!) I'm telling you.";
						//String string = "<td>Started on:</td><td>Wed Feb 22 10:07:53 PST 2017</td>";
						String string = line;
						String state = string.substring(string.indexOf("<td>Started on:</td><td>")+24, string.lastIndexOf("</td>"));
						System.out.println("line is" + state);
						bw.write("<td><font color=\"5DAA4B\">" + "<b>"
								+ state + "</b></font></td>");
					}
			}
			
			fr.close();
			br.close();
		} catch (Exception e) {
			System.out.println("Exception is " + e);
		}

		bw.write("<td>Chrome</td>");
		bw.write("</tr>");

		/* Footer Links */
		bw.write("<tr>");
		bw.write("<tr style=\"BACKGROUND-COLOR: F6FA7E\">");
		bw.write(" <td>Footer Links browse</td>");
		try {

			File file1 = new File("test-output/Footer_Links/Footer_Links.html");
			FileReader fr = new FileReader(file1);
			BufferedReader br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				if (line.contains("Tests passed/Failed/Skipped")) {
					if (line.contains("1/0/0")) {
						System.out.println("Test Case is Pass");
						bw.write("<td><font color=\"5DAA4B\">" + "<b>"
								+ "Test Case is Pass" + "</b></font></td>");
					}
					if (line.contains("0/1/0")) {

						System.out.println("Test Case is Fail");
						bw.write("<td><font color=\"EB3A13\">" + "<b>"
								+ "Test Case is Fail" + "</b></font></td>");

					}
				}
				if (line.contains("Started on:")) {
					//	String string = "It was hot (so hot!) I'm telling you.";
						//String string = "<td>Started on:</td><td>Wed Feb 22 10:07:53 PST 2017</td>";
						String string = line;
						String state = string.substring(string.indexOf("<td>Started on:</td><td>")+24, string.lastIndexOf("</td>"));
						System.out.println("line is" + state);
						bw.write("<td><font color=\"5DAA4B\">" + "<b>"
								+ state + "</b></font></td>");
					}
			}
			fr.close();
			br.close();
		} catch (Exception e) {
			System.out.println("Exception is " + e);
		}

		bw.write("<td>Chrome</td>");
		bw.write("</tr>");
		/* Registered user checkout flow */
		bw.write("<tr>");
		bw.write("<tr style=\"BACKGROUND-COLOR: F6FA7E\">");
		bw.write(" <td>Registered user checkout flow</td>");
		try {

			File file1 = new File(
					"test-output/Registereduser_Checkout/Registereduser_Checkout.html");
			FileReader fr = new FileReader(file1);
			BufferedReader br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				if (line.contains("Tests passed/Failed/Skipped")) {
					if (line.contains("1/0/0")) {
						System.out.println("Test Case is Pass");
						bw.write("<td><font color=\"5DAA4B\">" + "<b>"
								+ "Test Case is Pass" + "</b></font></td>");
					}
					if (line.contains("0/1/0")) {

						System.out.println("Test Case is Fail");
						bw.write("<td><font color=\"EB3A13\">" + "<b>"
								+ "Test Case is Fail" + "</b></font></td>");

					}
				}
				if (line.contains("Started on:")) {
					//	String string = "It was hot (so hot!) I'm telling you.";
						//String string = "<td>Started on:</td><td>Wed Feb 22 10:07:53 PST 2017</td>";
						String string = line;
						String state = string.substring(string.indexOf("<td>Started on:</td><td>")+24, string.lastIndexOf("</td>"));
						System.out.println("line is" + state);
						bw.write("<td><font color=\"5DAA4B\">" + "<b>"
								+ state + "</b></font></td>");
					}
			}
			fr.close();
			br.close();
		} catch (Exception e) {
			System.out.println("Exception is " + e);
		}

		bw.write("<td>Chrome</td>");
		bw.write("</tr>");
		/* Guest user checkout flow */
		bw.write("<tr>");
		bw.write("<tr style=\"BACKGROUND-COLOR: F6FA7E\">");
		bw.write(" <td>Guest user checkout flow</td>");
		try {

			File file1 = new File(
					"test-output/Guest_Checkout/Guest_Checkout_flow.html");
			FileReader fr = new FileReader(file1);
			BufferedReader br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				if (line.contains("Tests passed/Failed/Skipped")) {
					if (line.contains("1/0/0")) {
						System.out.println("Test Case is Pass");
						bw.write("<td><font color=\"5DAA4B\">" + "<b>"
								+ "Test Case is Pass" + "</b></font></td>");
					}
					if (line.contains("0/1/0")) {

						System.out.println("Test Case is Fail");
						bw.write("<td><font color=\"EB3A13\">" + "<b>"
								+ "Test Case is Fail" + "</b></font></td>");

					}
				}
				if (line.contains("Started on:")) {
					//	String string = "It was hot (so hot!) I'm telling you.";
						//String string = "<td>Started on:</td><td>Wed Feb 22 10:07:53 PST 2017</td>";
						String string = line;
						String state = string.substring(string.indexOf("<td>Started on:</td><td>")+24, string.lastIndexOf("</td>"));
						System.out.println("line is" + state);
						bw.write("<td><font color=\"5DAA4B\">" + "<b>"
								+ state + "</b></font></td>");
					}
			}
			fr.close();
			br.close();
		} catch (Exception e) {
			System.out.println("Exception is " + e);
		}

		bw.write("<td>Chrome</td>");
		bw.write("</tr>");
		/* Checkout flow with Payment Mode Gift Card */
		bw.write("<tr>");
		bw.write("<tr style=\"BACKGROUND-COLOR: F6FA7E\">");
		bw.write(" <td>Checkout flow with Payment Mode Gift Card</td>");
		try {

			File file1 = new File(
					"test-output/Checkout_withGiftCard/Checkout_withGiftCard.html");
			FileReader fr = new FileReader(file1);
			BufferedReader br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				if (line.contains("Tests passed/Failed/Skipped")) {
					if (line.contains("1/0/0")) {
						System.out.println("Test Case is Pass");
						bw.write("<td><font color=\"5DAA4B\">" + "<b>"
								+ "Test Case is Pass" + "</b></font></td>");
					}
					if (line.contains("0/1/0")) {

						System.out.println("Test Case is Fail");
						bw.write("<td><font color=\"EB3A13\">" + "<b>"
								+ "Test Case is Fail" + "</b></font></td>");

					}
				}
				if (line.contains("Started on:")) {
					//	String string = "It was hot (so hot!) I'm telling you.";
						//String string = "<td>Started on:</td><td>Wed Feb 22 10:07:53 PST 2017</td>";
						String string = line;
						String state = string.substring(string.indexOf("<td>Started on:</td><td>")+24, string.lastIndexOf("</td>"));
						System.out.println("line is" + state);
						bw.write("<td><font color=\"5DAA4B\">" + "<b>"
								+ state + "</b></font></td>");
					}
			}
			fr.close();
			br.close();
		} catch (Exception e) {
			System.out.println("Exception is " + e);
		}

		bw.write("<td>Chrome</td>");
		bw.write("</tr>");

		/* Order Cancellation */
		bw.write("<tr>");
		bw.write("<tr style=\"BACKGROUND-COLOR: F6FA7E\">");
		bw.write(" <td>Order Cancellation</td>");
		try {

			File file1 = new File(
					"test-output/Order_Cancellation/Order_Cancellation.html");
			FileReader fr = new FileReader(file1);
			BufferedReader br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				if (line.contains("Tests passed/Failed/Skipped")) {
					if (line.contains("1/0/0")) {
						System.out.println("Test Case is Pass");
						bw.write("<td><font color=\"5DAA4B\">" + "<b>"
								+ "Test Case is Pass" + "</b></font></td>");
					}
					if (line.contains("0/1/0")) {

						System.out.println("Test Case is Fail");
						bw.write("<td><font color=\"EB3A13\">" + "<b>"
								+ "Test Case is Fail" + "</b></font></td>");

					}
					
				}
				if (line.contains("Started on:")) {
					//	String string = "It was hot (so hot!) I'm telling you.";
						//String string = "<td>Started on:</td><td>Wed Feb 22 10:07:53 PST 2017</td>";
						String string = line;
						String state = string.substring(string.indexOf("<td>Started on:</td><td>")+24, string.lastIndexOf("</td>"));
						System.out.println("line is" + state);
						bw.write("<td><font color=\"5DAA4B\">" + "<b>"
								+ state + "</b></font></td>");
					}
			}
			fr.close();
			br.close();
		} catch (Exception e) {
			System.out.println("Exception is " + e);
		}

		bw.write("<td>Chrome</td>");
		bw.write("</tr>");

		bw.write("</table>");
		bw.close();
	}

}

