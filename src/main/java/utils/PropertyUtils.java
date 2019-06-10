package utils;

import driver.DriverLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyUtils {
    private static Map<String, String> configList = new PropertyUtils().getInfoFromPropertyFile();

    private Map<String, String> getInfoFromPropertyFile() {
        Properties prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        configList = new HashMap<>();
        configList.put("driver", prop.getProperty("driver"));
        configList.put("path", prop.getProperty("path"));
        configList.put("csv_path", prop.getProperty("csv_path"));
        configList.put("cite", prop.getProperty("cite"));
        return configList;
    }

    public static Map<String, String> getConfigList() {
        return configList;
    }
}
