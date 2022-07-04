package BaseTest;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;

import org.apache.commons.io.output.WriterOutputStream;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import reports.ExtentReport;
import reports.LogStatus;
import utils.ConstantsClass;

public class BaseTest {

	protected StringWriter writer;
	protected PrintStream logs_capture;

	/* This method helps to write the request and response to the extent report */
	@BeforeMethod
	public void setUp() {
		RestAssured.baseURI = ConstantsClass.baseUri;
		writer = new StringWriter();
		logs_capture = new PrintStream(new WriterOutputStream(writer), true);
	}

	@BeforeSuite
	public void setUpSuite() {
		ExtentReport.initialize();
	}

	/*
	 * Flushing the extent report Opening the extent report automatically after the
	 * test suite execution.
	 */

	@AfterSuite
	public void afterSuite() throws IOException {
		ExtentReport.report.flush();
		File htmlFile = new File(ExtentReport.EXTENTREPORTPATH);
		Desktop.getDesktop().browse(htmlFile.toURI());

	}

	public void writeRequestAndResponseInReport(String response) {

		LogStatus.info("---- Response ---");
		formatAPIAndLogInReport(response);
	}

	protected void formatAPIAndLogInReport(String content) {

		String prettyPrint = content.replace("\n", "<br>");
		LogStatus.info("<pre>" + prettyPrint + "</pre>");

	}

}