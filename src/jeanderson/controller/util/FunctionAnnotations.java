/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.util;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jeanderson.controller.annotations.ClearFields;
import jeanderson.controller.componentes.Inicializador;
import jeanderson.controller.annotations.ClearSelect;
import jeanderson.controller.annotations.DoNotClear;

/**
 *
 * @author Jeanderson
 */
public class FunctionAnnotations {

    /**
     * Faz a limpeza de componentes que possue a anotação FXML ou ClearSelect.
     * Obs: funciona somente se a classe tiver a anotação ClearFields.
     *
     * @see ClearFields
     * @see ClearSelect
     * @param objeto Classe de controller.
     */
    public static void clearFieldsWithAnnotations(Inicializador objeto) {
        if (objeto.getClass().isAnnotationPresent(ClearFields.class)) {
            Field[] atributos = objeto.getClass().getDeclaredFields();
            FunctionAnnotations.clear(atributos, objeto);
        }
        
    }

    /**
     * Faz a limpeza do campo de acordo com o seu tipo.
     *
     * @param componente Field.
     */
    private static void clearField(Object componente) {
        if (componente instanceof TextField) {
            ((TextField) componente).setText("");
        } else if (componente instanceof ComboBox) {
            ((ComboBox) componente).getSelectionModel().clearSelection();
        } else if (componente instanceof DatePicker) {
            ((DatePicker) componente).getEditor().setText("");
        } else if (componente instanceof TextArea) {
            ((TextArea) componente).setText("");
        } else if (componente instanceof ChoiceBox) {
            ((ChoiceBox) componente).getSelectionModel().clearSelection();
        } else if (componente instanceof CheckBox) {
            ((CheckBox) componente).setSelected(false);
        }
    }
    
    private static void clear(Field[] atributos, Inicializador objeto) {
        try {
            for (Field atributo : atributos) {
                if (atributo.isAnnotationPresent(FXML.class)) {
                    if (!atributo.isAnnotationPresent(DoNotClear.class)) {
                        atributo.setAccessible(true);
                        Object componente = atributo.get(objeto);
                        FunctionAnnotations.clearField(componente);
                    }
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException ex) {
            System.err.println("Houve um erro ao tentar limpar o componente. Exceção: " + ex);
            Logger.getLogger(FunctionAnnotations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
