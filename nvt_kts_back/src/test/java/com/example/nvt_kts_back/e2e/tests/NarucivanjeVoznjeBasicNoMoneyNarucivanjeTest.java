package com.example.nvt_kts_back.e2e.tests;


import com.example.nvt_kts_back.e2e.helper.Helper;
import org.testng.annotations.Test;

public class NarucivanjeVoznjeBasicNoMoneyNarucivanjeTest extends NarucivanjeTestBase {
    @Test
    public void test1() throws InterruptedException {

        login_NoMoney();

        goToOrderPage();

        ordering_ogTOEditPage();

        confirmOrder_NoMoney();

        Helper.takeScreenshoot(driver, "login_application_full");
    }
}
