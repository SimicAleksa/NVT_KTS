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

public class HomePage {
    private WebDriver driver;

    private static String URL_HOME = "http://localhost:4200/login";

    @FindBy(css = "body > app-root > app-login-page > div > div:nth-child(2) > app-login-form > form > div.row.card-style > div.row > span")
    private WebElement logIn;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        driver.get(URL_HOME);
        PageFactory.initElements(driver,this);
    }

    public boolean isOpened() {
        boolean isLoadedPage = (new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElement(logIn,"Login")));
        return isLoadedPage;
    }
}
