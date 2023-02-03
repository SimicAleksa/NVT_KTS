package com.example.nvt_kts_back.e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NarucivanjeEditProfilePage {
    private WebDriver driver;

    @FindBy(tagName = "h1")
    private WebElement editH1;

    @FindBy(id = "buttonIcon")
    private WebElement sideBarMenu;

    @FindBy(tagName = "h6")
    private WebElement myRidesH6;

    @FindBy(className = "btn-success")
    private WebElement acceptButton;

    @FindBy(css = ".toast-info > div")
    private WebElement toasterMessageContainerYou;

    @FindBy(css = ".toast-success:nth-child(2) > div")
    private WebElement toasterMessageContainerAllPassangers;

    @FindBy(css = ".toast-warning > div")
    private WebElement toasterMessageContainerNoTokens;

    @FindBy(css = ".toast-error > div")
    private WebElement toasterMessageError;

    @FindBy(linkText = "LOGOUT")
    private WebElement logOutBtn;

    @FindBy(id = "tokensLbl")
    private WebElement tokenCounter;

    public NarucivanjeEditProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void clickSideBarMenuBtn(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(sideBarMenu)).click();
    }

    public void clickLogOutBtn(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(logOutBtn)).click();
    }

    public void clickAcceptBtn(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(acceptButton)).click();
    }

    public boolean isTOkenCounterChanged() {
        boolean isTOkenCounterChanged = (new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.textToBePresentInElement(tokenCounter,"4814")));
        return isTOkenCounterChanged;
    }

    public boolean isSideBarOpen() {
        boolean isLoadedPage = (new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElement(myRidesH6,"My rides")));
        return isLoadedPage;
    }

    public boolean isApprovedByAll() {
        boolean isApprovedByAll = (new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElement(toasterMessageContainerAllPassangers,"All passengers approved ride.")));
        return isApprovedByAll;
    }

    public boolean isRideDeclined_NoDriver() {
        boolean isRideDeclined = (new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElement(toasterMessageError,"Your ride is declined because there are no available drivers")));
        return isRideDeclined;
    }

    public boolean isApprovedByYou() {
        boolean isApprovedByYou = (new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElement(toasterMessageContainerYou,"You successfully approved ride")));
        return isApprovedByYou;
    }

    public boolean isDeclined_NoTokens() {
        boolean isDeclined_NoTokens = (new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElement(toasterMessageContainerNoTokens,"You don't have enought tokens!")));
        return isDeclined_NoTokens;
    }

    public boolean isOpened() {
        boolean isLoadedPage = (new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElement(editH1,"Edit your data")));
        return isLoadedPage;
    }
}
