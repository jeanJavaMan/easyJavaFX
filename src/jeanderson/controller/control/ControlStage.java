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
import jeanderson.controller.util.ConfigStage;

/**
 * Classe responsável por todo o trabalho feito para inicializar uma Tela em
 * JavaFX.
 *
 * @author Jeanderson
 * @param <T> - Informa a Classe do Controller feito pelo usuário.
 */
public class ControlStage<T> {

    private ConfigStage configuracao;
    private Stage palco;
    private Parent root;
    private Scene cena;
    private FXMLLoader loader;
    private T controller;
    private boolean abriu_tela = false;

    public ControlStage(T controller, ConfigStage configuracao) {
        this.configuracao = configuracao;
        this.controller = controller;
    }

    public void show() throws Exception {
        if (!abriu_tela) {
            mostrarTela(ConfigStage.NO_AUTO_ENABLE, false);
        } else {
            this.palco.show();
            this.palco.requestFocus();
        }
    }
    
    public void show(boolean enableCampos) throws Exception {
        if (!abriu_tela) {
            mostrarTela(enableCampos, true);
        } else {
            this.palco.show();
            this.palco.requestFocus();
        }
    }

    private void mostrarTela(boolean enableCampos,boolean isCorrectShow) throws IOException, Exception {
        this.loader = FXMLLoader.load(getClass().getResource(this.configuracao.getUrlFromFXML()));
        this.root = this.loader.load();
        this.cena = new Scene(this.root);
        this.controller = this.loader.getController();
        /*Métodos importantes para a execução*/
        this.verificaController();
        this.verificaConfiguracoes(enableCampos, isCorrectShow);
        
        this.palco = this.configuracao.getPalco();
        this.palco.setTitle(this.configuracao.getTitleStage());
        if (this.configuracao.getUrlFromIcon() != null || !this.configuracao.getUrlFromIcon().isEmpty()) {
            this.palco.getIcons().add(new Image(getClass().getResourceAsStream(this.configuracao.getUrlFromIcon())));
        }
        this.palco.setScene(this.cena);
        this.palco.show();
        this.palco.requestFocus();
        this.abriu_tela = true;
    }

    private void verificaController() throws Exception {
        if (!(this.controller instanceof Inicializador)) {
            String msg = "A Classe: " + this.controller.getClass().getName()
                    + " Não implementa a Classe easyJavaFX.jeanderson.controller.componentes.Inicializador"
                    + " Por favor faça a implementação!";
            throw new Exception(msg);
        }
    }
    
    private void verificaConfiguracoes(boolean enableCampos, boolean isCorrectShow){
        if(this.configuracao.isAutoClearCampos()){
           ((Inicializador) this.controller).clearCampos();
        }
        if(this.configuracao.isAutoEnableCampos()){
            ((Inicializador) this.controller).enableCampos(enableCampos);
            if(!isCorrectShow){
                System.out.println("Você ativou o EnableCampos, mas está chamando a tela"
                        + " utilizando o método show(), por favor utilize o método"
                        + " show(boolean enableCampos) para informar atráves dos parâmetros devem"
                        + " ser ativados ou não!");
            }
        }
    }
}
