package com.example.nvt_kts_back.selenium_testing.login_test.tests;

import com.example.nvt_kts_back.selenium_testing.login_test.pages.LoginPage;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends SeleniumTest {
    @Test
    public void performsSuccessfulRegularLoginAsRegUser() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.performRegularLoginForRegUser();
        assertTrue(loginPage.verifyLoginAndPerformLogOut());
    }

    @Test
    public void performsSuccessfulRegularLoginAsDriver() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.performRegularLoginForDriver();
        assertTrue(loginPage.verifyLoginAndPerformLogOut());
    }

    @Test
    public void performsSuccessfulRegularLoginAsAdmin() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.performRegularLoginForAdmin();
        assertTrue(loginPage.verifyLoginAndPerformLogOut());
    }

    @Test
    public void performsIncorrectRegularLogin() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.performInvalidRegularLogin();
        assertTrue(loginPage.verifyErrorPopUp());
    }

    @Test
    public void performFacebookLogin() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.performFacebookLogin();
        //TODO da se prijavi na fb prvo
        System.out.println();
    }
}
