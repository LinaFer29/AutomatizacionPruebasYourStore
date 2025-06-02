package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Instant;
import java.util.List;

public class CategoryPage extends BasePage {

    //constructor
    public CategoryPage (WebDriver driver){super(driver);}

    //Elementos
    private By product (String productName){
        return By.xpath("//div[@class=\"product-thumb\"]//a[text()=\""+ productName +"\"]");
    }

    //Elementos Nuevos
    //Elemento para seleccionar la categoria principal
    private By CategoryInput (String category){
        return By.xpath("//a[text()=\""+category+"\"]");
    }

    //Elemento para seleccionar la subcategoria
    private By SubCategory(String subCategory){
        return By.xpath("//div[@class='dropdown-inner']//a[contains(text(),\""+subCategory+"\")]");
    }

    //Elemento para agregar el producto al carrito
    private By AddInput(String productName){
        return By.xpath("//div[@class=\"product-thumb\" and .//a[text()=\""+ productName +"\"]]//button[.//span[normalize-space()='Add to Cart']]");
    }

    //Metodo para verificar que si existe el producto a buscar
    public boolean isProductDisplayed(String productName) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(product(productName)));
            return driver.findElement(product(productName)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    //Acciones
    public void selectProductByName (String productName){

        wait.until(ExpectedConditions.visibilityOfElementLocated(product(productName)));
        driver.findElement(product(productName)).click();
    }

    //Accion nueva
    //verificar que si la categoria seleccionada tiene una subcategoria
    //verificar que el producto esta en la pagina
    //Agregar el producto al carrito
    public void selectProductsCategory(String category, String subCategory, String product) {
        driver.findElement(CategoryInput(category)).click();
        if(subCategory != null && !subCategory.isEmpty() ){
            driver.findElement(SubCategory(subCategory)).click();
        }
        if(isProductDisplayed(product)){
            System.out.println("CategoryPage: " + product);
            driver.findElement(AddInput(product)).click();
        }
    }

    public void addProductsToCart(List<String[]> rawData) {
        for (String[] rowData : rawData) {
            if (rowData.length >= 3) {
                String category = rowData[0]; // Columna A: categoría
                String subCategory = rowData[1]; // Columna B: subcategoría
                String product = rowData[2]; // Columna C: producto

                // Agregar el producto al carrito
                selectProductsCategory(category, subCategory, product);
            } else {
                System.out.println("Fila con datos insuficientes. Saltando...");
            }
        }
    }

}
