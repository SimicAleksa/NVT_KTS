package com.example.nvt_kts_back.e2e.tests;


import com.example.nvt_kts_back.e2e.helper.Helper;
import org.testng.annotations.Test;

public class NarucivanjeVoznjeDvaPrihvatanjaVoznje extends NarucivanjeTestBase {
    @Test
    public void test1() throws InterruptedException {

        loginPayFirst();

        goToOrderPage();

        goToEditProfilePage();

        confirmOrderLinkedPassangers_LogOut();

        loginPayLast();

        goToOrderPage();

        goToEditProfilePage();

        confirmOrder_twoCOnfirm();

        Helper.takeScreenshoot(driver, "login_application_full");
    }
}
