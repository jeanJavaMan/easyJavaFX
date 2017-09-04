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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import jeanderson.animations.BounceInDownTransition;
import jeanderson.controller.enums.DialogType;

/**
 * FXML Controller class
 *
 * @author jeand
 */
public class DialogMessage implements Initializable {

    @FXML
    private ImageView ivImagem;
    @FXML
    private Label txtMensagem;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void prepara(Stage stage, String message, DialogType type) {
        this.stage = stage;
        txtMensagem.setText(message);
        switch (type) {
            case INFORMATION:
                setImageAndIcon("information-icon.png", "information-new.png");
                break;
            case WARNING:
                setImageAndIcon("warning-icon.png", "warning-new.png");
                break;
            case SUCESS:
                setImageAndIcon("sucess.png", "success-new.png");
                break;
            case ERRO:
                setImageAndIcon("error-icon.png", "erro.png");
                break;
        }
    }

    private void setImageAndIcon(String iconName, String imageName) {
        stage.getIcons().add(new Image("/jeanderson/view/img/" + iconName));
        ivImagem.setImage(new Image("/jeanderson/view/img/" + imageName));
    }

    public void exibirAnimacao(){
        new BounceInDownTransition(ivImagem).play();
    }
    
    public ImageView getImagem(){
        return ivImagem;
    }
    
    @FXML
    private void actionOk(ActionEvent event) {
        this.stage.close();
    }

}
