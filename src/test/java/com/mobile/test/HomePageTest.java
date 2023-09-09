package com.mobile.test;


import com.mobile.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class HomePageTest extends BaseTest {


    @Test(priority = 1)
    public void verifyProductsTest(){
        ArrayList<String> expectedProductList = new ArrayList<>();
        expectedProductList.add("Sauce Labs Backpack");
        expectedProductList.add("Sauce Labs Bike Light");
        expectedProductList.add("Sauce Labs Bolt T-Shirt");
        expectedProductList.add("Sauce Labs Fleece Jacket");
        loginPage.verifyValidLoginTest(prop.getProperty("username"), prop.getProperty("password"));
        ArrayList<String> actualProductList =homePage.fetchAllProducts("Sauce Labs Fleece Jacket");
        Assert.assertEquals(actualProductList,expectedProductList);
    }

}
