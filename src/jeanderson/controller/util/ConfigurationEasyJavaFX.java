/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.util;

/**
 * Classe que contém informações que são usadas em várias classes do EasyjavaFx.
 *
 * @author Jeanderson
 */
public class ConfigurationEasyJavaFX {

    private final static String DEFAULT_URL_ICON = "/jeanderson/view/img/javafx-logo.png";
    private final static String DEFAULT_FXML = "/jeanderson/view/DefaultView.fxml";
    private final static String DEFAULT_TITLE = "Janela";
    private final static boolean DEFAULT_FULLSCREEN = false;
    private final static boolean DEFAULT_RESIZABLE = true;
    private final static boolean DEFAULT_MAXIMIZED = false;
    private final static boolean DEFAULT_AUTO_NEW_STAGE = true;
    private final static boolean DEFAULT_STATIC_CLASS = false;

    public static String getDEFAULT_URL_ICON() {
        return DEFAULT_URL_ICON;
    }

    public static String getDEFAULT_FXML() {
        return DEFAULT_FXML;
    }

    public static String getDEFAULT_TITLE() {
        return DEFAULT_TITLE;
    }

    public static boolean isDEFAULT_FULLSCREEN() {
        return DEFAULT_FULLSCREEN;
    }

    public static boolean isDEFAULT_RESIZABLE() {
        return DEFAULT_RESIZABLE;
    }

    public static boolean isDEFAULT_MAXIMIZED() {
        return DEFAULT_MAXIMIZED;
    }

    public static boolean isDEFAULT_AUTO_NEW_STAGE() {
        return DEFAULT_AUTO_NEW_STAGE;
    }

    public static boolean isDEFAULT_STATIC_CLASS() {
        return DEFAULT_STATIC_CLASS;
    }    
}
