package com.xw;


import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;

public class Application {
    private static  Box box;

    public static void main(String[] args) {
        Application application = new Application();
        application.start();

    }
    private void start() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        box = new Box();
        box.setVisible(true);

    }
}
