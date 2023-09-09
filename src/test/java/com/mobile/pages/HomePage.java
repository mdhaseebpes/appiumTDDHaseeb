package com.mobile.pages;

import com.mobile.Utils.ElementUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;



public class HomePage {

    private static Logger logger = LogManager.getLogger(HomePage.class);

    public AppiumDriver driver;
    public ElementUtils utils;

    public HomePage(AppiumDriver driver) {
        this.driver = driver;
        utils = new ElementUtils(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"container header\"]/android.widget.TextView")
    @iOSXCUITFindBy(iOSNsPredicate = "label =='Products' OR name =='Products'")
    private WebElement HomepageText;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"products screen\"]//android.widget.TextView[@content-desc=\"store item text\"]")
    @iOSXCUITFindBy(accessibility = "store item text")
    private WebElement productDetails;


    public String getHomePageText() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return utils.getText(HomepageText);

    }

    public ArrayList<String> fetchAllProducts(String product) {
        ArrayList<String> list = new ArrayList<>();
        String productString = "//android.view.ViewGroup[@content-desc=\"products screen\"]//android.widget.TextView[@content-desc=\"store item text\"]";
        List<WebElement> products = utils.getWebElementsByXpathString(productString);
        for (WebElement prod : products) {
            logger.info(prod.getText());
            list.add(prod.getText());
            utils.scrollToElement(product);
            utils.getWebElementsByXpathString(productString);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public void selectAProduct(String product) {
        String productString = "//android.view.ViewGroup[@content-desc=\"products screen\"]//android.widget.TextView[@content-desc=\"store item text\"]";
        List<WebElement> products = utils.getWebElementsByXpathString(productString);
        for (WebElement prod : products) {
            logger.info(prod.getText());
             if(prod.getText().equalsIgnoreCase(product)){
                 prod.click();
             }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            break;
        }
    }



}
