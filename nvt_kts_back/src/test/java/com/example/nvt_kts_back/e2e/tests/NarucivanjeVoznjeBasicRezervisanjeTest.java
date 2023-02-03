package com.example.nvt_kts_back.e2e.tests;


import com.example.nvt_kts_back.e2e.helper.Helper;
import org.testng.annotations.Test;

public class NarucivanjeVoznjeBasicRezervisanjeTest extends NarucivanjeTestBase {
    @Test
    public void test1() throws InterruptedException {

        login();

        goToOrderPage();

        ordering_RESERVE_ogTOEditPage();

        confirmOrder();

        Helper.takeScreenshoot(driver, "login_application_full");
    }
}
