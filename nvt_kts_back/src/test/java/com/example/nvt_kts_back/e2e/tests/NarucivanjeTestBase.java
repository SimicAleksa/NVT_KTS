package com.example.nvt_kts_back.e2e.tests;

import com.example.nvt_kts_back.e2e.pages.NarucivanjeEditProfilePage;
import com.example.nvt_kts_back.e2e.pages.NarucivanjeHomePage;
import com.example.nvt_kts_back.e2e.pages.NarucivanjeOrderRidePage;
import com.example.nvt_kts_back.e2e.pages.NarucivanjeRegisteredUserPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class NarucivanjeTestBase {
    public static WebDriver driver;

    @BeforeSuite
    public void initializeWebDriver() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();

    }

    @AfterSuite
    public void quitDriver() {
        driver.quit();
    }

    protected void login(){
        NarucivanjeHomePage narucivanjeHomePage = new NarucivanjeHomePage(driver,true,false);
        Assertions.assertTrue(narucivanjeHomePage.isOpened());
        narucivanjeHomePage.enterEmail();
        narucivanjeHomePage.enterPassword();
        narucivanjeHomePage.clickLogInButton();
    }

    protected void loginPayFirst(){
        NarucivanjeHomePage narucivanjeHomePage = new NarucivanjeHomePage(driver,true,false);
        Assertions.assertTrue(narucivanjeHomePage.isOpened());
        narucivanjeHomePage.enterEmailPlatio();
        narucivanjeHomePage.enterPassword();
        narucivanjeHomePage.clickLogInButton();
    }

    protected void loginPayLast(){
        NarucivanjeHomePage narucivanjeHomePage = new NarucivanjeHomePage(driver,true,true);
        Assertions.assertTrue(narucivanjeHomePage.isOpened());
        narucivanjeHomePage.enterEmailPlatio();
        narucivanjeHomePage.enterPassword();
        narucivanjeHomePage.clickLogInButton();
    }

    protected void login_NoMoney(){
        NarucivanjeHomePage narucivanjeHomePage = new NarucivanjeHomePage(driver,false,false);
        Assertions.assertTrue(narucivanjeHomePage.isOpened());
        narucivanjeHomePage.enterEmail();
        narucivanjeHomePage.enterPassword();
        narucivanjeHomePage.clickLogInButton();
    }

    protected void goToOrderPage(){
        NarucivanjeRegisteredUserPage narucivanjeRegisteredUserPage = new NarucivanjeRegisteredUserPage(driver);
        Assertions.assertTrue(narucivanjeRegisteredUserPage.isOpened());
        narucivanjeRegisteredUserPage.clickOrderRideButtonNavBar();
    }

    protected void goToEditProfilePage(){
        NarucivanjeOrderRidePage narucivanjeOrderRidePage = new NarucivanjeOrderRidePage(driver);
        Assertions.assertTrue(narucivanjeOrderRidePage.isOpened());
        narucivanjeOrderRidePage.clickEditProfile();
    }

    protected void ordering_ogTOEditPage(){
        NarucivanjeOrderRidePage narucivanjeOrderRidePage = new NarucivanjeOrderRidePage(driver);
        Assertions.assertTrue(narucivanjeOrderRidePage.isOpened());
        narucivanjeOrderRidePage.enterStartLocation();
        narucivanjeOrderRidePage.enterEndLocation();
        narucivanjeOrderRidePage.clickOrderRideButton();
        Assertions.assertTrue(narucivanjeOrderRidePage.isOrdered());
        narucivanjeOrderRidePage.clickEditProfile();
    }

    protected void ordering_NODRIVER_ogTOEditPage(){
        NarucivanjeOrderRidePage narucivanjeOrderRidePage = new NarucivanjeOrderRidePage(driver);
        Assertions.assertTrue(narucivanjeOrderRidePage.isOpened());
        narucivanjeOrderRidePage.enterStartLocation();
        narucivanjeOrderRidePage.enterEndLocation();

        narucivanjeOrderRidePage.deselectAllExceptLimo();

        narucivanjeOrderRidePage.clickOrderRideButton();
        Assertions.assertTrue(narucivanjeOrderRidePage.isOrdered());
        narucivanjeOrderRidePage.clickEditProfile();
    }

    protected void ordering_RESERVE_ogTOEditPage(){
        NarucivanjeOrderRidePage narucivanjeOrderRidePage = new NarucivanjeOrderRidePage(driver);
        Assertions.assertTrue(narucivanjeOrderRidePage.isOpened());
        narucivanjeOrderRidePage.enterStartLocation();
        narucivanjeOrderRidePage.enterEndLocation();

        narucivanjeOrderRidePage.clickReserveBox();

        narucivanjeOrderRidePage.clickOrderRideButton();
        Assertions.assertTrue(narucivanjeOrderRidePage.isNotIn5HMark());

        narucivanjeOrderRidePage.enter1PlusTIme();
        narucivanjeOrderRidePage.clickOrderRideButton();
        Assertions.assertTrue(narucivanjeOrderRidePage.isOrdered());
        narucivanjeOrderRidePage.clickEditProfile();
    }

    protected void ordering_ogTOEditPageLinkedPassangersMoreWaypoints(){
        NarucivanjeOrderRidePage narucivanjeOrderRidePage = new NarucivanjeOrderRidePage(driver);
        Assertions.assertTrue(narucivanjeOrderRidePage.isOpened());
        narucivanjeOrderRidePage.clickAddWaypoint();
        narucivanjeOrderRidePage.enterStartLocation();
        narucivanjeOrderRidePage.enterEndLocation();
        narucivanjeOrderRidePage.enterAdditionalWaypoint();
        narucivanjeOrderRidePage.enterLinkedPassanger();
        narucivanjeOrderRidePage.clickOrderRideButton();
        Assertions.assertTrue(narucivanjeOrderRidePage.isOrdered());
        narucivanjeOrderRidePage.clickEditProfile();
    }

    protected void confirmOrder(){
        NarucivanjeEditProfilePage narucivanjeEditProfilePage= new NarucivanjeEditProfilePage(driver);
        Assertions.assertTrue(narucivanjeEditProfilePage.isOpened());
        narucivanjeEditProfilePage.clickSideBarMenuBtn();
        narucivanjeEditProfilePage.isSideBarOpen();
        narucivanjeEditProfilePage.clickAcceptBtn();
        Assertions.assertTrue(narucivanjeEditProfilePage.isApprovedByYou());
        Assertions.assertTrue(narucivanjeEditProfilePage.isApprovedByAll());
    }

    protected void confirmOrder_nodriver(){
        NarucivanjeEditProfilePage narucivanjeEditProfilePage= new NarucivanjeEditProfilePage(driver);
        Assertions.assertTrue(narucivanjeEditProfilePage.isOpened());
        narucivanjeEditProfilePage.clickSideBarMenuBtn();
        narucivanjeEditProfilePage.isSideBarOpen();
        narucivanjeEditProfilePage.clickAcceptBtn();
        Assertions.assertTrue(narucivanjeEditProfilePage.isRideDeclined_NoDriver());
    }



    protected void confirmOrder_NoMoney(){
        NarucivanjeEditProfilePage narucivanjeEditProfilePage= new NarucivanjeEditProfilePage(driver);
        Assertions.assertTrue(narucivanjeEditProfilePage.isOpened());
        narucivanjeEditProfilePage.clickSideBarMenuBtn();
        narucivanjeEditProfilePage.isSideBarOpen();
        narucivanjeEditProfilePage.clickAcceptBtn();
        Assertions.assertTrue(narucivanjeEditProfilePage.isDeclined_NoTokens());
    }

    protected void confirmOrderLinkedPassangers(){
        NarucivanjeEditProfilePage narucivanjeEditProfilePage= new NarucivanjeEditProfilePage(driver);
        Assertions.assertTrue(narucivanjeEditProfilePage.isOpened());
        narucivanjeEditProfilePage.clickSideBarMenuBtn();
        narucivanjeEditProfilePage.isSideBarOpen();
        narucivanjeEditProfilePage.clickAcceptBtn();
        Assertions.assertTrue(narucivanjeEditProfilePage.isApprovedByYou());
    }

    protected void confirmOrderLinkedPassangers_LogOut(){
        NarucivanjeEditProfilePage narucivanjeEditProfilePage= new NarucivanjeEditProfilePage(driver);
        Assertions.assertTrue(narucivanjeEditProfilePage.isOpened());
        narucivanjeEditProfilePage.clickSideBarMenuBtn();
        narucivanjeEditProfilePage.isSideBarOpen();
        narucivanjeEditProfilePage.clickAcceptBtn();
        Assertions.assertTrue(narucivanjeEditProfilePage.isApprovedByYou());
        narucivanjeEditProfilePage.clickLogOutBtn();
    }
}
