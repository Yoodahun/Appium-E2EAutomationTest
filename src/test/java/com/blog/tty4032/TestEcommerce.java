package com.blog.tty4032;

import com.blog.tty4032.base.Base;
import com.blog.tty4032.ecommerce.CheckoutPage;
import com.blog.tty4032.ecommerce.FormPage;
import com.blog.tty4032.util.Utilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

public class TestEcommerce extends Base {


    @Test
    public void totalValidation() throws InterruptedException {

            appiumDriverLocalService = startServer();

            Thread.sleep(5000);

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

        //4. Validate the total Amount displayed in the checkout page matches with sum of product amounts selected for Shopping
        driver = initializeCapabilities("GeneralStoreApp");
        Thread.sleep(5000);
        login(driver);

        elements = driver.findElementsByXPath("//*[@text='ADD TO CART']");
        for (AndroidElement element: elements
        ) {
            element.click();
        }
        driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart").click();

        Thread.sleep(1000);
        double priceSum = 0.0;

//        List<AndroidElement> totalPrices = driver.findElementsById("com.androidsample.generalstore:id/productPrice");
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        for (WebElement priceElement: checkoutPage.getProductPrice()
        ) {
            priceSum += Double.parseDouble(priceElement.getText().replace("$",""));
        }

        String expectedSum = checkoutPage.totalAmount.getText().split(" ")[1];

        System.out.println(expectedSum);
        System.out.println(priceSum);

        Assert.assertEquals(
                priceSum, Double.parseDouble(expectedSum)
        );



        System.out.println("5 --------------------------");

        appiumDriverLocalService.stop();




    }

    @AfterClass
    public void stop() {
        appiumDriverLocalService.stop();
    }

        public static void login(AndroidDriver<AndroidElement> driver) throws InterruptedException {

            FormPage formPage = new FormPage(driver);

            formPage.loginButton.click();
            System.out.println(formPage.toast.getText());

            formPage.getNamefield().sendKeys("Hello");
            driver.hideKeyboard();
            formPage.radioFemale.click();
            formPage.getCountrySelect().click();

            Utilities util = new Utilities(driver);

            util.scrolling("Argentina");

            formPage.loginButton.click();
        }


}
