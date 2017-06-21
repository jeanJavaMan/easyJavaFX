/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.componentes;

import jeanderson.controller.interfaces.WindowConfiguration;
import jeanderson.controller.util.ConfigurationEasyJavaFX;

/**
 *
 * @author Jeanderson
 */
public abstract class Configurator implements WindowConfiguration{
    
    private String url_icon;
    private String title;
    private boolean isResizable;
    private boolean isFullscreen;
    private boolean isMaximized;
    private boolean isAutoNewStage;
    private boolean staticClass;

    /**
     * Construtor que já define algumas configurações padrões.
     */
    public Configurator() {
        this.url_icon = ConfigurationEasyJavaFX.getDEFAULT_URL_ICON();
        this.title = ConfigurationEasyJavaFX.getDEFAULT_TITLE();
        this.isFullscreen = ConfigurationEasyJavaFX.isDEFAULT_FULLSCREEN();
        this.isMaximized = ConfigurationEasyJavaFX.isDEFAULT_MAXIMIZED();
        this.isResizable = ConfigurationEasyJavaFX.isDEFAULT_RESIZABLE();       
        this.isAutoNewStage = ConfigurationEasyJavaFX.isDEFAULT_AUTO_NEW_STAGE();
        this.staticClass = ConfigurationEasyJavaFX.isDEFAULT_STATIC_CLASS();
    }

    /**
     * Altera URL do Icone referente a Janela.
     *
     * @param url_icon  URL do Icone.
     */
    public void setUrl_icon(String url_icon) {
        this.url_icon = url_icon;
    }

    /**
     * Altera o Titulo da Janela.
     * @param title 
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Informa se a Janela é redimensionavel.
     *
     * @param isResizable é redimensionavel;
     */
    public void setResizable(boolean isResizable) {
        this.isResizable = isResizable;
    }

    /**
     * Informa se é para fazer a exibição em modo fullscreen.
     * 
     * @param isFullscreen Exibir em fullscreen.
     */
    public void setFullscreen(boolean isFullscreen) {
        this.isFullscreen = isFullscreen;
    }

    /**
     * Informa se é para fazer a exibição em modo maximizado.
     *
     * @param isMaximized Exibir maximizado.
     */
    public void setMaximized(boolean isMaximized) {
        this.isMaximized = isMaximized;
    }

    public void setAutoNewStage(boolean isAutoNewStage) {
        this.isAutoNewStage = isAutoNewStage;
    }

    /**
     * Define se a Classe deve ser estática ou não.
     * @param staticMod é estática.
     */
    public void setStaticClass(boolean staticMod) {
        this.staticClass = staticMod;
    }
    
    
    //get da classe

    /**
     * Informa se o auto new stage está ativado.
     * @return Boolean informando se está ativo.
     */
    public boolean isAutoNewStage() {
        return isAutoNewStage;
    }

    /**
     * Informa se foi definido a classe ControlWindow como estática.
     * @return 
     */
    public boolean isStaticClass() {
        return staticClass;
    }
    
    //métodos da interface

    /**
     * Retorna o titulo da janela.
     *
     * @return Titulo da janela.
     */
    @Override
    public String title() {
        return this.title;
    }
 
    /**
     * Retorna a URL do icone referente a janela.
     * @return URL do icone.
     */
    @Override
    public String url_Icon() {
        return this.url_icon;
    }

    /**
     * Informa se a Janela é redimensionavel.
     * @return Boolean.
     */
    @Override
    public boolean isResizable() {
       return this.isResizable;
    }

    /**
     * Informa se é para fazer a exibição em modo fullscreen.
     * @return Boolean.
     */
    @Override
    public boolean isFullscreen() {
        return this.isFullscreen;
    }

    /**
     * Informa se é para fazer a exibição em modo maximizado.
     * @return Boolean.
     */
    @Override
    public boolean isMaximized() {
        return this.isMaximized;
    }
}
