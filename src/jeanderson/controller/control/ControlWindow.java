/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.control;

import jeanderson.controller.componentes.ControlBuilder;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jeanderson.controller.componentes.Configurator;
import jeanderson.controller.interfaces.Exibicao;
import jeanderson.controller.interfaces.Inicializador;
import jeanderson.controller.enums.StaticMod;
import jeanderson.controller.util.DialogFX;
import jeanderson.controller.enums.DialogType;
import jeanderson.controller.util.FunctionAnnotations;

/**
 * Classe para controle da Janela feita em JavaFX.
 *
 * @author Jeanderson
 * @param <T>
 */
public class ControlWindow<T extends Inicializador> implements Exibicao {

    private final ControlBuilder<T> controlBuilder;
    //informa se o Stage possui um Stage pai, ele só é necessário quando o autoNewStage está desativado.
    private boolean atribuiuDependenciaStage;

    /**
     * Construtor que recebe um controlBuilder já pronto, e também informa se a
     * classe deve ser mantida de maneira estática.
     *
     * @param controlBuilder Classe com todas as configurações importante da
     * Janela.
     */
    public ControlWindow(ControlBuilder<T> controlBuilder) {
        this.controlBuilder = controlBuilder;
        this.defineHowStatic(this.controlBuilder.getConfigurator().isStaticClass());
    }

    //métodos estáticos.
    /**
     * Retorna uma instancia da classe ControlBuilder que será usada para
     * auxiliar na construção da classe ControlWindow.
     *
     * @param urlOrNameFromFXML Nome ou URL do arquivo FXML.
     * @return Classe ControlBuilder com configurações padrões.
     */
    public static ControlBuilder prepareBuilder(String urlOrNameFromFXML) {
        return new ControlBuilder<>(urlOrNameFromFXML);
    }

    /**
     * Retorna uma instancia da classe ControlBuilder que será usada para
     * auxiliar na construção da classe ControlWindow.
     *
     * @param configurator Configuração referente a Janela.
     * @return Classe ControlBuilder com configurações padrões.
     */
    public static ControlBuilder prepareBuilder(Configurator configurator) {
        return new ControlBuilder(configurator);
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
            if (this.controlBuilder.getConfigurator().isAutoNewStage()) {
                Stage father = StaticMod.CONTROLADOR.getControlador(windowReference).controlBuilder.getStage();
                this.controlBuilder.getStage().initOwner(father);

            } else if (!this.atribuiuDependenciaStage) {
                Stage father = StaticMod.CONTROLADOR.getControlador(windowReference).controlBuilder.getStage();
                this.controlBuilder.getStage().initOwner(father);
                this.atribuiuDependenciaStage = true;
            } else {
                System.out.println("Não foi atribuido uma janela pai, pois o provavel motivo é que o autoNewStage foi desativado ou já foi atribuido um Janela pai!");
            }
        } catch (Exception ex) {
            System.err.println("Houve um exceção no método " + methodName + " Exceção: " + ex);
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
        FunctionAnnotations.clearFieldsWithAnnotations(this.controlBuilder.getController());
        this.controlBuilder.getController().enableFields(enableFields);
    }

    private void preparaShowEditMode(Object data) {
        this.controlBuilder.getController().clearFields();
        FunctionAnnotations.clearFieldsWithAnnotations(this.controlBuilder.getController());
        this.controlBuilder.getController().editMode(data);
    }

    private void preparaShowEditAndEnable(Object data, boolean enableFields) {
        this.controlBuilder.getController().clearFields();
        FunctionAnnotations.clearFieldsWithAnnotations(this.controlBuilder.getController());
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
        FunctionAnnotations.clearFieldsWithAnnotations(this.controlBuilder.getController());
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
        FunctionAnnotations.clearFieldsWithAnnotations(this.controlBuilder.getController());
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

    /**
     * Faz a exibição da Janela. Implementa duas funções em uma: EditMode e
     * EnableFields, ou seja ele chama o método enableFields e logo após é
     * chamado o método EditMode. Obs: Sua classe de controle tem q
     * sobreescrever este dois método, caso contrário nada acontecerá.
     *
     * @param data Dados que será passado para a classe de Controller que
     * sobreescreveu o método EditMode.
     * @param enableFields Informa se ativar os campos ou não.
     */
    @Override
    public void showEditAndEnable(Object data, boolean enableFields) {
        this.controlBuilder.newStage();
        this.preparaShowEditAndEnable(data, enableFields);
        this.fazerExibicao();
    }

    /**
     * Faz a exibição da Janela com dependencia de outra janela. Implementa duas
     * funções em uma: EditMode e EnableFields, ou seja ele chama o método
     * enableFields e logo após é chamado o método EditMode. Obs: Sua classe de
     * controle tem q sobreescrever este dois método, caso contrário nada
     * acontecerá.
     *
     * @param data Dados que será passado para a classe de Controller que
     * sobreescreveu o método EditMode.
     * @param enableFields Informa se ativar os campos ou não.
     * @param windowReference Classe de Controller referente a Janela que possui
     * o Stage.
     */
    @Override
    public void showEditAndEnable(Object data, boolean enableFields, Class<? extends Inicializador> windowReference) {
        this.definirStagePai(windowReference, "showEditAndEnable()");
        this.preparaShowEditAndEnable(data, enableFields);
        this.fazerExibicao();
    }

    /**
     * Faz a exibição da janela, mas com a condição informada.
     *
     * @param hasAccess Informa se tem acesso a Janela.
     * @param showMessage Informa se Exibe uma mensagem falando que não tem
     * acesso.
     */
    @Override
    public void showSecurityMode(boolean hasAccess, boolean showMessage) {
        if (hasAccess) {
            this.show();
        } else if (showMessage) {
            this.exibirMsgSemAcesso();
        }
    }

    /**
     * Faz a exibição da janela tendo como dependencia outra Janela, mas com a
     * condição informada.
     *
     * @param hasAccess Informa se tem acesso a Janela.
     * @param showMessage Informa se Exibe uma mensagem falando que não tem
     * acesso.
     * @param windowReference Informa a classe de controller que é referente a
     * Janela que terá como dependencia.
     */
    @Override
    public void showSecurityMode(boolean hasAccess, boolean showMessage, Class<? extends Inicializador> windowReference) {
        if (hasAccess) {
            this.show(windowReference);
        } else if (showMessage) {
            this.exibirMsgSemAcesso();
        }
    }

    /**
     * Faz a exibição da Janela e chama o método EditMode na classe de Controle
     * da Janela, mas só é exibido com a condição informada. Obs: Sua classe de
     * Controller deve sobreescrever o método EditMode.
     *
     * @param hasAccess Informa se tem acesso a Janela.
     * @param showMessage Informa se Exibe uma mensagem falando que não tem
     * acesso.
     * @param data Dados que serão passados para a classe de Controller
     * referente a Janela.
     */
    @Override
    public void showSecurityAndEdit(boolean hasAccess, boolean showMessage, Object data) {
        if (hasAccess) {
            this.showEditMode(data);
        } else if (showMessage) {
            this.exibirMsgSemAcesso();
        }
    }

    /**
     * Faz a exibição da Janela com dependencia de outra Janela e chama o método
     * EditMode na classe de Controle da Janela, mas só é exibido com a condição
     * informada. Obs: Sua classe de Controller deve sobreescrever o método
     * EditMode.
     *
     * @param hasAccess Informa se tem acesso a Janela.
     * @param showMessage Informa se Exibe uma mensagem falando que não tem
     * acesso.
     * @param data Dados que serão passados para a classe de Controller atráves
     * do método editMode.
     * @param windowReference Informa a classe de controller que é referente a
     * Janela que terá como dependencia.
     */
    @Override
    public void showSecurityAndEdit(boolean hasAccess, boolean showMessage, Object data, Class<? extends Inicializador> windowReference) {
        if (hasAccess) {
            this.showEditMode(data, windowReference);
        } else if (showMessage) {
            this.exibirMsgSemAcesso();
        }
    }

    /**
     * Exibi a Janela e chama dois métodos na classe de controller, EditMode e
     * EnableFields.
     *
     * @param hasAccess Informa se tem acesso a Janela.
     * @param showMessage Informa se Exibe uma mensagem falando que não tem
     * acesso.
     * @param enableFields É passada para a classe de controller atráves do
     * método enableFields
     * @param data Dados que serão passados para a classe de Controller atráves
     * do método editMode.
     */
    @Override
    public void showSecurityEditAndEnable(boolean hasAccess, boolean showMessage, boolean enableFields, Object data) {
        if (hasAccess) {
            this.showEditAndEnable(data, enableFields);
        } else if (showMessage) {
            this.exibirMsgSemAcesso();
        }
    }

    /**
     * Exibi a Janela em modo segurança com dependencia de outra Janela. e chama
     * dois métodos na classe de controller, EditMode e EnableFields.
     *
     * @param hasAccess Informa se tem acesso a Janela.
     * @param showMessage Informa se Exibe uma mensagem falando que não tem
     * acesso.
     * @param enableFields É passada para a classe de controller atráves do
     * método enableFields
     * @param data Dados que serão passados para a classe de Controller atráves
     * do método editMode.
     * @param windowReference Informa a classe de controller que é referente a
     * Janela que terá como dependencia.
     */
    @Override
    public void showSecurityEditAndEnable(boolean hasAccess, boolean showMessage, boolean enableFields, Object data, Class<? extends Inicializador> windowReference) {
        if (hasAccess) {
            this.showEditAndEnable(data, enableFields, windowReference);
        } else if (showMessage) {
            this.exibirMsgSemAcesso();
        }
    }

    /**
     * Exibi a Janela com requisição de atenção. Não é possivel utilizar outra
     * janela enquanto não fechar.
     */
    @Override
    public void showModality() {
        this.controlBuilder.newStage();
        this.controlBuilder.getStage().initModality(Modality.APPLICATION_MODAL);
        this.controlBuilder.getController().clearFields();
        FunctionAnnotations.clearFieldsWithAnnotations(this.controlBuilder.getController());
        this.fazerExibicao();
    }

    /**
     * Exibi a Janela com dependencia e com requisição de atenção. Não é
     * possivel utilizar outra janela enquanto não fechar.
     *
     * @param windowReference Informa a classe de controller que é referente a
     * Janela que terá como dependencia.
     */
    @Override
    public void showModality(Class<? extends Inicializador> windowReference) {
        this.definirStagePai(windowReference, "showModality(class reference)");
        this.controlBuilder.getStage().initModality(Modality.APPLICATION_MODAL);
        this.controlBuilder.getController().clearFields();
        FunctionAnnotations.clearFieldsWithAnnotations(this.controlBuilder.getController());
        this.fazerExibicao();
    }

    /**
     * Primeiro é chamado o método EnableFields e logo após é feito a exibição
     * da janela com requisição de atenção. Obs: este método é utilizado para
     * caso seja necessário ativar e desativar campos da tela, então é
     * necessario que sua classe de controller sobreescreva o método
     * enableFields.
     *
     * @param enableFields Informa se ativar os campos ou não.
     */
    @Override
    public void showEnableFieldsModality(boolean enableFields) {
        this.controlBuilder.newStage();
        this.controlBuilder.getStage().initModality(Modality.APPLICATION_MODAL);
        this.preparaShowEnableFields(enableFields);
        this.fazerExibicao();
    }

    /**
     * Primeiro é chamado o método EnableFields e logo após é feito a exibição
     * da janela com requisição de atenção e com dependencia de outra Janela.
     * Obs: este método é utilizado para caso seja necessário ativar e desativar
     * campos da tela, então é necessario que sua classe de controller
     * sobreescreva o método enableFields.
     *
     * @param enableFields Informa se ativar os campos ou não.
     * @param windowReference Informa a classe de controller que é referente a
     * Janela que terá como dependencia.
     */
    @Override
    public void showEnableFieldsModality(boolean enableFields, Class<? extends Inicializador> windowReference) {
        this.definirStagePai(windowReference, "showEnableFieldsModality(boolean enableFields, Class<? extends Inicializador> windowReference)");
        this.controlBuilder.getStage().initModality(Modality.APPLICATION_MODAL);
        this.preparaShowEnableFields(enableFields);
        this.fazerExibicao();
    }

    /**
     * Primeiro é chamado o método clearFields e logo após é chamado o metodo
     * editMode e só então é feita a exibição da janela com requisição de
     * atenção. Obs: esté método é utilizado quando é necessário a classe de
     * controller da janela receber dados externos antes de sua exibição, então
     * para que sua classe receba os dados passados como parâmetro neste método
     * é necessario que sua Classe de Controller sobreescreva o método EditMode.
     *
     * @param data Dados que será passado para a classe de Controller que
     * sobreescreveu o método EditMode.
     */
    @Override
    public void showEditModeModality(Object data) {
        this.controlBuilder.newStage();
        this.controlBuilder.getStage().initModality(Modality.APPLICATION_MODAL);
        this.preparaShowEditMode(data);
        this.fazerExibicao();
    }

    /**
     * Primeiro é chamado o método clearFields e logo após é chamado o metodo
     * editMode e só então é feita a exibição da janela com requisição de
     * atenção e com dependencia de outra Janela. Obs: esté método é utilizado
     * quando é necessário a classe de controller da janela receber dados
     * externos antes de sua exibição, então para que sua classe receba os dados
     * passados como parâmetro neste método é necessario que sua Classe de
     * Controller sobreescreva o método EditMode.
     *
     * @param data Dados que será passado para a classe de Controller que
     * sobreescreveu o método EditMode.
     * @param windowReference Informa a classe de controller que é referente a
     * Janela que terá como dependencia.
     */
    @Override
    public void showEditModeModality(Object data, Class<? extends Inicializador> windowReference) {
        this.definirStagePai(windowReference, "showEditModeModality(Object data, Class<? extends Inicializador> windowReference)");
        this.controlBuilder.getStage().initModality(Modality.APPLICATION_MODAL);
        this.preparaShowEditMode(data);
        this.fazerExibicao();
    }

    /**
     * Faz a exibição da Janela com requisição de atenção. Implementa duas
     * funções em uma: EditMode e EnableFields, ou seja ele chama o método
     * enableFields e logo após é chamado o método EditMode. Obs: Sua classe de
     * controle tem q sobreescrever este dois método, caso contrário nada
     * acontecerá.
     *
     * @param data Dados que será passado para a classe de Controller que
     * sobreescreveu o método EditMode.
     * @param enableFields Informa se ativar os campos ou não.
     */
    @Override
    public void showEditAndEnableModality(Object data, boolean enableFields) {
        this.controlBuilder.newStage();
        this.controlBuilder.getStage().initModality(Modality.APPLICATION_MODAL);
        this.preparaShowEditAndEnable(data, enableFields);
        this.fazerExibicao();
    }

    /**
     * Faz a exibição da Janela com requisição de atenção e com dependencia de
     * outra janela. Implementa duas funções em uma: EditMode e EnableFields, ou
     * seja ele chama o método enableFields e logo após é chamado o método
     * EditMode. Obs: Sua classe de controle tem q sobreescrever este dois
     * método, caso contrário nada acontecerá.
     *
     * @param data Dados que será passado para a classe de Controller que
     * sobreescreveu o método EditMode.
     * @param enableFields Informa se ativar os campos ou não.
     * @param windowReference Informa a classe de controller que é referente a
     * Janela que terá como dependencia.
     */
    @Override
    public void showEditAndEnableModality(Object data, boolean enableFields, Class<? extends Inicializador> windowReference) {
        this.definirStagePai(windowReference, "showEditAndEnableModality(Object data, boolean enableFields, Class<? extends Inicializador> windowReference)");
        this.controlBuilder.getStage().initModality(Modality.APPLICATION_MODAL);
        this.preparaShowEditAndEnable(data, enableFields);
        this.fazerExibicao();
    }

    /**
     * Exibi a Janela em modo segurança e com requisição de atenção. Não é
     * possivel utilizar outra janela enquanto não fechar.
     *
     * @param hasAccess Informa se tem acesso a Janela.
     * @param showMessage Informa se Exibe uma mensagem falando que não tem
     * acesso.
     */
    @Override
    public void showSecurityModeModality(boolean hasAccess, boolean showMessage) {
        if (hasAccess) {
            this.showModality();
        } else if (showMessage) {
            this.exibirMsgSemAcesso();
        }
    }

    /**
     * Exibi a Janela em modo segurança e com requisição de atenção. Não é
     * possivel utilizar outra janela enquanto não fechar.
     *
     * @param hasAccess Informa se tem acesso a Janela.
     * @param showMessage Informa se Exibe uma mensagem falando que não tem
     * acesso.
     * @param windowReference Informa a classe de controller que é referente a
     * Janela que terá como dependencia.
     */
    @Override
    public void showSecurityModeModality(boolean hasAccess, boolean showMessage, Class<? extends Inicializador> windowReference) {
        if (hasAccess) {
            this.showModality(windowReference);
        } else if (showMessage) {
            this.exibirMsgSemAcesso();
        }
    }

    /**
     * Primeiro é chamado o método clearFields e logo após é chamado o metodo
     * editMode e só então é feita a exibição da janela com requisição de
     * atenção e em modo segurança. Obs: esté método é utilizado quando é
     * necessário a classe de controller da janela receber dados externos antes
     * de sua exibição, então para que sua classe receba os dados passados como
     * parâmetro neste método é necessario que sua Classe de Controller
     * sobreescreva o método EditMode.
     *
     * @param data Dados que será passado para a classe de Controller que
     * sobreescreveu o método EditMode.
     * @param hasAccess Informa se tem acesso a Janela.
     * @param showMessage Informa se Exibe uma mensagem falando que não tem
     * acesso.
     *
     */
    @Override
    public void showSecurityAndEditModality(boolean hasAccess, boolean showMessage, Object data) {
        if (hasAccess) {
            this.showEditModeModality(data);
        } else if (showMessage) {
            this.exibirMsgSemAcesso();
        }
    }

    /**
     * Primeiro é chamado o método clearFields e logo após é chamado o metodo
     * editMode e só então é feita a exibição da janela com requisição de
     * atenção e em modo segurança. Obs: esté método é utilizado quando é
     * necessário a classe de controller da janela receber dados externos antes
     * de sua exibição, então para que sua classe receba os dados passados como
     * parâmetro neste método é necessario que sua Classe de Controller
     * sobreescreva o método EditMode.
     *
     * @param data Dados que será passado para a classe de Controller que
     * sobreescreveu o método EditMode.
     * @param hasAccess Informa se tem acesso a Janela.
     * @param showMessage Informa se Exibe uma mensagem falando que não tem
     * acesso.
     * @param windowReference Informa a classe de controller que é referente a
     * Janela que terá como dependencia.
     */
    @Override
    public void showSecurityAndEditModality(boolean hasAccess, boolean showMessage, Object data, Class<? extends Inicializador> windowReference) {
        if (hasAccess) {
            this.showEditModeModality(data, windowReference);
        } else if (showMessage) {
            this.exibirMsgSemAcesso();
        }
    }

    /**
     * Faz a exibição da Janela com requisição de atenção e no modo segurança.
     * Implementa duas funções em uma: EditMode e EnableFields, ou seja ele
     * chama o método enableFields e logo após é chamado o método EditMode. Obs:
     * Sua classe de controle tem q sobreescrever este dois método, caso
     * contrário nada acontecerá.
     *
     * @param hasAccess Informa se tem acesso a Janela.
     * @param showMessage Informa se Exibe uma mensagem falando que não tem
     * acesso.
     * @param data Dados que será passado para a classe de Controller que
     * sobreescreveu o método EditMode.
     * @param enableFields Informa se ativar os campos ou não.
     */
    @Override
    public void showSecurityEditAndEnableModality(boolean hasAccess, boolean showMessage, boolean enableFields, Object data) {
        if (hasAccess) {
            this.showEditAndEnableModality(data, enableFields);
        } else if (showMessage) {
            this.exibirMsgSemAcesso();
        }
    }

    /**
     * Faz a exibição da Janela com requisição de atenção e no modo segurança
     * com dependencia de outra janela. Implementa duas funções em uma: EditMode
     * e EnableFields, ou seja ele chama o método enableFields e logo após é
     * chamado o método EditMode. Obs: Sua classe de controle tem q
     * sobreescrever este dois método, caso contrário nada acontecerá.
     *
     * @param hasAccess Informa se tem acesso a Janela.
     * @param showMessage Informa se Exibe uma mensagem falando que não tem
     * acesso.
     * @param data Dados que será passado para a classe de Controller que
     * sobreescreveu o método EditMode.
     * @param enableFields Informa se ativar os campos ou não.
     * @param windowReference Informa a classe de controller que é referente a
     * Janela que terá como dependencia.
     */
    @Override
    public void showSecurityEditAndEnableModality(boolean hasAccess, boolean showMessage, boolean enableFields, Object data, Class<? extends Inicializador> windowReference) {
        if (hasAccess) {
            this.showEditAndEnableModality(data, enableFields, windowReference);
        } else if (showMessage) {
            this.exibirMsgSemAcesso();
        }
    }

}
