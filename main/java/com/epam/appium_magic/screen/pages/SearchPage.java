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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    @AndroidFindBy(id = "android:id/list")
    private MobileElement listOfResults;


    By searchResultsPresentLocator = By.id("fm.last.android:id/row_icon");
    By webView = By.id("fm.last.android:id/webview");


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
      By searchLocator = new MobileBy.ByAndroidUIAutomator("textContains(\""+searchResult+"\")");
      List<WebElement> elements = listOfResults.findElements(searchLocator);
        if (elements.size()==0) {
            new WebDriverWait(driver(), EXTENDED_ELEMENT_WAIT_SEC).until(ExpectedConditions.visibilityOf(driver().scrollTo(searchResult)));
            elements = listOfResults.findElements(searchLocator);
    }
      return (!elements.isEmpty() && elements.get(0).isDisplayed());
    }

    public void clickOnSearchResult(String searchResult) {
        By searchLocator = new MobileBy.ByAndroidUIAutomator("textContains(\""+searchResult+"\")");
        listOfResults.findElement(searchLocator).click();
        waitForElement(webView);
    }
}
