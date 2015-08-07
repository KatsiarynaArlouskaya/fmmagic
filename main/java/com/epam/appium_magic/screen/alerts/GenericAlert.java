package com.epam.appium_magic.screen.alerts;
import com.epam.appium_magic.harness.pageobject.PageObject;


import com.epam.qatools.mobileelements.annotations.AndroidFindBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.openqa.selenium.lift.Matchers.text;

public class GenericAlert extends PageObject
{

    private static final Logger LOGGER = Logger.getLogger(GenericAlert.class);
    private final int EXTENDED_ELEMENT_WAIT_SEC = 300;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"No\")")
    MobileElement btnNo;
    By alertLocator = By.id("android:id/alertTitle");

    public GenericAlert(AppiumDriver driver)
    {
        super(driver);
    }

    public void clickNo(){
        waitForElement(alertLocator, EXTENDED_ELEMENT_WAIT_SEC);
        btnNo.click();
    }
}
