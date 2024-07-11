package org.lms.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExtentReportListener implements ITestListener {

    private static ExtentReports extent;
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    public static ThreadLocal<Page> page = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("JMS_Automation.html");
        sparkReporter.config().setDocumentTitle("JMS_AutomationReports");
        sparkReporter.config().setReportName("JMS_Automation_Test_Report");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester", "Sakthi");
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testDescription = result.getMethod().getDescription();
        if (testDescription == null || testDescription.isEmpty()) {
            testDescription = result.getMethod().getMethodName();
        }
        ExtentTest extentTest = extent.createTest(testDescription);
        test.set(extentTest);
       /* //
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);*/

        // Log parameters (if any)
        Object[] parameters = result.getParameters();
        for (Object parameter : parameters) {
            test.get().log(Status.INFO, "Parameter: " + parameter);
        }

        // Start tracing for screenshots and snapshots
        Page currentPage = page.get();
        if (currentPage != null) {
            currentPage.context().tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true));
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test Passed");
        saveTrace(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "Test Failed");
        test.get().log(Status.FAIL, result.getThrowable());

        // Capture and add screenshot
        Page currentPage = page.get();
        if (currentPage != null) {
            try {
                String screenshotPath = "screenshots/" + result.getMethod().getMethodName() + ".png";
                Path path = Paths.get(screenshotPath);
                Files.createDirectories(path.getParent());
                currentPage.screenshot(new Page.ScreenshotOptions().setPath(path).setFullPage(true));
                test.get().addScreenCaptureFromPath(screenshotPath);
            } catch (IOException e) {
                test.get().log(Status.WARNING, "Failed to capture screenshot: " + e.getMessage());
            }

        }
        saveTrace(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "Test Skipped");
        saveTrace(result);
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    // Set the Page object for Playwright
    public static void setPage(Page p) {
        page.set(p);
    }

    // Get the current ExtentTest instance
    public static ExtentTest getTest() {
        return test.get();
    }

    // Method to save the trace
    private void saveTrace(ITestResult result) {
        Page currentPage = page.get();
        if (currentPage != null) {
            try {
                String tracePath = "traces/" + result.getMethod().getMethodName() + ".zip";
                Path path = Paths.get(tracePath);
                Files.createDirectories(path.getParent());
                currentPage.context().tracing().stop(new Tracing.StopOptions().setPath(path));
                //test.get().log(Status.INFO, "Trace saved: " + tracePath);
                test.get().log(Status.INFO, "Trace saved: <a href='" + tracePath + "'>" + tracePath + "</a>");

            } catch (IOException e) {
               // test.get().log(Status.WARNING, "Failed to save trace: " + e.getMessage());
                test.get().log(Status.WARNING, "Failed to save trace: " + e.getMessage());

            }
        }
}}
