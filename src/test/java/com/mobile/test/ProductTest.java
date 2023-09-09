package com.mobile.test;

import com.mobile.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {



    @Test(priority = 1)
    public void verifyProductPrice(){
        String product  = "Sauce Labs Backpack";

        loginPage.verifyValidLoginTest(prop.getProperty("username"), prop.getProperty("password"));
        homePage.selectAProduct(product);
        productDetails.verifyProductPrice(product,"$29.99");
        productDetails.addToCart();
        String actualProdCount = productDetails.getAddToCount();
        Assert.assertEquals(Integer.parseInt(actualProdCount),1);
    }
}
