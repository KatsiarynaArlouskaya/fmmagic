package com.epam.appium_magic.harness.context;

import io.appium.java_client.AppiumDriver;

/**
 * Manages thread-safe WebDriver implementation for iOS automation based on
 * Appium
 */
public class AppiumDriverProvider {

   private static final ThreadLocal<AppiumDriver> singleton = new ThreadLocal<AppiumDriver>();

//   private AppiumDriverProvider() {
//   }

   public static boolean isDriverNotNull() {
      return singleton.get() != null;
   }

   public static boolean isDriverNull() {
      return singleton.get() == null;
   }

   /**
    * Gets current web driver instance of creates new on if null
    * 
    * @return Web Driver instance
    */
   public static AppiumDriver driver() {
      if (isDriverNull()) {
         singleton.set(Context.actualConfiguration().ChooseDriver());
      }
      return singleton.get();
   }

   /**
    * 
    * Close WebDriver session and tear down driver instance
    * 
    */
   public static void teardown() {
      if (isDriverNotNull()) {
         driver().quit();
         singleton.set(null);
      }
   }

}
