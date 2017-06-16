/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.componentes;

import jeanderson.controller.control.ControlWindow;
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

    public Configurator() {
        this.url_icon = ConfigurationEasyJavaFX.getDEFAULT_URL_ICON();
        this.title = ConfigurationEasyJavaFX.getDEFAULT_TITLE();
        this.isFullscreen = ConfigurationEasyJavaFX.isDEFAULT_FULLSCREEN();
        this.isMaximized = ConfigurationEasyJavaFX.isDEFAULT_MAXIMIZED();
        this.isResizable = ConfigurationEasyJavaFX.isDEFAULT_RESIZABLE();        
    }

    public void setUrl_icon(String url_icon) {
        this.url_icon = url_icon;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsResizable(boolean isResizable) {
        this.isResizable = isResizable;
    }

    public void setIsFullscreen(boolean isFullscreen) {
        this.isFullscreen = isFullscreen;
    }

    public void setIsMaximized(boolean isMaximized) {
        this.isMaximized = isMaximized;
    }

    //métodos da interface

    @Override
    public String title() {
        return this.title;
    }

    @Override
    public String url_Icon() {
        return this.url_icon;
    }

    @Override
    public boolean isResizable() {
       return this.isResizable;
    }

    @Override
    public boolean isFullscreen() {
        return this.isFullscreen;
    }

    @Override
    public boolean isMaximized() {
        return this.isMaximized;
    }
}
