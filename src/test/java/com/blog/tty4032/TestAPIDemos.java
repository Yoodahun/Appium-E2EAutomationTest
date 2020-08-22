package com.blog.tty4032;

import com.blog.tty4032.APIDemo.Homepage;
import com.blog.tty4032.APIDemo.Preference;
import com.blog.tty4032.base.Base;
import com.blog.tty4032.util.TestDataProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestAPIDemos extends Base {

    @Test(dataProvider = "InputData", dataProviderClass = TestDataProvider.class)
    public void apiDemo(String inputData) throws InterruptedException {
        appiumDriverLocalService = startServer();
        Thread.sleep(5000);
        AndroidDriver<AndroidElement> driver = initializeCapabilities("APIDemos-debug");

        Homepage homepage = new Homepage(driver);

        homepage.preference.click();

        Preference preference = new Preference(driver);

        preference.prefereneceDependencies.click();
        driver.findElementById("android:id/checkbox").click();
        driver.findElementsByXPath("//android.widget.RelativeLayout").get(1).click();
        driver.findElementById("android:id/edit").sendKeys(inputData);
        driver.findElementsByClassName("android.widget.Button").get(1).click();

        appiumDriverLocalService.stop();

    }
}
