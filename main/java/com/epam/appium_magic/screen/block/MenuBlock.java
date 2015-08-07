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
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Events']")
    private MobileElement tabEvents;


}
