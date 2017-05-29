/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import jeanderson.controller.componentes.Inicializador;
import jeanderson.controller.util.Configuration;

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
    private String urlOrNameFromFXML;
    private String urlFromIcon;
    private String stageTitle;
    private boolean staticMod;
    private final HashMap<Configuration, Boolean> configuracoes;

    public ControlBuilder(Stage stage) {
        this.configuracoes = new HashMap<>();
        this.stage = stage;
        this.urlFromIcon = "/jeanderson/view/img/easyJavaFX.png";
        this.urlOrNameFromFXML = "/jeanderson/view/DefaultView.fxml";
        this.stageTitle = "Janela";
        this.configuracoes.put(Configuration.FULLSCREEN, false);
        this.configuracoes.put(Configuration.RESIZABLE, true);
        this.configuracoes.put(Configuration.MAXIMIZED, false);
    }

    public ControlBuilder() {
        this(new Stage());
    }

    public ControlBuilder addNameOrUrlFXML(String nameOrUrl) {
        this.verificaUrlFromFXML(nameOrUrl);
        return this;
    }

    public ControlBuilder addUrlFromIcon(String urlFromIcon) {
        this.urlFromIcon = urlFromIcon;
        return this;
    }

    public ControlBuilder addTitle(String titleToStage) {
        this.stageTitle = titleToStage;
        return this;
    }

    public ControlBuilder fullScreen(boolean fullscreenMod) {
        this.configuracoes.put(Configuration.FULLSCREEN, fullscreenMod);
        return this;
    }

    public ControlBuilder maximized(boolean maximizedMod) {
        this.configuracoes.put(Configuration.MAXIMIZED, maximizedMod);
        return this;
    }

    public ControlBuilder resizable(boolean resizable) {
        this.configuracoes.put(Configuration.RESIZABLE, resizable);
        return this;
    }

    public ControlBuilder defineHowStaticClass() {
        //quem vai definir como classe estatic é a propria classe controlWindow.
        this.staticMod = true;
        return this;
    }

    public ControlWindow<T> build() {
        this.prepararConfigScene();
        return new ControlWindow<>(this, this.staticMod);
    }

    public void newStage(){
        this.stage = new Stage();
        this.stage.setResizable(configuracoes.get(Configuration.RESIZABLE));
        this.stage.setFullScreen(this.configuracoes.get(Configuration.FULLSCREEN));
        this.stage.setMaximized(this.configuracoes.get(Configuration.MAXIMIZED));
        this.stage.getIcons().add(new Image(getClass().getResourceAsStream(this.urlFromIcon)));
        this.stage.setTitle(stageTitle);
        this.stage.setScene(this.scene);
    }
    
    //gets e sets
    public T getController() {
        return controller;
    }

    public Stage getStage() {
        return stage;
    }

    public Parent getParentRoot() {
        return parentRoot;
    }

    public Scene getScene() {
        return scene;
    }

    public String getUrlFromIcon() {
        return urlFromIcon;
    }

    public String getStageTitle() {
        return stageTitle;
    }

    public FXMLLoader getFXMLLoader() {
        return fXMLLoader;
    }

    public String getUrlFromFXML() {
        return this.urlOrNameFromFXML;
    }

    public void setParentRoot(Parent parentRoot) {
        this.parentRoot = parentRoot;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setUrlFromIcon(String urlFromIcon) {
        this.urlFromIcon = urlFromIcon;
    }

    public void setStageTitle(String stageTitle) {
        this.stageTitle = stageTitle;
    }
    
    public void setResizable(boolean resizable){
        this.configuracoes.put(Configuration.RESIZABLE, resizable);
    }
    
    public void setFullscreen(boolean fullscreen){
        this.configuracoes.put(Configuration.FULLSCREEN, fullscreen);
    }
    
    public void setMaximized(boolean maximized){
        this.configuracoes.put(Configuration.MAXIMIZED, maximized);
    }

    //métodos para auxilio interno.
    private void verificaUrlFromFXML(String nameOrUrl) {
        if (nameOrUrl.contains("/")) {
            this.urlOrNameFromFXML = nameOrUrl;
        } else if (nameOrUrl.contains(".")) {
            this.urlOrNameFromFXML = "/view/" + nameOrUrl;
        } else {
            this.urlOrNameFromFXML = "/view/" + nameOrUrl + ".fxml";
        }
    }

    private void prepararConfigScene() {
        this.fXMLLoader = new FXMLLoader(getClass().getResource(this.urlOrNameFromFXML));
        try {
            this.parentRoot = this.fXMLLoader.load();
            this.controller = this.fXMLLoader.getController();
        } catch (IOException ex) {
            Logger.getLogger(ControlBuilder.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex);
        }
        this.scene = new Scene(parentRoot);
        this.stage.setScene(scene);
        this.stage.setTitle(stageTitle);
        this.stage.getIcons().add(new Image(getClass().getResourceAsStream(this.urlFromIcon)));
    }
}
