package com.blog.tty4032.base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Base {

    public static AndroidDriver<AndroidElement> initializeCapabilities(String app) {
        // write your code here

        File file = new File("/Users/yoodahun/Documents/Github/Java/Appium -Mobile Automation Testing from Scratch/Library/"
                            + app);

        AndroidDriver<AndroidElement> driver = null;

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"PracticeEmul");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 10);
        capabilities.setCapability(MobileCapabilityType.APP,
                file.getAbsolutePath());

        try {
            driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(4000, TimeUnit.SECONDS);
        } catch (Exception e) {

        }

        return driver;

    }
}
