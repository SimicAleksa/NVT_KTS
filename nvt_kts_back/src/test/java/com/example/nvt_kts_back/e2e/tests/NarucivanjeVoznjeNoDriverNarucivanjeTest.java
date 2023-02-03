package com.example.nvt_kts_back.e2e.tests;


import com.example.nvt_kts_back.e2e.helper.Helper;
import org.testng.annotations.Test;

public class NarucivanjeVoznjeNoDriverNarucivanjeTest extends NarucivanjeTestBase {
    @Test
    public void test1() throws InterruptedException {

        login_NoDriver();

        goToOrderPage();

        ordering_NODRIVER_ogTOEditPage();

        confirmOrder_nodriver();

        Helper.takeScreenshoot(driver, "login_application_full");
    }
}
