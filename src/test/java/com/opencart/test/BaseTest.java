package com.opencart.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Beta;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.opencart.utils.Constants;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void setup(){
        //Configurar el Driver
        WebDriverManager.chromedriver().setup();

        //Crear una instancia de WebDriver para Chrome
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(Constants.BASE_URL);
    }

    @AfterEach
    public void tearDown(){
        if(driver != null){
           // driver.quit();
        }
    }
}
