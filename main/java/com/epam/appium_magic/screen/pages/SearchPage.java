package com.epam.appium_magic.screen.pages;

import com.epam.appium_magic.harness.pageobject.PageObject;
import com.epam.appium_magic.screen.block.MenuBlock;
import com.epam.qatools.mobileelements.annotations.AndroidFindBy;
import com.epam.qatools.mobileelements.annotations.AndroidFindBys;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Katsiaryna_Arlouskay on 8/10/2015.
 */
public class SearchPage extends PageObject {
    public SearchPage(AppiumDriver driver) {
        super(driver);
    }

    private final int EXTENDED_ELEMENT_WAIT_SEC = 300;
    private MenuBlock menu;

    @AndroidFindBy(id = "fm.last.android:id/station_editbox")
    private MobileElement searchForm;

    @AndroidFindBy(id = "fm.last.android:id/search")
    private MobileElement searchButton;


    By searchResultsPresentLocator = By.id("fm.last.android:id/row_icon");

    public void clickSearchTab() {
        menu.clickSearchTab();
    }

    public void enterInSearch(String searchRequest) {
        searchForm.sendKeys(searchRequest);
    }

    public void clickSearch() {
        searchButton.click();
        waitForElement(searchResultsPresentLocator, EXTENDED_ELEMENT_WAIT_SEC);
    }

    public boolean presentInList(String searchResult) {
       // return isElementPresent(By.xpath("//android.widget.TextView[@text='System of a Down - Forest']"));
        By searchLocator = new MobileBy.ByAndroidUIAutomator("new UiSelector().textContains(\""+searchResult+"\")");
       return isElementPresent(searchLocator);
    }
}
