package com.example.nvt_kts_back.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class RegistrationPage {
    private WebDriver driver;

    @FindBy(css = "form > div.form-group.row.float-right > div.col-1.float-right > button")
    private WebElement registerBtn;

    @FindBy(css = "#name")
    private WebElement nameInput;

    @FindBy(css = "#surname")
    private WebElement surnameInput;

    @FindBy(css = "#email")
    private WebElement emailInput;

    @FindBy(css = "#password")
    private WebElement passwordInput;

    @FindBy(css = "#repeated")
    private WebElement repeatedPasswordInput;

    @FindBy(css = "#city")
    private WebElement city;

    @FindBy(css = "#phone")
    private WebElement phoneNumberInput;


    public RegistrationPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    public boolean isOpened() {
        boolean isLoadedPage = (new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElement(registerBtn,"Register")));
        return isLoadedPage;
    }

    public void setCorrectValues() {
        this.nameInput.sendKeys("Isidora");
        this.surnameInput.sendKeys("Vasic");
        this.emailInput.sendKeys("is@gmail.com");
        this.passwordInput.sendKeys("mladjaa");
        this.repeatedPasswordInput.sendKeys("mladjaa");
        this.city.sendKeys("Novi Sad");
        this.phoneNumberInput.sendKeys("065-775-801");
    }

    public void clickRegisterBtn() {
        this.registerBtn.click();
    }

    public void setIncorrectValues() {
        this.nameInput.sendKeys("");
        this.surnameInput.sendKeys("P");
        this.emailInput.sendKeys("nevalidanmejl");
        this.passwordInput.sendKeys("mladjaa");
        this.repeatedPasswordInput.sendKeys("mladjaan");
        this.city.sendKeys("N");
        this.phoneNumberInput.sendKeys("06-71");
    }

    public boolean thereAre5smalls() {
        List<WebElement> smalls = driver.findElements(By.cssSelector("small"));
        return smalls.size()==5;
    }
}
