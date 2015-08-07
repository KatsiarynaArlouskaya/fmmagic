package com.epam.appium_magic;

import com.epam.appium_magic.harness.context.AppiumDriverProvider;
import com.epam.appium_magic.harness.context.AutomationModule;
import com.epam.appium_magic.step.LoginStepDefs;
import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Created by Hleb_Hrybouski on 3/9/2015.
 */

@Guice(modules = { AutomationModule.class })
public class LoginTests
{
    @Inject
    private LoginStepDefs step;

    private String username, login, password;


    @BeforeClass
    @Parameters({"username","login","password"})
    public void setParams(String username, String login, String password){
        this.username = username;
        this.login = login;
        this.password = password;
    }

    @Test
    public void userLoginTest() {
        step.login(login, password);
        step.skipAlertClickNo();
        step.skipAlertClickNo();
        Assert.assertTrue(step.textPresent(username));
        step.clickEvents();

    }

    @AfterSuite
    public void tearDown(){
        AppiumDriverProvider.teardown();
    }
}
