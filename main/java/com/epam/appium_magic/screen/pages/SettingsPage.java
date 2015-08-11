package com.epam.appium_magic.screen.pages;

import com.epam.appium_magic.harness.pageobject.PageObject;
import com.epam.qatools.mobileelements.annotations.AndroidFindBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;


/**
 * Created by Katsiaryna_Arlouskay on 8/10/2015.
 */
public class SettingsPage extends PageObject {
    public SettingsPage(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(uiAutomator = "resourceId(\"android:id/title\").text(\"Settings\")")
    private MobileElement btnSettings;

    @AndroidFindBy(uiAutomator = "text(\"Scrobble Percentage\")")
    private MobileElement btnScrobble;

    @AndroidFindBy(uiAutomator = "className(\"android.widget.SeekBar\")")
    private MobileElement seekBar;

    @AndroidFindBy(uiAutomator = "text(\"OK\")")
    private MobileElement btnOk;

    @AndroidFindBy(uiAutomator = "className(\"android.widget.TextView\").instance(2)")
    private MobileElement textScroblePercentageValue;

    By alertLocator = By.id("android:id/alertTitle");

    public void clickOnSettings() {
        btnSettings.click();
    }

    public void clickOnScrobblePercentage(){
        btnScrobble.click();
        waitForElement(alertLocator);
    }
    public void setScrobblePercentage(String scrobblePercentage) {
        int stepsSeekBar = 100;
        int valueForLeftX = 2;
        int leftX = seekBar.getLocation().getX();
        int middleX = leftX+(seekBar.getSize().getWidth()/2);
        int middleY = seekBar.getLocation().getY()+(seekBar.getSize().getHeight()/2);
        int targetX = leftX+((Integer.parseInt(scrobblePercentage)-valueForLeftX)*seekBar.getSize().getWidth()/(stepsSeekBar-2*valueForLeftX));
        swipe(middleX, middleY, targetX, middleY, 500);

        btnOk.click();
    }

    public String getScrobbleParcentage() {
        System.out.println("value=" + Integer.parseInt(textScroblePercentageValue.getText()));
        return textScroblePercentageValue.getText();
    }
}
