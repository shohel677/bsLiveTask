package TestComponents;

import Testcases.StandAloneTest;
import Utils.ExtentReporterNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class ReportListeners extends StandAloneTest implements ITestListener{
	//WebDriverd driver;
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //Thread safe
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);//unique thread id(ErrorValidationTest)->test
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.PASS, "Test Passed");
	}
	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().fail(result.getThrowable());//

		String filePath = null;
		try {

			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
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
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}
	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

	public String getScreenshot(String testCaseName, WebDriver instanceDriver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)instanceDriver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") +File.separator+ "reports" +File.separator+ testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + File.separator +"reports" + File.separator + testCaseName + ".png";
	}
}
