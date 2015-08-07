package com.epam.appium_magic.harness.context;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.StringUtils.*;

/**
 * 
 * Shared context to keep any execution configuration (properties)
 */
public class Context extends Properties {

   private static final long serialVersionUID = 1761036770016382900L;
   private static String DEFAULT_CONFIGURATION = "lastfm.default.properties";
   private static final String CAPABILITY_PREFIX = "capabilities.";
   private static final String START_PLACEHOLDER = "${";
   private static final String END_PLACEHOLDER = "}";
   private static final String REMOTE_ADDRESS_PROPERTY = "appium.remote.address";
   private static final String IMPLICITLY_WAIT_PROPERTY = "appium.implicity.wait";
   private static String platformType;
   private static final Injector injector = Guice.createInjector(new AutomationModule());

   public static Context configurations(String filePath) {
      Context configuration = new Context();
      try {
         configuration.load(getParametersFromClasspath(filePath));
      } catch (IOException e) {
         throw new IllegalArgumentException("Configuration cannot be loaded", e);
      }

      return configuration;
   }

   public static Injector guice() {
      return injector;
   }

   /**
    * Loads context from default configuration file
    * 
    * @return Context
    */
   public static Context actualConfiguration() {
      return configurations(System.getProperty("context",DEFAULT_CONFIGURATION));
   }

   /**
    * @return device type from properties like -Dplatform=android.phone
    */
   public String getDeviceType(){
      return platformType;
      
   }

    public boolean isAndroid(){
        if (platformType.contains("android")){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isIOS(){
        if (platformType.contains("ios")){
            return true;
        }
        else{
            return false;
        }
    }

   private static InputStream getParametersFromClasspath(String path) {
      return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

   }

   /**
    * Creates WebDriver Desired Capabilities from properties in configuration
    * file. Aggregates properties with prefix <b>capabilities.*</b> and creates
    * object
    * 
    * @return Desired Capabilities
    */
   public DesiredCapabilities capabilities() {
      DesiredCapabilities capabilities = new DesiredCapabilities();
      Iterator<java.util.Map.Entry<Object, Object>> iterator = iterator();
      while (iterator.hasNext()) {
         java.util.Map.Entry<Object, Object> entry = iterator.next();
         String key = entry.getKey().toString();
         if (isCapabilityProperty(key)) {
            capabilities.setCapability(processCapabilityPropertyName(key), entry.getValue());
         }
      }
      return capabilities;
   }

   /**
    * Reads WebDriver implicitly wait
    * 
    * @return implicitly wait
    */
   public long getImplicitlyWait() {
      return Long.valueOf(getProperty(IMPLICITLY_WAIT_PROPERTY));
   }

   public AppiumDriver ChooseDriver() {
      AppiumDriver driver = null;
      if (System.getProperty("platform").startsWith("ios")){
         platformType = System.getProperty("platform").split("\\.")[1];
         driver = iOSDriver();
      } else if (System.getProperty("platform").startsWith("android")){
         platformType = System.getProperty("platform").split("\\.")[0];
         driver = AndroidDriver();
      }
      return driver;
   }

   /**
    * Creates new iOS Driver instance
    * @return ios driver
    */
   public IOSDriver iOSDriver() {
      IOSDriver iosDriver = new IOSDriver(getAppiumServerURL(),capabilities());
      iosDriver.manage().timeouts().implicitlyWait(getImplicitlyWait(), TimeUnit.SECONDS);
      return iosDriver;
   }

   /**
    * Creates new AndroidDriver instance
    * 
    * @return web driver
    */
   public AndroidDriver AndroidDriver() {
      AndroidDriver androidDriver = new AndroidDriver(getAppiumServerURL(), capabilities());
      androidDriver.manage().timeouts().implicitlyWait(getImplicitlyWait(), TimeUnit.SECONDS);
      return androidDriver;
   }

   private String getRemoteAddressProperty() {
      return getProperty(REMOTE_ADDRESS_PROPERTY);
   }

   /**
    * 
    * Reads Appium server URL
    * 
    * @return Appium server remote address
    */
   public URL getAppiumServerURL() {
      try {
         return new URL(getRemoteAddressProperty());
      } catch (MalformedURLException e) {
         throw new IllegalStateException(e);
      }
   }

   @Override
   public synchronized Object put(Object key, Object value) {
      return super.put(key, processPlaceholder(value));
   }

   private boolean isPlaceholder(Object value) {
      return value != null && startsWith(value.toString(), START_PLACEHOLDER);
   }

   private boolean isCapabilityProperty(String key) {
      return startsWith(key, CAPABILITY_PREFIX);
   }

   private String processCapabilityPropertyName(String key) {
      return substringAfter(key, CAPABILITY_PREFIX);
   }

   private Object processSystemPropertyPlaceholder(String value) {
      String argName = substringBefore(substringAfter(value, START_PLACEHOLDER), END_PLACEHOLDER);
      String propertyValue = System.getProperty(argName);
      return propertyValue == null ? "" : propertyValue;
   }

   /**
    * 
    * Handles JVM property placeholder
    * 
    * @param value value
    * @return processed value (replaced from JVM arg, or origin value)
    */
   private Object processPlaceholder(Object value) {
      return isPlaceholder(value) ? processSystemPropertyPlaceholder(value.toString()) : value;
   }

   private Iterator<java.util.Map.Entry<Object, Object>> iterator() {
      return entrySet().iterator();
   }

}
