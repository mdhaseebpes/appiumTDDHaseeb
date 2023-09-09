package com.mobile.pages;

import com.mobile.Utils.ElementUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ProductDetails {

    public AppiumDriver driver;
    public ElementUtils utils;

    public ProductDetails(AppiumDriver driver) {
        this.driver = driver;
        utils = new ElementUtils(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"container header\"]/android.widget.TextView")
    @iOSXCUITFindBy(accessibility = "Sauce Labs Backpack")
    private WebElement productBag;


    @AndroidFindBy(accessibility = "product price")
    @iOSXCUITFindBy(accessibility = "product price")
    private WebElement productPriceEle;

    @AndroidFindBy(accessibility = "Add To Cart button")
    @iOSXCUITFindBy(accessibility = "Add To Cart button")
    private WebElement addToCart;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"cart badge\"]/android.widget.TextView")
    @iOSXCUITFindBy(accessibility = "cart badge")
    private WebElement addToNotification;

    public void verifyProductPrice(String productName, String productPrice){
        utils.waitForVisibility(productBag);
        Assert.assertEquals(productBag.getText(),productName);
        Assert.assertEquals(productPriceEle.getText(),productPrice);
    }

    public void addToCart(){
        utils.clickElement(addToCart);

    }

    public String getAddToCount(){
        utils.waitForVisibility(addToNotification);
       return  utils.getText(addToNotification);

    }
}
