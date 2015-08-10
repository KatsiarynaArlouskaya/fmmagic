package com.epam.appium_magic.step;

import com.epam.appium_magic.harness.context.AppiumDriverProvider;
import com.epam.appium_magic.harness.pageobject.Screens;
import com.epam.appium_magic.screen.pages.*;
import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;

/**
 * Created by Hleb_Hrybouski on 3/16/2015.
 */
public class EventStepDefs
{
    private static final Logger LOGGER = Logger.getLogger(EventStepDefs.class);
    private AppiumDriver driver = AppiumDriverProvider.driver();
    private EventPage eventPage = new EventPage(driver);


    public void clickEventTab() {
        eventPage.clickEventTab();
    }

    public void clickFirstEvent() {
        eventPage.clickFirstEvent();
    }

    public void changeToAttending() {
        eventPage.changeToAttending();
    }
}
