package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class HomePage extends BasePage {

    //Constructor

    public HomePage (WebDriver driver){super(driver);}

    //Crear elementos de la pagina
    private By category(String category){
        return By.xpath("//a[text()=\""+ category +"\"]");
    }

    private By subCategory(String subCategory){
        return By.xpath("//div[@class=\"dropdown-inner\"]//a[contains(text(),\""+subCategory+"\")]");
    }

    //Metodos o acciones de la pagina
    public void selectCategory (String category){
        driver.findElement(category(category)).click();
    }

    //div[@class=""]//a[text()="PC (0)"]
    public void selectSubCategory (String subCategory){
        //Fluent Wait
        Wait<WebDriver> waitF = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15)) //Tiempo espera del elemento
                .pollingEvery(Duration.ofMillis(300)) //Frecuencia de verificacion
                .ignoring(NoSuchFieldError.class); //Excepciones que ignora
        WebElement subCat = waitF.until(driver -> driver.findElement(subCategory(subCategory)));
        subCat.click();
    }
}
