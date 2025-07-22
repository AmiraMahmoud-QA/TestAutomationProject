package com.swaglabs.utils;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;
public class CustomSoftAssertion extends SoftAssert {
    public static CustomSoftAssertion softAssertion = new CustomSoftAssertion();

    public static void customAssertAll(ITestResult result) {
        try {
            softAssertion.assertAll("Custom Soft assertion ");
        } catch (Exception e) {

            System.out.println("Custom Soft assertion failed: " + e.getMessage());
            result.setStatus(ITestResult.FAILURE);
            result.setThrowable(e);
        }

        finally {
            reinitializeSoftAssertion();
        }

    }

    private static void reinitializeSoftAssertion() {
        softAssertion = new CustomSoftAssertion();
    }
}