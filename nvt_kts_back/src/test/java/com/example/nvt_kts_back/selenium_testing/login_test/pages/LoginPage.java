package com.example.nvt_kts_back.selenium_testing.login_test.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage  {
    private final WebDriver webDriver;

    private final String PAGE_URL = "http://localhost:4200/login";

    private final By EMAIL_INPUT_BY;
    private final By PASSWORD_INPUT_BY;
    private final By REG_LOGIN_BTN_BY;
    private final By LOGOUT_BTN_BY;
    private final By POPUP_BY;
    private final By CONTINUE_WITH_FB_BTN_BY;

    private final String REG_USER_EMAIL = "strahinjapopovic.evilpops@gmail.com";
    private final String DRIVER_EMAIL = "michael.brown@example.com";
    private final String ADMIN_EMAIL = "admin@gmail.com";
    private final String ALL_USERS_PASSWORD = "sifra123";

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        EMAIL_INPUT_BY = By.id("email");
        PASSWORD_INPUT_BY = By.id("password");
        REG_LOGIN_BTN_BY = By.id("login-btn");
        LOGOUT_BTN_BY = By.id("logout-btn");
        POPUP_BY = By.id("pop-up-id");
        CONTINUE_WITH_FB_BTN_BY = By.id("fb-login-btn");
    }

    public void performRegularLoginForRegUser() {
        redirectToLoginPage();
        fillLoginForm(REG_USER_EMAIL);
        performRegularLoginBtnClick();
    }

    public void performRegularLoginForDriver() {
        redirectToLoginPage();
        fillLoginForm(DRIVER_EMAIL);
        performRegularLoginBtnClick();
    }

    public void performRegularLoginForAdmin() {
        redirectToLoginPage();
        fillLoginForm(ADMIN_EMAIL);
        performRegularLoginBtnClick();
    }

    public void performInvalidRegularLogin() {
        redirectToLoginPage();
        fillLoginForm("");
        performRegularLoginBtnClick();
    }

    public boolean verifyErrorPopUp() {
        try {
            new WebDriverWait(webDriver, Duration.ofSeconds(2)).until(ExpectedConditions.presenceOfElementLocated(POPUP_BY));
            webDriver.findElement(POPUP_BY);
        } catch (TimeoutException ignored) {
            return false;
        }
        return true;
    }

    public void performFacebookLogin() {
        redirectToLoginPage();
        performContinueWithFBBtnClick();
    }

    public boolean verifyLoginAndPerformLogOut() {
        try {
            new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(LOGOUT_BTN_BY));
            webDriver.findElement(LOGOUT_BTN_BY).click();
        } catch (TimeoutException ignored) {
            return false;
        }
        return true;
    }

    private void redirectToLoginPage() {
        webDriver.get(PAGE_URL);
    }

    private void fillLoginForm(String userEmail) {
        new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(EMAIL_INPUT_BY));

        webDriver.findElement(EMAIL_INPUT_BY).sendKeys(userEmail);
        webDriver.findElement(PASSWORD_INPUT_BY).sendKeys(ALL_USERS_PASSWORD);
    }

    private void performRegularLoginBtnClick() {
        webDriver.findElement(REG_LOGIN_BTN_BY).click();
    }

    private void performContinueWithFBBtnClick() {
        new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(CONTINUE_WITH_FB_BTN_BY));
        webDriver.findElement(CONTINUE_WITH_FB_BTN_BY).click();
    }
}
