/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.enums;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import jeanderson.controller.componentes.DialogAssembly;

/**
 * Enum responsável por diferenciar os tipos de DialogFX. Obs: a partir da
 * versão 2.0, o enum agora faz a implementação de uma Alert(Dialog).
 *
 * @version 2.0
 * @author Jeanderson
 */
public enum DialogType implements DialogAssembly {
    /**
     * Informa que o tipo do DialogFX é do tipo de sucesso(Operação que ocorre
     * com sucesso).
     */
    SUCESS {
        @Override
        public Alert createDialog(String msg, String title, String header) {
            Alert dialog = new Alert(Alert.AlertType.NONE);
            /*Alteramos o icone(Imagem) que aparece junto com o Dialog*/
            Image icon = new Image(getClass().getResourceAsStream("/jeanderson/view/img/sucess.png"));
            dialog.setGraphic(new ImageView(icon));
            /*Alteramos o icone da Janela*/
            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
            stage.getIcons().add(icon);
            dialog.getButtonTypes().add(new ButtonType("OK", ButtonBar.ButtonData.CANCEL_CLOSE));
            dialog.setTitle(title);
            dialog.setHeaderText(header);
            dialog.setContentText(msg);
            return dialog;
        }

    },
    /**
     * Informa que o tipo do DialogFX é do tipo de informação(Apresentação de
     * uma informação).
     */
    INFORMATION {
        @Override
        public Alert createDialog(String msg, String title, String header) {
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/jeanderson/view/img/information-icon.png")));
            dialog.setTitle(title);
            dialog.setHeaderText(header);
            dialog.setContentText(msg);
            return dialog;
        }

    },
    /**
     * Informa que o tipo do DialogFX é do tipo de confirmação(Exigi uma
     * resposta de confirmação).
     */
    CONFIRMATION {
        @Override
        public Alert createDialog(String msg, String title, String header) {
            Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/jeanderson/view/img/confirmation-icon.png")));
            /*Criamos os botões personalizados*/
            ButtonType btnSim = new ButtonType("Sim", ButtonBar.ButtonData.YES);
            ButtonType btnNao = new ButtonType("Não", ButtonBar.ButtonData.NO);
            /*Substituimos todos os botões do Dialog pelo o nosso*/
            dialog.getButtonTypes().setAll(btnSim, btnNao);
            dialog.setTitle(title);
            dialog.setHeaderText(header);
            dialog.setContentText(msg);
            return dialog;
        }

    },
    /**
     * Informa que o tipo do DialogFX é do tipo de Atenção(Informando que algo
     * requer uma atenção).
     */
    WARNING {
        @Override
        public Alert createDialog(String msg, String title, String header) {
            Alert dialog = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/jeanderson/view/img/warning-icon.png")));
            dialog.setTitle(title);
            dialog.setHeaderText(header);
            dialog.setContentText(msg);
            return dialog;
        }

    },
    /**
     * Informa que o tipo do DialoFX é do tipo de Erro(Informa que algum erro
     * ocorreu).
     */
    ERRO {
        @Override
        public Alert createDialog(String msg, String title, String header) {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/jeanderson/view/img/error-icon.png")));
            dialog.setTitle(title);
            dialog.setHeaderText(header);
            dialog.setContentText(msg);
            return dialog;
        }

    };
}
