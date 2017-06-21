/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.util;

import java.lang.annotation.Annotation;
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
import jeanderson.controller.annotations.ClearField;
import jeanderson.controller.annotations.ClearFields;
import jeanderson.controller.enums.Clear;
import jeanderson.controller.componentes.Inicializador;

/**
 *
 * @author Jeanderson
 */
public class FunctionAnnotations {

    /**
     * Faz a limpeza de componentes que possue a anotação FXML ou ClearField.
     * Obs: funciona somente se a classe tiver a anotação ClearFields.
     *
     * @see ClearFields
     * @see ClearField
     * @param objeto Classe de controller.
     */
    public static void clearFieldsWithAnnotations(Inicializador objeto) {
        Annotation anotacaoDaClasse = objeto.getClass().getAnnotation(ClearFields.class);
        if (anotacaoDaClasse instanceof ClearFields) {
            ClearFields classeComAnotacao = (ClearFields) anotacaoDaClasse;
            Field[] atributos = objeto.getClass().getDeclaredFields();
            switch (classeComAnotacao.limpar()) {
                case CLEAR_ALL:
                    FunctionAnnotations.clearAll(atributos, objeto);
                    break;
                case CHOOSE_FIELD:
                    FunctionAnnotations.clearFields(atributos, objeto);
                    break;
                default:
            }
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

    /**
     * Limpa todos os componentes que anotação FXML.
     *
     * @param atributos Atributos da Classe de controller.
     * @param objeto Classe de controller.
     */
    private static void clearAll(Field[] atributos, Inicializador objeto) {
        try {
            for (Field atributo : atributos) {
                if (atributo.isAnnotationPresent(FXML.class)) {
                    atributo.setAccessible(true);
                    Object componente = atributo.get(objeto);
                    FunctionAnnotations.clearField(componente);
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            System.err.println("Houve um erro ao tentar limpar o componente. Exceção: " + ex);
            Logger.getLogger(FunctionAnnotations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Limpa somente os componentes com a Anotação ClearField e FXML.
     *
     * @param atributos Atributos da Classe de controller.
     * @param objeto Classe de controller.
     */
    private static void clearFields(Field[] atributos, Inicializador objeto) {
        try {
            for (Field atributo : atributos) {
                if (atributo.isAnnotationPresent(FXML.class) && atributo.isAnnotationPresent(ClearField.class)) {
                    ClearField anotacaoDoAtributo = atributo.getAnnotation(ClearField.class);
                    if (anotacaoDoAtributo.limpar() == Clear.YES) {
                        atributo.setAccessible(true);
                        Object componente = atributo.get(objeto);
                        FunctionAnnotations.clearField(componente);
                    }
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            System.err.println("Houve um erro ao tentar limpar o componente. Exceção: " + ex);
            Logger.getLogger(FunctionAnnotations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
