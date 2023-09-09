package com.mobile.Utils;

import com.mobile.appium.DriverInit;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.screenrecording.CanRecordScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.Map;


public class VideoRecording {

    private static Logger logger = LogManager.getLogger(VideoRecording.class);
    static AppiumDriver driver;
    public static void startRecording(){
        DriverInit driverInit = new DriverInit();
        logger.info("Starting video recording");
        ((CanRecordScreen)driverInit.getDriver()).startRecordingScreen();
    }

    public static void stopRecording(ITestResult result){
        DriverInit driverInit = new DriverInit();
        logger.info("Stopping video recording");
       String media = ((CanRecordScreen)driverInit.getDriver()).stopRecordingScreen();

       if(result.getStatus()==2){
           logger.info("Capturing video recording on failure -" + result.getName() + " " + result.getStatus() );
           Map<String,String> params = result.getTestContext().getCurrentXmlTest().getAllParameters();


           String dir =  "videos" +  File.separator + params.get("platformName") +
                   "_" + params.get("platformVersion")  + File.separator +
                   driverInit.getDateTime() +
                   File.separator + result.getTestClass().getRealClass().getSimpleName();

           File videoDir = new File(dir);

           synchronized (videoDir) {
               if (!videoDir.exists()) {
                   videoDir.mkdirs();
               }
           }
           try{
               FileOutputStream stream = new FileOutputStream(videoDir + File.separator +
                       result.getName()+".mp4");
               stream.write(Base64.getDecoder().decode(media));
           }catch (Exception ex){
               ex.printStackTrace();
           }
       }



    }
}
