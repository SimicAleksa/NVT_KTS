package com.example.nvt_kts_back.e2e.tests;


import com.example.nvt_kts_back.e2e.helper.Helper;
import com.example.nvt_kts_back.e2e.pages.HomePage;
import com.example.nvt_kts_back.e2e.pages.OrderRidePage;
import com.example.nvt_kts_back.e2e.pages.RegisteredUserPage;
import org.junit.jupiter.api.Assertions;
import org.springframework.test.context.ActiveProfiles;
import org.testng.annotations.Test;

public class KolkokSelTest  extends  TestBase{
    @Test
    public void test1() throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        Assertions.assertTrue(homePage.isOpened());
        homePage.enterEmail();
        homePage.enterPassword();
        homePage.clickLogInButton();

        RegisteredUserPage registeredUserPage = new RegisteredUserPage(driver);
        Assertions.assertTrue(registeredUserPage.isOpened());
        registeredUserPage.clickOrderRideButtonNavBar();

        OrderRidePage orderRidePage = new OrderRidePage(driver);
        Assertions.assertTrue(orderRidePage.isOpened());

        orderRidePage.enterStartLocation();
        orderRidePage.enterEndLocation();
        orderRidePage.clickOrderRideButton();

        Thread.sleep(3000);
        Helper.takeScreenshoot(driver, "login_application_full");
    }
}
