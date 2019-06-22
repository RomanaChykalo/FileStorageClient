package allure;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static Logger log = LogManager.getLogger(CustomListener.class);
    private String logPath = "test-output/log4j-Allure.log";

    @Override
    public void onStart(ITestContext context) {
        log.info("------------------TEST START---------------------- ");
        log.info("TEST STARTED in time: " + context.getStartDate().toString());
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("TEST FINISHED in time: " + context.getEndDate().toString());
        log.info("------------------TEST FINISH---------------------- ");
        removeLogs();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info(format(" Result : SUCCESS : %s", result.getMethod().getMethodName()));
        appendLogToAllure();
        removeLogs();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("result : FAILURE " + result.getMethod().getMethodName().toUpperCase());
        appendLogToAllure();
        removeLogs();
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

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info(result.getMethod().getMethodName() + "result : SKIPPED");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.info(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
    }

    private void removeLogs() {
        try {
            FileUtils.write(new File(logPath), "", "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
