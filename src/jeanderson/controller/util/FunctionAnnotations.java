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
import jeanderson.controller.annotations.EditableWithoutEffect;
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
     * Valida os campos que possuem anotação @ValidadeField e mostra uma
     * mensagem caso o campo não esteja preenchido. Por favor veja quais tipos
     * de componentes são verificados em observação. Observação: Tipos de
     * componentes verificados são:
     * TextField,TextArea,ComboBox,ChoiceBox,CheckBox e DatePicker.
     *
     * @param objeto instancia de uma classe que possui a anotação
     * @validadeField.
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
                    ValidateField validateField = atributo.getAnnotation(ValidateField.class);
                    if (!validate(componente,validateField)) {
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

    private static boolean validate(Object componente, ValidateField validateField) {
        if (componente instanceof TextField) {

            if (((TextField) componente).getText().isEmpty()) {
                exibirMsgCampoNaoPreenchido(validateField);
                ((TextField) componente).requestFocus();
                return false;
            }
        } else if (componente instanceof ComboBox) {
            if (((ComboBox) componente).getSelectionModel().getSelectedIndex() == -1) {
                exibirMsgCampoNaoPreenchido(validateField);
                ((ComboBox) componente).requestFocus();
                return false;
            }
        } else if (componente instanceof DatePicker) {
            if (((DatePicker) componente).getEditor().getText().isEmpty()) {
                exibirMsgCampoNaoPreenchido(validateField);
                ((DatePicker) componente).requestFocus();
                return false;
            }
        } else if (componente instanceof TextArea) {
            if (((TextArea) componente).getText().isEmpty()) {
                exibirMsgCampoNaoPreenchido(validateField);
                ((TextArea) componente).requestFocus();
                return false;
            }
        } else if (componente instanceof ChoiceBox) {
            if (((ChoiceBox) componente).getSelectionModel().isSelected(-1)) {
                exibirMsgCampoNaoPreenchido(validateField);
                ((ChoiceBox) componente).requestFocus();
                return false;
            }
        } else if (componente instanceof CheckBox) {
            if (((CheckBox) componente).isSelected()) {
                exibirMsgCampoNaoPreenchido(validateField);
                ((ComboBox) componente).requestFocus();
                return false;
            }
        }
        return true;
    }

    private static void exibirMsgCampoNaoPreenchido(ValidateField validateField) {
        DialogFX.showMessage("Por favor preencha o(s) campo(s) " +validateField.nome(), "Campo não preenchido", DialogType.WARNING);
    }

    /**
     * Informa se um componente é editavél, Obs: o componente deve ter anotação
     *
     * @FXML. é possivel informa que este método não tenha efeito sobre um
     * componente utilizando a anotação @EditableWithoutEffect. Obs: só
     * funcionar com componentes do tipo TextField,ChoiceBox,CheckBox, ComboBox, DatePicker e TextArea,
     * ChoiceBox,CheckBox, ComboBox e DatePicker será usado a função Disable. caso não queria este efeito nestes elementos
     * utilize a anotação @EditableWithoutEffect.
     * @param objeto instancia de uma classe.
     * @param editable é editavél.
     * @see EditableWithoutEffect
     */
    public static void editableFields(Inicializador objeto, boolean editable) {
        Field[] atributos = objeto.getClass().getDeclaredFields();
        try {
            for (Field atributo : atributos) {
                if (atributo.isAnnotationPresent(FXML.class) && !atributo.isAnnotationPresent(EditableWithoutEffect.class)) {
                    atributo.setAccessible(true);
                    Object componente = atributo.get(objeto);
                    editableComponente(componente, editable);
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException ex) {
            System.err.println("Erro ao informa que um componente é editavel. Motivo: " + ex.getMessage());
            Logger.getLogger(FunctionAnnotations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void editableComponente(Object componente, boolean editable) {
        if (componente instanceof TextField) {
            ((TextField) componente).setEditable(editable);
        } else if (componente instanceof ComboBox) {
            ((ComboBox) componente).setDisable(!editable);
        } else if (componente instanceof DatePicker) {
            ((DatePicker) componente).setDisable(!editable);
        } else if (componente instanceof TextArea) {
            ((TextArea) componente).setEditable(editable);
        } else if (componente instanceof ChoiceBox) {
            ((ChoiceBox) componente).setDisable(!editable);
        } else if(componente instanceof CheckBox){
            ((CheckBox) componente).setDisable(!editable);
        }
    }
}
