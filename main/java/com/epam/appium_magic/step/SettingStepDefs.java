package com.epam.appium_magic.step;

import com.epam.appium_magic.harness.context.AppiumDriverProvider;
import com.epam.appium_magic.harness.context.Context;
import com.epam.appium_magic.harness.pageobject.Screens;

import com.epam.appium_magic.screen.pages.EventPage;
import com.epam.appium_magic.screen.pages.SettingsPage;
import com.epam.qatools.mobileelements.annotations.AndroidFindBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.apache.log4j.Logger;

/**
 * Created by Hleb_Hrybouski on 3/20/2015.
 */
public class SettingStepDefs
{
    private static final Logger LOGGER = Logger.getLogger(SettingStepDefs.class);
    private AppiumDriver driver = AppiumDriverProvider.driver();
    private SettingsPage settingsPage = new SettingsPage(driver);


    public void goToSettings() {
//        if (System.getProperty("platform").startsWith("android")){
//            ((AndroidDriver) driver).sendKeyEvent(AndroidKeyCode.MENU);
//            settingsPage.clickOnSettings();
//        }

        if (driver instanceof  AndroidDriver){
            ((AndroidDriver) driver).sendKeyEvent(AndroidKeyCode.MENU);
            settingsPage.clickOnSettings();
        }
    }

    public void setScrobblePercentage(String scrobblePercentage) {
        settingsPage.clickOnScrobblePercentage();
        settingsPage.setScrobblePercentage(scrobblePercentage);

    }

    public String getScrobbleParcentage() {
        settingsPage.clickOnScrobblePercentage();
        return settingsPage.getScrobbleParcentage();
    }

    public void goBack() {
        driver.navigate().back();
    }
}
