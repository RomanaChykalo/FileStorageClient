package allure;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;
import org.testng.annotations.Listeners;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.lang.String.format;

@Listeners
public class CustomListener extends TestListenerAdapter {
   /* private File logFile = new File("test-output/log4j-Allure.log");
    private Logger log = Logger.getLogger(CustomListener.class.getName());

    @Override
    public void onStart(ITestContext context) {
        log.info("-------=======  Test \"" + context.getStartDate().toString()+ "\" started =======-------");
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("-------======= Test \"" + context.getEndDate().toString() + "\" finished =======-------");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info(format("result : SUCCESS : %s", result.getMethod().getMethodName()));

    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("result : FAILURE " + result.getMethod().getMethodName().toUpperCase());
        getScreenshot();
        appendLogToAllure();
        try {
            FileUtils.write(new File("test-output/log4j-Allure.log"), "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Attachment(value = "Test logs", type = "text/html")
    private byte[] appendLogToAllure() {
        try {
            Path path = Paths.get("test-output/log4j-Allure.log");
            return Files.readAllBytes(path);
        } catch (IOException e) {
            log.error("Can't read logs to Allure Report");
        }
        return null;
    }

    @Attachment(value = "screenshot", type = "image/png")
    private byte[] getScreenshot() {
        log.info("Screenshot have been added to Allure Report.");
        return ((TakesScreenshot) DriverLoader.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info(result.getMethod().getMethodName() + "result : SKIPPED");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.info("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName());
    }*/
}
