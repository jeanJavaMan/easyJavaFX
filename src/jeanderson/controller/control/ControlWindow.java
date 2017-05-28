/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.control;

import javafx.stage.Stage;
import jeanderson.controller.componentes.Exibicao;
import jeanderson.controller.componentes.Inicializador;
import jeanderson.controller.componentes.StaticMod;
import jeanderson.controller.util.DialogFX;
import jeanderson.controller.util.DialogType;

/**
 *
 * @author Jeanderson
 * @param <T>
 */
public class ControlWindow<T extends Inicializador> implements Exibicao {
    
    private final ControlBuilder<T> controlBuilder;
    
    public ControlWindow(ControlBuilder<T> controlBuilder, boolean staticMod) {
        this.controlBuilder = controlBuilder;
        this.defineHowStatic(staticMod);
    }

    //métodos estáticos.
    public static ControlBuilder prepareBuilder() {
        return new ControlBuilder<>();
    }
    
    public static ControlBuilder prepareBuilder(Stage primaryStage) {
        return new ControlBuilder<>(primaryStage);
    }
    
    public static <U extends Inicializador> ControlWindow<U> getStaticClass(Class<U> classDefinedStatic) throws Exception {
        return StaticMod.CONTROLADOR.getControlador(classDefinedStatic);
    }

    //métodos de gets
    public ControlBuilder<T> getWindow() {
        return this.controlBuilder;
    }

    //métodos para uso da classe.
    private void defineHowStatic(boolean staticMod) {
        if (staticMod) {
            StaticMod.CONTROLADOR.addControlador(this);
        }
    }
    
    private void fazerExibicao() {
        this.controlBuilder.getStage().show();
        this.controlBuilder.getStage().requestFocus();
    }
    
    private void definirStagePai(Class<? extends Inicializador> windowReference, String methodName) {
        try {
            Stage father = StaticMod.CONTROLADOR.getControlador(windowReference).controlBuilder.getStage();
            this.controlBuilder.getStage().initOwner(father);
        } catch (Exception ex) {
            System.err.println("Houve um exceção no método " + methodName + ", classe passada como parâmetro não encontrada. Exceção: " + ex);
        }
    }
    
    private void exibirMsgSemAcesso() {
        DialogFX.showMessage("Você não tem acesso a está janela.", "Acesso Negado", DialogType.WARNING);
    }
    
    @Override
    public void show() {
        this.controlBuilder.getController().clearFields();
        this.fazerExibicao();
    }
    
    @Override
    public void show(Class<? extends Inicializador> windowReference) {
        this.definirStagePai(windowReference, "show()");
        this.show();
    }
    
    @Override
    public void showEnableFields(boolean enableFields) {
        this.controlBuilder.getController().clearFields();
        this.controlBuilder.getController().enableFields(enableFields);
        this.fazerExibicao();
    }
    
    @Override
    public void showEnableFields(boolean enableFields, Class<? extends Inicializador> windowReference) {
        this.definirStagePai(windowReference, "showEnableFields()");
        this.showEnableFields(enableFields);
    }
    
    @Override
    public void showEditMode(Object data) {
        this.controlBuilder.getController().clearFields();
        this.controlBuilder.getController().editMode(data);
        this.fazerExibicao();
    }
    
    @Override
    public void showEditMode(Object data, Class<? extends Inicializador> windowReference) {
        this.definirStagePai(windowReference, "showEditMode()");
        this.showEditMode(data);
    }
    
    @Override
    public void showEditAndEnable(Object data, boolean enableFields) {
        this.controlBuilder.getController().clearFields();
        this.controlBuilder.getController().enableFields(enableFields);
        this.controlBuilder.getController().editMode(data);
        this.fazerExibicao();
    }
    
    @Override
    public void showEditAndEnable(Object data, boolean enableFields, Class<? extends Inicializador> windowReference) {
        this.definirStagePai(windowReference, "showEditAndEnable()");
        this.showEditAndEnable(data, enableFields);
    }
    
    @Override
    public void showSecurityMode(boolean hasAccess, boolean showMessage) {
        if (hasAccess) {
            this.show();
        } else if (showMessage) {
            this.exibirMsgSemAcesso();
        }
    }
    
    @Override
    public void showSecurityMode(boolean hasAccess, boolean showMessage, Class<? extends Inicializador> windowReference) {
        if (hasAccess) {
            this.show(windowReference);
        } else if (showMessage) {
            this.exibirMsgSemAcesso();
        }
    }
    
    @Override
    public void showSecurityAndEdit(boolean hasAccess, boolean showMessage, Object data) {
        if (hasAccess) {
            this.showEditMode(data);
        } else if (showMessage) {
            this.exibirMsgSemAcesso();
        }
    }
    
    @Override
    public void showSecurityAndEdit(boolean hasAccess, boolean showMessage, Object data, Class<? extends Inicializador> windowReference) {
        if (hasAccess) {
            this.showEditMode(data, windowReference);
        } else if (showMessage) {
            this.exibirMsgSemAcesso();
        }
    }
    
    @Override
    public void showSecurityEditAndEnable(boolean hasAccess, boolean showMessage, boolean enableFields, Object data) {
        if (hasAccess) {
            this.showEditAndEnable(data, enableFields);
        } else if (showMessage) {
            this.exibirMsgSemAcesso();
        }
    }
    
    @Override
    public void showSecurityEditAndEnable(boolean hasAccess, boolean showMessage, boolean enableFields, Object data, Class<? extends Inicializador> windowReference) {
        if (hasAccess) {
            this.showEditAndEnable(data, enableFields, windowReference);
        } else if (showMessage) {
            this.exibirMsgSemAcesso();
        }
    }
    
}
