/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.componentes;

import java.util.HashMap;
import jeanderson.controller.control.ControlStage;

/**
 * Classe que mantém salvo Classes ControlStage em modo estático.
 *
 * @version 2.0 Utilizando HashMap
 * @author Jeanderson
 */
public enum AllSee {
    CONTROLADORES();
    /**
     * Lista que vai conter as classes.
     */
    private final HashMap<String, ControlStage> controladores;

    /**
     * Construtor Padrão.
     */
    AllSee() {
        this.controladores = new HashMap<>();
    }

    /**
     * Adiciona uma Classe a Lista colocando no index informado.
     *
     * @param classController Informe qual a Classe de controller que está sendo
     * passada. Ex: TelaHomeController.class
     * @param controlador Classe do ControlStage que será armazenado.
     */
    public void addControlador(Class<? extends Inicializador> classController, ControlStage controlador) {
        this.controladores.put(classController.getName(), controlador);
    }

    /**
     * Retorna a classe do Index Informado.
     *
     * @param className Informe qual a nome da Classe de Controller de
     * identificação. Ex: TelaHomeController.class
     * Obs: pode retorna uma exceção caso não seja encontrado a classe armazenada no HashMap.
     * @return Uma classe ControlStage.
     */
    public ControlStage getControlador(Class<? extends Inicializador> className) throws Exception {
        ControlStage control = this.controladores.get(className.getName());
        if (control == null) {
            throw new Exception("Não foi encontrado nenhum ControlStage que tenha como chave de identificação a classe: " + className);
        }
        return control;
    }
}
