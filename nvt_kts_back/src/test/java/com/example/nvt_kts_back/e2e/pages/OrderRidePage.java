package com.example.nvt_kts_back.e2e.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.KeyInput;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderRidePage {
    private WebDriver driver;
    private static String STARTLOCATIOn = "puskinova 6, Novi Sad";
    private static String ENDLOCATIOn = "dragise brasovana 10, Novi Sad";

    @FindBy(id = "coupe")
    private WebElement coupeCheckBox;

    @FindBy(id = "orderIdBUtton")
    private WebElement orderButton;

    @FindBy(css = "div.leaflet-routing-geocoders > div:nth-child(1) > input")
    private WebElement startLocationInput;

    @FindBy(css = "div.leaflet-routing-geocoders > div:nth-child(2) > input")
    private WebElement endLocationInput;

    public OrderRidePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void enterStartLocation(){
        startLocationInput.clear();
        startLocationInput.sendKeys(STARTLOCATIOn);
        startLocationInput.sendKeys(Keys.ENTER);
    }

    public void enterEndLocation(){
        endLocationInput.clear();
        endLocationInput.sendKeys(ENDLOCATIOn);
        endLocationInput.sendKeys(Keys.ENTER);
    }

    public void clickOrderRideButton(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(orderButton)).click();
    }

    public boolean isOpened() {
        boolean isLoadedPage = (new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeSelected(coupeCheckBox)));
        return isLoadedPage;
    }
}
