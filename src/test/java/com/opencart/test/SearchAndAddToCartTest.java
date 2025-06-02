package com.opencart.test;

import com.opencart.pages.CategoryPage;
import com.opencart.pages.HomePage;
import com.opencart.utils.Constants;
import com.opencart.utils.Excel;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class SearchAndAddToCartTest extends BaseTest{

    @Test
    public void SearchProductAndAddToCart(){

        HomePage basePage = new HomePage(driver);
        CategoryPage categorypage = new CategoryPage(driver);
        Excel excelProducts = new Excel(Constants.FILE_PATH_EXCEL_PRODUCTS);

        //Leer datos del Excel
        List<String[]> products = excelProducts.readData();

        //Abrir la pagina
        //basePage.navigateTo(Constants.BASE_URL);

        //Test con los datos del Excel
        categorypage.addProductsToCart(products);
    }
}
