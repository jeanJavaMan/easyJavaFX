/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.componentes;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import jeanderson.controller.control.ControlWindow;
import jeanderson.controller.util.ConfigurationEasyJavaFX;

/**
 * Classe que auxilia na construção da classe ControlWindow. Possibilitando
 * várias configurações iniciais.
 *
 * @see ControlWindow
 * @author Jeanderson
 * @param <T>
 */
public class ControlBuilder<T extends Inicializador> {

    private T controller;
    private Stage stage;
    private Parent parentRoot;
    private Scene scene;
    private FXMLLoader fXMLLoader;
    private final Configurator configuracao;
    private Image imgIcon;
    private final String oldUrlIcon;

    /**
     * Construtor já define algumas configurações padrões e Atribui o diretorio
     * do arquivo FXML referente a Janela. Obs: se voce segue o padrão MVC e tem
     * o diretorio apenas como view, só é necessario informar o nome do arquivo,
     * caso contrario é necessario informa a URL.
     *
     * @param urlOrnameFromFXML Nome ou URL do arquivo FXML.
     */
    public ControlBuilder(String urlOrnameFromFXML) {
        String urlFromFXMLVerificada = this.verificaUrlFromFXML(urlOrnameFromFXML);
        this.configuracao = new Configurator() {
            @Override
            public String url_Fxml() {
                return urlFromFXMLVerificada;
            }
        };
        this.oldUrlIcon = configuracao.url_Icon();
    }

    /**
     * Construtor recebe um Configurator com algumas configurações padrões.
     *
     * @param configurator Configuração referente a Janela.
     */
    public ControlBuilder(Configurator configurator) {
        this.configuracao = configurator;
        this.oldUrlIcon = this.configuracao.url_Icon();
    }

    /**
     * Atribui um icone para a Janela.
     *
     * @param urlFromIcon URL do Icone.
     * @return Um ControlBuilder.
     */
    public ControlBuilder addUrlFromIcon(String urlFromIcon) {
        this.configuracao.setUrl_icon(urlFromIcon);
        return this;
    }

    /**
     * Atribui um titulo a Janela.
     *
     * @param titleToStage Titulo do Janela.
     * @return ControlBuilder.
     */
    public ControlBuilder addTitle(String titleToStage) {
        this.configuracao.setTitle(titleToStage);
        return this;
    }

    /**
     * Informa se é para fazer a exibição em modo fullscreen.
     *
     * @param fullscreenMod Exibir em fullscreen.
     * @return ControlBuilder.
     */
    public ControlBuilder fullScreen(boolean fullscreenMod) {
        this.configuracao.setFullscreen(fullscreenMod);
        return this;
    }

    /**
     * Informa se é para fazer a exibição em modo maximizado.
     *
     * @param maximizedMod Exibir maximizado.
     * @return ControlBuilder.
     */
    public ControlBuilder maximized(boolean maximizedMod) {
        this.configuracao.setMaximized(maximizedMod);
        return this;
    }

    /**
     * Informa se a Janela é redimensionavel.
     *
     * @param resizable é redimensionavel;
     * @return ControlBuilder.
     */
    public ControlBuilder resizable(boolean resizable) {
        this.configuracao.setResizable(resizable);
        return this;
    }

    /**
     * Define se toda vez que for exibida uma Janela, um novo stage sejá criado.
     * Obs: se vc desativar o autoNewStage, a sua janela não pode sofrer mudanças como abrir em modo Modality ou atribuir uma nova
     * janela de dependencia.
     * @param auto Ativa ou não.
     * @return ControlBuider.
     */
    public ControlBuilder autoNewStage(boolean auto){
        this.configuracao.setAutoNewStage(auto);
        return this;
    }
    
    /**
     * Define se o ControlWindow que será construido deverá ser mantido como
     * estatico. Obs: fazendo esta definição é possivel chamar em qualquer outra
     * Classe em runtime atráves do método getStaticClass.
     *
     * @return ControlBuilder.
     */
    public ControlBuilder defineHowStaticClass() {
        //quem vai definir como classe estatic é a propria classe controlWindow.
        this.configuracao.setStaticClass(true);
        return this;
    }

    /**
     * Faz a construção do ControlWindow que é responsável pela janela.
     *
     * @return ControlBuider.
     */
    public ControlWindow<T> build() {
        this.prepararConfigScene();
        ControlWindow control = new ControlWindow<>(this);
        this.controller.afterConstruct(control);
        return control;
    }

    /**
     * Atribui um novo Stage.
     * Obs: somente se o AutoNewStage for true.
     */
    public void newStage() {
        if (this.configuracao.isAutoNewStage()) {
            this.stage = new Stage();
            this.stage.setResizable(this.configuracao.isResizable());
            this.stage.setFullScreen(this.configuracao.isFullscreen());
            this.stage.setMaximized(this.configuracao.isMaximized());
            if (!this.oldUrlIcon.equals(this.configuracao.url_Icon())) {
                this.imgIcon = new Image(getClass().getResourceAsStream(this.configuracao.url_Icon()));
            }
            this.stage.getIcons().add(this.imgIcon);
            this.stage.setTitle(this.configuracao.title());
            this.stage.setScene(this.scene);
        }
    }

    //gets e sets
    /**
     * Retorna a Classe de controller referente a janela.
     *
     * @return Classe de Controller da Janela.
     */
    public T getController() {
        return controller;
    }

    /**
     * Retorna o Stage referente a Janela.
     *
     * @return Stage da janela.
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Retorna um Configurator que possui configurações referente a janela.
     *
     * @return Configurator Classe que possui algumas configurações referente a janela.
     */
    public Configurator getConfigurator() {
        return this.configuracao;
    }

    /**
     * Retorna o Parent referente a janela.
     *
     * @return Parent da janela.
     */
    public Parent getParentRoot() {
        return parentRoot;
    }

    /**
     * Retorna a Scene referente a janela.
     *
     * @return Scene da janela.
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Retorna a classe usada para carregar o arquivo FXML.
     *
     * @return FXMLLoader referente a Janela.
     */
    public FXMLLoader getFXMLLoader() {
        return fXMLLoader;
    }
//
//    public void setParentRoot(Parent parentRoot) {
//        this.parentRoot = parentRoot;
//    }
//
//    public void setScene(Scene scene) {
//        this.scene = scene;
//    }

    //métodos para auxilio interno.
    private String verificaUrlFromFXML(String nameOrUrl) {
        if (nameOrUrl == null) {
            return ConfigurationEasyJavaFX.getDEFAULT_FXML();
        } else if (nameOrUrl.contains("/")) {
            return nameOrUrl;
        } else if (nameOrUrl.contains(".")) {
            return "/view/" + nameOrUrl;
        } else {
            return "/view/" + nameOrUrl + ".fxml";
        }
    }

    private void prepararConfigScene() {
        this.fXMLLoader = new FXMLLoader(getClass().getResource(this.configuracao.url_Fxml()));
        try {
            this.parentRoot = this.fXMLLoader.load();
            this.controller = this.fXMLLoader.getController();
        } catch (IOException ex) {
            Logger.getLogger(ControlBuilder.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex);
        }
        this.scene = new Scene(parentRoot);
        this.imgIcon = new Image(getClass().getResourceAsStream(this.configuracao.url_Icon()));
    }
}
