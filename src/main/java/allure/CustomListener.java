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
        appendLogToAllure();
        removeLogs();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info(format(" Result : SUCCESS : %s", result.getMethod().getMethodName()));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("result : FAILURE " + result.getMethod().getMethodName().toUpperCase());
       // saveScreenshotToReport(result);
        appendLogToAllure();
    }

    @Attachment(value = "Test logs", type = "text/html")
    private byte[] appendLogToAllure() {
        try {
            log.info("Start read logs...........");
            Path path = Paths.get("test-output/log4j-Allure.log");
            return Files.readAllBytes(path);
        } catch (IOException e) {
            log.error("Can't read logs to Allure Report");
        }
        return null;
    }

    /*private void saveScreenshotToReport(ITestResult result) {
        if (!result.isSuccess()) {
            try {
                System.setProperty("org.uncommons.reportng.escape-output", "false");
                String failureImageFileName = new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime());
                File scrFile = ((TakesScreenshot) DriverLoader.getDriver()).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File(failureImageFileName));
                String userDirector = System.getProperty("user.dir") + "/";
                String screenCapture = "<table><tr><td><font style=\"text-decoration: underline;\" " +
                        "size=\"3\" face=\"Comic sans MS\" color=\"green\"><b>Screenshot</b></font></td></tr> ";
                Reporter.log(screenCapture);
                Reporter.log("<tr><td><a href=\"" + userDirector + failureImageFileName + "\"><img src=\"file:///"
                        + userDirector + failureImageFileName + "\" alt=\"\"" + "height=’120′ width=’120′/></td></tr> ");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }*/

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info(result.getMethod().getMethodName() + "result : SKIPPED");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.info(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
    }

    public void removeLogs() {
        try {
            FileUtils.write(new File(logPath), "", "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
