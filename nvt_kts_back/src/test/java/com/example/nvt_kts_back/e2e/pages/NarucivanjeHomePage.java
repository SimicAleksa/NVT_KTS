package com.example.nvt_kts_back.e2e.pages;

import net.bytebuddy.asm.Advice;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.KeyInput;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.Key;
import java.time.Duration;

public class NarucivanjeHomePage {
    private WebDriver driver;

    private static String URL_HOME = "http://localhost:4200/login";

    private static String EMAIL = "seleBrateMojhihixD@gmail.com";
    private static String EMAIL_NOMONEY = "nomoneysadgeSelen@gmail.com";

    private static String EMAILPRVIPRIHVATIO = "nijeplatiozadnji@gmail.com";
    private static String EMAILPOSLEDNJIPRIHVATIO = "jesteplatiozadnji@gmail.com";

    private static String EMAILDODATNOPUTNICI = "goran@gmail.com";
    private static String EMAILNODRIVER = "anastasija@gmail.com";

    private static String PASSWORD = "sifra123";
    private boolean money;
    private boolean lastPayed;

    @FindBy(css = "app-login-form > form > div.row.card-style > div.row > span")
    private WebElement logIn;

    @FindBy(css = "#email")
    private WebElement emailInput;

    @FindBy(css = "#password")
    private WebElement passwordInput;

    @FindBy(css = "#login-btn")
    private WebElement logInButton;

    public NarucivanjeHomePage(WebDriver driver,boolean money,boolean lastPayed) {
        this.money = money;
        this.driver = driver;
        driver.get(URL_HOME);
        PageFactory.initElements(driver,this);
    }

    public boolean isOpened() {
        boolean isLoadedPage = (new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElement(logIn,"Login")));
        return isLoadedPage;
    }

    public void enterEmailPlatio(){
        emailInput.clear();
        if(this.lastPayed)
            emailInput.sendKeys(EMAILPOSLEDNJIPRIHVATIO);
        else
            emailInput.sendKeys(EMAILPRVIPRIHVATIO);
    }
    public void enterEmailNoDriver(){
        emailInput.clear();
        emailInput.sendKeys(EMAILNODRIVER);
    }


    public void enterEmail(){
        emailInput.clear();
        if(this.money)
            emailInput.sendKeys(EMAIL);
        else
            emailInput.sendKeys(EMAIL_NOMONEY);
    }

    public void enterEmailDodato(){
        emailInput.clear();
        emailInput.sendKeys(EMAILDODATNOPUTNICI);
    }

    public void enterPassword(){
        passwordInput.clear();
        passwordInput.sendKeys(PASSWORD);
    }

    public void clickLogInButton(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(logInButton)).click();
    }
}
