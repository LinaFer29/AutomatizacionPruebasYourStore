package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductDetailsPage extends BasePage {

    public ProductDetailsPage (WebDriver driver){super(driver);}

    //Elementos
    private By quantityInput (String quantityInput){
        return By.xpath("//div[@class=\"form-group\"]//input[@id=\""+ quantityInput +"\"]");
    }

    private By addToCartBtn (String button){
        return By.xpath("//div[@class=\"form-group\"]//button[@id=\""+ button +"\"]");
    }

    //Acciones
    public void setQuantity(int quantity, String input){
        WebElement quantityInput =  driver.findElement(quantityInput(input));
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(quantity));
    }

    public void selectAddToCartBtn (String button){
        driver.findElement(addToCartBtn(button)).click();
    }


}
