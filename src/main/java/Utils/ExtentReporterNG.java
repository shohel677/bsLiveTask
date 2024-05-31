package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentReporterNG {

	
	public static ExtentReports getReportObject()
	{
		String path =System.getProperty("user.dir")+ File.separator+"reports"+ File.separator+"report.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Report");
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extent =new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Golzar Ahamed Shohel");
		return extent;
		
		
		
	}


}
