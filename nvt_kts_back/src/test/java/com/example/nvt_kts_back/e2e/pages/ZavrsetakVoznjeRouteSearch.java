package com.example.nvt_kts_back.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ZavrsetakVoznjeRouteSearch {
    private WebDriver driver;

    private static String STARTLOCATIOn = "puskinova 6, Novi Sad";
    private static String ENDLOCATIOn = "dragise brasovana 10, Novi Sad";


    public ZavrsetakVoznjeRouteSearch(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }



    @FindBy(css = "div.leaflet-routing-geocoders > div:nth-child(1) > input")
    private WebElement startLocation;

    @FindBy(css = "div.leaflet-routing-geocoders > div:nth-child(2) > input")
    private WebElement endLocation;

    @FindBy(css = "#quickTime")
    private WebElement quickTime;

    @FindBy(css="section > div > div > div.col > div:nth-child(7) > div.col > button")
    private WebElement orderBtn;

    @FindBy(css=" nav > div > ul > li:nth-child(8) > a")
    private WebElement userLogoutBtn;

    @FindBy(css="nav > div > ul > li:nth-child(6) > a")
    private WebElement driverLogoutBtn;

    public boolean isOpened() {
        (new WebDriverWait(driver, Duration.ofSeconds(5)))
                .until(ExpectedConditions.visibilityOf(startLocation));
        return true;
    }

    public void fillForm() throws InterruptedException {
        this.startLocation.clear();
        this.startLocation.sendKeys(STARTLOCATIOn);
        this.startLocation.sendKeys(Keys.ENTER);


        this.endLocation.clear();
        this.endLocation.sendKeys(ENDLOCATIOn);
        this.endLocation.sendKeys(Keys.ENTER);
    }

    public boolean btnIsNotPresent() {
        (new WebDriverWait(driver, Duration.ofSeconds(5)))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("section > div > div > div.col > div:nth-child(7) > div.col > button")));
        return true;
    }

    public void logoutUser() {
        this.userLogoutBtn.click();
    }

    public void logoutDriver() {
        this.driverLogoutBtn.click();
    }


    public void schedule() {
        this.orderBtn.click();
    }

    public boolean btnIsPresent() {
        (new WebDriverWait(driver, Duration.ofSeconds(5)))
                .until(ExpectedConditions.visibilityOf(orderBtn));
        return true;
    }
}
