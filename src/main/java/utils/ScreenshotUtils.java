package utils;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {
//<<<<<<< HEAD

	private static final String SCREENSHOT_DIR = "screenshots";
	 
	private static String getTimestamp() {
		return new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date(0));
	}
 
	private static String sanitizeFileName(String name) {
		return name.replaceAll("[^a-zA-Z0-9-_]", "_");
	}
	private static File getScreenshotDir() {
		File dir = new File(SCREENSHOT_DIR);
		if (dir.exists()) {
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
//=======
//    public static String takeScreenshot(WebDriver driver, String testName) {
//
//        try {
//            if (driver == null) {
//                System.out.println("Driver is NULL - cannot take screenshot");
//                return null;
//            }
//
//            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//
//            // ✅ Absolute path (VERY IMPORTANT FIX)
//            String projectPath = System.getProperty("user.dir");
//
//            String dirPath = projectPath + "/screenshots/";
//            File directory = new File(dirPath);
//
//            // ✅ Create folder if not exists
//            if (!directory.exists()) {
//                boolean created = directory.mkdirs();
//                System.out.println("📁 Screenshot folder created: " + created);
//            }
//
//            String filePath = dirPath + testName + "_" + System.currentTimeMillis() + ".png";
//
//            File dest = new File(filePath);
//            Files.copy(src.toPath(), dest.toPath());
//
//            // ✅ Debug print
//            System.out.println("📸 Screenshot saved at: " + dest.getAbsolutePath());
//
//            return dest.getAbsolutePath();
//
//        } catch (Exception e) {
//            System.out.println("Screenshot FAILED due to:");
//            e.printStackTrace();
//            return null;
//        }
//    }

    /*public static String takeScreenshot(WebDriver driver, String testName) {

        try {
            File src = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            String path = "screenshots/" + testName + ".png";
            File dest = new File(path);
            Files.copy(src.toPath(), dest.toPath());

            return path;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }*/
//>>>>>>> 60b16f85522a6f646434bd7a712944c265e983ab
}

