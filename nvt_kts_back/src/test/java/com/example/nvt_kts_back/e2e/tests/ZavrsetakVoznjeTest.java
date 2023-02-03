package com.example.nvt_kts_back.e2e.tests;

import com.example.nvt_kts_back.e2e.pages.ZavrsetakVoznjeAllVehiclesPage;
import com.example.nvt_kts_back.e2e.pages.ZavrsetakVoznjeLoginPage;
import com.example.nvt_kts_back.e2e.pages.ZavrsetakVoznjeReportPage;
import com.example.nvt_kts_back.e2e.pages.ZavrsetakVoznjeRouteSearch;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

public class ZavrsetakVoznjeTest extends TestBase {

    @Test
    public void validData() throws InterruptedException {
        ZavrsetakVoznjeLoginPage loginPage = new ZavrsetakVoznjeLoginPage(driver);
        Assertions.assertTrue(loginPage.isOpened());

        loginPage.loginAsDriver();
        ZavrsetakVoznjeAllVehiclesPage allVehiclesPage = new ZavrsetakVoznjeAllVehiclesPage(driver);
        Assertions.assertTrue(allVehiclesPage.isOpened());

        allVehiclesPage.openReportPageDriver();
        ZavrsetakVoznjeReportPage reportPage = new ZavrsetakVoznjeReportPage(driver);
        Assertions.assertTrue(reportPage.isOpened());

        reportPage.openSideBar();
        Assertions.assertTrue(reportPage.isSidebarOpen());

        reportPage.endRide();
        Assertions.assertTrue(reportPage.rideIsNotPresent());
    }

//    @Test
//    public void alreadyOrdered() throws InterruptedException {
//        ZavrsetakVoznjeLoginPage loginPage1 = new ZavrsetakVoznjeLoginPage(driver);
//        Assertions.assertTrue(loginPage1.isOpened());
//
//        loginPage1.loginAsUser();
//        ZavrsetakVoznjeAllVehiclesPage allVehiclesPage1 = new ZavrsetakVoznjeAllVehiclesPage(driver);
//        Assertions.assertTrue(allVehiclesPage1.isOpened());
//
//        allVehiclesPage1.openRouteSearch();
//        ZavrsetakVoznjeRouteSearch routeSearch = new ZavrsetakVoznjeRouteSearch(driver);
//        Assertions.assertTrue(routeSearch.isOpened());
//
//        routeSearch.fillForm();
//        Assertions.assertTrue(routeSearch.btnIsNotPresent());
//
//        routeSearch.logoutUser();
//        ZavrsetakVoznjeLoginPage loginPage2 = new ZavrsetakVoznjeLoginPage(driver);
//        Assertions.assertTrue(loginPage2.isOpened());
//
//        /////////////////// OVO SE PONAVLJA///////////////////
//        loginPage2.loginAsDriver();
//        ZavrsetakVoznjeAllVehiclesPage allVehiclesPage2 = new ZavrsetakVoznjeAllVehiclesPage(driver);
//        Assertions.assertTrue(allVehiclesPage2.isOpened());
//
//        allVehiclesPage2.openReportPageDriver();
//        ZavrsetakVoznjeReportPage reportPage = new ZavrsetakVoznjeReportPage(driver);
//        Assertions.assertTrue(reportPage.isOpened());
//
//        reportPage.openSideBar();
//        Assertions.assertTrue(reportPage.isSidebarOpen());
//
//        reportPage.endRide();
//        Assertions.assertTrue(reportPage.rideIsNotPresent());
//        //////////////////////////////////////////////////
//
//        routeSearch.logoutDriver();
//        ZavrsetakVoznjeLoginPage loginPage3 = new ZavrsetakVoznjeLoginPage(driver);
//        Assertions.assertTrue(loginPage3.isOpened());
//
//        loginPage3.loginAsUser();
//        ZavrsetakVoznjeAllVehiclesPage allVehiclesPage3 = new ZavrsetakVoznjeAllVehiclesPage(driver);
//        Assertions.assertTrue(allVehiclesPage3.isOpened());
//
//        allVehiclesPage3.openRouteSearch();
//        ZavrsetakVoznjeRouteSearch routeSearch3 = new ZavrsetakVoznjeRouteSearch(driver);
//        Assertions.assertTrue(routeSearch3.isOpened());
//        Thread.sleep(5000);
//        routeSearch3.fillForm();
//        Assertions.assertTrue(routeSearch3.btnIsNotPresent());
//
//
//        routeSearch3.logoutUser();


//        allVehiclesPage.openReportPageUser();
//        ZavrsetakVoznjeReportPage reportPage = new ZavrsetakVoznjeReportPage(driver);
//        Assertions.assertTrue(reportPage.isOpened());
//
//        reportPage.openSideBar();
//        Assertions.assertTrue(reportPage.isSidebarOpen());
//
//        reportPage.endRide();
//        Assertions.assertTrue(reportPage.rideIsNotPresent());

//    }

    @Test
    public void alreadyOrdered() throws InterruptedException {
        ZavrsetakVoznjeLoginPage loginPage = new ZavrsetakVoznjeLoginPage(driver);
        Assertions.assertTrue(loginPage.isOpened());

        loginPage.loginAsUser();
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
        loginPage.loginAsDriver();
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

        loginPage.loginAsUser();
        Assertions.assertTrue(allVehiclesPage.isOpened());

        allVehiclesPage.openRouteSearch();
        Assertions.assertTrue(routeSearch.isOpened());

        routeSearch.fillForm();
        Assertions.assertTrue(routeSearch.btnIsNotPresent());

    }
}
