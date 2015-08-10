package com.epam.appium_magic.screen.pages;

import com.epam.appium_magic.harness.pageobject.PageObject;
import com.epam.appium_magic.screen.block.MenuBlock;
import com.epam.qatools.mobileelements.annotations.AndroidFindBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Katsiaryna_Arlouskay on 8/10/2015.
 */
public class EventPage extends PageObject {
    public EventPage(AppiumDriver driver) {
        super(driver);
    }

    private MenuBlock menu;

    @AndroidFindBy(uiAutomator = "className(\"android.widget.LinearLayout\").index(1)")
    private MobileElement firstEvent;

    @AndroidFindBy(uiAutomator = "resourceId(\"fm.last.android:id/attending\")")
    private MobileElement radioBtnAttending;

    @AndroidFindBy(uiAutomator = "resourceId(\"fm.last.android:id/ok\")")
    private MobileElement btnOk;

    @AndroidFindBy(uiAutomator = "text(\"My Events\")")
    private MobileElement btnMyEvents;

    @AndroidFindBy(uiAutomator = "resourceId(\"fm.last.android:id/myevents_list_view\")")
    private MobileElement listOfMyEvents;

    By menuLocator = By.id("android:id/tabs");
    By eventItemLocator = By.id("fm.last.android:id/list_header_title");

    public void clickEventTab() {
        menu.clickEventTab();
    }

    public void clickFirstEvent() {
        firstEvent.click();

    }

    public void changeToAttending() {
        radioBtnAttending.click();
        btnOk.click();
        waitForElement(menuLocator);
    }

    public void clickMyEvents() {
        btnMyEvents.click();
    }

    public int countOfListItem() {
        List<WebElement> events = driver().findElements(eventItemLocator);
        return events.size();
    }

}
