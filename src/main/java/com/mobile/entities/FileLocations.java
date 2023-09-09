package com.mobile.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface  FileLocations {
 String APPLICATION_DIRECTORY = System.getProperty("user.dir");
    public static String OUTPUT_DIRECTORY = System.getenv("OUTPUT_DIRECTORY") != null
            ? "/" + System.getenv("OUTPUT_DIRECTORY") + "/" : "/target/";
     String PARALLEL_XML_LOCATION = OUTPUT_DIRECTORY + "testNG.xml";
    String MOBILE_APP_LOCATION = APPLICATION_DIRECTORY + "/src/test/resources/Apps/";
    String  APP_CONFIG_DIRECTORY = APPLICATION_DIRECTORY + "/src/main/resources/config.properties";

    String  APP_DATA_DIRECTORY = APPLICATION_DIRECTORY + "/src/test/resources/data/testdata.properties";

    String  APP_DIRECTORY = APPLICATION_DIRECTORY + "/src/test/resources/apps";

    public static String currentDateTime() {
        String currentDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return currentDate;
    }



}

