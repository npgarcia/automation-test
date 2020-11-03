package com.guitarinventory;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestSearch {
    private WebDriver driver;
    private WebDriverWait waitDriver;

    private String inputLocator = ".//form/input";
    private String buttonLocator = ".//form/button";
    private String resultURLLocator = ".//a[text()='URL']";
    private String cardFromTitle = ".//span[text()='{title}']/.//ancestor::div[contains(@class,'card')]";
    private String backButton = ".//a[@class='btn btn-primary']";

    private final String URL = "http://localhost:3000/shows";
    private final String testColor = "#4a148c";

    public void search(String searchTerm) {
        WebElement input = driver.findElement(By.xpath(inputLocator));
        input.sendKeys(searchTerm);

        WebElement button = driver.findElement(By.xpath(buttonLocator));
        button.click();
    }

    public String getInputValue() {
        WebElement input = driver.findElement(By.xpath(inputLocator));
        return input.getAttribute("value");
    }

    public void goToResult(int index) {
        List<WebElement> results = driver.findElements(By.xpath(resultURLLocator));
        WebElement url = results.get(0);

        url.click();
    }

    public void changeResultBackgroundBackground(String title) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement card = driver.findElement(By.xpath(cardFromTitle.replace("{title}",title)));
        js.executeScript("arguments[0].setAttribute('style', 'background-color: "+testColor+" !important')", card);
    }

    public void clickBackButton() {
        WebElement back = driver.findElement(By.xpath(backButton));
        waitDriver.until(ExpectedConditions.elementToBeClickable(back));
        back.click();
    }

    @Before
    public void setDriver() {
        ChromeOptions capabilities = new ChromeOptions();
        capabilities.addArguments("--window-size=1204,1080");
        capabilities.addArguments("--no-sandbox");

        driver = new ChromeDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        waitDriver = new WebDriverWait(driver,5);
    }

    @After
    public void destroyDriver() {
        driver.quit();
    }

    @Test
    public void searchForm() {
        driver.get(URL);
        search("batman");
        goToResult(1);
        driver.navigate().back();
        changeResultBackgroundBackground("Batman Unlimited");
        clickBackButton();

        Assert.assertEquals(getInputValue(),"");
    }
}
