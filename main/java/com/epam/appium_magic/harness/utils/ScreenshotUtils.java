package com.epam.appium_magic.harness.utils;

import com.epam.appium_magic.harness.context.AppiumDriverProvider;
import com.epam.ta.reportportal.client.core.message.ReportPortalMessage;
import com.google.common.io.ByteSource;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Utility to take screenshot from driver
 * 
 * @author Tatsiana_Lobach
 * 
 */
public class ScreenshotUtils {

   private static final String SCREENSHOTS = "screenshots";

   private static final String DATE_FORMAT = "dd_MMM_yyyy__hh_mm_ssaa_SSS";

   private static String fileSeparator = System.getProperty("file.separator");

   private static final String PATH_TO_SCREENSHOTS = "./build";

   public synchronized static byte[] takeScreenshot(WebDriver driver, String screenshotName) {
      try {

         WebDriver augmentedDriver = new Augmenter().augment(driver);
         byte[] data = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES);

         Date date = new Date();
         DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

         File directory;
         directory = new File(PATH_TO_SCREENSHOTS + fileSeparator + SCREENSHOTS + fileSeparator);
         directory.mkdirs();
         screenshotName = screenshotName + "_" + dateFormat.format(date);
         File f = new File(directory, screenshotName + ".png");
         if (!f.exists()) {
            f.createNewFile();
         }

         try {
            FileUtils.writeByteArrayToFile(f, data);
         } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
         }

         return data;

      } catch (IOException e) {
         throw new RuntimeException(e.getMessage(), e);
      }
   }

   public synchronized static ReportPortalMessage takeScreenshotForReportPortal(String message) {
      byte[] screenshot = ScreenshotUtils.takeScreenshot(AppiumDriverProvider.driver(), UUID.randomUUID().toString());
      return new ReportPortalMessage(ByteSource.wrap(screenshot), message);
   }

}