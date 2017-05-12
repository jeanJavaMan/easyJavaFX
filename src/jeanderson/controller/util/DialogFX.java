/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.util;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Classe que exibi uma Dialog do JavaFX.
 *
 * @author Jeanderson
 */
public class DialogFX {

    /**
     * Método que criar um Dialog do javaFX de acordo com o tipo informado.
     *
     * @param msg Mensagem do Dialog.
     * @param title Titulo do Dialog.
     * @param header Mensagem de Header do Dialog.
     * @param dialogType Tipo de Dialog
     * @return Retorna uma Alert da Classe do JavaFX.
     */
    private static Alert createDialog(String msg, String title, String header, DialogType dialogType) {
        Alert dialog;
        Stage stage;
        switch (dialogType) {
            case INFORMATION:
                dialog = new Alert(Alert.AlertType.INFORMATION);
                stage = (Stage) dialog.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(DialogFX.class.getResourceAsStream("/jeanderson/view/img/information-icon.png")));
                break;
            case WARNING:
                dialog = new Alert(Alert.AlertType.WARNING);
                stage = (Stage) dialog.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(DialogFX.class.getResourceAsStream("/jeanderson/view/img/warning-icon.png")));
                break;
            case ERRO:
                dialog = new Alert(Alert.AlertType.ERROR);
                stage = (Stage) dialog.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(DialogFX.class.getResourceAsStream("/jeanderson/view/img/error-icon.png")));
                break;
            case SUCESS:
                dialog = new Alert(Alert.AlertType.NONE);
                /*Alteramos o icone(Imagem) que aparece junto com o Dialog*/
                Image icon = new Image(DialogFX.class.getResourceAsStream("/jeanderson/view/img/sucess.png"));
                dialog.setGraphic(new ImageView(icon));
                /*Alteramos o icone da Janela*/
                stage = (Stage) dialog.getDialogPane().getScene().getWindow();
                stage.getIcons().add(icon);

                dialog.getButtonTypes().add(new ButtonType("OK", ButtonBar.ButtonData.CANCEL_CLOSE));
                break;
            case CONFIRMATION:
                dialog = new Alert(Alert.AlertType.CONFIRMATION);
                stage = (Stage) dialog.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(DialogFX.class.getResourceAsStream("/jeanderson/view/img/confirmation-icon.png")));
                break;
            default:
                dialog = new Alert(Alert.AlertType.NONE);
                dialog.initStyle(StageStyle.UTILITY);
                break;
        }
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(msg);
        return dialog;
    }
    
      /**
     * Exibi uma mensagem do tipo INFORMATION(informação)e não para a execução
     * de comandos.
     *
     * @param msg Mensagem a ser exibida.
     */
    public static void showMessage(String msg) {
        createDialog(msg, "Informação", "", DialogType.INFORMATION).show();
    }

    /**
     * Exibi uma mensagem do tipo INFORMATION(informação)e não para a execução
     * de comandos.
     *
     * @param msg Mensagem a ser exibida.
     * @param title Titulo do Dialog.
     */
    public static void showMessage(String msg, String title) {
        createDialog(msg, title, "", DialogType.INFORMATION).show();
    }

    /**
     * Exibi uma mensagem e não aguarda(não para execução de comandos).
     *
     * @param msg Mensagem a ser exibida.
     * @param title Titulo do Dialog.
     * @param dialogType Tipo do Dialog.
     */
    public static void showMessage(String msg, String title, DialogType dialogType) {
        createDialog(msg, title, "", dialogType).show();
    }

    /**
     * Exibi uma mensagem do tipo INFORMATION(informação) e aguarda (Para a
     * execução de comandos até que seja fechado).
     *
     * @param msg Mensagem a ser exibida.
     */
    public static void showMessageAndWait(String msg) {
        createDialog(msg, "Informação", "", DialogType.INFORMATION).showAndWait();
    }

    /**
     * Exibi uma mensagem do tipo INFORMATION(informação) e aguarda (Para a
     * execução de comandos até que seja fechado).
     *
     * @param msg Mensagem a ser exibida.
     * @param title Titulo do Dialog.
     */
    public static void showMessageAndWait(String msg, String title) {
        createDialog(msg, title, "", DialogType.INFORMATION).showAndWait();
    }

    /**
     * Exibi uma mensagem e aguarda(Para execução de comandos).
     *
     * @param msg Mensagem a ser exibida.
     * @param title Titulo do Dialog.
     * @param dialogType Tipo do Dialog.
     */
    public static void showMessageAndWait(String msg, String title, DialogType dialogType) {
        createDialog(msg, title, "", dialogType).showAndWait();
    }

    /**
     * Exibi uma Tela de confirmação. Retorna true caso seja selecionado sim
     *
     * @param question Mensagem de interrogação.
     * @return Retorna true se escolheu sim, retorna false se escolheu não.
     */
    public static boolean showConfirmation(String question) {
        Alert dialog = createDialog(question, "Mensagem", "", DialogType.CONFIRMATION);
        /*Criamos os botões personalizados*/
        ButtonType btnSim = new ButtonType("Sim", ButtonBar.ButtonData.YES);
        ButtonType btnNao = new ButtonType("Não", ButtonBar.ButtonData.NO);
       // ButtonType btnCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        /*Substituimos todos os botões do Dialog pelo o nosso*/
        dialog.getButtonTypes().setAll(btnSim, btnNao);
        Optional<ButtonType> resultado = dialog.showAndWait();
        return resultado.get() == btnSim;
    }

    /**
     * Exibi uma Tela de confirmação. Retorna true caso seja selecionado sim
     *
     * @param question Mensagem de interrogação.
     * @param title Titulo do Dialog.
     * @return Retorna true se escolheu sim, retorna false se escolheu não.
     */
    public static boolean showConfirmation(String question, String title) {
        Alert dialog = createDialog(question, title, "", DialogType.CONFIRMATION);
        ButtonType btnSim = new ButtonType("Sim", ButtonBar.ButtonData.YES);
        ButtonType btnNao = new ButtonType("Não", ButtonBar.ButtonData.NO);
        //ButtonType btnCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getButtonTypes().setAll(btnSim, btnNao);
        Optional<ButtonType> resultado = dialog.showAndWait();
        return resultado.get() == btnSim;
    }
}
