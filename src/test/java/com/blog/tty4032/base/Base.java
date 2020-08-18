package com.blog.tty4032.base;

import com.blog.tty4032.App;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Base {

    protected AppiumDriverLocalService appiumDriverLocalService = null;

    public static AndroidDriver<AndroidElement> initializeCapabilities(String app) throws InterruptedException {
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

        ///Users/yoodahun/Library/Android/sdk/emulator
        ///emulator -avd PracticeEmul

        if(properties.getProperty("device").contains("Practice")) {
            startEmulator();
            Thread.sleep(5000);
        }


        try {
            driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(4000, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.getMessage();
        }

        return driver;

    }

    public AppiumDriverLocalService startServer() {

        boolean flag = checkIfServerIsRunning(4723);
        if(!flag) {
            try {
                HashMap<String, String> environment = new HashMap<>();

                environment.put("PATH", "/usr/local/bin:" + System.getenv("PATH"));
                environment.put("ANDROID_HOME", "/Users/yoodahun/Library/Android/sdk");
                environment.put("JAVA_HOME", "/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home");
                AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
                appiumServiceBuilder.usingDriverExecutable(new File("/usr/local/bin/node"));
                appiumServiceBuilder.withAppiumJS(new File("/usr/local/bin/appium"));
                appiumServiceBuilder.withEnvironment(environment);

                appiumDriverLocalService = AppiumDriverLocalService.buildService(
                        appiumServiceBuilder
                );

            } catch (Exception e) {
                e.getMessage();
            }
            appiumDriverLocalService.start();
        }

        return appiumDriverLocalService;
    }

    public boolean checkIfServerIsRunning(int port) {
        boolean isServerRunning = false;

        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            e.getMessage();
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }

        return isServerRunning;
    }

    public static void startEmulator() {

        try {
            List<String> cmdList = new ArrayList<String>();
            // adding command and args to the list
            cmdList.add("sh");
            cmdList.add("src/test/resources/executeEmulator.sh");
            ProcessBuilder pb = new ProcessBuilder(cmdList);
            pb.start();


        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }



}
