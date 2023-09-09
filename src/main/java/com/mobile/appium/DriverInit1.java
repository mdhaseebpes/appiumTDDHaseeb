/*
package com.mobile.appium;

import com.mobile.Utils.TestUtils;
import com.mobile.entities.FileLocations;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class DriverInit1 {

    public static AppiumDriver driver;
    static Properties prop;
    protected static String dateTime;

    TestUtils utils = new TestUtils();

    public AppiumDriver beforeSuite(String platformName,String platformVersion , String udid) throws Exception {
        initProp();
        dateTime = utils.getDateTime();
        try {
            DesiredCapabilities dc = new DesiredCapabilities();
            URL url = new URL(prop.getProperty("appiumURL"));
            dc.setCapability(MobileCapabilityType.PLATFORM_NAME ,platformName);
            dc.setCapability(MobileCapabilityType.PLATFORM_VERSION,platformVersion);
            dc.setCapability(MobileCapabilityType.UDID ,udid);
            dc.setCapability("newCommandTimeout", 300);

            switch (platformName.toLowerCase()) {
                case "android":
                    dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiAutomator2");
                    dc.setCapability(MobileCapabilityType.FULL_RESET, false);
                    dc.setCapability(MobileCapabilityType.NO_RESET, false);

                    String androidUrl = FileLocations.APP_DIRECTORY + File.separator + "android"
                            + File.separator + "AndroidSauceLabs.apk";
                    dc.setCapability(MobileCapabilityType.APP, androidUrl);
                    // dc.setCapability("appPackage",prop.getProperty("appPackage"));
                    //  dc.setCapability("appActivity",prop.getProperty("appActivity"));
                    driver = new AndroidDriver(url, dc);
                    break;
                case "ios":

                    dc.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 14");
                    dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                    dc.setCapability("simulatorStartupTimeout", 180000);
                    */
/*
                   String iosUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" +
                            File.separator + "resources" + File.separator + "apps.ios" + File.separator + "SauceLabs.app";
                   dc.setCapability(MobileCapabilityType.APP, iosUrl);
                   *//*

                    dc.setCapability("bundleId", prop.getProperty("bundleId"));
                    driver = new IOSDriver(url, dc);
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

    public static Properties initProp(){
        prop= new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(FileLocations.APP_CONFIG_DIRECTORY);
            prop.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }


    public void setDriver(AppiumDriver driver){
        this.driver=driver;
    }

    public static AppiumDriver getDriver(){
        return driver;
    }

    public AppiumDriver getPlatform(){
        return driver;
    }

    public AppiumDriver getDeviceName(){
        return driver;
    }

    public static String getDateTime(){
        return dateTime;
    }



*/
/*    public AppiumDriver getDriver(){

        return driver.get();
    }

    public void setDriver(AppiumDriver driver2){
        driver.set(driver2);
    }

    public AppiumDriver getPlatform(){
        return driver.get();
    }

    public AppiumDriver getDeviceName(){
        return driver.get();
    }

    public String getDateTime(){
        return dateTime.get();
    }

    public void setDateTime(String dateTime1){
        dateTime.set(dateTime1);
    }

    public Properties getProp(){
        return prop.get();
    }

    public void setProp(Properties prop1){
        setProp(prop1);
    }*//*



}
*/
