package com.epam.appium_magic.screen.block;

import com.epam.qatools.mobileelements.annotations.AndroidBlock;
import com.epam.qatools.mobileelements.annotations.AndroidFindBy;
import com.epam.qatools.mobileelements.element.BlockElement;
import io.appium.java_client.MobileElement;

/**
 * Created by Katsiaryna_Arlouskay on 8/7/2015.
 */
@AndroidBlock(@AndroidFindBy(id = "android:id/tabs"))
public class MenuBlock extends BlockElement {

//    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Events\")")
    @AndroidFindBy(uiAutomator = "text(\"Events\")")
    private MobileElement tabEvents;
   @AndroidFindBy(name = "Search")
   private MobileElement tabSearch;
    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").selected(true)")
    private MobileElement activeTab;
 //   @AndroidFindBy(xpath = "//android.widget.TextView[@text='Radio']")
 //   private MobileElement tabRadio;


/*    @AndroidFindBy(using = )
    private MobileElement tabSearch;*/

    public void clickEventTab(){
        tabEvents.click();
    }

    public void clickSearchTab(){
        tabSearch.click();
    }

    public void clickRadioTab(){
//        tabRadio.click();
    }


    public String getNameActiveTab() {
        return activeTab.getText();
    }
}
