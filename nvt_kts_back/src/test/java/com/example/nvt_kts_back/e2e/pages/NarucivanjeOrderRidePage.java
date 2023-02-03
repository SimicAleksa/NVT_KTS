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
import java.time.LocalDate;
import java.time.LocalDateTime;

public class NarucivanjeOrderRidePage {
    private WebDriver driver;
    private static String STARTLOCATIOn = "puskinova 6, Novi Sad";
    private static String ENDLOCATIOn = "dragise brasovana 10, Novi Sad";

    // ADDITIONALLOCATION ce zapravo biti poslednja uneta lokacija (zanemari ime)
    private static String ADDITIONALLOCATION = "sekspirova 2, Novi Sad";

    private static String LINKEDPASSANGEREMAIL = "djura@gmail.com";
    private String checkIfReserved;

    @FindBy(id = "coupe")
    private WebElement coupeCheckBox;

    @FindBy(linkText = "EDIT PROFILE")
    private WebElement editProfileBtn;

    @FindBy(id = "orderIdBUtton")
    private WebElement orderButton;

    @FindBy(css = "section > div > div > div.col > div:nth-child(5) > div:nth-child(2) > input")
    private WebElement calendarInput;

    @FindBy(css = "div.leaflet-routing-geocoders > div:nth-child(1) > input")
    private WebElement startLocationInput;

    @FindBy(css = "div.leaflet-routing-geocoders > div:nth-child(2) > input")
    private WebElement endLocationInput;

    @FindBy(css = "div.leaflet-routing-geocoders > div:nth-child(3) > input")
    private WebElement additionalWaypoint;

    @FindBy(css = "div.leaflet-routing-geocoders > button")
    private WebElement addWayPointButton;

    @FindBy(css = "#toast-container > div")
    private WebElement toasterMessageContainer;

    @FindBy(id = "addLinkedInput")
    private WebElement inputLinkedPassengers;

    @FindBy(id = "reserve")
    private WebElement reserveCheckBox;

    @FindBy(css = ".toast-warning:nth-child(2) > div")
    private WebElement toasterMessageContainer5H;

    @FindBy(id = "minivan")
    private WebElement minivan_check;

    @FindBy(id = "hatchback")
    private WebElement hatchback_check;

    @FindBy(id = "suv")
    private WebElement suv_check;

    @FindBy(id = "van")
    private WebElement van_check;

    @FindBy(id = "sedan")
    private WebElement sedan_check;

    @FindBy(id = "coupe")
    private WebElement coupe_check;

    public NarucivanjeOrderRidePage(WebDriver driver) {
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

    public void enterAdditionalWaypoint(){
        additionalWaypoint.clear();
        additionalWaypoint.sendKeys(ADDITIONALLOCATION);
        additionalWaypoint.sendKeys(Keys.ENTER);
    }

    public void enterLinkedPassanger(){
        inputLinkedPassengers.clear();
        inputLinkedPassengers.sendKeys(LINKEDPASSANGEREMAIL);
        inputLinkedPassengers.sendKeys(Keys.ENTER);
    }

    public void enter1PlusTIme(){
        calendarInput.clear();
        String day = String.format("%02d",LocalDateTime.now().getDayOfMonth());
        String hour = String.format("%02d",LocalDateTime.now().getHour()+1);
        String minute = String.format("%02d",LocalDateTime.now().getMinute());
        checkIfReserved = "2023-02-"+day+"T"+hour+":"+minute;
        System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        System.out.println(checkIfReserved);
        calendarInput.sendKeys(day);
        // ZEZA!!!!!!!!!!!!!!!!
//        calendarInput.sendKeys(Keys.TAB);
        calendarInput.sendKeys(hour);
        calendarInput.sendKeys(minute);
        calendarInput.sendKeys(Keys.TAB);
        calendarInput.sendKeys(Keys.ENTER);
    }

    public void deselectAllExceptLimo(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(coupe_check)).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(hatchback_check)).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(sedan_check)).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(suv_check)).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(minivan_check)).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(van_check)).click();
    }

    public boolean isTimeSet() {
        boolean isTimeSet = (new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElement(calendarInput,checkIfReserved)));
        return isTimeSet;
    }


    public void clickReserveBox(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(reserveCheckBox)).click();
    }

    public void clickAddWaypoint(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(addWayPointButton)).click();
    }

    public void clickEditProfile(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(editProfileBtn)).click();
    }

    public void clickOrderRideButton(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(orderButton)).click();
    }

    public boolean isNotIn5HMark() {
        boolean isNotIn5HMark = (new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElement(toasterMessageContainer5H,"Invalid date or time selected!")));
        return isNotIn5HMark;
    }

    public boolean isOrdered() {
        boolean isRideOrdered = (new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElement(toasterMessageContainer,"Successfully ordered")));
        return isRideOrdered;
    }

    public boolean isOpened() {
        boolean isLoadedPage = (new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeSelected(coupeCheckBox)));
        return isLoadedPage;
    }
}
