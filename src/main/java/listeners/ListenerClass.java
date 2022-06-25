package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import reports.ExtentManager;
import reports.ExtentReport;
import reports.LogStatus;

public class ListenerClass implements ITestListener {

	private static String TestcaseName;

	public static String getTestcaseName() {
		return TestcaseName;
	}

	public static void setTestcaseName(String testcaseName) {
		TestcaseName = testcaseName;
	}

	public void onTestStart(ITestResult result) {
		TestcaseName = result.getMethod().getDescription();
		setTestcaseName(TestcaseName);
		ExtentManager.setExtentTest(ExtentReport.report.startTest(TestcaseName));
		LogStatus.pass(TestcaseName + " is started successfully");

	}

	public void onTestSuccess(ITestResult result) {

		LogStatus.pass(result.getMethod().getDescription() + " test case is passed");
		ExtentReport.report.endTest(ExtentManager.getExtTest());
	}

	public void onTestFailure(ITestResult result) {

		LogStatus.fail(result.getMethod().getDescription() + " is failed");
		LogStatus.fail(result.getThrowable().toString());
		ExtentReport.report.endTest(ExtentManager.getExtTest());

	}

	public void onTestSkipped(ITestResult result) {

		LogStatus.skip(result.getMethod().getDescription() + " is skipped");

		ExtentReport.report.endTest(ExtentManager.getExtTest());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		ExtentReport.report.endTest(ExtentManager.getExtTest());
	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {
		ExtentReport.report.endTest(ExtentManager.getExtTest());

	}

}
