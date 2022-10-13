package org.example;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.log4j.BasicConfigurator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SortTests {

    public WebDriver driver;

    @BeforeAll
    public static void setDriver() {
        BasicConfigurator.configure();
        System.setProperty("webdriver.chrome.driver","./drivers/chromedriver");
    }

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void quit() {
        driver.quit();
    }

    // Validate Selecting a sort option return results that match the selected sort option
    @Test
    public void sortByName() {
        driver.get("https://highlifeshop.com/speedbird-cafe");

        // get all visible products titles and store them in a list
        List<String> productTitles = getScreenProductsTitles();

        // Sort list titles Alphapetically using java sort method
        Collections.sort(productTitles);

        // Accept Cookies
        WebElement allowButtonElement = new WebDriverWait(driver,Duration.ofSeconds(60)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[title='ALLOW ALL']")));
        allowButtonElement.click();

        // Wait un till the loader finish
        try{Thread.sleep(500);}
        catch(InterruptedException e){System.out.println(e);}

        // Click on sort dropdown list
        WebElement sortDropList = new WebDriverWait(driver,Duration.ofSeconds(60)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class='toolbar-sorter sorter']")));
        sortDropList.click();

        // Change sorting criteria to be by name
        WebElement sortByNameOption = new WebDriverWait(driver,Duration.ofSeconds(60)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("option[value='name']")));
        sortByNameOption.click();

       // Store the products after sorting in another list
        List<String> productTitlesAfterSort = getScreenProductsTitles();

        // Assert that the two lists have the same ordering
        assertEquals(productTitlesAfterSort, productTitles);
    }

    //  Function that store the products in a list
    public List<String> getScreenProductsTitles() {
        List<String> titles = new ArrayList<>();
        List<WebElement> productsList = driver.findElements(By.className("product-item-info"));
        for(int i = 0 ; i < productsList.stream().count() ; i ++) {
            // select element with title
            WebElement currentProduct = productsList.get(i);
            WebElement titleLink =  currentProduct.findElement(
                    By.cssSelector("a[class='product-item-link']")
            );
            titles.add(titleLink.getText());
        }
        return titles;
    }

}
