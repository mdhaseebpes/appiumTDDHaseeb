package com.mobile.appium;

import com.mobile.Utils.TestUtils;
import com.mobile.entities.FileLocations;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.logging.log4j.ThreadContext;
import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.*;
import java.net.URL;
import java.util.Properties;

public class DriverInit {

    public static ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();
    public static Properties props;
    protected static ThreadLocal<String> dateTime = new ThreadLocal<String>();

    TestUtils utils = new TestUtils();

    /**
     *
     * @param platformName
     * @param platformVersion
     * @param udid
     * @return
     * @throws Exception
     */
    public AppiumDriver beforeSuite(String platformName, String platformVersion, String udid) throws Exception {
        initProp();
        setDateTime(utils.getDateTime());
        AppiumDriver driver;

        String strFile = "logs" + File.separator + platformName + "_" + platformVersion;
        File logFile = new File(strFile);
        if(!logFile.exists()){
            logFile.mkdirs();
        }
        ThreadContext.put("ROUTINGKEY" ,strFile);
        try {
            DesiredCapabilities dc = new DesiredCapabilities();
            URL url = new URL(props.getProperty("appiumURL"));
            dc.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
            dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
            dc.setCapability(MobileCapabilityType.UDID, udid);
           // dc.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            dc.setCapability("newCommandTimeout", 300);

            switch (platformName.toLowerCase()) {
                case "android":
                    dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiAutomator2");
                    dc.setCapability(MobileCapabilityType.FULL_RESET, false);
                    dc.setCapability(MobileCapabilityType.NO_RESET, false);
                    //dc.setCapability("systemPort", systemPort);
                   // dc.setCapability("chromeDriverPort", chromeDriverPort);

                    String androidUrl = FileLocations.APP_DIRECTORY + File.separator + "android" + File.separator + "AndroidSauceLabs.apk";
                    dc.setCapability(MobileCapabilityType.APP, androidUrl);
                    // dc.setCapability("appPackage",prop.getProperty("appPackage"));
                    //  dc.setCapability("appActivity",prop.getProperty("appActivity"));
                    driver = new AndroidDriver(url, dc);
                    setDriver(driver);
                    break;
                case "ios":
                    dc.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 14");
                    dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                    dc.setCapability("simulatorStartupTimeout", 180000);
                   // dc.setCapability("wdaLocalPort", wdaLocalPort);
                    //dc.setCapability("webkitDebug", webkitDebugProxyPort);
                    /*
                   String iosUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" +
                            File.separator + "resources" + File.separator + "apps.ios" + File.separator + "SauceLabs.app";
                   dc.setCapability(MobileCapabilityType.APP, iosUrl);
                   */
                    dc.setCapability("bundleId", props.getProperty("bundleId"));
                    driver = new IOSDriver(url, dc);
                    setDriver(driver);
                    break;

                default:
                    throw new Exception("invalid platform" + platformName);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return driver;
    }

    public static Properties initProp() {
        props = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(FileLocations.APP_CONFIG_DIRECTORY);
            props.load(fileInputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }


    public static AppiumDriver getDriver() {

        return driver.get();
    }

    public void setDriver(AppiumDriver driver2) {
        driver.set(driver2);
    }

    public AppiumDriver getPlatform() {
        return driver.get();
    }

    public AppiumDriver getDeviceName() {
        return driver.get();
    }

    public String getDateTime() {
        return dateTime.get();
    }

    public void setDateTime(String dateTime1) {
        dateTime.set(dateTime1);
    }

    public String getProp(String key) {
        return props.getProperty(key);
    }

    public void setProp(Properties prop1) {
        setProp(prop1);
    }

    public static AppiumDriver getDriver1() {

        return driver.get();
    }

    public static String getScreenShot(){
        File fileSrc = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + File.separator + "build" + File.separator + "screenshot"+ File.separator + System.currentTimeMillis() + ".png";
        File destination = new File(path);
        try {
            FileUtil.copyFile(fileSrc,destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}
