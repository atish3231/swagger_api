package reports;

import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;

public class ExtentReport {

	public static ExtentReports report = null;
	public static String extentreportpath = "";
	public static final String EXTENTREPORTPATH = System.getProperty("user.dir") + "/ExtentReports/index.html";
	public static final String EXTENTCONFIGFILEPATH = System.getProperty("user.dir")
			+ "/src/test/resources/extentreport.xml";

	private ExtentReport() {
		extentreportpath = EXTENTREPORTPATH;
		report = new ExtentReports(extentreportpath);
		report.loadConfig(new File(EXTENTCONFIGFILEPATH));

	}

	public static void initialize() {
		new ExtentReport();
	}

}
