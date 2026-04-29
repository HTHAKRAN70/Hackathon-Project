package config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();

    static {
        try {
            InputStream input = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream("ObjectRepo/config.properties");

            if (input == null) {
                throw new RuntimeException("Config.properties not found in resources folder");
            }

            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load Config.properties");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}