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
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jeanderson.controller.annotations.AutoInstance;
import jeanderson.controller.annotations.ClearFields;
import jeanderson.controller.annotations.DefineConfiguration;
import jeanderson.controller.componentes.Inicializador;
import jeanderson.controller.annotations.DoNotClear;
import jeanderson.controller.annotations.EditableWithoutEffect;
import jeanderson.controller.annotations.ValidateField;
import jeanderson.controller.componentes.WindowBuilder;
import jeanderson.controller.enums.DialogType;
import jeanderson.controller.enums.ValidateType;

/**
 *
 * @author Jeanderson
 */
public class FunctionAnnotations {

    /**
     * Faz a limpeza de componentes que possue a anotação FXML ou ClearFields.
     * Obs: funciona somente se a classe tiver a anotação ClearFields.
     *
     * @see ClearFields
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
            ((DatePicker) componente).setValue(null);
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
     * TextField,TextArea,ComboBox,ChoiceBox,CheckBox, TableView e DatePicker.
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
                    if (!validate(componente, validateField)) {
                        verificado = false;
                        break;
                    }
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException ex) {
            System.err.println("Houve um erro ao tentar validar campos. Exceção: " + ex);
            Logger.getLogger(FunctionAnnotations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return verificado;
    }

    private static boolean validate(Object componente, ValidateField validateField) {
        if (componente instanceof TextField) {

            if (((TextField) componente).getText().isEmpty()) {
                exibirMsgCampoNaoPreenchidoCorretamente(validateField);
                ((TextField) componente).requestFocus();
                return false;
            } else {
                if (!validarTextField((TextField) componente, validateField)) {
                    exibirMsgCampoNaoPreenchidoCorretamente(validateField);
                    return false;
                }
            }
        } else if (componente instanceof ComboBox) {
            if (((ComboBox) componente).getSelectionModel().getSelectedIndex() == -1) {
                exibirMsgCampoNaoPreenchidoCorretamente(validateField);
                ((ComboBox) componente).requestFocus();
                return false;
            }
        } else if (componente instanceof DatePicker) {
            if (((DatePicker) componente).getEditor().getText().isEmpty()) {
                exibirMsgCampoNaoPreenchidoCorretamente(validateField);
                ((DatePicker) componente).requestFocus();
                return false;
            }
        } else if (componente instanceof TextArea) {
            if (((TextArea) componente).getText().isEmpty()) {
                exibirMsgCampoNaoPreenchidoCorretamente(validateField);
                ((TextArea) componente).requestFocus();
                return false;
            }
        } else if (componente instanceof ChoiceBox) {
            if (((ChoiceBox) componente).getSelectionModel().isSelected(-1)) {
                exibirMsgCampoNaoPreenchidoCorretamente(validateField);
                ((ChoiceBox) componente).requestFocus();
                return false;
            }
        } else if (componente instanceof CheckBox) {
            if (((CheckBox) componente).isSelected()) {
                exibirMsgCampoNaoPreenchidoCorretamente(validateField);
                ((ComboBox) componente).requestFocus();
                return false;
            }
        } else if (componente instanceof TableView) {
            if (((TableView) componente).getSelectionModel().getSelectedIndex() == -1) {
                exibirMsgCampoNaoPreenchidoCorretamente(validateField);
                ((TableView) componente).requestFocus();
                return false;
            }
        }
        return true;
    }

    private static void exibirMsgCampoNaoPreenchidoCorretamente(ValidateField validateField) {
        DialogFX.showMessage("Por favor preencha o campo " + validateField.nome() + " corretamente, pode está vazio ou não foi preenchido corretamente!", "Campo pode não estar preenchido", DialogType.WARNING);
    }

    /**
     * Informa se um componente é editavél, Obs: o componente deve ter anotação
     *
     * @FXML. é possivel informa que este método não tenha efeito sobre um
     * componente utilizando a anotação @EditableWithoutEffect. Obs: só
     * funcionar com componentes do tipo TextField,ChoiceBox,CheckBox, ComboBox,
     * DatePicker e TextArea, ChoiceBox,CheckBox, ComboBox e DatePicker será
     * usado a função Disable. caso não queria este efeito nestes elementos
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
        } else if (componente instanceof CheckBox) {
            ((CheckBox) componente).setDisable(!editable);
        }
    }

    /**
     * Verificar os dados informados no textefield para ver se está conforme o
     * tipo passado.
     *
     * @param textField
     * @param validateField
     * @return Se os campos estão em conformidade;
     */
    private static boolean validarTextField(TextField textField, ValidateField validateField) {
        if (validateField.type() != ValidateType.NONE) {
            String textoSoNumeros = textField.getText().replaceAll("[^0-9]", "");
            switch (validateField.type()) {
                case TEL_DIG:
                    return textoSoNumeros.length() == 10 || textoSoNumeros.length() == 11;
                case CPF:
                    return textoSoNumeros.length() == 11;
                case DATAS:
                    return textoSoNumeros.length() == 8;
                default:
                    return false;
            }
        } else {
            //já que não informou o tipo,retorna sempre true.
            return true;
        }
    }

    /**
     * Faz o instanciamento do objeto da Classe ControlWindow. o ideial não é
     * utilizar esté método através dessa classe, mas sim atráves da classe
     * EasyJavaFX utilizando o método inicializarComponentes().
     *
     *
     * @param objeto objeto que tenha declarado um ControlWindow com anotação
     * @AutoInstace.
     */
    public static void inicializarControlWindows(Object objeto) {
        Field[] atributos = objeto.getClass().getDeclaredFields();
        try {
            for (Field atributo : atributos) {
                if (atributo.isAnnotationPresent(AutoInstance.class)) {
                    atributo.setAccessible(true);
                    AutoInstance autoInstance = atributo.getAnnotation(AutoInstance.class);
                    if (autoInstance.classController().isAnnotationPresent(DefineConfiguration.class)) {
                        //cria a instancia da classe ControlWindow
                        atributo.set(objeto, WindowBuilder.construct(autoInstance.classController()));
                    } else {
                        System.err.println("Na classe de controller do objeto passado não possui a anotação @DefineConfiguration.! Objeto: " + objeto.getClass().getName());
                    }
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException ex) {
            System.err.println("Erro ao acessar o objeto da Classe ControlWindow com a anotação @AutoInstance. Motivo: " + ex.getMessage());
            Logger.getLogger(FunctionAnnotations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
