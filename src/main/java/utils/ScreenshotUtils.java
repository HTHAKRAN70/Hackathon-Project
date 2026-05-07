//package utils;
//
//import java.io.File;
//import java.nio.file.Files;
//import java.nio.file.StandardCopyOption;
//import org.openqa.selenium.*;
//
//public class ScreenshotUtils {
//
//    public static String takeScreenshot(WebDriver driver, String testName) {
//
//        try {
//            // 1. Define the directory and ensure it exists
//            File directory = new File("screenshots");
//            if (!directory.exists()) {
//                boolean created = directory.mkdirs();
//                if (created) {
//                    System.out.println("Created screenshots directory.");
//                }
//            }
//
//            // 2. Capture the screenshot
//            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//
//            // 3. Define the destination path
//            String path = "screenshots/" + testName + ".png";
//            File dest = new File(path);
//
//            // 4. Copy the file (using REPLACE_EXISTING to avoid errors on repeat runs)
//            Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
//
//            return path;
//
//        } catch (Exception e) {
//            System.err.println("Failed to capture screenshot: " + e.getMessage());
//            e.printStackTrace();
//            return null;
//        }
//    }
//}
//

package utils;

import java.io.File;
import java.nio.file.Files;

import org.openqa.selenium.*;

public class ScreenshotUtils {

    public static String takeScreenshot(WebDriver driver, String testName) {

        try {
            File src = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            File folder = new File("screenshots");
            if (!folder.exists()) folder.mkdirs();

            String path = "screenshots/" + testName + ".png";
            File dest = new File(path);
            if (dest.exists()) dest.delete();
            Files.copy(src.toPath(), dest.toPath());

            return path;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
