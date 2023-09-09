package com.mobile.pages;

import com.mobile.Utils.ElementUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    AppiumDriver driver;
    public ElementUtils utils;
    public LoginPage(AppiumDriver driver) {
        this.driver=driver;
        utils = new ElementUtils(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);

    }

    private By menuAndroid = AppiumBy.accessibilityId("open menu");
    private By menuIOS = AppiumBy.accessibilityId("tab bar option menu");

    private By loginMenuOptionAI = AppiumBy.accessibilityId("menu item log in");

    private By userNameInputAI = AppiumBy.accessibilityId("Username input field");
    private By passwordInputAI = AppiumBy.accessibilityId("Password input field");


    private By loginBtnAI = AppiumBy.accessibilityId("Login button");

    private By loginTextAndroid = AppiumBy.xpath("//*[text='Login']");
    private By loginTextIOS = AppiumBy.iOSNsPredicateString("label == \"Login\" AND name == \"Login\" AND value=\"Login\"");

    private By loginErrorAndroid = AppiumBy.xpath( "//android.view.ViewGroup[@content-desc=\"generic-error-message\"]/android.widget.TextView");
    private By loginErrorIOS = AppiumBy.accessibilityId( "Provided credentials do not match any user in this service.");
    @AndroidFindBy(accessibility = "open menu")
    @iOSXCUITFindBy(accessibility = "tab bar option menu")
    private WebElement menu;

    @AndroidFindBy(accessibility = "menu item log in")
    @iOSXCUITFindBy(accessibility = "menu item log in")
    private WebElement loginMenuOption;

    @AndroidFindBy(accessibility = "menu item log out")
    @iOSXCUITFindBy(accessibility = "menu item log out")
    private WebElement loginOutMenuOption;

    @AndroidFindBy(accessibility = "Username input field")
    @iOSXCUITFindBy(accessibility = "Username input field")
    private WebElement userNameInput;

    @AndroidFindBy(accessibility = "Password input field")
    @iOSXCUITFindBy(accessibility = "Password input field")
    private WebElement passwordInput;

    @AndroidFindBy(accessibility = "Login button")
    @iOSXCUITFindBy(accessibility = "Login button")
    private WebElement loginBtn;

    @AndroidFindBy(xpath = "//*[text='Login']")
    @iOSXCUITFindBy(iOSNsPredicate = "label == \"Login\" AND name == \"Login\" AND value=\"Login\"")
    private WebElement loginText;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"generic-error-message\"]/android.widget.TextView")
    @iOSXCUITFindBy(accessibility = "Provided credentials do not match any user in this service.")
    private WebElement loginError;


    /**
     *
     * @param userName
     * @param password
     * @return
     */

    public String verifyLoginError(String userName , String password){
        utils.clickElement(menu);
        utils.clickElement(loginMenuOption);
        utils.enterText(userNameInput, userName);
        utils.enterText(passwordInput, password);
        if(driver.getCapabilities().getPlatformName().toString().equalsIgnoreCase("ios")) {
           // ((IOSDriver)driver).hideKeyboard();
            utils.clickElement(loginText);
        }
        utils.clickElement(loginBtn);
       return utils.getText(loginError);
    }

    /**
     *
     * @param userName
     * @param password
     * @return
     */
    public void verifyValidLoginTest(String userName, String password) {
        utils.clickElement(menu);
        utils.clickElement(loginMenuOption);
        utils.enterText(userNameInput, userName);
        utils.enterText(passwordInput, password);
        if(driver.getCapabilities().getPlatformName().toString().equalsIgnoreCase("ios")) {
            // ((IOSDriver)driver).hideKeyboard();
            utils.clickElement(loginText);
        }
        utils.clickElement(loginBtn);
    }

    /**
     *
     */
    public void TestSetUp(){
        utils.closeApp();
        utils.launchApp();
        utils.clickElement(menu);
        utils.clickElement(loginOutMenuOption);
    }

}

