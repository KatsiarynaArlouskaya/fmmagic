package com.epam.appium_magic.step;

import com.epam.appium_magic.harness.context.AppiumDriverProvider;
import com.epam.appium_magic.harness.pageobject.Screens;

import com.epam.appium_magic.screen.pages.SearchPage;
import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;

/**
 * Created by Hleb_Hrybouski on 3/10/2015.
 */
public class SearchStepDefs
{
    private static final Logger LOGGER = Logger.getLogger(LoginStepDefs.class);
    private AppiumDriver driver = AppiumDriverProvider.driver();
    private SearchPage searchPage= new SearchPage(driver);

    public void clickSearchTab() {
        searchPage.clickSearchTab();
    }

    public void toDoSearch(String searchRequest) {
        searchPage.enterInSearch(searchRequest);
        searchPage.clickSearch();
    }

    public boolean presentInList(String searchResult) {
        return searchPage.presentInList(searchResult);
    }
}
