package com.blog.tty4032;

import com.blog.tty4032.base.Base;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class TestEcommerce extends Base {

        public static void main(String[] args) throws InterruptedException {
            AndroidDriver<AndroidElement> driver = initializeCapabilities("GeneralStoreApp");

            Thread.sleep(5000);

            System.out.println("1 --------------------------");
            //1. Verifying toast messages for error validations and then, fill the form details for shopping
            login(driver);
            Thread.sleep(3000);


            System.out.println("2 --------------------------");
            //2. Shop the items in the app by scrolling to specific Product and add to cart
            driver.findElementByAndroidUIAutomator(
                    "new UiScrollable(" +
                            "new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")" +
                            ").scrollIntoView(" +
                            "new UiSelector().textMatches(\"Jordan 6 Rings\").instance(0)" +
                            ");"
            );

            List<AndroidElement> elements = driver.findElementsById("com.androidsample.generalstore:id/productName");
            int count = elements.size();
            String text = null;

            for (int i=0;i<count;i++) {
                text = elements.get(i).getText();

                if (text.equals("Jordan 6 Rings")) {
                    driver.findElementsById("com.androidsample.generalstore:id/productAddCart").get(i).click();
                    System.out.println("Jordan 6 Rings add");
                    break;
                }
            }
            driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart").click();

            Thread.sleep(1000);
            System.out.println("3 --------------------------");

            //3. Validate if the item selected in the page 2 are matching with items displayed in checkout page
            List<AndroidElement> orderPageElement = driver.findElementsById("com.androidsample.generalstore:id/productName");

            for(AndroidElement element : orderPageElement) {
                if(element.getText().equals(text)) {
                    System.out.println("find!");
                    System.out.println(element.getText());
                }
            }
            driver.quit();
            System.out.println("4 --------------------------");


        }

        public static void login(AndroidDriver<AndroidElement> driver) throws InterruptedException {

            driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();
            System.out.println(driver.findElementByXPath("//android.widget.Toast").getText());
            System.out.println(driver.findElementByXPath("//android.widget.Toast[1]").getAttribute("name"));

            driver.findElementById("com.androidsample.generalstore:id/nameField").sendKeys("hello");
            driver.hideKeyboard();
            driver.findElementById("com.androidsample.generalstore:id/radioFemale").click();
            driver.findElementById("android:id/text1").click();
            driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));").click();

            driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();
        }


}
