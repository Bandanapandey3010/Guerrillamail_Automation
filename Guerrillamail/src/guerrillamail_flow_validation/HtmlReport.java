package guerrillamail_flow_validation;

import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class HtmlReport {
	String s = "";
	String line = "";

	@Test
	public void html_report(String mail_sender_matching_status,
			String mail_subject_matching_status,
			String mail_body_matching_status, String delete_email_status)
			throws Exception {

		FileWriter fw = null;
		BufferedWriter bw = null;

		fw = new FileWriter("consolidated_html_report/report.html");
		bw = new BufferedWriter(fw);
		bw.write("<font size =\"4\"><b>" + "<span style=color:#128CBB>"
				+ "Consolidated Test Status Report "
				+ "</span></b></font>");
		bw.write("<table border='1' style='table-layout: fixed'  width=\"100%\">");
		bw.write("<body bgcolor='#e6ffcc'>");
		bw.write("<tr>");
		bw.write("<tr style=\"BACKGROUND-COLOR: 1289E3\">");
		bw.write("<th><font size =\"4\" color=\"white\">" + "<b>"
				+ "Test Scenario" + "</b></font></th>");

		bw.write("<th><font size =\"4\" color=\"white\">" + "<b>"
				+ "Test Status" + "</b></font></th>");

		bw.write("</tr>");

		/* Check Email Content */
		bw.write("<tr>");
		bw.write("<tr style=\"BACKGROUND-COLOR: F6FA7E\">");
		bw.write(" <td>Mail Sender Matching Status</td>");
		bw.write("<td><font color=\"5DAA4B\">" + "<b>"
				+ mail_sender_matching_status + "</b></font></td>");
		bw.write("<tr>");
		bw.write("<tr style=\"BACKGROUND-COLOR: F6FA7E\">");
		bw.write(" <td>Mail Subject Matching Status</td>");
		bw.write("<td><font color=\"5DAA4B\">" + "<b>"
				+ mail_subject_matching_status + "</b></font></td>");

		bw.write("<tr>");
		bw.write("<tr style=\"BACKGROUND-COLOR: F6FA7E\">");
		bw.write(" <td>Mail Body Matching Status</td>");
		bw.write("<td><font color=\"5DAA4B\">" + "<b>"
				+ mail_body_matching_status + "</b></font></td>");

		bw.write("<tr>");
		bw.write("<tr style=\"BACKGROUND-COLOR: F6FA7E\">");
		bw.write(" <td>Delete Email Status</td>");
		bw.write("<td><font color=\"5DAA4B\">" + "<b>" + delete_email_status
				+ "</b></font></td>");

		bw.write("</table>");
		bw.close();
	}
}
