/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.util;

import jeanderson.controller.enums.DialogType;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

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
        return dialogType.createDialog(msg, title, header);
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
        Optional<ButtonType> resultado = dialog.showAndWait();
        return resultado.get().getButtonData().equals(ButtonBar.ButtonData.YES);
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
        Optional<ButtonType> resultado = dialog.showAndWait();
        return resultado.get().getButtonData().equals(ButtonBar.ButtonData.YES);
    }

    /**
     * Exibi uma Tela com uma caixa de texto. Retorna o texto digitado na caixa. caso seja cancelado retorna vázio.
     * @param title Titulo do Dialog
     * @param header Header do Dialog
     * @param msg Mensagem do Dialog
     * @return String
     */
    public static String showInputText(String title, String header, String msg) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(msg);
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            return result.get();
        } else {
            return "";
        }
    }
}
