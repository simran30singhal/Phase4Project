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

public class Day4Spinner {

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
	
	public MobileElement getTextViewbyText(String text) {

		return (MobileElement) driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"" + text + "\"]");

	}

	public void SwipeLong() {

		touchAction.press(PointOption.point(904, 2663)).moveTo(PointOption.point(493, 599)).release().perform();
	}

	@Test
	public void SpinnerTest() {

		getTextViewbyContentDesc("Views").click();

		SwipeLong();
		SwipeLong();

		getTextViewbyContentDesc("Spinner").click();

		MobileElement spinner_Color = (MobileElement) driver.findElementById("android:id/text1");
		spinner_Color.click();
		getTextViewbyText("yellow").click();
		

	}

	@After
	public void tearDown() {
		driver.quit();
	}
}
