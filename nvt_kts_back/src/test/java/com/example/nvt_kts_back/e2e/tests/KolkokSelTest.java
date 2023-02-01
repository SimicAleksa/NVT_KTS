package com.example.nvt_kts_back.e2e.tests;


import com.example.nvt_kts_back.e2e.helper.Helper;
import com.example.nvt_kts_back.e2e.pages.HomePage;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

public class KolkokSelTest  extends  TestBase{
    @Test
    public void test1() throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        Assertions.assertTrue(homePage.isOpened());

        Helper.takeScreenshoot(driver, "login_application_full");
    }
}
