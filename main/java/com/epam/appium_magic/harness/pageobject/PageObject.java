package com.epam.appium_magic.harness.pageobject;

import com.epam.appium_magic.harness.context.AppiumDriverProvider;
import com.epam.appium_magic.harness.utils.ScreenshotUtils;
import com.epam.qatools.mobileelements.annotations.AndroidFindBy;
import com.epam.qatools.mobileelements.annotations.IOSFindBy;
import com.epam.qatools.mobileelements.loader.MobileElementLoader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.List;

/**
 * Base class for any page object
 */
public class PageObject {


   private static final Logger LOGGER = Logger.getLogger(PageObject.class);

   private final int DEFAULT_ELEMENT_WAIT_SEC = 30;

   private final AppiumDriver driver;

   public PageObject(AppiumDriver driver) {
      MobileElementLoader.populate(this, driver);
      this.driver = driver;
   }

   public AppiumDriver driver() {
      return driver;
   }

   public void waitForElement(final By by, long timeOutInSeconds) {
      LOGGER.info("Waiting for element '" + by.toString() + "' exists during " + timeOutInSeconds + "sec timeout ...");
      new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.visibilityOfElementLocated(by));
   }

   public void waitForElement(final By by) {
      waitForElement(by, DEFAULT_ELEMENT_WAIT_SEC);
   }

   public void waitForVisibility(MobileElement element){
      waitForVisibility(element,DEFAULT_ELEMENT_WAIT_SEC);
   }

   public void waitForVisibility(MobileElement element, int timeout){
      LOGGER.info("Waiting for element visibility");
      new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
   }

   public boolean isStaticTextPresent(String text) {
      LOGGER.info("Verify static text {" + text + "} presents on screen");
      return isElementPresent(By.xpath(String.format(".staticTexts().firstWithPredicate(\"value=='%s'\")", text)));
   }

   public boolean isElementPresent(By locator) {
      LOGGER.info("Verify {" + locator + "} presents on screen");
      List<WebElement> elements = driver().findElements(locator);
      return !elements.isEmpty() && elements.get(0).isDisplayed();
   }

   public boolean isElementTableGroup(WebElement element) {
      return "UIATableGroup".equalsIgnoreCase(element.getTagName());
   }

   public boolean isElementContainsStaticText(WebElement element, String text) {
      List<WebElement> elements = element.findElements(By.xpath("//UIAStaticText[1]"));
      return !elements.isEmpty() && StringUtils.equalsIgnoreCase(text, elements.get(0).getAttribute("value"));
   }

   public WebElement getInnerStaticText(WebElement element) {
      return element.findElement(By.xpath("//UIAStaticText[1]"));
   }

   public void swipeLeft() {
      LOGGER.info("Swipe left");
      swipe(300, 250, 25, 250, 1000);
   }

   public void swipeRight() {
      LOGGER.info("Swipe right");
      swipe(50, 250, 250, 250, 500);
   }

   public void swipeUp() {
      LOGGER.info("Swipe Up");
      swipe(150, 220, 150, 100, 500);
   }

   public void swipeDown() {
      LOGGER.info("Swipe down");
      swipe(150, 100, 150, 220, 500);
   }

    @AndroidFindBy(id = "fm.last.android:id/home")
    @IOSFindBy(xpath = "//UIATabBar/UIAButton[@name='Profile']")
    private MobileElement activeScreen;

    public void swipeJS(double startX, double startY, double endX, double endY, double duration) {
        JavascriptExecutor js = driver();
        HashMap<Object, Object> swipeObject = new HashMap<Object, Object>();
        swipeObject.put("startX", startX);
        swipeObject.put("startY", startY);
        swipeObject.put("endX", endX);
        swipeObject.put("endY", endY);
        swipeObject.put("duration", duration);
        js.executeScript("mobile: swipe", swipeObject);
    }

    public void swipe(int startX, int startY, int endX, int endY, int duration )
    {
        TouchAction action = new TouchAction(driver);
        action.press(startX, startY).waitAction(duration).moveTo(endX, endY).release().perform();
    }
   /**
    * Move 1 screen view
    *
    * @param direction
    */
   public void move(String direction) {
      JavascriptExecutor js = AppiumDriverProvider.driver();
      HashMap<Object, Object> scrollMap = new HashMap<Object, Object>();
      scrollMap.put("direction", direction);
      js.executeScript("mobile: scroll", scrollMap);
   }

   /**
    * Move up for 1 view (screen)
    */
   public void moveUp() {
      move("up");
   }

   /**
    * Move down for 1 view (screen)
    */
   public void moveDown() {
      move("down");
   }

   public void takeScreenshot(String message) {
      try {
         LOGGER.info(ScreenshotUtils.takeScreenshotForReportPortal(message));
      } catch (Throwable ignored){}
   }

   @AfterMethod
   public void tearDown(ITestResult result) {
      if (result.getStatus() == ITestResult.FAILURE) {
         takeScreenshot("fail screenshot");
      }
   }

    @AfterSuite
    public void tearDown()
    {
        AppiumDriverProvider.teardown();
    }

}
