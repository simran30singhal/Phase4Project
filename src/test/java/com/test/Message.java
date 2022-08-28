package com.test;

import io.appium.java_client.MobileBy.ByAccessibilityId;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Message {

	private AndroidDriver<MobileElement> driver;
	TouchAction touchaction;

	@Before
	public void setUp() throws MalformedURLException {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("platformName", "Android");
		desiredCapabilities.setCapability("appium:platformVersion", "9");
		desiredCapabilities.setCapability("appium:deviceName", "AOSP on IA Emulator");
		desiredCapabilities.setCapability("appium:appPackage", "com.google.android.apps.messaging");
		desiredCapabilities.setCapability("appium:appActivity", "com.google.android.apps.messaging.ui.ConversationListActivity");
		desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
		desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
		desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
		desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

		URL remoteUrl = new URL("http://localhost:4723/wd/hub");

		driver = new AndroidDriver<MobileElement>(remoteUrl, desiredCapabilities);
		
	}

	public MobileElement getTextViewbyContentDesc(String text) {

		return (MobileElement) driver.findElementByXPath("//android.widget.ImageView[@content-desc=\"" + text + "\"]");

	}

	public MobileElement getTextViewbyText(String text) {

		return (MobileElement) driver.findElementByXPath("//android.widget.Button[@text=\"" + text + "\"]");

	}

	@Test
	public void messgaeTest() throws Throwable {

		getTextViewbyContentDesc("Start new conversation").click();
		//driver.findElementByXPath("//android.widget.MultiAutoCompleteTextView[@text='Type a name, phone number, or email']").sendKeys("5554");
		//getTextViewbyText("Type a name, phone number, or email").sendKeys("5554");
		driver.findElement(By.id("com.google.android.apps.messaging:id/recipient_text_view")).sendKeys("5554");
		driver.findElement(By.id("com.google.android.apps.messaging:id/contact_picker_create_group")).click();
		driver.findElement(By.id("com.google.android.apps.messaging:id/compose_message_text")).sendKeys("Hi, How are You?");
		getTextViewbyContentDesc("Send SMS").click();
		
	}

	@After
	public void tearDown() throws Throwable {
		Thread.sleep(5000);
		driver.quit();
	}
}
