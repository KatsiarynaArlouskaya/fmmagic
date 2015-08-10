package com.epam.appium_magic;

import com.epam.appium_magic.harness.context.AutomationModule;
import com.epam.appium_magic.step.SearchStepDefs;
import com.google.inject.Inject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Guice;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
/**
 * Created by Hleb_Hrybouski on 3/10/2015.
 */
@Guice(modules = {AutomationModule.class})
public class SearchTests
{
    @Inject
    private SearchStepDefs step;

    private String artist, nameSong;

    @BeforeClass
    @Parameters({"artist","nameSong"})
    public void setParams(String artist, String nameSong){
        this.artist = artist;
        this.nameSong = nameSong;
    }

    @Test
    public void searchTest()
    {
        String searchRequest="System of a Down";
        String searchResult="Forest";
        step.clickSearchTab();
        step.toDoSearch(searchRequest);
        Assert.assertTrue(step.presentInList(searchResult));
        step.clickOnSearchResult(searchResult);

    }

}
