package com.epam.appium_magic;

import com.epam.appium_magic.harness.context.AppiumDriverProvider;
import com.epam.appium_magic.harness.context.AutomationModule;
import com.epam.appium_magic.step.EventStepDefs;
import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
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
     //   AppiumDriver driver = AppiumDriverProvider.driver();
     //   System.out.print("driver set");
    }


    @AfterClass
    public void teardown(){
       // AppiumDriverProvider.teardown();
    }
}
