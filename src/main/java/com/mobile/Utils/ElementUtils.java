package com.mobile.Utils;


import com.google.common.collect.ImmutableMap;
import com.mobile.appium.DriverInit;
import com.mobile.entities.Constants;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class ElementUtils {

    AppiumDriver driver;

    public ElementUtils(AppiumDriver driver) {
        this.driver = driver;
    }


    public void waitForVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.LONG_WAIT));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickElement(WebElement element) {
        waitForVisibility(element);
        element.click();

    }

    public void enterText(WebElement element, String text) {
        waitForVisibility(element);
        element.clear();
        element.sendKeys(text);
    }


    public String getText(WebElement element) {
        waitForVisibility(element);
        if (driver.getCapabilities().getPlatformName().toString().equalsIgnoreCase("android")) {
            return element.getAttribute("text");
        } else {
            return element.getAttribute("label");
        }
    }

    public void closeApp() {
        switch (driver.getCapabilities().getPlatformName().toString().toLowerCase()) {
            case "android":
                ((InteractsWithApps) driver).terminateApp(DriverInit.initProp().getProperty("appPackage"));
                break;
            case "ios":
                ((InteractsWithApps) driver).terminateApp(DriverInit.initProp().getProperty("bundleId"));
        }
    }


    public void launchApp() {
        switch (driver.getCapabilities().getPlatformName().toString().toLowerCase()) {
            case "android":
                ((InteractsWithApps) driver).activateApp(DriverInit.initProp().getProperty("appPackage"));
                break;
            case "ios":
                ((InteractsWithApps) driver).activateApp(DriverInit.initProp().getProperty("bundleId"));
        }
    }

    public WebElement getWebElement(By locator) {
        return driver.findElement(locator);
    }

    public WebElement getWebElementByXpathString(String ele) {
        return driver.findElement(By.xpath(ele));
    }

    public List<WebElement> getWebElementsByXpathString(String ele) {
        return driver.findElements(By.xpath(ele));
    }

    public void scrollDownByCoOrdinate() {
        if (driver.getCapabilities().getPlatformName().toString().equalsIgnoreCase("android")) {
            driver.executeScript("mobile:swipeGesture", ImmutableMap.of(
                    "left", 400,
                    "top", 300,
                    "width",100,
                    "height",600,
                    "direction","up",
                    "percent",0.75
            ));
        } else {
            HashMap<String, Object> params = new HashMap<>();
            params.put("direction" , "up");
              driver.executeScript("mobile:swipe",params);
        }
    }


    public void scrollToElement(String ele) {
        if (driver.getCapabilities().getPlatformName().toString().equalsIgnoreCase("android")) {
             driver.findElement(AppiumBy.androidUIAutomator("" +
                     "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().description(\"Sauce Labs Fleece Jacket\"))\n"));
        }
    else{
            //	  RemoteWebElement element = (RemoteWebElement)getDriver().findElement(By.name("test-ADD TO CART"));
            //	  String elementID = element.getId();
            HashMap<String, String> scrollObject = new HashMap<String, String>();
            //	  scrollObject.put("element", elementID);
            //scrollObject.put("direction", "down");
      	  scrollObject.put("predicateString", "label == 'ADD TO CART'");
      //	  scrollObject.put("name", "test-ADD TO CART");
     //	  scrollObject.put("toVisible", "sdfnjksdnfkld");
          driver.executeScript("mobile:scroll", scrollObject);
        }
    }



}
