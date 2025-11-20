package finalProject.listeners;

import com.aventstack.extentreports.Status;
import finalProject.helpers.CaptureHelper;
import finalProject.helpers.PropertiesHelper;
import finalProject.reports.ExtentReportManager;
import finalProject.reports.ExtentTestManager;
import finalProject.utils.LogUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static int test_total;
    private static int test_passed_total;
    private static int test_failed_total;
    private static int test_skipped_total;

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onStart(ITestContext result) {
        LogUtils.info("\uD83D\uDEE0Setup môi trường onStart: " + result.getStartDate());

        PropertiesHelper.loadAllFiles();
    }

    @Override
    public void onFinish(ITestContext result) {
        LogUtils.info("⭕Kết thúc bộ test: " + result.getEndDate());
        LogUtils.info("Test total: " + test_total);
        LogUtils.info("Test passed total: " + test_passed_total);
        LogUtils.info("Test failed total: " + test_failed_total);
        LogUtils.info("Test skipped total: " + test_skipped_total);

        ExtentReportManager.getExtentReports().flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        LogUtils.info("▶Bắt đầu chạy test case: " + result.getName());
        test_total++;
        //Bắt đầu ghi 1 TCs mới vào Extent Report
        ExtentTestManager.saveToReport(getTestName(result), getTestDescription(result));

        CaptureHelper.startRecord(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info("✅Test case " + result.getName() + " is passed.");
        //Extent Report
        ExtentTestManager.logMessage(Status.PASS, "✅Test case " + result.getName() + " is passed.");

        test_passed_total++;

        CaptureHelper.stopRecord();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.error("❌Test case " + result.getName() + " is failed.");
        LogUtils.error(result.getThrowable());

        //Extent Report
        ExtentTestManager.logMessage(Status.FAIL, result.getThrowable().toString());
        ExtentTestManager.addScreenshot(result.getName());
        ExtentTestManager.logMessage(Status.FAIL, "❌Test case " + result.getName() + " is failed.");

        test_failed_total++;
        CaptureHelper.captureScreenshot(result.getName());

        CaptureHelper.stopRecord();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtils.warn("\uD83D\uDFE1Test case " + result.getName() + " is skipped.");
        LogUtils.warn(result.getThrowable());

        //Extent Report
        ExtentTestManager.logMessage(Status.SKIP, result.getThrowable().toString());
        ExtentTestManager.logMessage(Status.SKIP, "\uD83D\uDFE1Test case " + result.getName() + " is skipped.");

        test_skipped_total++;

        CaptureHelper.stopRecord();
    }
}
