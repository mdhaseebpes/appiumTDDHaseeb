package com.mobile.base;

import com.mobile.Utils.TestUtils;
import com.mobile.Utils.VideoRecording;
import com.mobile.Utils.initProperties;
import com.mobile.appium.DriverInit;
import com.mobile.entities.FileLocations;
import com.mobile.pages.HomePage;
import com.mobile.pages.LoginPage;
import com.mobile.pages.ProductDetails;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Properties;

public class BaseTest {

    private static Logger logger = LogManager.getLogger(BaseTest.class);

    public static AppiumDriver driver;

    public  Properties prop;
    public DriverInit driverInit;
    protected LoginPage loginPage;

    protected HomePage homePage;

    protected ProductDetails productDetails;

    private static AppiumDriverLocalService server;

    @BeforeSuite
    public void beforeSuite(){
        ThreadContext.put("ROUTINGKEY", "ServerLogs");
         server = getAppiumServiceDefault(); // - Mac
        try {
            if(!checkIfAppiumServerIsRunning(4723)) {
                server.start();
                server.clearOutPutStreams(); // -> Comment this if you want to see server logs in the console
                logger.info("Appium server started");
            } else {
               logger.info("Appium server already running");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkIfAppiumServerIsRunning(int port) throws Exception {
        boolean isAppiumServerRunning = false;
        ServerSocket socket;
        try {
            socket = new ServerSocket(port);
            socket.close();
        } catch (IOException e) {
            logger.info("1");
            isAppiumServerRunning = true;
        } finally {
            socket = null;
        }
        return isAppiumServerRunning;
    }

    @AfterSuite
    public void afterSuite(){
        server.stop();
        logger.info("Appium server stop");
    }


    // for Windows
    public AppiumDriverLocalService getAppiumServerDefault() {
        return AppiumDriverLocalService.buildDefaultService();
    }

    //for Mac
    public AppiumDriverLocalService getAppiumServiceDefault(){
        HashMap<String,String> environment = new HashMap<>();
        environment.put("PATH" ,"/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home/bin:/usr/local/opt/node@20/bin:/Users/mohammedhaseebalikhan/Desktop/Maven/apache-maven-3.8.2/bin:/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home/bin:/Users/mohammedhaseebalikhan/Library/Android/sdk/tools:/Users/mohammedhaseebalikhan/Library/Android/sdk/platform-tools:" +
                "/usr/local/bin:/System/Cryptexes/App/usr/bin:/usr/bin:/bin:/usr/sbin:/sbin" + System.getenv("PATH"));
        environment.put("ANDROID_HOME","/Users/mohammedhaseebalikhan/Library/Android/sdk");
            return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                    .usingDriverExecutable(new File("/usr/local/bin/node"))
                    .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                    .usingPort(4723)
                    .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                    .withEnvironment(environment)
                    .withLogFile(new File("serverLogs/server.log")));
    }

    @Parameters({"platformName", "platformVersion", "udid","deviceName","systemPort",
    "chromeDriverPort", "wdaLocalPort","webkitDebugProxyPort"})
    @BeforeMethod
    public void beforeMethod(String platformName, String platformVersion, String udid ,@Optional String deviceName,
                             @Optional String systemPort,@Optional String chromeDriverPort,
                             @Optional String wdaLocalPort , @Optional String webKitDebugProxyPort) throws Exception {
      /*  if(systemPort== null || chromeDriverPort==null){
            systemPort= "10000";
            chromeDriverPort="11000";
        }
        if(wdaLocalPort== null || webKitDebugProxyPort==null){
            wdaLocalPort= "10001";
            webKitDebugProxyPort="11001";
        }*/
        prop = initProperties.initProp(FileLocations.APP_DATA_DIRECTORY);
        driverInit = new DriverInit();
        driver = driverInit.beforeSuite(platformName,platformVersion,udid);
        VideoRecording.startRecording();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        productDetails = new ProductDetails(driver);
    }

    @AfterMethod
    public void setUp(ITestResult result){
        VideoRecording.stopRecording(result);
    }


    @AfterTest
    public void afterTest(){
        driver.quit();
    }

}
