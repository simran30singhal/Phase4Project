package com.test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

public class Day2 {

  private AndroidDriver driver;

  @Before
  public void setUp() throws MalformedURLException {
    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    desiredCapabilities.setCapability("platformName", "Android");
    desiredCapabilities.setCapability("appium:platformVersion", "9");
    desiredCapabilities.setCapability("appium:deviceName", "AOSP on IA Emulator");
    desiredCapabilities.setCapability("appium:appPackage", "io.appium.android.apis");
    desiredCapabilities.setCapability("appium:appActivity", "io.appium.android.apis.ApiDemos");
    desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
    desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
    desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
    desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

    URL remoteUrl = new URL("http://localhost:4723/wd/hub");

    driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  }

  @Test
  public void putSomethingInTestView() {
	  
	//android.widget.TextView[@content-desc="Text"]
	//android.widget.TextView[@content-desc="LogTextBox"]
	//Textboxele- /hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.TextView
	 
	  MobileElement menuOptionText=(MobileElement)driver.findElementByXPath("//android.widget.TextView[@content-desc=\"Text\"]"); 
	  menuOptionText.click();
	  MobileElement menuOption_logTextBox=(MobileElement)driver.findElementByXPath("//android.widget.TextView[@content-desc=\"LogTextBox\"]"); 
	  menuOption_logTextBox.click();
	  MobileElement txtBox_logTextBox=(MobileElement)driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.TextView");
	  txtBox_logTextBox.sendKeys("Hello to Phase 4 Day 4");
  }

//  @After
//  public void tearDown() {
//    driver.quit();
//  }
}
