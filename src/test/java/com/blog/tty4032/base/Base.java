package com.blog.tty4032.base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {

    public static AndroidDriver<AndroidElement> initializeCapabilities(String app) {
        // write your code here
        Properties properties = null;
            FileInputStream fis = null;
        try {
            fis = new FileInputStream("src/test/resources/global.properties");
            properties = new Properties();
            properties.load(fis);

        } catch (Exception e) {
            e.getMessage();
        }


        File file = new File(
                "/Users/yoodahun/Documents/Github/Java/Appium -Mobile Automation Testing from Scratch/Library/"
                            + properties.getProperty(app));

        AndroidDriver<AndroidElement> driver = null;

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,properties.getProperty("device"));
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 10);
        capabilities.setCapability(MobileCapabilityType.APP,
                file.getAbsolutePath());

        try {
            driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(4000, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.getMessage();
        }

        return driver;

    }
}
