package com.test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Day4SwipeAndSelect {

	private AndroidDriver driver;
	TouchAction touchAction;

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
		touchAction = new TouchAction(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public MobileElement getTextViewbyContentDesc(String text) {

		return (MobileElement) driver.findElementByXPath("//android.widget.TextView[@content-desc=\"" + text + "\"]");

	}

	public void SwipeLong() {

		touchAction.press(PointOption.point(904, 2663)).moveTo(PointOption.point(493, 599)).release().perform();
	}

	@Test
	public void SwitchesTest() {

		// android.widget.TextView[@content-desc="Text"]
		// android.widget.TextView[@content-desc="LogTextBox"]
		// Textboxele-
		// /hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.TextView

//MobileElement menuOptionViews=(MobileElement)driver.findElementByXPath("//android.widget.TextView[@content-desc=\"Views\"]"); 
//	  menuOptionViews.click();
		getTextViewbyContentDesc("Views").click();
		// TouchAction touchAction=new TouchAction(driver);
		// touchAction.press(PointOption.point(904,2663)).moveTo(PointOption.point(493,599)).release().perform();
		SwipeLong();
		SwipeLong();
		// touchAction.press(PointOption.point(904,2663)).moveTo(PointOption.point(493,599)).release().perform();
//	 MobileElement menuOptionSwitches=(MobileElement)driver.findElementByXPath("//android.widget.TextView[@content-desc=\"Switches\"]"); 
//	 menuOptionSwitches.click();
		getTextViewbyContentDesc("Switches").click();

		MobileElement switch_customizedText = (MobileElement) driver.findElementByAccessibilityId("Customized text");
		String isChecked = switch_customizedText.getAttribute("checked");
		System.out.println("Switch Status = " + isChecked);

		switch_customizedText.click();
		isChecked = switch_customizedText.getAttribute("checked");
		System.out.println("Switch Status = " + isChecked);

	}

	@After
	public void tearDown() {
		driver.quit();
	}
}
