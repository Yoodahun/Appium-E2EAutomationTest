package com.blog.tty4032;

import com.blog.tty4032.base.Base;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.annotations.Test;

public class TestAPIDemos extends Base {

    @Test
    public void apiDemo(){
        AndroidDriver<AndroidElement> driver = initializeCapabilities("APIDemos-debug");

    }
}
