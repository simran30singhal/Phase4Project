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
import org.openqa.selenium.remote.DesiredCapabilities;

public class Day4SeekBar {

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
	public void seekBarTest() throws Throwable{

		getTextViewbyContentDesc("Views").click();

		SwipeLong();
		SwipeLong();

		getTextViewbyContentDesc("Seek Bar").click();

		MobileElement seekbar = (MobileElement) driver.findElementById("io.appium.android.apis:id/seek");
		
		int startXPos=seekbar.getLocation().getX();
		int yPos=seekbar.getLocation().getY();
		
		int lastXPos=startXPos+seekbar.getSize().getWidth();
		int centerXPos=seekbar.getCenter().getX();
		
		System.out.println("xPos = "+startXPos);
		System.out.println("yPos = "+yPos);
		System.out.println("lastXPos = "+lastXPos);
		System.out.println("centerXPos = "+centerXPos);
		
		//move To start position=0
		touchAction.press(ElementOption.element(seekbar))
		.moveTo(PointOption.point(0,yPos))
		.release()
		.perform(); 
		
		Thread.sleep(5000);
		//move To last position=0
				touchAction.press(ElementOption.element(seekbar))
				.moveTo(PointOption.point(lastXPos,yPos))
				.release()
				.perform();
		
				Thread.sleep(5000);
				//move To position=30
				int point30=30*(startXPos+seekbar.getSize().getWidth() /100);
				touchAction.press(ElementOption.element(seekbar))
				.moveTo(PointOption.point(point30,yPos))
				.release()
				.perform();

	}

	@After
	public void tearDown() throws Throwable {
		Thread.sleep(5000);
		driver.quit();
	}
}
