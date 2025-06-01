package com.opencart.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    public BasePage(WebDriver driver){
        this.driver = driver;
        //Tiempo para vairable de espera
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        //Espera implicita, Se Configura una sola vez y aplica para todos los elementos
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public void navigateTo(String url){
        driver.get(url);
    }
    public String getTitle(){
        return driver.getTitle();
    }



}
