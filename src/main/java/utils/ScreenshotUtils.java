package utils;

import java.io.File;
import java.nio.file.Files;

import org.openqa.selenium.*;

public class ScreenshotUtils {
    public static String takeScreenshot(WebDriver driver, String testName) {

        try {
            if (driver == null) {
                System.out.println("Driver is NULL - cannot take screenshot");
                return null;
            }

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // ✅ Absolute path (VERY IMPORTANT FIX)
            String projectPath = System.getProperty("user.dir");

            String dirPath = projectPath + "/screenshots/";
            File directory = new File(dirPath);

            // ✅ Create folder if not exists
            if (!directory.exists()) {
                boolean created = directory.mkdirs();
                System.out.println("📁 Screenshot folder created: " + created);
            }

            String filePath = dirPath + testName + "_" + System.currentTimeMillis() + ".png";

            File dest = new File(filePath);
            Files.copy(src.toPath(), dest.toPath());

            // ✅ Debug print
            System.out.println("📸 Screenshot saved at: " + dest.getAbsolutePath());

            return dest.getAbsolutePath();

        } catch (Exception e) {
            System.out.println("Screenshot FAILED due to:");
            e.printStackTrace();
            return null;
        }
    }

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
}
