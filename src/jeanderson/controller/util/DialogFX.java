/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.util;

import java.io.IOException;
import jeanderson.controller.enums.DialogType;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jeanderson.controller.DialogConfirmation;
import jeanderson.controller.DialogInput;
import jeanderson.controller.DialogMessage;

/**
 * Classe que exibi uma Dialog do JavaFX.
 *
 * @author Jeanderson
 */
public class DialogFX {

    /**
     * Exibi uma mensagem do tipo INFORMATION(informação)e não para a execução
     * de comandos.
     *
     * @param msg Mensagem a ser exibida.
     */
    public static void showMessage(String msg) {
        createDialogMessage("Informação", msg, DialogType.INFORMATION).show();
    }

    /**
     * Exibi uma mensagem do tipo INFORMATION(informação)e não para a execução
     * de comandos.
     *
     * @param msg Mensagem a ser exibida.
     * @param title Titulo do Dialog.
     */
    public static void showMessage(String msg, String title) {
        createDialogMessage(title, msg, DialogType.INFORMATION).show();
    }

    /**
     * Exibi uma mensagem e não aguarda(não para execução de comandos).
     *
     * @param msg Mensagem a ser exibida.
     * @param title Titulo do Dialog.
     * @param dialogType Tipo do Dialog.
     */
    public static void showMessage(String msg, String title, DialogType dialogType) {
        createDialogMessage(title, msg, dialogType).show();
    }

    /**
     * Exibi uma mensagem do tipo INFORMATION(informação) e aguarda (Para a
     * execução de comandos até que seja fechado).
     *
     * @param msg Mensagem a ser exibida.
     */
    public static void showMessageAndWait(String msg) {
        createDialogMessage("Informação", msg, DialogType.INFORMATION).showAndWait();
    }

    /**
     * Exibi uma mensagem do tipo INFORMATION(informação) e aguarda (Para a
     * execução de comandos até que seja fechado).
     *
     * @param msg Mensagem a ser exibida.
     * @param title Titulo do Dialog.
     */
    public static void showMessageAndWait(String msg, String title) {
       createDialogMessage(title, msg, DialogType.INFORMATION).showAndWait();
    }

    /**
     * Exibi uma mensagem e aguarda(Para execução de comandos).
     *
     * @param msg Mensagem a ser exibida.
     * @param title Titulo do Dialog.
     * @param dialogType Tipo do Dialog.
     */
    public static void showMessageAndWait(String msg, String title, DialogType dialogType) {
        createDialogMessage(title, msg, dialogType).showAndWait();
    }

    /**
     * Exibi uma Tela de confirmação. Retorna true caso seja selecionado sim
     *
     * @param question Mensagem de interrogação.
     * @return Retorna true se escolheu sim, retorna false se escolheu não.
     */
    public static boolean showConfirmation(String question) {
        return createDialogConfirmation("Confirmação", question);
    }

    /**
     * Exibi uma Tela de confirmação. Retorna true caso seja selecionado sim
     *
     * @param question Mensagem de interrogação.
     * @param title Titulo do Dialog.
     * @return Retorna true se escolheu sim, retorna false se escolheu não.
     */
    public static boolean showConfirmation(String question, String title) {
        return createDialogConfirmation(title, question);
    }

    /**
     * Exibi uma Tela com uma caixa de texto. Retorna o texto digitado na caixa.
     * caso seja cancelado retorna vázio.
     *
     * @param title Titulo do Dialog
     * @param header Header do Dialog
     * @param msg Mensagem do Dialog
     * @return String
     */
    public static String showInputText(String title, String header, String msg) {
        try {
            FXMLLoader loader = new FXMLLoader(DialogFX.class.getResource("/jeanderson/view/DialogInput.fxml"));
            Parent root = loader.load();
            DialogInput dialogInput = loader.getController();
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setResizable(false);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(title);
            dialogInput.preparaExibicao(header, msg);
            dialogInput.exibirAnimacao();
            stage.showAndWait();
            return dialogInput.getInput();
        } catch (IOException ex) {
            Logger.getLogger(DialogFX.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

    private static Stage createDialogMessage(String title, String msg, DialogType type) {
        try {
            FXMLLoader loader = new FXMLLoader(DialogFX.class.getResource("/jeanderson/view/DialogMessage.fxml"));
            Parent root = loader.load();            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setResizable(false);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            DialogMessage dialog = (DialogMessage) loader.getController();
            stage.setTitle(title);
            dialog.prepara(stage, msg, type);
            dialog.exibirAnimacao();
            return stage;
        } catch (IOException ex) {
            Logger.getLogger(DialogFX.class.getName()).log(Level.SEVERE, null, ex);
            return new Stage();
        }
    }
    
    private static boolean createDialogConfirmation(String title, String msg){
        try {
            FXMLLoader loader = new FXMLLoader(DialogFX.class.getResource("/jeanderson/view/DialogConfirmation.fxml"));
            Parent root = loader.load();            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setResizable(false);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            DialogConfirmation dialog = loader.getController();
            stage.setTitle(title);
            dialog.prepara(msg);
            dialog.exibirAnimacao();
            stage.showAndWait();
            return dialog.getResultado();
        } catch (IOException ex) {
            Logger.getLogger(DialogFX.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
