package com.opencart.test;

import com.opencart.pages.CartPage;
import com.opencart.utils.Excel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class CartValidationTest {

    private WebDriver driver;
    private CartPage cartPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://opencart.abstracta.us/");
        cartPage = new CartPage(driver);
    }

    @Test
    public void validateProductsFromExcelInCart() {
        // Read products from Excel
        Excel excel = new Excel("resources/inputData.xlsx");
        List<String[]> rawData = excel.readData();

        // Extract only the first column (product names)
        List<String> expectedProducts = rawData.stream()
                .map(row -> row[0])
                .collect(Collectors.toList());

        // Navigate to cart page
        driver.findElement(By.cssSelector("#top-links a[title='Shopping Cart']")).click();

        // Get products currently in the cart
        List<String> cartProducts = cartPage.getProductNamesInCart();

        // Assert each expected product is present in the cart
        for (String expected : expectedProducts) {
            Assert.assertTrue(cartProducts.contains(expected),
                    "Product '" + expected + "' is not found in the cart.");
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
