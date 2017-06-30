/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.util;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author jeanderson
 */
public class AutoCompleteComboBox {

    private final ComboBox comboBox;
    private ObservableList itensDoBox;
    private FilteredList listaParaFiltrar;
    private SortedList listaFiltrada;

    public AutoCompleteComboBox(ComboBox comboBox) {
        this.comboBox = comboBox;
        this.comboBox.setEditable(true);
    }

    public void start() {
        this.comboBox.setOnKeyReleased(this::actionKeyEvent);
        this.itensDoBox = this.comboBox.getItems();
        this.listaParaFiltrar = new FilteredList(itensDoBox);
    }

    private void actionKeyEvent(KeyEvent evento) {
        String textoAnterior = this.comboBox.getEditor().getText();
        KeyCode teclaDigitada = evento.getCode();
        if (teclaDigitada == KeyCode.UP || teclaDigitada == KeyCode.DOWN || teclaDigitada == KeyCode.TAB) {

            evento.consume();
        } else if (teclaDigitada == KeyCode.ENTER) {
            int indexSelecionada = comboBox.getSelectionModel().getSelectedIndex();
            if (indexSelecionada != -1) {
                comboBox.getSelectionModel().select(indexSelecionada);
                moveCaret(textoAnterior.length());
                if (comboBox.isShowing()) {
                    comboBox.hide();
                }
            }
        } else if (comboBox.getEditor().getText().length() == 0) {
            this.comboBox.setItems(null);
            this.comboBox.setItems(itensDoBox);
            if (comboBox.isShowing()) {
                comboBox.hide();
                comboBox.show();
            }
            evento.consume();
        } else {
            this.listaParaFiltrar.setPredicate(item -> verificaItem(item));
            this.listaFiltrada = new SortedList(listaParaFiltrar);
            this.comboBox.setItems(listaFiltrada);
            if (!this.comboBox.isShowing()) {
                this.comboBox.show();
            }
            this.comboBox.getEditor().setText(textoAnterior);
            moveCaret(textoAnterior.length());
        }
    }

    private void moveCaret(int posicao) {
        this.comboBox.getEditor().positionCaret(posicao);
    }

    private boolean verificaItem(Object item) {
        return this.comboBox.getConverter().toString(item).toLowerCase().startsWith(this.comboBox.getEditor().getText().toLowerCase());
    }
}
