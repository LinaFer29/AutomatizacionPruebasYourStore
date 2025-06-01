package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Instant;

public class CategoryPage extends BasePage {

    //constructor
    public CategoryPage (WebDriver driver){super(driver);}

    //Elementos
    private By product (String productName){
        return By.xpath("//div[@class=\"product-thumb\"]//a[text()=\""+ productName +"\"]");
    }

    //Acciones
    public void selectProductByName (String productName){

        wait.until(ExpectedConditions.visibilityOfElementLocated(product(productName)));
        driver.findElement(product(productName)).click();
    }

}
