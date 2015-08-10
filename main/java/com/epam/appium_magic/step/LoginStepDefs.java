package com.epam.appium_magic.step;

import com.epam.appium_magic.harness.context.AppiumDriverProvider;
import com.epam.appium_magic.harness.pageobject.Screens;
import com.epam.appium_magic.screen.alerts.GenericAlert;

import com.epam.appium_magic.screen.block.MenuBlock;
import com.epam.appium_magic.screen.pages.LoginPage;
import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

/**
 * Created by Kiryl_Yemelyanenka on 2/17/2015.
 */
public class LoginStepDefs
{
    private static final Logger LOGGER = Logger.getLogger(LoginStepDefs.class);
    private AppiumDriver driver = AppiumDriverProvider.driver();
    LoginPage loginPage = new LoginPage(driver);
    GenericAlert alert = new GenericAlert(driver);


    public void login(String login, String pass)
    {
        loginPage.enterLoginPass(login, pass);
        loginPage.clickLogin();
    }


    public void skipAlertClickNo() {
        alert.clickNo();
    }

    public boolean textPresent(String text) {
        By usernameLocator = By.xpath(".//android.widget.TextView[@text='"+text+"']");
        return loginPage.isElementPresent(usernameLocator);
    }

     public String getNameActiveTab() {
        return loginPage.getNameActiveTab();
    }

    public void clickRadioTab() {
        loginPage.clickRadioTab();
    }
}
