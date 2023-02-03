package com.example.nvt_kts_back.e2e.tests;


import com.example.nvt_kts_back.e2e.helper.Helper;
import org.testng.annotations.Test;

public class NarucivanjeVoznjeDodatneLokacijeDodatniPutniciNarucivanjeTest extends NarucivanjeTestBase {
    @Test
    public void test1() throws InterruptedException {

        login();

        goToOrderPage();

        ordering_ogTOEditPageLinkedPassangersMoreWaypoints();

        confirmOrderLinkedPassangers();

        Thread.sleep(3000);
        Helper.takeScreenshoot(driver, "login_application_full");
    }
}
