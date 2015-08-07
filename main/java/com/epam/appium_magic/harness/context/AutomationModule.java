package com.epam.appium_magic.harness.context;

import com.epam.appium_magic.step.*;
import com.google.inject.AbstractModule;

public class AutomationModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(LoginStepDefs.class);
      bind(SearchStepDefs.class);
      bind(EventStepDefs.class);
      bind(SettingStepDefs.class);
   }
}
