package com.epam.appium_magic.screen.block;

import io.appium.java_client.*;
import org.openqa.selenium.*;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.remote.FileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

/**
 * Created by Katsiaryna_Arlouskay on 8/17/2015.
 */

    public class SeekBar extends HtmlElement{

    public void setValue(String value){
        int stepsSeekBar = 100;
        int valueForLeftX = 2;
        int leftX = getLocation().getX();
        int middleX = leftX+(getSize().getWidth()/2);
        int middleY = getLocation().getY()+(getSize().getHeight()/2);
        int targetX = leftX+((Integer.parseInt(value)-valueForLeftX)*getSize().getWidth() / (stepsSeekBar-2*valueForLeftX));
        TouchAction action = new TouchAction((AppiumDriver) getDriver());
        action.press(middleX, middleY ).waitAction(500).moveTo(targetX, middleY).release().perform();
    }
    // check real value, provide more accuracy
    public void setValue(String scrobblePercentage, MobileElement textScroblePercentageValue) {
        int scrobblePercentageTo = Integer.parseInt(scrobblePercentage);
        int stepOnePersent = getSize().width / 100;
        org.openqa.selenium.Point center = getCenter();
        int x = center.x;
        int y = center.y;
        int step;
        AppiumDriver driver = (AppiumDriver) getDriver();
        TouchAction action = new TouchAction(driver);
        driver.performTouchAction(action.press(x, y));

        if (scrobblePercentage.equals("0"))
            driver.performTouchAction(action.moveTo(x -= stepOnePersent * 50, y).release());
        else {
            do {
                step = stepOnePersent * (Integer.parseInt(textScroblePercentageValue.getText()) - scrobblePercentageTo) / 2;
                action.moveTo(x -= step, y);
                driver.performTouchAction(action);
            } while (!textScroblePercentageValue.getText().equals(scrobblePercentage));
            driver.performTouchAction(action.moveTo(x -= step, y).release());
        }
    }

    private WebDriver getDriver(){
        WebElement element = this.getWrappedElement();
        WebDriver driver = ((WrapsDriver) element).getWrappedDriver();
        return  driver;
    }

    public Point getCenter() {
        Point upperLeft = this.getLocation();
        Dimension dimensions = this.getSize();
        return new Point(upperLeft.getX() + dimensions.getWidth() / 2, upperLeft.getY() + dimensions.getHeight() / 2);
    }



}
