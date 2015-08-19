package com.epam.appium_magic;

import com.epam.appium_magic.harness.context.AppiumDriverProvider;
import com.epam.appium_magic.harness.context.AutomationModule;
import com.epam.appium_magic.step.EventStepDefs;
import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.testng.annotations.*;

/**
 * Created by Hleb_Hrybouski on 3/16/2015.
 */
@Guice(modules = { AutomationModule.class })
public class EventTests
{
    @Inject
    private EventStepDefs step;

    private String artist;

    @BeforeClass
    @Parameters({"artist"})
    public void setParams(String artist){
        this.artist = artist;
    }

    @Test
    public void addEvent()
    {
        int containEvents = 1;
        step.clickEventTab();
        step.clickFirstEvent();
        step.changeToAttending();
        step.back();
        step.clickEventTab();
        step.clickMyEvents();
        Assert.assertEquals(step.countOfEvents(), containEvents);

    }

}
