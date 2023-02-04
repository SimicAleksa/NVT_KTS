package com.example.nvt_kts_back.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ZavrsetakVoznjeReportPage {
    private WebDriver driver;

    @FindBy(css = "#confirm")
    private WebElement confirmBtn;

    @FindBy(css = "#toggleBtn")
    private WebElement sideArrow;

    @FindBy(css = "#offcanvas > div.offcanvas-header > div.col-1.d-flex.justify-content-end > button")
    private WebElement sideX;


    @FindBy(css = "#menu > li > div > div:nth-child(5) > div.col-4 > button")
    private WebElement endRideBtn;

    public ZavrsetakVoznjeReportPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public boolean isOpened() {
        boolean isLoadedPage = (new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElement(confirmBtn,"Confirm")));
        return isLoadedPage;
    }

    public void openSideBar() {
        sideArrow.click();
    }

    public boolean isSidebarOpen() {
        (new WebDriverWait(driver, Duration.ofSeconds(5)))
                .until(ExpectedConditions.elementToBeClickable(endRideBtn));
        return true;
    }

    public void endRide() {
        endRideBtn.click();
    }

    public boolean rideIsNotPresent() {
        (new WebDriverWait(driver, Duration.ofSeconds(5)))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#menu > li > div > div:nth-child(5) > div.col-4 > button")));
    return true;
    }
}
