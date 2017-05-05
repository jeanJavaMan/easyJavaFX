
package jeanderson.controller.control;

import javafx.stage.Stage;
import jeanderson.controller.componentes.Inicializador;
import jeanderson.controller.control.ControlStage;
import jeanderson.controller.util.ConfigStage;
import jeanderson.controller.util.ConfigStage;

/**
 * Classe complementar de ControlStage. Utilizando o design pattern builder
 * @param <T>
 * @see http://stefanteixeira.com.br/2015/07/29/design-patterns-para-melhorar-seus-testes-parte-1-builder-fluent-interfaces/
 * @author Jeanderson
 */
public class ControlStageBuilder<T extends Inicializador> {
    private final ConfigStage configuracao;
    private T controller;

    public ControlStageBuilder() {
        this.configuracao = new ConfigStage();
    }
    
    public ControlStageBuilder(Stage primaryStage){
        this.configuracao = new ConfigStage();
        this.configuracao.setPalco(primaryStage);
    }
    
    public ControlStageBuilder addNameFromFXML(String urlOrName){
        this.configuracao.setUrlFromFXML(urlOrName);
        return this;
    }
    
    public ControlStageBuilder addClassController(T controller){
        this.controller = controller;
        return this;
    }
    
    public ControlStageBuilder addUrlFromIcon(String urlFromIcon){
        this.configuracao.setUrlFromIcon(urlFromIcon);
        return this;
    }
    
    public ControlStageBuilder setAutoEnableClear(boolean autoClear){
        this.configuracao.setAutoClearCampos(autoClear);
        return this;
    }
    
    public ControlStageBuilder setAutoEnableCampos(boolean autoEnablecampos){
        this.configuracao.setAutoEnableCampos(autoEnablecampos);
        return this;
    }
    
    public ControlStageBuilder setFullScreen(boolean fullscreenMod){
        this.configuracao.setShowFullScreen(fullscreenMod);
        return this;
    }
    
    public ControlStageBuilder setMaximized(boolean maximizedMod){
        this.configuracao.setShowMaximized(maximizedMod);
        return this;
    }
    
    public ControlStageBuilder setResizable(boolean isResizable){
        this.configuracao.setIsResizable(isResizable);
        return this;
    }
    public ControlStage build(){
        return new ControlStage(this.controller, this.configuracao);
    }
    
}
