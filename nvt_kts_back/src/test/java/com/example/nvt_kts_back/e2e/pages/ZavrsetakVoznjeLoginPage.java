package com.example.nvt_kts_back.e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ZavrsetakVoznjeLoginPage {

    private WebDriver driver;

    private static String URL_HOME = "http://localhost:4200/login";
    private static String DRIVER_EMAIL = "registrovani1@gmail.com";
    private static String DRIVER_EMAIL_2 = "bugfixDriver2@gmail.com";
    private static String DRIVER_PASSWORD = "sifra123";
    private static String USER_EMAIL = "djura@gmail.com";
    private static String USER_EMAIL_2 = "selena@gmail.com";
    private static String USER_PASSWORD = "sifra123";

    @FindBy(css = "body > app-root > app-login-page > div > div:nth-child(2) > app-login-form > form > div.row.card-style > div.row > span")
    private WebElement logIn;

    @FindBy(css = "nav > div > ul > li:nth-child(3) > a")
    private WebElement registerBtnNavbar;

    @FindBy(css = "#email")
    private WebElement emailInput;

    @FindBy(css = "#password")
    private WebElement passwordInput;

    @FindBy(css = "#login-btn")
    private WebElement logInButton;

    public ZavrsetakVoznjeLoginPage(WebDriver driver) {
        this.driver = driver;
        driver.get(URL_HOME);
        PageFactory.initElements(driver,this);
    }

    public boolean isOpened() {
        boolean isLoadedPage = (new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElement(logIn,"Login")));
        return isLoadedPage;
    }

    public void clickRegisterBtnNavbar() {
        (new WebDriverWait(driver, Duration.ofSeconds(5)))
                .until(ExpectedConditions.elementToBeClickable(registerBtnNavbar)).click();
    }

    public void loginAsDriver() {
        this.emailInput.sendKeys(DRIVER_EMAIL);
        this.passwordInput.sendKeys(DRIVER_PASSWORD);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(logInButton)).click();
    }

    public void loginAsUser() {
        this.emailInput.sendKeys(USER_EMAIL);
        this.passwordInput.sendKeys(USER_PASSWORD);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(logInButton)).click();
    }

    public void loginAsDriver2() {
        this.emailInput.sendKeys(DRIVER_EMAIL_2);
        this.passwordInput.sendKeys(DRIVER_PASSWORD);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(logInButton)).click();
    }

    public void loginAsUser2() {
        this.emailInput.sendKeys(USER_EMAIL_2);
        this.passwordInput.sendKeys(USER_PASSWORD);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(logInButton)).click();
    }
}
