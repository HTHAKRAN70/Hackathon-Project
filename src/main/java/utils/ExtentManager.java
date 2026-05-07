package utils;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getExtent() {
    	if(extent==null) {
    		File reportDir=new File("reports");
    		if(!reportDir.exists()) reportDir.mkdirs();
    		ExtentSparkReporter reporter=new ExtentSparkReporter("reports/ExtentReport.html");
    		reporter.config().setReportName("Health Insurance automation");
    		reporter.config().setDocumentTitle("Test result");
    		extent=new ExtentReports();
    		extent.attachReporter(reporter);
    		extent.setSystemInfo("Framework", "Selenium+ TestNg");
    		extent.setSystemInfo("Author", "Aarav Thakran ");
    	}
    	
    	return extent;
    	
    }
}
