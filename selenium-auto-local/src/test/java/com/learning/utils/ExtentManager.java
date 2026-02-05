package com.learning.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setTheme(Theme.DARK);
            spark.config().setDocumentTitle("OrangeHRM Automation Report");
            spark.config().setReportName("Automation Test Results");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Project", "OrangeHRM");
            extent.setSystemInfo("Tester", "Bhupinder");
            extent.setSystemInfo("Browser", "Chrome");
        }
        return extent;
    }
}
