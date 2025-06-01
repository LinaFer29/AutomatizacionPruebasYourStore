package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage {
    private final WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> getProductNamesInCart() {
        List<String> names = new ArrayList<>();
        List<WebElement> rows = driver.findElements(By.cssSelector(".table-responsive tbody tr"));
        for (WebElement row : rows) {
            String productName = row.findElement(By.cssSelector("td:nth-child(2) a")).getText().trim();
            names.add(productName);
        }
        return names;

    }
}
