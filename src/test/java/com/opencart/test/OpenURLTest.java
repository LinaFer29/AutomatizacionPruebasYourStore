package com.opencart.test;

import com.opencart.pages.BasePage;
import com.opencart.pages.CategoryPage;
import com.opencart.pages.ProductDetailsPage;
import com.opencart.utils.Constants;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class OpenURLTest extends BaseTest{

    @Test
    public void OpenURL(){
        //llamar a todas las paginas que requiera para el test
        BasePage basePage = new BasePage(driver);
        basePage.navigateTo(Constants.BASE_URL);
    }
}
