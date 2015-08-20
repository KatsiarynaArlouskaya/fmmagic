package com.epam.appium_magic.screen.pages;

import com.epam.appium_magic.harness.pageobject.PageObject;
import com.epam.qatools.mobileelements.element.SeekBar;
import com.epam.qatools.mobileelements.annotations.AndroidFindBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.*;
import org.openqa.selenium.internal.WrapsDriver;

import java.awt.*;


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
    private SeekBar seekBar;

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

       // seekBar.setValue(scrobblePercentage);
        //        WebElement element = this.getWrappedElement();
        //WebDriver driver = ((WrapsDriver) element).getWrappedDriver();
        int scrobblePercentageTo = Integer.parseInt(scrobblePercentage);
        int stepOnePersent = seekBar.getSize().width / 100;
        org.openqa.selenium.Point center = seekBar.getCenter();
        int x = center.x;
        int y = center.y;

        TouchAction action = new TouchAction(driver());

        driver().performTouchAction(action.press(x, y));

        if (scrobblePercentage.equals("0"))
            driver().performTouchAction(action.moveTo(x -= stepOnePersent * 50, y).release());
        else {
            do {
                int step = stepOnePersent * (Integer.parseInt(textScroblePercentageValue.getText()) - scrobblePercentageTo) / 2;
                action.moveTo(x -= step, y);
                driver().performTouchAction(action);
            } while (!textScroblePercentageValue.getText().equals(scrobblePercentage));
            driver().performTouchAction(action.moveTo(x, y).release());
        }

        btnOk.click();
    }

    public String getScrobbleParcentage() {
        System.out.println("value=" + Integer.parseInt(textScroblePercentageValue.getText()));
        return textScroblePercentageValue.getText();
    }
}
