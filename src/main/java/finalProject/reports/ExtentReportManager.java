package finalProject.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static final ExtentReports extentReports = new ExtentReports();

    // Setup thông tin, hình dạng của report: muốn report name gì, sáng tối màu mè ra sao
    public synchronized static ExtentReports getExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("exports/reports/extentreport/extentreport.html");
        reporter.config().setReportName("Extent Report | Final Project");
//        reporter.config().setTheme(Theme.DARK);
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Framework Name", "Selenium Java | Final Project");
        extentReports.setSystemInfo("Author", "KP");
        return extentReports;
    }
}
