package utils;


import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getExtent() {

        if (extent == null) {
        	File reportDir = new File("reports");
        	if (!reportDir.exists()) reportDir.mkdirs();

        	ExtentSparkReporter reporter =
        	    new ExtentSparkReporter("reports/ExtentReport.html");
            reporter.config().setReportName("Health Insurance Automation");

            reporter.config().setDocumentTitle("Test Results");

            extent = new ExtentReports();
            extent.attachReporter(reporter);

            extent.setSystemInfo("Framework", "Selenium + TestNG");
            extent.setSystemInfo("Author", "Automation Team");

        }
        return extent;

    }
}
