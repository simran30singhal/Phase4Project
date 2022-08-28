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
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.DesiredCapabilities;

public class YearPicker {

	private AndroidDriver<MobileElement> driver;
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

		return (MobileElement) driver.findElementByXPath("//android.widget.Button[@text=\"" + text + "\"]");

	}

	public void ScrollUpYear() {

		MobileElement yearList = driver.findElement(By.id("android:id/date_picker_year_picker"));

		int x = yearList.getLocation().getX();
		int y = yearList.getLocation().getY();

		int Width = yearList.getSize().getWidth();
		int Height = yearList.getSize().getHeight();

		int startX = (int) (x + Width * 0.5);
		int startY = (int) (y + Height * 0.9);

		int endX = startX;
		int endY = (int) (y + Height * 0.1);

		touchaction.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX, endY)).release()
				.perform();

	}

	public void ScrollDownYear() {

		MobileElement yearList = driver.findElement(By.id("android:id/date_picker_year_picker"));

		int x = yearList.getLocation().getX();
		int y = yearList.getLocation().getY();

		int Width = yearList.getSize().getWidth();
		int Height = yearList.getSize().getHeight();

		int startX = (int) (x + Width * 0.5);
		int startY = (int) (y + Height * 0.1);
		
		int endX = startX;
		int endY = (int) (y + Height * 0.9);
		
		touchaction.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX, endY)).release()
				.perform();

	}

	@Test
	public void datepickerTest() throws Throwable {

		getTextViewbyContentDesc("Views").click();
		getTextViewbyContentDesc("Date Widgets").click();
		getTextViewbyContentDesc("1. Dialog").click();
		getTextViewbyText("CHANGE THE DATE").click();
		driver.findElement(By.id("android:id/date_picker_header_year")).click();
		MobileElement current_year = driver.findElement(By.id("android:id/date_picker_header_year"));
		String strcurYear = current_year.getAttribute("text");
		String strReqYear = "2014";
		int crYear = Integer.parseInt(strcurYear);
		int reqYear = Integer.parseInt(strReqYear);

		if (crYear < reqYear) {
//			

			boolean flagFound = false;

			while (!flagFound) {
				List<MobileElement> lstReqYr = driver.findElements(By.xpath("//android.widget.TextView[@text='2030']"));
				if (lstReqYr.size() > 0) {
					flagFound = true;
					break;
				}
				ScrollUpYear();
			}
			driver.findElement(By.xpath("//android.widget.TextView[@text='2030']")).click();
			Thread.sleep(5000);

		} else {
			// Scroll Down
			boolean flagFound = false;

			while (!flagFound) {
				List<MobileElement> lstReqYr = driver.findElements(By.xpath("//android.widget.TextView[@text='2030']"));
				if (lstReqYr.size() > 0) {
					flagFound = true;
					break;
				}
				ScrollDownYear();
			}
			driver.findElement(By.xpath("//android.widget.TextView[@text='2030']")).click();
			Thread.sleep(5000);
		}

	}

	@After
	public void tearDown() throws Throwable {
		Thread.sleep(5000);
		driver.quit();
	}
}
