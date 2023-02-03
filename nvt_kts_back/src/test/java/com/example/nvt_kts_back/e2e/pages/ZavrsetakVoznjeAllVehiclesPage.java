package com.example.nvt_kts_back.e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ZavrsetakVoznjeAllVehiclesPage {
    private WebDriver driver;

    @FindBy(css = "a.leaflet-control-zoom-in > span")
    private WebElement plusBtn;

    @FindBy(css = "nav > div > ul > li:nth-child(4) > a")
    private WebElement driverReportsA;

    @FindBy(css = "nav > div > ul > li:nth-child(5) > a")
    private WebElement userReportsA;

    @FindBy(css = "nav > div > ul > li:nth-child(7) > a")
    private WebElement searchRoutesA;





    public ZavrsetakVoznjeAllVehiclesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void openReportPageDriver() {
        this.driverReportsA.click();
    }

    public void openReportPageUser() {
        this.userReportsA.click();
    }

    public boolean isOpened() {
        boolean isLoadedPage = (new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElement(plusBtn,"+")));
        return isLoadedPage;
    }

    public void openRouteSearch() {
        this.searchRoutesA.click();
    }
}
