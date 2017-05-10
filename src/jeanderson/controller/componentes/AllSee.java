/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.componentes;

import java.util.ArrayList;
import java.util.List;
import jeanderson.controller.control.ControlStage;

/**
 * Classe que mantÃ©m salvo Classes ControlStage em modo estÃ¡tico.
 *
 * @author Jeanderson
 */
public enum AllSee {
    CONTROLADORES();
    /**
     * Lista que vai conter as classes.
     */
    private final List<ControlStage<?>> controladores;

    /**
     * Construtor PadrÃ£o.
     */
    AllSee() {
        this.controladores = new ArrayList<>();
    }

    /**
     * Adiciona uma Classe a Lista colocando no index informado.
     *
     * @param index posiÃ§Ã£o onde serÃ¡ armazenada a classe na lista.
     * @param controlador Classe que serÃ¡ armazenada.
     */
    public void addControlador(int index, ControlStage<?> controlador) {
        this.controladores.add(index, controlador);
    }

    /**
     * Retorna a classe do Index Informado.
     *
     * @param index Index de consulta.
     * @return Uma classe ControlStage.
     */
    public ControlStage<?> getControlador(int index) {
        return this.controladores.get(index);
    }
}
