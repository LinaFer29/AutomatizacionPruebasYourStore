package com.opencart.utils;

import java.util.ArrayList;
import java.util.List;

public class Verify {

    private static final List<AssertionError> errors  = new ArrayList<>();

    public static void verify(Runnable assertion){
        try{
            assertion.run();
        } catch (AssertionError e) {
            System.err.println("Verify Failed: " + e.getMessage());
           errors.add(e);// Consolido los errores
        }
    }

    public static void verifyAll(){
        if (!errors.isEmpty()){
            AssertionError combined = new AssertionError("Se encontraron los siguientes errores:");
            errors.forEach(combined::addSuppressed);
            errors.clear();//limpiar cada test
            throw combined;
        }
    }
}
