package com.example.nvt_kts_back.e2e.tests;

import com.example.nvt_kts_back.e2e.helper.Helper;
import com.example.nvt_kts_back.e2e.pages.HomePage;
import com.example.nvt_kts_back.e2e.pages.RegistrationPage;;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

public class RegistrationTest extends TestBase {

    @Test
    public void validData() throws InterruptedException
    {
        HomePage homePage = new HomePage(driver);
        Assertions.assertTrue(homePage.isOpened());
        homePage.clickRegisterBtnNavbar();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        Assertions.assertTrue(registrationPage.isOpened());
        registrationPage.setCorrectValues();
        registrationPage.clickRegisterBtn();
        Thread.sleep(10000);
//        Helper.takeScreenshoot(driver, "login_application_full");
    }

    @Test
    public void invalidValues() throws InterruptedException
    {
        HomePage homePage = new HomePage(driver);
        Assertions.assertTrue(homePage.isOpened());
        homePage.clickRegisterBtnNavbar();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        Assertions.assertTrue(registrationPage.isOpened());
        registrationPage.setIncorrectValues();
        Assertions.assertTrue(registrationPage.thereAre5smalls());
        Thread.sleep(5000);
        Helper.takeScreenshoot(driver, "login_application_full");

    }
}
