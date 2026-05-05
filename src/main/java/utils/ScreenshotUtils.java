package utils;

import java.io.File;
import java.nio.file.Files;

import org.openqa.selenium.*;

public class ScreenshotUtils {

    public static String takeScreenshot(WebDriver driver, String testName) {

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
        }
    }
}

//import java.io.File;
//
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//
//import com.google.common.io.Files;
//
//public static String takeScreenshot(WebDriver driver, String testName) {
//
//    try {
//        File dir = new File("screenshots");
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//
//        File src = ((TakesScreenshot) driver)
//                .getScreenshotAs(OutputType.FILE);
//
//        String path = "screenshots/" + testName + ".png";
//        File dest = new File(path);
//
//        Files.copy(src.toPath(), dest.toPath());
//
//        return path;
//
//    } catch (Exception e) {
//        e.printStackTrace();
//        return null;
//    }
//}
