package com.epam.appium_magic;

import com.epam.appium_magic.harness.context.AutomationModule;
import com.epam.appium_magic.step.SettingStepDefs;
import com.google.inject.Inject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Guice;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by Hleb_Hrybouski on 3/20/2015.
 */

@Guice(modules = {AutomationModule.class})
public class SettingsTests
{
    @Inject
    private SettingStepDefs step;

    private String scrobblePercentage;


    @BeforeClass
    @Parameters({"scrobblePercentage"})
    public void setParams(String scrobblePercentage)
    {
        this.scrobblePercentage=scrobblePercentage;
    }

    @Test
    public void changeSettings()
    {
        step.goToSettings();
        step.setScrobblePercentage(scrobblePercentage);
        Assert.assertEquals(step.getScrobbleParcentage(), scrobblePercentage);
        step.goBack();
        step.goBack();
    }


}
