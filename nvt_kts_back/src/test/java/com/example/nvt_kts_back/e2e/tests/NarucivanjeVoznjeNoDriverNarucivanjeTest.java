package com.example.nvt_kts_back.e2e.tests;


import com.example.nvt_kts_back.e2e.helper.Helper;
import org.testng.annotations.Test;

public class NarucivanjeVoznjeNoDriverNarucivanjeTest extends NarucivanjeTestBase {
    @Test
    public void test1() throws InterruptedException {

        login();

        goToOrderPage();

        ordering_NODRIVER_ogTOEditPage();

        confirmOrder_nodriver();

        Thread.sleep(3000);
        Helper.takeScreenshoot(driver, "login_application_full");
    }
}