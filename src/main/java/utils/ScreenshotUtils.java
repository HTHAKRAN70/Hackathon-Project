package utils;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {
	private static final String SCREENSHOT_DIR = "screenshots";
	 
	private static String getTimestamp() {
		return new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date(0));
	}
 
	private static String sanitizeFileName(String name) {
		return name.replaceAll("[^a-zA-Z0-9-_]", "_");
	}
	private static File getScreenshotDir() {
		File dir = new File(SCREENSHOT_DIR);
		if (!dir.exists()) {
			dir.mkdirs();
		}
 
		return dir;
	}
	
    public static String takeScreenshot(WebDriver driver, String testName) {

    	try {
			File dir = getScreenshotDir();
			String timestamp = getTimestamp();
            String safeName = sanitizeFileName(testName);
 
			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
 
			File destination = new File(dir, safeName + "_" + timestamp + ".png");
 
			FileUtils.copyFile(source, destination);
			return destination.getAbsolutePath();
 
		} catch (Exception e) {
			throw new RuntimeException("Screenshot capture failed", e);
		}
    }
}


