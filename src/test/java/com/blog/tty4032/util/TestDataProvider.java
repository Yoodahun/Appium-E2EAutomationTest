package com.blog.tty4032.util;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name="InputData")
    public Object[][] getDataForEditField() {

        Object[][] objects = new Object[][]{
                {"hello"},{"@#$%"}
        };

        return objects;
    }

}
