package com.opencart.test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserLoginTest extends BaseTest {

    @Test
    public void testNavigationToLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {

            WebElement myAccountButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//nav[@id='top']//div[@id='top-links']//li[@class='dropdown']//a[@title='My Account']")));
            myAccountButton.click();

            WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']")));


            WebElement loginOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[text()='Login']")));

            loginOption.click();

            //Verificar navegación a la página de login
            wait.until(ExpectedConditions.titleContains("Login"));

        } catch (Exception e) {
            System.err.println("eror en test de navegación: " + e.getMessage());
        }
    }
}
