package com.epam.appium_magic.screen.pages;

import com.epam.appium_magic.harness.pageobject.PageObject;
import com.epam.appium_magic.screen.block.MenuBlock;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import com.epam.qatools.mobileelements.annotations.AndroidFindBy;

/**
 * Created by Katsiaryna_Arlouskay on 8/6/2015.
 */
public class LoginPage extends PageObject {

    @AndroidFindBy(id = "fm.last.android:id/username")
    MobileElement inputUsername;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"fm.last.android:id/password\")")
    MobileElement inputPassword;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"fm.last.android:id/sign_in_button\")")
    MobileElement btnLogin;


    public LoginPage(AppiumDriver driver) {
        super(driver);
    }


    public void enterLoginPass(String login, String pass) {
        inputUsername.sendKeys(login);
        inputPassword.sendKeys(pass);
    }


    public void clickLogin() {
        btnLogin.click();
    }

    private MenuBlock menu;
    public String getNameActiveTab() {
        return menu.getNameActiveTab();
    }


}
