package com.opencart.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Beta;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void setup(){
        //Configurar el Driver
        WebDriverManager.chromedriver().setup();

        //Crear una instancia de WebDriver para Chrome
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown(){
        if(driver != null){
           // driver.quit();
        }
    }
}
