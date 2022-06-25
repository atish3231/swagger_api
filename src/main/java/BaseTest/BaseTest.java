package BaseTest;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

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

	public String generateStringFromResource(String path) throws IOException {
		String temp = "";
		try {
			temp = new String(Files.readAllBytes(Paths.get(path)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}

	@AfterSuite
	public void afterSuite() throws IOException {
		ExtentReport.report.flush();
		File htmlFile = new File(ExtentReport.EXTENTREPORTPATH);
		Desktop.getDesktop().browse(htmlFile.toURI());

	}

	public void writeRequestAndResponseInReport(String request, String response) {

		LogStatus.info("---- Request ---");
		formatAPIAndLogInReport(request);
		LogStatus.info("---- Response ---");
		formatAPIAndLogInReport(response);
	}

	protected void formatAPIAndLogInReport(String content) {

		String prettyPrint = content.replace("\n", "<br>");
		LogStatus.info("<pre>" + prettyPrint + "</pre>");

	}

}