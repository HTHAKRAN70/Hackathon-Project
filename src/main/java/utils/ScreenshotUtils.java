package utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.*;

public class ScreenshotUtils {

    public static String takeScreenshot(WebDriver driver, String testName) {

        try {
            File src = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            String path = "screenshots/" + testName + ".png";
            File dest = new File(path);
            Files.copy(src.toPath() , dest.toPath());

            return path;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
