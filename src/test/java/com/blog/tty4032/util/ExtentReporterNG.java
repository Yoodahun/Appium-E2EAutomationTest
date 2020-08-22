package com.blog.tty4032.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentReporter;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.util.List;
import java.util.Map;

public class ExtentReporterNG implements IReporter {
    private ExtentReports extentReports;
    private ExtentHtmlReporter extentHtmlReporter;

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

        //path should report generate.
        extentHtmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/ExtentReportsTestNG.html");
        extentReports = new ExtentReports();

        extentReports.attachReporter(extentHtmlReporter);

        for (ISuite isuite : suites) {
            //get the test result from TestNG
            Map<String, ISuiteResult> result = isuite.getResults();

            for (ISuiteResult suiteResult : result.values()) {
                ITestContext context = suiteResult.getTestContext();

                //write testResult
                buildTestNode(context.getPassedTests(), Status.PASS);
                buildTestNode(context.getFailedTests(), Status.FAIL);
                buildTestNode(context.getSkippedTests(), Status.SKIP);
            }
        }

        //export Test Report file
        extentReports.flush();

    }

    private void buildTestNode(IResultMap tests, Status status) {
        ExtentTest test;

        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                //create node that testResult using test method name
                test = extentReports.createTest(result.getMethod().getMethodName());

                //write test group if there is.
                for(String group : result.getMethod().getGroups()) {
                    test.assignCategory(group);
                }

                String message = "Test " + status.toString().toLowerCase() + "ed";

                //if there is error, then write error message
                if (result.getThrowable() != null) {
                    message = result.getThrowable().getMessage();
                }

                test.log(status, message);

            }
        }
    }
}
