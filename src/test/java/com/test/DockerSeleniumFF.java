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
import org.openqa.selenium.remote.RemoteWebDriver;

public class DockerSeleniumFF {

	private AndroidDriver driver;
	TouchAction touchAction;

	
	public static void main(String[] args) throws Throwable {
		DesiredCapabilities caps = DesiredCapabilities.firefox();

		URL hubUrl = new URL("http://ec2-18-234-241-245.compute-1.amazonaws.com:4444/wd/hub");

		   
        System.out.println("connecting ....");
        RemoteWebDriver driver = new RemoteWebDriver(hubUrl,caps);
        
        
        System.out.println("starting test....");
        driver.get("http://www.yahoo.com");
        System.out.println("title= "+ driver.getTitle());
        System.out.println("finished test....");
        
        Thread.sleep(60000);
        driver.quit();
        
        System.out.println("driver quit...");


	}
}
