package com.mobile.test;

import com.mobile.base.BaseTest;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;


public class LoginTest extends BaseTest {
    InputStream inputStream;
    JSONObject data;


    @BeforeClass
    public void beforeClass() throws IOException {
        try {
            String dataFileName = "data/data.json";
            inputStream = getClass().getClassLoader().getResourceAsStream(dataFileName);
            JSONTokener tokener = new JSONTokener(inputStream);
            data = new JSONObject(tokener);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if(inputStream!=null){
                inputStream.close();
            }
        }

    }


    @Test(priority = 1)
    public void verifyInvalidLoginUserName() {
        String actualError = loginPage.verifyLoginError("Standard",data.getJSONObject("invalidUser").getString("username") );
        Assert.assertEquals(actualError, prop.getProperty("errorText"));
    }

    @Test(priority = 1)
    public void verifyInvalidLoginPassword() {
        String actualError = loginPage.verifyLoginError(prop.getProperty("username"), "Standard");
        Assert.assertEquals(actualError, prop.getProperty("errorText"));
    }

    @Test(priority = 3)
    public void verifyLoginTest() {
        loginPage.verifyValidLoginTest(prop.getProperty("username"), prop.getProperty("password"));
        String actualHomePageText = homePage.getHomePageText();
        Assert.assertEquals(actualHomePageText, "Products");
    }




}
