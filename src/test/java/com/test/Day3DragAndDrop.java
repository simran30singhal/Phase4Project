package com.test;

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
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Day3DragAndDrop {

	private AndroidDriver <MobileElement>driver;
	TouchAction touchaction;

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

		driver = new AndroidDriver<MobileElement>(remoteUrl, desiredCapabilities);
		touchaction = new TouchAction(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		System.out.println(driver.getSessionId());
	}

	public MobileElement getTextViewbyContentDesc(String text) {

		return (MobileElement) driver.findElementByXPath("//android.widget.TextView[@content-desc=\"" + text + "\"]");

	}
	
	public MobileElement getTextViewbyText(String text) {

		return (MobileElement) driver.findElementByXPath("//android.widget.EditText[@text=\"" + text + "\"]");

	}
	
	@Test
	public void draganddropTest() throws Throwable{

		getTextViewbyContentDesc("Views").click();
		getTextViewbyContentDesc("Drag and Drop").click();
		MobileElement source= driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
		MobileElement target= driver.findElement(By.id("io.appium.android.apis:id/drag_dot_2"));
		
		
		Point pntSRC = source.getCenter();
		Point pntTGT = target.getCenter();
		
		touchaction
		.longPress(PointOption.point(pntSRC))
		.moveTo(PointOption.point(pntTGT))
		.release()
		.perform();
	}

	@After
	public void tearDown() throws Throwable {
		Thread.sleep(5000);
		driver.quit();
	}
}
