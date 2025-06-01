package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SuccessMessagePage extends BasePage {

    public SuccessMessagePage (WebDriver driver) { super(driver);}

    private By successMessage (){
        return By.xpath("//div[@id=\"content\"]//p[contains(text(),\"Congratulations\")]");
    }

    public String getSuccessMessage (){
        WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage()));
        return messageElement.getText();
    }
}
