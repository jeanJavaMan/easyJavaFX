/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.util;

/**
 *
 * @author Jeanderson
 */
public enum Configuration {
    /**
     * Define se a janela deve abrir em fullscreen. Utilizar Boolean para está definição.
     */
    FULLSCREEN, 
    /**
     * Define se a janela é redimensionavel  Utilizar Boolean para está definição.
     */
    RESIZABLE, 
    /**
     * Define se a janela é maximizado. Utilizar Boolean para está definição.
     */
    MAXIMIZED, 
    /**
     * Define um titulo para a janela. Utilizar String para está definição.
     */
    TITLE, 
    /**
     * Define um icone para a janela. Utilizar String para está definição.
     */
    ICON_URL,
    /**
     * Define o arquivo FXML referente a Janela. Utilizar String para está definição.
     */
    FXML_URL;
}
