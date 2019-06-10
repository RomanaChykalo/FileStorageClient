package driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.PropertyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverLoader {
    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    static {
        System.setProperty(PropertyUtils.getConfigList().get("driver"), PropertyUtils.getConfigList().get("path"));
    }

    private DriverLoader() {
    }

    public static WebDriver getDriver() {
        if (Objects.isNull(driverPool.get())) {
            driverPool.set(new ChromeDriver());
            driverPool.get().manage().window().maximize();
            driverPool.get().manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        }
        return driverPool.get();
   }

    public static void tearDown() {
            driverPool.get().quit();
            driverPool.remove();
    }
    }
