package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/*
 * Listener class which is implementing ITestListener and hence we can use this to dynamically create reports, write logs.
 */
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

	}

	public void onStart(ITestContext context) {

	}

}
