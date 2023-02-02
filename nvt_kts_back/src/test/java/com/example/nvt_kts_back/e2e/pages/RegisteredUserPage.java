package com.example.nvt_kts_back.e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisteredUserPage {
    private WebDriver driver;

    @FindBy(linkText = "\uD83D\uDD0E\uD83D\uDDFA")
    private WebElement orderRouteNavBarButton;

    @FindBy(css = "nav > div > ul > li:nth-child(8) > a")
    private WebElement logOutButton;

    @FindBy(id = "coupe")
    private WebElement coupeCheckBox;

    public RegisteredUserPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public boolean isOpened() {
        boolean isLoadedPage = (new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElement(logOutButton,"LOGOUT")));
        return isLoadedPage;
    }

    public void clickOrderRideButtonNavBar(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(orderRouteNavBarButton)).click();
    }
}
