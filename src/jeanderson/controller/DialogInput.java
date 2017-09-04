/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jeanderson.animations.BounceInDownTransition;
import jeanderson.animations.FadeInDownBigTransition;
import jeanderson.animations.FadeInRightBigTransition;
import jeanderson.animations.FadeInUpBigTransition;

/**
 * FXML Controller class
 *
 * @author jeand
 */
public class DialogInput implements Initializable {

    @FXML
    private TextField txtEntrada;
    private String entrada;
    @FXML
    private Label txtHeader;
    @FXML
    private Label txtMensagem;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        entrada = "";
    }    

    public void preparaExibicao(String header, String msg){
        this.txtHeader.setText(header);
        txtMensagem.setText(msg);
    }
    
    public void exibirAnimacao(){
        new BounceInDownTransition(txtHeader).play();
        new FadeInRightBigTransition(txtMensagem).play();
        new FadeInDownBigTransition(txtEntrada).play();
    }
    
    @FXML
    private void actionOk(ActionEvent event) {
        this.entrada = txtEntrada.getText();
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    private void actionCancelar(ActionEvent event) {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }
    
    public String getInput(){
        return this.entrada;
    }
    
}
