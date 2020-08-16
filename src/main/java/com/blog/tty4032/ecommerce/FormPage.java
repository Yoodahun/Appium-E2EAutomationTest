package com.blog.tty4032.ecommerce;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class FormPage {

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    public WebElement namefield;

    @AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")
    public WebElement radioFemale;

    @AndroidFindBy(id ="com.androidsample.generalstore:id/btnLetsShop")
    public WebElement loginButton;

    @AndroidFindBy(xpath = "//android.widget.Toast")
    public WebElement toast;

    @AndroidFindBy(id = "android:id/text1")
    public WebElement countrySelect;



    public FormPage(AndroidDriver<AndroidElement> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public WebElement getNamefield() {
        return namefield;
    }
}
