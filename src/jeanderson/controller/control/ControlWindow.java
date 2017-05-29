/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.control;

import javafx.stage.Modality;
import javafx.stage.Stage;
import jeanderson.controller.componentes.Exibicao;
import jeanderson.controller.componentes.Inicializador;
import jeanderson.controller.componentes.StaticMod;
import jeanderson.controller.util.DialogFX;
import jeanderson.controller.util.DialogType;

/**
 * Classe para controle da Janela feita em JavaFX.
 *
 * @author Jeanderson
 * @param <T>
 */
public class ControlWindow<T extends Inicializador> implements Exibicao {
    
    private final ControlBuilder<T> controlBuilder;
    private boolean atribuiuInitOwner;

    /**
     * Construtor que recebe um controlBuilder já pronto, e também informa se a
     * classe deve ser mantida de maneira estática.
     *
     * @param controlBuilder Classe com todas as configurações importante da
     * Janela.
     * @param staticMod Informa se a classe deve ser mantida estática ou não.
     */
    public ControlWindow(ControlBuilder<T> controlBuilder, boolean staticMod) {
        this.controlBuilder = controlBuilder;
        this.defineHowStatic(staticMod);
    }

    //métodos estáticos.
    /**
     * Retorna uma instancia da classe ControlBuilder que será usada para
     * auxiliar na construção da classe ControlWindow.
     *
     * @return Classe ControlBuilder com configurações padrões.
     */
    public static ControlBuilder prepareBuilder() {
        return new ControlBuilder<>();
    }

    /**
     * Retorna uma instancia da classe ControlBuilder que será usada para
     * auxiliar na construção da classe ControlWindow.
     *
     * @param primaryStage Será usado como o Stage da Janela.
     * @return Classe ControlBuilder com configurações padrões.
     */
    public static ControlBuilder prepareBuilder(Stage primaryStage) {
        return new ControlBuilder<>(primaryStage);
    }

    /**
     * Retorna um ControlWindow que foi definido como estático anteriormente
     * atráves do método defineHowStaticClass. Deve ser passado como parâmetro a
     * Classe de Controller que foi usado como parâmetro do ControlWindow.
     *
     * @param <U> Retorna o ControlWindow que tenha como tipo o que foi passado
     * como parâmetro neste método.
     * @param classDefinedStatic Classe de Controller usado no ControlWindow.
     * @return Um ControlWindow que já foi declarado anteriormente e mantido
     * como estático.
     * @throws Exception Caso a classe de Controller passado no parâmetro não
     * esteja registrada em nenhuma Classe ControlWindow mantida como estática,
     * será retornado uma exceção.
     */
    public static <U extends Inicializador> ControlWindow<U> getStaticClass(Class<U> classDefinedStatic) throws Exception {
        return StaticMod.CONTROLADOR.getControlador(classDefinedStatic);
    }

    //métodos de gets
    /**
     * Retorna a Classe ControlBuilder que foi usada para a construção desta
     * classe.
     *
     * @return Retorna uma Classe ControlBuilder que possui todos os objetos
     * referentes a janela.
     */
    public ControlBuilder<T> getWindow() {
        return this.controlBuilder;
    }

    //métodos para uso da classe.
    /**
     * Método que define está classe como estática.
     *
     * @param staticMod Se deve ou não definir está classe como estática.
     */
    private void defineHowStatic(boolean staticMod) {
        if (staticMod) {
            StaticMod.CONTROLADOR.addControlador(this);
        }
    }

    /**
     * Faz a exibição do stage.
     */
    private void fazerExibicao() {
        this.controlBuilder.getStage().show();
        this.controlBuilder.getStage().requestFocus();
    }

    /**
     * Define um Stage Pai para o Stage desta classe, ou seja a janela vai
     * depender de outro stage.
     *
     * @param windowReference Classe de controller do ControlWindow que foi
     * definido como estático atráves do método defineHowStaticClass.
     * @param methodName Nome do método que está chamado este método. Apenas
     * para informa o usuário.
     */
    private void definirStagePai(Class<? extends Inicializador> windowReference, String methodName) {
        this.controlBuilder.newStage();
        try {
            Stage father = StaticMod.CONTROLADOR.getControlador(windowReference).controlBuilder.getStage();
            this.controlBuilder.getStage().initOwner(father);
            this.atribuiuInitOwner = true;
        } catch (Exception ex) {
            System.err.println("Houve um exceção no método " + methodName + ", classe passada como parâmetro não encontrada. Exceção: " + ex);
        }
    }

    /**
     * Usada para exibir uma DialoFx com uma mensagem informando que o usuário
     * não tem acesso a janela.
     */
    private void exibirMsgSemAcesso() {
        DialogFX.showMessage("Você não tem acesso a está janela.", "Acesso Negado", DialogType.WARNING);
    }
    
    private void preparaShowEnableFields(boolean enableFields) {
        this.controlBuilder.getController().clearFields();
        this.controlBuilder.getController().enableFields(enableFields);
    }
    
    private void preparaShowEditMode(Object data) {
        this.controlBuilder.getController().clearFields();
        this.controlBuilder.getController().editMode(data);
    }
    
    private void preparaShowEditAndEnable(Object data, boolean enableFields) {
        this.controlBuilder.getController().clearFields();
        this.controlBuilder.getController().enableFields(enableFields);
        this.controlBuilder.getController().editMode(data);
    }

    /**
     * Primeiro é chamado o método clearFields e logo após é feito a exibição da
     * janela. Obs: se sua Janela precisa limpar os campos toda vez que abrir,
     * sobreescreva na sua classe de controller o método clearFields, pois ele é
     * chamado toda vez que a janela for aberta.
     */
    @Override
    public void show() {
        this.controlBuilder.newStage();
        this.controlBuilder.getController().clearFields();
        this.fazerExibicao();
    }

    /**
     * Faz a exibição tendo um Stage pai de uma Classe de Controller que é
     * passada como parâmetro, ou seja ele se torna dependente do Stage pai.
     * Obs: se sua Janela precisa limpar os campos toda vez que abrir,
     * sobreescreva na sua classe de controller o método clearFields, pois ele é
     * chamado toda vez que a janela for aberta.
     *
     * @param windowReference Classe de Controller referente a Janela que possui
     * o Stage.
     */
    @Override
    public void show(Class<? extends Inicializador> windowReference) {
        this.definirStagePai(windowReference, "show()");
        this.controlBuilder.getController().clearFields();
        this.fazerExibicao();
    }

    /**
     * Primeiro é chamado o método EnableFields e logo após é feito a exibição
     * da janela. Obs: este método é utilizado para caso seja necessário ativar
     * e desativar campos da tela, então é necessario que sua classe de
     * controller sobreescreva o método enableFields.
     *
     * @param enableFields Informa se ativar os campos ou não.
     */
    @Override
    public void showEnableFields(boolean enableFields) {
        this.controlBuilder.newStage();
        this.preparaShowEnableFields(enableFields);
        this.fazerExibicao();
    }

    /**
     * Primeiro é chamado o método EnableFields e logo após é feito a exibição
     * da janela sendo o stage dessa janela dependente do Stage de outra Janela
     * que é informada no parâmetro deste método. Obs: este método é utilizado
     * para caso seja necessário ativar e desativar campos da tela, então é
     * necessario que sua classe de controller sobreescreva o método
     * enableFields.
     *
     * @param enableFields Informa se ativar os campos ou não.
     * @param windowReference Classe de Controller referente a Janela que possui
     * o Stage.
     */
    @Override
    public void showEnableFields(boolean enableFields, Class<? extends Inicializador> windowReference) {
        this.definirStagePai(windowReference, "showEnableFields()");
        this.preparaShowEnableFields(enableFields);
        this.fazerExibicao();
    }

    /**
     * Primeiro é chamado o método clearFields e logo após é chamado o metodo
     * editMode e só então é feita a exibição da janela. Obs: esté método é
     * utilizado quando é necessário a classe de controller da janela receber
     * dados externos antes de sua exibição, então para que sua classe receba os
     * dados passados como parâmetro neste método é necessario que sua Classe de
     * Controller sobreescreva o método EditMode.
     *
     * @param data Dados que será passado para a classe de Controller que
     * sobreescreveu o método EditMode.
     */
    @Override
    public void showEditMode(Object data) {
        this.controlBuilder.newStage();
        this.preparaShowEditMode(data);
        this.fazerExibicao();
    }

    /**
     * Primeiro é chamado o método clearFields e logo após é chamado o metodo
     * editMode e só então é feita a exibição da janela sendo o stage dessa
     * janela dependente do Stage de outra Janela que é informada no parâmetro
     * deste método. Obs: esté método é utilizado quando é necessário a classe
     * de controller da janela receber dados externos antes de sua exibição,
     * então para que sua classe receba os dados passados como parâmetro neste
     * método é necessario que sua Classe de Controller sobreescreva o método
     * EditMode.
     *
     * @param data Dados que será passado para a classe de Controller que
     * sobreescreveu o método EditMode.
     * @param windowReference Classe de Controller referente a Janela que possui
     * o Stage.
     */
    @Override
    public void showEditMode(Object data, Class<? extends Inicializador> windowReference) {
        this.definirStagePai(windowReference, "showEditMode()");
        this.preparaShowEditMode(data);
        this.fazerExibicao();
    }
    
    @Override
    public void showEditAndEnable(Object data, boolean enableFields) {
        this.controlBuilder.newStage();
        this.preparaShowEditAndEnable(data, enableFields);
        this.fazerExibicao();
    }
    
    @Override
    public void showEditAndEnable(Object data, boolean enableFields, Class<? extends Inicializador> windowReference) {
        this.definirStagePai(windowReference, "showEditAndEnable()");
        this.preparaShowEditAndEnable(data, enableFields);
        this.fazerExibicao();
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
    
    @Override
    public void showModality() {
        this.controlBuilder.newStage();
        this.controlBuilder.getStage().initModality(Modality.APPLICATION_MODAL);
        this.fazerExibicao();
    }
    
    @Override
    public void showModality(Class<? extends Inicializador> windowReference) {
        this.definirStagePai(windowReference, "showModality(class reference)");
        this.controlBuilder.getStage().initModality(Modality.APPLICATION_MODAL);
        this.fazerExibicao();
    }
    
    @Override
    public void showEnableFieldsModality(boolean enableFields) {
        this.controlBuilder.newStage();
        this.controlBuilder.getStage().initModality(Modality.APPLICATION_MODAL);
        this.preparaShowEnableFields(enableFields);
        this.fazerExibicao();
    }
    
    @Override
    public void showEnableFieldsModality(boolean enableFields, Class<? extends Inicializador> windowReference) {
        this.definirStagePai(windowReference, "showEnableFieldsModality(boolean enableFields, Class<? extends Inicializador> windowReference)");
        this.controlBuilder.getStage().initModality(Modality.APPLICATION_MODAL);
        this.preparaShowEnableFields(enableFields);
        this.fazerExibicao();
    }
    
    @Override
    public void showEditModeModality(Object data) {
        this.controlBuilder.newStage();
        this.controlBuilder.getStage().initModality(Modality.APPLICATION_MODAL);
        this.preparaShowEditMode(data);
        this.fazerExibicao();
    }
    
    @Override
    public void showEditModeModality(Object data, Class<? extends Inicializador> windowReference) {
        this.definirStagePai(windowReference, "showEditModeModality(Object data, Class<? extends Inicializador> windowReference)");
        this.controlBuilder.getStage().initModality(Modality.APPLICATION_MODAL);
        this.preparaShowEditMode(data);
        this.fazerExibicao();
    }
    
    @Override
    public void showEditAndEnableModality(Object data, boolean enableFields) {
        this.controlBuilder.newStage();
        this.controlBuilder.getStage().initModality(Modality.APPLICATION_MODAL);
        this.preparaShowEditAndEnable(data, enableFields);
        this.fazerExibicao();
    }
    
    @Override
    public void showEditAndEnableModality(Object data, boolean enableFields, Class<? extends Inicializador> windowReference) {
        this.definirStagePai(windowReference, "showEditAndEnableModality(Object data, boolean enableFields, Class<? extends Inicializador> windowReference)");
        this.controlBuilder.getStage().initModality(Modality.APPLICATION_MODAL);
        this.preparaShowEditAndEnable(data, enableFields);
        this.fazerExibicao();
    }
    
    @Override
    public void showSecurityModeModality(boolean hasAccess, boolean showMessage) {
        if (hasAccess) {
            this.showModality();
        } else if (showMessage) {
            this.exibirMsgSemAcesso();
        }
    }
    
    @Override
    public void showSecurityModeModality(boolean hasAccess, boolean showMessage, Class<? extends Inicializador> windowReference) {
        if (hasAccess) {
            this.showModality(windowReference);
        } else if (showMessage) {
            this.exibirMsgSemAcesso();
        }
    }
    
    @Override
    public void showSecurityAndEditModality(boolean hasAccess, boolean showMessage, Object data) {
        if (hasAccess) {
            this.showEditModeModality(data);
        } else if (showMessage) {
            this.exibirMsgSemAcesso();
        }
    }
    
    @Override
    public void showSecurityAndEditModality(boolean hasAccess, boolean showMessage, Object data, Class<? extends Inicializador> windowReference) {
        if (hasAccess) {
            this.showEditModeModality(data, windowReference);
        } else if (showMessage) {
            this.exibirMsgSemAcesso();
        }
    }
    
    @Override
    public void showSecurityEditAndEnableModality(boolean hasAccess, boolean showMessage, boolean enableFields, Object data) {
        if (hasAccess) {
            this.showEditAndEnableModality(data, enableFields);
        } else if (showMessage) {
            this.exibirMsgSemAcesso();
        }
    }
    
    @Override
    public void showSecurityEditAndEnableModality(boolean hasAccess, boolean showMessage, boolean enableFields, Object data, Class<? extends Inicializador> windowReference) {
        if (hasAccess) {
            this.showEditAndEnableModality(data, enableFields, windowReference);
        } else if (showMessage) {
            this.exibirMsgSemAcesso();
        }
    }
    
}
