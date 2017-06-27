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
import jeanderson.controller.annotations.ValidateField;
import jeanderson.controller.enums.DialogType;

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

    /**
     * Valida os campos que possuem anotação @ValidadeField e mostra uma mensagem caso o campo não esteja preenchido. Por favor veja quais tipos de componentes são verificados em observação.
     * Observação: Tipos de componentes verificados são: TextField,TextArea,ComboBox,ChoiceBox,CheckBox e DatePicker.
     * @param objeto instancia de uma classe que possui a anotação @validadeField.
     * @return True se todos os componentes estão preenchidos.
     */
    public static boolean validationFields(Inicializador objeto) {
        Field[] atributos = objeto.getClass().getDeclaredFields();
        boolean verificado = true;
        try {            
            for (Field atributo : atributos) {
                if (atributo.isAnnotationPresent(ValidateField.class)) {
                    atributo.setAccessible(true);
                    Object componente = atributo.get(objeto);
                    if(!validate(componente)){
                        verificado = false;
                        break;
                    }
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException ex) {
            System.err.println("Houve um erro ao tentar limpar o componente. Exceção: " + ex);
            Logger.getLogger(FunctionAnnotations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return verificado;
    }
    
    private static boolean validate(Object componente){
        if (componente instanceof TextField) {
           
           if(((TextField) componente).getText().isEmpty()){
               exibirMsgCampoNaoPreenchido();
               ((TextField) componente).requestFocus();
               return false;
           }
        } else if (componente instanceof ComboBox) {
            if(((ComboBox) componente).getSelectionModel().isSelected(-1)){
               exibirMsgCampoNaoPreenchido();
               ((ComboBox) componente).requestFocus();
               return false;
           }
        } else if (componente instanceof DatePicker) {
            if(((DatePicker) componente).getEditor().getText().isEmpty()){
               exibirMsgCampoNaoPreenchido();
               ((DatePicker) componente).requestFocus();
               return false;
           }
        } else if (componente instanceof TextArea) {
            if(((TextArea) componente).getText().isEmpty()){
               exibirMsgCampoNaoPreenchido();
               ((TextArea) componente).requestFocus();
               return false;
           }
        } else if (componente instanceof ChoiceBox) {
            if(((ChoiceBox) componente).getSelectionModel().isSelected(-1)){
               exibirMsgCampoNaoPreenchido();
               ((ChoiceBox) componente).requestFocus();
               return false;
           }
        } else if (componente instanceof CheckBox) {
            if(((CheckBox) componente).isSelected()){
               exibirMsgCampoNaoPreenchido();
               ((ComboBox) componente).requestFocus();
               return false;
           }
        }
        return true;
    }
    
    private static void exibirMsgCampoNaoPreenchido(){
        DialogFX.showMessage("Por favor preencha a campo que está em focus para continuar.", "Campo não preenchido", DialogType.WARNING);
    }
}