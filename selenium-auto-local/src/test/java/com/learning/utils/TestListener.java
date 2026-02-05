package com.learning.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestListener implements ITestListener {
    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> testReport = new ThreadLocal<>();
    private static WebDriver driver;

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

    public static ExtentTest getTest() {
        return testReport.get();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        testReport.set(test);
        test.log(Status.INFO, "Starting test: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String screenshotPath = takeScreenshot(result.getMethod().getMethodName());
        testReport.get().log(Status.PASS, "Test Passed");
        testReport.get().addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String screenshotPath = takeScreenshot(result.getMethod().getMethodName());
        testReport.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());
        testReport.get().addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testReport.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    private String takeScreenshot(String methodName) {
        String screenshotPath = System.getProperty("user.dir") + "/test-output/screenshots/" + methodName + ".png";
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/test-output/screenshots/"));
            Files.copy(src.toPath(), Paths.get(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "./screenshots/" + methodName + ".png";
    }
}
