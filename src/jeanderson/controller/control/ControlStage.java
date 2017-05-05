/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.control;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import jeanderson.controller.componentes.Inicializador;
import jeanderson.controller.util.AuxIntern;
import jeanderson.controller.util.ConfigStage;

/**
 * Classe responsável por todo o trabalho feito para inicializar uma Tela em
 * JavaFX.
 *
 * @author Jeanderson
 * @param <T> - Informa a Classe do Controller feito pelo usuário.
 */
public class ControlStage<T extends Inicializador> extends AuxIntern {

    private ConfigStage configuracao;
    private Stage palco;
    private Parent root;
    private Scene cena;
    private FXMLLoader loader;
    private T controller;

    public ControlStage(T controller, ConfigStage configuracao) {
        super();
        this.configuracao = configuracao;
        this.controller = controller;
    }

    public void show() throws Exception {
        if (!super.isShowStage()) {
            super.setCorrectShowForEnableCampos(false);
            this.prerapaTela();
            this.palco.show();
            this.palco.requestFocus();
            super.setShowStage(true);
        } else {
            this.palco.show();
            this.palco.requestFocus();
        }
    }

    public void show(boolean enableCampos) throws Exception {
        if (!super.isShowStage()) {
            super.setCorrectShowForEnableCampos(true);
            super.setEnableCampos(enableCampos);
            this.prerapaTela();
            this.palco.show();
            this.palco.requestFocus();
            super.setShowStage(true);
        } else {
            this.palco.show();
            this.palco.requestFocus();
        }
    }

    public void showEditMode(Object data) throws Exception {

        if (!super.isShowStage()) {
            super.setCorrectShowForEnableCampos(false);
            this.prerapaTela();
            ((Inicializador)this.controller).editMode(data);
            this.palco.show();
            this.palco.requestFocus();
            super.setShowStage(true);
        } else {
            ((Inicializador)this.controller).editMode(data);
            this.palco.show();
            this.palco.requestFocus();
        }
    }
    
    public void showEditMode(Object data, boolean enablecampos) throws Exception {
        if (!super.isShowStage()) {
            super.setCorrectShowForEnableCampos(false);
            super.setEnableCampos(enablecampos);
            this.prerapaTela();
            ((Inicializador)this.controller).editMode(data);
            this.palco.show();
            this.palco.requestFocus();
            super.setShowStage(true);
        } else {
            ((Inicializador)this.controller).editMode(data);
            this.palco.show();
            this.palco.requestFocus();
        }
    }

    private void configTela() {
        this.palco = this.configuracao.getPalco();
        this.palco.setTitle(this.configuracao.getTitleStage());
        if (!this.configuracao.getUrlFromIcon().isEmpty()) {
            this.palco.getIcons().add(new Image(getClass().getResourceAsStream(this.configuracao.getUrlFromIcon())));
        }
        this.cena = new Scene(this.root);
        this.palco.setScene(this.cena);
    }

    private void prerapaTela() throws IOException, Exception {
        this.loaderFXML();
        this.verificaController();
        this.configTela();
        this.verificaConfiguracoes();
    }

    private void loaderFXML() throws IOException {
        this.loader = new FXMLLoader(getClass().getResource(this.configuracao.getUrlFromFXML()));
        this.root = this.loader.load();
        this.controller = this.loader.getController();
    }

    private void verificaController() throws Exception {
        if (!(this.controller instanceof Inicializador)) {
            String msg = "A Classe: " + this.controller.getClass().getName()
                    + " Não implementa a Classe: " + Inicializador.class.getName()
                    + " Por favor faça a implementação!";
            throw new Exception(msg);
        }
    }

    private void verificaConfiguracoes() {
        if (this.configuracao.isAutoClearCampos() && super.isShowStage()) {
            ((Inicializador) this.controller).clearCampos();
        }
        if (this.configuracao.isAutoEnableCampos()) {
            ((Inicializador) this.controller).enableCampos(super.isEnableCampos());
            if (!super.isCorrectShowForEnableCampos()) {
                System.out.println("Você ativou o EnableCampos, mas está chamando a tela"
                        + " utilizando o método show() que não tem como parâmetro enablecampos, por favor utilize um método"
                        + " show que tenha como parâmetro EnableCampos para informar atráves dos parâmetros se os campos"
                        + " devem ser ativados ou não!");
            }
        }
    }

    public ConfigStage getConfiguracao() {
        return configuracao;
    }

    public void setConfiguracao(ConfigStage configuracao) {
        this.configuracao = configuracao;
    }

    public Stage getStage() {
        return palco;
    }

    public void setStage(Stage palco) {
        this.palco = palco;
    }

    public Parent getParent() {
        return root;
    }

    public void setParent(Parent root) {
        this.root = root;
    }

    public Scene getScene() {
        return cena;
    }

    public void setScene(Scene cena) {
        this.cena = cena;
    }

    public FXMLLoader getFXMLLoader() {
        return loader;
    }

    public void setFXMLLoader(FXMLLoader loader) {
        this.loader = loader;
    }

    public T getController() {
        return controller;
    }

    public void setController(T controller) {
        this.controller = controller;
    }
    

}
