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

public class Flipkart_Android {

	private AndroidDriver driver;
	TouchAction touchAction;

	@Before
	public void setUp() throws MalformedURLException {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("platformName", "Android");
		desiredCapabilities.setCapability("appium:platformVersion", "9");
		desiredCapabilities.setCapability("appium:deviceName", "AOSP on IA Emulator");
		desiredCapabilities.setCapability("appium:appPackage", "com.flipkart.android");
		desiredCapabilities.setCapability("appium:appActivity",
				"com.flipkart.android.activity.HomeFragmentHolderActivity");
		desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
		desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
		desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
		desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

		URL remoteUrl = new URL("http://localhost:4723/wd/hub");

		driver = new AndroidDriver(remoteUrl, desiredCapabilities);
		touchAction = new TouchAction(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void FlipkartTest() {

		// 1. select english =
		// //android.widget.RelativeLayout[4]//android.widget.ImageView[1]
//				 2. click continue - id = 	com.flipkart.android:id/select_btn
//				 3. close button - id = com.flipkart.android:id/custom_back_icon

		driver.findElementByXPath("//android.widget.RelativeLayout[4]//android.widget.ImageView[1]").click();
		driver.findElementById("com.flipkart.android:id/select_btn").click();
		driver.findElementById("com.flipkart.android:id/custom_back_icon").click();
		driver.findElementByXPath("//android.widget.TextView[@text='Search for products']").click();
		driver.findElementByXPath("//android.widget.EditText[@text='Search for Products, Brands and More']").sendKeys("laptop bag");

	

	}

	@After
	public void tearDown() {
		driver.quit();
	}
}
