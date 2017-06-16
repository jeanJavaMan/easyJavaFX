/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.util;

/**
 * Classe que contém informações que são usadas em várias classes do EasyjavaFx.
 * @author Jeanderson
 */
public  class ConfigurationEasyJavaFX {
    private final static String DEFAULT_URL_ICON = "/jeanderson/view/img/easyJavaFX.png";
    private final static String DEFAULT_FXML = "/jeanderson/view/DefaultView.fxml";
    private final static String DEFAULT_TITLE = "Janela";

    public static String getDEFAULT_URL_ICON() {
        return DEFAULT_URL_ICON;
    }

    public static String getDEFAULT_FXML() {
        return DEFAULT_FXML;
    }

    public static String getDEFAULT_TITLE() {
        return DEFAULT_TITLE;
    }

    
    
    
}
