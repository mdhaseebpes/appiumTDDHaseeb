package com.mobile.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class initProperties {

    static Properties prop;

    /**
     *
     * @param path
     * @return
     */
    public static Properties initProp(String path){
        prop= new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            prop.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }


}
