package org.example;

import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        System.setProperty("webdriver.chrome.driver","./drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
//        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        driver.get("https://highlifeshop.com/speedbird-cafe");
//
        WebElement allowButtonElement = new WebDriverWait(driver,Duration.ofSeconds(60)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[title='ALLOW ALL']")));
        allowButtonElement.click();

        try{Thread.sleep(500);}
        catch(InterruptedException e){System.out.println(e);}

        List<WebElement> productsList = driver.findElements(By.className("product-item-info"));

//         Click on sort dropdown list
//        WebElement sortDropList = new WebDriverWait(driver,Duration.ofSeconds(60)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class='toolbar-sorter sorter']")));
//        sortDropList.click();
//
//        // Change sorting criteria to be by name
//        WebElement sortByNameOption = sortDropList.findElement(By.cssSelector("option[value='name']"));
//        sortByNameOption.click();
//
//        WebElement sortElement = driver.findElement(By.className("btn btn-outline-primary mt-3"));
//        sortElement.click();

//        String title = driver.getTitle();
//
//        WebElement textBox = driver.findElement(By.name("my-text"));
//        WebElement submitButton = driver.findElement(By.cssSelector("button"));
//
//        textBox.sendKeys("Selenium");
//        submitButton.click();
//
//        WebElement message = driver.findElement(By.id("message"));
//        String value = message.getText();

        driver.close();
    }
}