package com.sinaukoding.librarymanagementsystem_SKPlus_Assesment.model.app;

public class Checks {

    public static void isTrue(boolean param, String message) {
        if (!param) throw newE(message);
    }

    public static RuntimeException newE(String message) {
        return new RuntimeException(message);
    }

}
