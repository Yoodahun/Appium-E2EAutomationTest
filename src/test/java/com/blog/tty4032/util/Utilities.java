package com.blog.tty4032.util;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.annotations.DataProvider;

public class Utilities {

    private AndroidDriver<AndroidElement> driver;

    public Utilities(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
    }
    public void scrolling(String text) {
        driver.findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text +"\"));"
        ).click();

    }


}
