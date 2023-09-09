package com.mobile.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ExtentReportListener implements ITestListener {

	private static Logger logger = LogManager.getLogger(ExtentReportListener.class);
	private static final String OUTPUT_FOLDER = "./build/";
	private static final String FILE_NAME = "MobileAutomationReport.html";

	private static ExtentReports extent = init();
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	private static ExtentReports extentReports;
	static Map<Integer ,ExtentTest> extentTestMap = new HashMap<>();


	private static ExtentReports init() {

		Path path = Paths.get(OUTPUT_FOLDER);
		// if directory exists?
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				// fail to create directory
				e.printStackTrace();
			}
		}

		extentReports = new ExtentReports();
		ExtentSparkReporter reporter = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
		reporter.config().setDocumentTitle("CreatorsAPIReport");
		reporter.config().setReportName("API Creators Test Results");
		reporter.config().setTheme(Theme.DARK);
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("Team", "Creators Automation");
		extentReports.setSystemInfo("ENV NAME", "QA");
		return extentReports;
	}

	public synchronized void onStart(ITestContext context) {
		logger.info("Test Suite started!");

	}

	public synchronized void onFinish(ITestContext context) {
		logger.info(("Test Suite is ending!"));
		extent.flush();
		test.remove();
	}

	public synchronized void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String qualifiedName = result.getMethod().getQualifiedName();
		int last = qualifiedName.lastIndexOf(".");
		int mid = qualifiedName.substring(0, last).lastIndexOf(".");
		String className = qualifiedName.substring(mid + 1, last);

		logger.info(methodName + " started!");
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
				result.getMethod().getDescription());

		extentTest.assignCategory(result.getTestContext().getSuite().getName());
		/*
		 * methodName = StringUtils.capitalize(StringUtils.join(StringUtils.
		 * splitByCharacterTypeCamelCase(methodName), StringUtils.SPACE));
		 */
		extentTest.assignCategory(className);
		test.set(extentTest);
		test.get().getModel().setStartTime(getTime(result.getStartMillis()));
	}

	public synchronized void onTestSuccess(ITestResult result) {
		logger.info((result.getMethod().getMethodName() + " passed!"));
		test.get().pass("Test passed");
		//test.get().pass(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	public synchronized void onTestFailure(ITestResult result) {
		logger.info((result.getMethod().getMethodName() + " failed!"));
		test.get().fail(result.getThrowable());
		//test.get().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	public synchronized void onTestSkipped(ITestResult result) {
		logger.info((result.getMethod().getMethodName() + " skipped!"));
		//test.get().skip(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		logger.info(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}


}
