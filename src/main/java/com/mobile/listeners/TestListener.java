package com.mobile.listeners;

import com.mobile.appium.DriverInit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    public void onTestFailure(ITestResult result){
        if(result.getThrowable()!=null){
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            result.getThrowable().printStackTrace(pw);
            System.out.println(sw);

            Map<String,String> params = new HashMap<>();
            params = result.getTestContext().getCurrentXmlTest().getAllParameters();

            DriverInit driverInit = new DriverInit();

            String imagePath = "screenshots" + File.separator + params.get("platformName") +
                    "_" + params.get("platformVersion")  + File.separator +
                   // DriverInit.getDateTime() +
                    driverInit.getDateTime() +
                    File.separator + result.getTestClass().getRealClass().getSimpleName()+
                    File.separator + result.getTestClass().getName() + ".png";
            String completeImagePath = System.getProperty("user.dir")+ File.separator + imagePath;

           //File file = DriverInit.getDriver().getScreenshotAs(OutputType.FILE);
            File file = DriverInit.getDriver().getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(file ,new File(imagePath));
                Reporter.log("Sample Screenshot");
                Reporter.log("<a href='"+completeImagePath+"'> <img " +
                        "src='"+completeImagePath+"' height='100' width='100'/> </a>");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
