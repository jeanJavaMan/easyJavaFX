
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

    /**
     * Contrutor Padrão.
     */
    public ControlStageBuilder() {
        this.configuracao = new ConfigStage();
    }
    
    /**
     * Contrutor padrão que recebe um Stage.
     * @see Stage
     * @param primaryStage 
     */
    public ControlStageBuilder(Stage primaryStage){
        this.configuracao = new ConfigStage();
        this.configuracao.setPalco(primaryStage);
    }
    /**
     * Adiciona o Nome ou a URL do arquivo FXML.
     * Obs: na urlOrName pode ser passado só o nome do arquivo FXML, pois ele vai colocar
     * que o arquivo está em /view/ e sua extensão. Ex:
     * passado Home - montara a seguinte URL /view/Home.fxml
     * Caso preferir mudar a URL é só passar URL.
     * @param urlOrName - Nome ou a URL do arquivo FXML.
     * @return 
     */
    public ControlStageBuilder addNameFromFXML(String urlOrName){
        this.configuracao.setUrlFromFXML(urlOrName);
        return this;
    }
    /**
     * Adiciona a Classe de Controller.
     * @param controller
     * @return 
     */
    public ControlStageBuilder addClassController(T controller){
        this.controller = controller;
        return this;
    }
    /**
     * Adiciona a URL de um Icon para a Tela.
     * @param urlFromIcon
     * @return 
     */
    public ControlStageBuilder addUrlFromIcon(String urlFromIcon){
        this.configuracao.setUrlFromIcon(urlFromIcon);
        return this;
    }
    /**
     * Adiciona um titulo para a Tela.
     * @param title
     * @return 
     */
    public ControlStageBuilder addTitleStage(String title){
        this.configuracao.setTitleStage(title);
        return this;
    }
    /**
     * Informa se deve ativar o ClearCampos.
     * Obs: por padrão o ClearCampos já é ativado.
     * @see Inicializador
     * @param autoClear
     * @return 
     */
    public ControlStageBuilder setAutoEnableClear(boolean autoClear){
        this.configuracao.setAutoClearCampos(autoClear);
        return this;
    }
    /**
     * Informa se deve ativar o EnableCampos.
     * Obs: Por padrão é desativado.
     * @param autoEnablecampos
     * @return 
     */
    public ControlStageBuilder setAutoEnableCampos(boolean autoEnablecampos){
        this.configuracao.setAutoEnableCampos(autoEnablecampos);
        return this;
    }
    /**
     * Informa se deve abrir a Tela em modo Fullscreen.
     * @param fullscreenMod
     * @return 
     */
    public ControlStageBuilder setFullScreen(boolean fullscreenMod){
        this.configuracao.setShowFullScreen(fullscreenMod);
        return this;
    }
    /**
     * Informa se deve abrir a Tela Maximizada.
     * @param maximizedMod
     * @return 
     */
    public ControlStageBuilder setMaximized(boolean maximizedMod){
        this.configuracao.setShowMaximized(maximizedMod);
        return this;
    }
    /**
     * Informa se a Tela é redimensionavel.
     * @param isResizable
     * @return 
     */
    public ControlStageBuilder setResizable(boolean isResizable){
        this.configuracao.setIsResizable(isResizable);
        return this;
    }
    /**
     * Constroi a Classe Pronta.
     * Obs: é necessário passar pelo menos a classe de Controller.
     * @return 
     * @throws java.lang.Exception 
     */
    public ControlStage build() throws Exception{
        if(this.controller == null) throw new Exception("Não foi passado uma Classe de Controller, então não foi possivel criar o Builder! no método builder na classe: " + getClass().getName());
        return new ControlStage(this.controller, this.configuracao);
    }
    
}
