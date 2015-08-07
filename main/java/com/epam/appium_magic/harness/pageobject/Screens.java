package com.epam.appium_magic.harness.pageobject;

import com.epam.appium_magic.harness.context.AppiumDriverProvider;
import com.epam.qatools.mobileelements.loader.MobileElementLoader;
import io.appium.java_client.AppiumDriver;

import java.lang.reflect.Constructor;

/**
 *
 * Shared context and factory for Page Objects reusing driver instance from
 * driver provider
 *
 * @author Dzianis_Shlychkou
 *
 */
public class Screens {

   private final AppiumDriver appiumDriver;

   public Screens(AppiumDriver appiumDriver) {
      this.appiumDriver = appiumDriver;
   }

   /**
    * Helper method to get page object on demand creating Pages object on the
    * fly
    *
    *           Object class
    * @return page Object instance
    */
   public static <T> T on(Class<T> klass) {
      return new Screens(AppiumDriverProvider.driver()).get(klass);
   }

   /**
    * Initialize page object with driver and populate mobile elements
    *
    *           object class
    * @return page object populated instance
    */
   public <T> T get(Class<T> klass) {

      try {
         Constructor<T> constructor = klass.getConstructor(AppiumDriver.class);
         T page = constructor.newInstance(driver());
         MobileElementLoader.populate(page, driver());
         return page;
      } catch (Exception e) {
         throw new IllegalStateException("PageObject of type {" + klass + "} cannot be created", e);
      }
   }

   public AppiumDriver driver() {
      return appiumDriver;
   }

}
