package listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Resource.Base;
import utilities.ExtentReporter;

public class Listeners extends Base implements ITestListener {
	ExtentReports extentReport = ExtentReporter.getExtentReport();
	ExtentTest extentTest;
	
	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extentReport.createTest(result.getName()+" execution started");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS,"Test Passed");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver = null;
		extentTest.fail(result.getThrowable());
		String testMethodName = result.getName();

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			takeScreenshot(testMethodName, driver);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();

	}

}
