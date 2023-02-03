package com.example.nvt_kts_back.selenium_testing.login_test.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class SeleniumTest {
    public static WebDriver webDriver;

    @BeforeSuite
    public static void initializeWebDriver() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
    }

    @AfterSuite
    public static void quitDriver() {
        webDriver.quit();
    }

}
