/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeanderson.controller.util;

/**
 * Classe que tem constantes para identificações do tipos de máscaras.
 *
 * @author Jeanderson
 */
public enum MaskType {
    /**
     * Informa que é um campo para dígitos no formato telefone. Ex: (61)
     * 8899-8899 ou (61) 98899-8899.
     */
    TEL_DIG,
    /**
     * Informa que é um campo para dígitos no formato de CPF. Ex: 000.111.222-33
     */
    CPF_DIG,
    /**
     * Informa que é um campo para dígitos no formato RG. Ex: 1.111.222-3
     */
    RG_DIG,
    /**
     * Informa que é um campo para dígitos no formato Data separada por barras.
     * Ex: 08/05/2017
     */
    DATA_BARRA_DIG,
    /**
     * Informa que é um campo para dígitos no formato Data separada por traco.
     * Ex: 08-05-2017
     */
    DATA_TRACO_DIG;
}
