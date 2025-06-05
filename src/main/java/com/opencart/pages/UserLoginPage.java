package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class UserLoginPage extends BasePage {

    public UserLoginPage(WebDriver driver) {
        super(driver);
    }

    // Elementos de la página de login
    private By emailField() {
        return By.id("input-email");
    }

    private By passwordField() {
        return By.id("input-password");
    }

    private By loginButton() {
        return By.xpath("//input[@value='Login']");
    }

    private By accountDashboard() {
        return By.xpath("//div[@id='content']//h2[text()='My Account']");
    }

    public void login(String email, String password) {
        // Esperar a que la página de login esté completamente cargada
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField()));

        // Limpiar y llenar el campo de email
        WebElement emailInput = driver.findElement(emailField());
        emailInput.clear();
        emailInput.sendKeys(email);

        // Limpiar y llenar el campo de password
        WebElement passwordInput = driver.findElement(passwordField());
        passwordInput.clear();
        passwordInput.sendKeys(password);

        // Hacer clic en el botón de login
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton()));
        loginBtn.click();
    }

    public boolean isLoginSuccessful() {
        try {
            WebElement dashboard = wait.until(ExpectedConditions.visibilityOfElementLocated(accountDashboard()));
            return dashboard.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        try {
            return driver.findElement(By.cssSelector(".alert-danger")).getText();
        } catch (Exception e) {
            return "No se encontró mensaje de error";
        }
    }
}
