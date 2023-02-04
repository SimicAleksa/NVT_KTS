package com.example.nvt_kts_back.e2e.tests;

import com.example.nvt_kts_back.e2e.pages.ZavrsetakVoznjeAllVehiclesPage;
import com.example.nvt_kts_back.e2e.pages.ZavrsetakVoznjeLoginPage;
import com.example.nvt_kts_back.e2e.pages.ZavrsetakVoznjeReportPage;
import com.example.nvt_kts_back.e2e.pages.ZavrsetakVoznjeRouteSearch;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

public class ZavrsetakVoznjeTest extends NarucivanjeTestBase {

    @Test
    public void zavrsetakAlreadyOrdered() throws InterruptedException {
        ZavrsetakVoznjeLoginPage loginPage = new ZavrsetakVoznjeLoginPage(driver);
        Assertions.assertTrue(loginPage.isOpened());

        loginPage.loginAsUser2();
        ZavrsetakVoznjeAllVehiclesPage allVehiclesPage = new ZavrsetakVoznjeAllVehiclesPage(driver);
        Assertions.assertTrue(allVehiclesPage.isOpened());

        allVehiclesPage.openRouteSearch();
        ZavrsetakVoznjeRouteSearch routeSearch = new ZavrsetakVoznjeRouteSearch(driver);
        Assertions.assertTrue(routeSearch.isOpened());

        routeSearch.fillForm();
        Assertions.assertTrue(routeSearch.btnIsNotPresent());

        routeSearch.logoutUser();
        Assertions.assertTrue(loginPage.isOpened());

        //////////////////////////////////////
        loginPage.loginAsDriver2();
        Assertions.assertTrue(allVehiclesPage.isOpened());

        allVehiclesPage.openReportPageDriver();
        ZavrsetakVoznjeReportPage reportPage = new ZavrsetakVoznjeReportPage(driver);
        Assertions.assertTrue(reportPage.isOpened());

        reportPage.openSideBar();
        Assertions.assertTrue(reportPage.isSidebarOpen());

        reportPage.endRide();
        Assertions.assertTrue(reportPage.rideIsNotPresent());
        ////////////////////////////////////////

        routeSearch.logoutDriver();
        Assertions.assertTrue(loginPage.isOpened());

        loginPage.loginAsUser2();
        Assertions.assertTrue(allVehiclesPage.isOpened());

        allVehiclesPage.openRouteSearch();
        Assertions.assertTrue(routeSearch.isOpened());

        routeSearch.fillForm();
        Assertions.assertTrue(routeSearch.btnIsPresent());
        routeSearch.schedule();
    }










//    @Test
//    public void zavrsetakValidData() throws InterruptedException {
//        ZavrsetakVoznjeLoginPage loginPage = new ZavrsetakVoznjeLoginPage(driver);
//        Assertions.assertTrue(loginPage.isOpened());
//
//        loginPage.loginAsDriver2();
//        ZavrsetakVoznjeAllVehiclesPage allVehiclesPage = new ZavrsetakVoznjeAllVehiclesPage(driver);
//        Assertions.assertTrue(allVehiclesPage.isOpened());
//
//        allVehiclesPage.openReportPageDriver();
//        ZavrsetakVoznjeReportPage reportPage = new ZavrsetakVoznjeReportPage(driver);
//        Assertions.assertTrue(reportPage.isOpened());
//
//        reportPage.openSideBar();
//        Assertions.assertTrue(reportPage.isSidebarOpen());
//
//        reportPage.endRide();
//        Assertions.assertTrue(reportPage.rideIsNotPresent());
//
//
//
//
//        //////////////////////////////////////
//        loginPage.loginAsDriver2();
//        Assertions.assertTrue(allVehiclesPage.isOpened());
//
//        allVehiclesPage.openReportPageDriver();
//        ZavrsetakVoznjeReportPage reportPage = new ZavrsetakVoznjeReportPage(driver);
//        Assertions.assertTrue(reportPage.isOpened());
//
//        reportPage.openSideBar();
//        Assertions.assertTrue(reportPage.isSidebarOpen());
//
//        reportPage.endRide();
//        Assertions.assertTrue(reportPage.rideIsNotPresent());
//        ////////////////////////////////////////
//
//        routeSearch.logoutDriver();
//        Assertions.assertTrue(loginPage.isOpened());
//
//        loginPage.loginAsUser();
//        Assertions.assertTrue(allVehiclesPage.isOpened());
//
//        allVehiclesPage.openRouteSearch();
//        Assertions.assertTrue(routeSearch.isOpened());
//
//        routeSearch.fillForm();
//        Assertions.assertTrue(routeSearch.btnIsNotPresent());
//
//    }
}
